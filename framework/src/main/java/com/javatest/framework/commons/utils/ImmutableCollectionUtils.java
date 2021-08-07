package com.javatest.framework.commons.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.NonNull;



public class ImmutableCollectionUtils {


    public static <K, V> ImmutableMap<K, V> addOrUpdateEntry(@NonNull ImmutableMap<K, V> old,
                                                     @NonNull K key,
                                                     @NonNull V value) {

        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        // 把旧的其他的bmpclient copy过来
        old.forEach((k, v) -> {
            if (!k.equals(key)) {
                builder.put(k, v);
            }
        });

        // 增加新的
        builder.put(key, value);

        // 返回一个新的
        return builder.build();

    }

    public static <K, V> ImmutableMap<K, V> remEntry(@NonNull ImmutableMap<K, V> old,
                                                     @NonNull K key) {

        if (old.get(key) == null) {
            // 没有改变
            return old;
        }

        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        // 把旧的其他的bmpclient copy过来
        old.forEach((k, v) -> {
            if (!k.equals(key)) {
                builder.put(k, v);
            }
        });

        // 返回一个新的
        return builder.build();
    }


    public static <E> ImmutableList<E> addOrUpdateElement(@NonNull ImmutableList<E> old, @NonNull E element) {

        ImmutableList.Builder<E> builder = ImmutableList.builder();
        // 把旧的其他的bmpclient copy过来
        old.forEach(e -> {
            if (!e.equals(element)) {
                builder.add(e);
            }
        });

        // 增加新的
        builder.add(element);

        // 返回一个新的
        return builder.build();

    }

    public static <E> ImmutableList<E> remElement(@NonNull ImmutableList<E> old, @NonNull E element) {

        ImmutableList.Builder<E> builder = ImmutableList.builder();
        // 把旧的其他的bmpclient copy过来
        old.forEach(e -> {
            if (!e.equals(element)) {
                builder.add(e);
            }
        });

        // 返回一个新的
        return builder.build();
    }

}
