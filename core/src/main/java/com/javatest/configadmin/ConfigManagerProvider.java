package com.javatest.configadmin;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javatest.utils.Strings;
import com.javatest.utils.Utils;

public class ConfigManagerProvider implements ConfigManagerService, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigManagerProvider.class);


    private final Map<String, Object> configTargetMap = new ConcurrentHashMap<>();

    private WatchService watchService;
    private static final String DEFAULT_CONFIG_PATH = "etc" + File.separator + "test";
    private static final Path PATH = Paths.get(DEFAULT_CONFIG_PATH);

    private Thread watcherThread;

    public ConfigManagerProvider() {

    }

    /**
     * Method called when the blueprint container is created.
     */
    public void init() {

        LOG.info("ConfigManagerProvider Session Initiated begin");

        try {

            if (!Files.exists(PATH)) {
                Files.createDirectories(PATH);
            }

            watchService = FileSystems.getDefault().newWatchService();

            WatchKey key = PATH.register(watchService, OVERFLOW, ENTRY_CREATE, ENTRY_MODIFY);
            if (isNull(key)) {
                LOG.error("registered key is null, path: {}", PATH.toString());
            }

            watcherThread = new Thread(() -> {

                try {

                    LOG.debug("watcherThread start");
                    while (!Thread.currentThread().isInterrupted()) {
                        WatchKey watchKey = watchService.take();
                        if (Objects.nonNull(watchKey)) {

                            List<WatchEvent<?>> events = watchKey.pollEvents();

                            LOG.debug("WatchKey start: {}", events.size());
                            for (WatchEvent event : events) {

                                String filename = event.context().toString();
                                LOG.debug("WatchEvent : {}, filename: {}", event.kind().name(), filename);
                                // ConfigManagerProvider.this.processEvent(filename);

                            }

                            events.stream()
                                    .map(event -> event.context().toString())
                                    .distinct()
                                    .forEach((filename -> ConfigManagerProvider.this.processEvent(filename)));

                            boolean reset = watchKey.reset();
                            if (!reset) {
                                LOG.error("reset watchkey failed");
                            }

                        }

                    }
                } catch (Exception e) {
                    LOG.warn(Utils.exceptionStackTrace(e));
                }

            });

            watcherThread.start();

        } catch (Exception e) {
            LOG.warn(Utils.exceptionStackTrace(e));
        }


        LOG.info("ConfigManagerProvider Session Initiated");
    }

    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() {


        watcherThread.interrupt();

        Utils.closeRresource(watchService);

        LOG.info("ConfigManagerProvider Closed");
    }

    @Override
    public void register(@Nonnull final String path, @Nonnull final Class<?> target) {

        Objects.requireNonNull(path, "file path must not be null");
        Objects.requireNonNull(target, "file path must not be null");

        register0(path, target);
    }

    @Override
    public void register(@Nonnull final String path, @Nonnull final Object target) {

        Objects.requireNonNull(path, "file path must not be null");
        Objects.requireNonNull(target, "file path must not be null");

        register0(path, target);

    }

    public void register0(final String path, final Object target) {

        LOG.debug("path={}, target={}", path, target);

        configTargetMap.put(path, target);

        processEvent(path);
/*
        return new AbstractRegistration() {
            @Override
            protected void removeRegistration() {
                synchronized(ConfigManagerProvider.this) {
                    ConfigManagerProvider.this.configTargetMap.remove(path);
                }

            }
        };
*/    }


    private void processEvent(final String filename) {

        configTargetMap.entrySet().stream().filter((entry) -> {

            boolean result = Pattern.matches(entry.getKey(), filename);

            LOG.debug("key: {}, filename: {}, result: {}", entry.getKey(), filename, result);

            return result;
        }).forEach(
                (entry) -> {
                    LOG.debug("filename: {}, object: {}", filename, entry.getValue());
                    parseConfigFile(entry.getValue(), filename);
                });

    }

    private void parseConfigFile(final Object target, final String filename) {


        LOG.debug("parseConfigFile filename: {}", filename);
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(DEFAULT_CONFIG_PATH + File.separator + filename));

            // 读取配置到properties
            loadConfig(target, properties);

        } catch (Exception e) {
            LOG.error(Utils.exceptionStackTrace(e));
        }

    }

    private void loadConfig(final Object target, final Properties properties) {

        LOG.debug("loadConfig properties: {}", properties);

        try {
            if (target instanceof Class) {
                loadConfigForStatic((Class<?>) target, properties);
            } else {
                loadConfigForInstance(target, properties);
            }
        } catch (Exception e) {
            LOG.warn(Utils.exceptionStackTrace(e));
        }

    }

    private void loadConfigForStatic(final Class<?> clazz,
                                     final Properties properties) throws IllegalAccessException, NoSuchFieldException {

        LOG.debug("loadConfigForStatic properties: {}", properties);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (nonNull(field.getAnnotation(Ignore.class))) {
                    continue;
                }

                ConfigName configName = field.getAnnotation(ConfigName.class);
                String name = nonNull(configName) ? configName.value() : field.getName();

                String val = properties.getProperty(name);
                field.setAccessible(true);

                int modifiers = field.getModifiers();
                if ((modifiers & Modifier.STATIC) != 0) {
                    // 是static变量
                    if ((modifiers & Modifier.FINAL) == 0) {

                        setPrimitiveField(field, null, val);
                        setPackedPrimitiveField(field, null, val);
                        setClassField(field, null, val);
                        LOG.debug("not final field,  {}: {}", field.getName(), field.get(null));

                    } else {

                        removeFinalModifier(field, modifiers);

                        setPackedPrimitiveField(field, null, val);
                        setClassField(field, null, val);
                        LOG.debug("{}: {}", field.getName(), field.get(null));
                    }
                }

                LOG.debug("{}: {}", field.getName(), field.get(null));
            } catch (Throwable t) {
                LOG.warn("process failed, fieldname={}", field.getName());
                LOG.warn(Utils.exceptionStackTrace(t));
            }
        }




    }


    private void loadConfigForInstance(final Object instance,
                                       final Properties properties) throws IllegalAccessException, NoSuchFieldException {

        LOG.debug("loadConfigForStatic properties: ");

        Class<?> clazz = instance.getClass();

        Field[] fields = clazz.getDeclaredFields();


        for (Field field : fields) {
            try {
                String val = properties.getProperty(field.getName());

                field.setAccessible(true);
                int modifiers = field.getModifiers();
                if ((modifiers & Modifier.FINAL) == 0) {
                    setPrimitiveField(field, instance, val);
                    setPackedPrimitiveField(field, instance, val);
                    setClassField(field, instance, val);
                } else {
                    removeFinalModifier(field, modifiers);

                    setPackedPrimitiveField(field, instance, val);
                    setClassField(field, instance, val);
                }

                LOG.debug("{}: {}", field.getName(), field.get(instance));
            } catch (Throwable t) {
                LOG.warn("process failed, fieldname={}", field.getName());
                LOG.warn(Utils.exceptionStackTrace(t));
            }

        }


    }

    private void removeFinalModifier(final Field field,
                                     final int modifiers) throws IllegalAccessException, NoSuchFieldException {

        Field modifierField = Field.class.getDeclaredField("modifiers");
        modifierField.setAccessible(true);
        modifierField.setInt(field, (modifiers & ~Modifier.FINAL));
    }


    private Field setPrimitiveField(final Field field,
                                    final Object obj,
                                    final String val) throws IllegalAccessException {

        if (field.getType() == boolean.class) {
            field.setBoolean(obj, Strings.nonNullAndEmpty(val) && Boolean.parseBoolean(val));
        } else if (field.getType() == char.class) {
            field.setChar(obj, Strings.nonNullAndEmpty(val) ? val.charAt(0) : '\0');
        } else if (field.getType() == byte.class) {
            field.setByte(obj, Strings.nonNullAndEmpty(val) ? Byte.parseByte(val) : (byte) 0);
        } else if (field.getType() == short.class) {
            field.setShort(obj, Strings.nonNullAndEmpty(val) ? Short.parseShort(val) : (short) 0);
        } else if (field.getType() == int.class) {
            field.setInt(obj, Strings.nonNullAndEmpty(val) ? Integer.parseInt(val) : 0);
        } else if (field.getType() == long.class) {
            field.setLong(obj, Strings.nonNullAndEmpty(val) ? Long.parseLong(val) : 0L);
        } else if (field.getType() == float.class) {
            field.setFloat(obj, Strings.nonNullAndEmpty(val) ? Float.parseFloat(val) : 0F);
        } else if (field.getType() == double.class) {
            field.setDouble(obj, Strings.nonNullAndEmpty(val) ? Double.parseDouble(val) : 0D);
        }


        return field;

    }


    private Field setPackedPrimitiveField(final Field field,
                                          final Object obj,
                                          final String val) throws IllegalAccessException {

        if (field.getType() == Boolean.class) {
            field.set(obj, Strings.nonNullAndEmpty(val) ? Boolean.valueOf(val) : null);
        } else if (field.getType() == Character.class) {
            field.set(obj, Strings.nonNullAndEmpty(val) ? val.charAt(0) : null);
        } else if (field.getType() == Byte.class) {
            field.set(obj, Strings.nonNullAndEmpty(val) ? Byte.valueOf(val) : null);
        } else if (field.getType() == Short.class) {
            field.set(obj, Strings.nonNullAndEmpty(val) ? Short.valueOf(val) : null);
        } else if (field.getType() == Integer.class) {
            field.set(obj, Strings.nonNullAndEmpty(val) ? Integer.valueOf(val) : null);
        } else if (field.getType() == Long.class) {
            field.set(obj, Strings.nonNullAndEmpty(val) ? Long.valueOf(val) : null);
        } else if (field.getType() == Float.class) {
            field.set(obj, Strings.nonNullAndEmpty(val) ? Float.valueOf(val) : null);
        } else if (field.getType() == Double.class) {
            field.set(obj, Strings.nonNullAndEmpty(val) ? Double.valueOf(val) : null);
        }

        return field;

    }

    private Field setClassField(final Field field,
                                final Object obj,
                                final String val) throws IllegalAccessException {

        if (field.getType() == String.class) {
            field.set(obj, val);
        }

        if (field.getType().isEnum()) {
            try {
                if (Strings.nonNullAndEmpty(val)) {
                    field.set(obj, Enum.valueOf( (Class<Enum>) field.getType(), val));
                } else {
                    field.set(obj, null);
                }
            } catch (Throwable e) {
                field.set(obj, null);
            }

        }

        return field;

    }



}