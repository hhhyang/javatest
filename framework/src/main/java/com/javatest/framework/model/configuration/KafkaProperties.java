package com.javatest.framework.model.configuration;

import lombok.Data;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.core.io.Resource;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration properties for Spring for Apache Kafka.
 * <p>
 * Users should refer to Kafka documentation for complete descriptions of these
 * properties.
 *
 * @author Gary Russell
 * @author Stephane Nicoll
 * @author Artem Bilan
 * @since 1.5.0
 */
@Data
public class KafkaProperties {

    /**
     * topics
     */
    private List<String> topics = new ArrayList<>();

    /**
     * Comma-delimited list of host:port pairs to use for establishing the initial
     * connection to the Kafka cluster.
     */
    private List<List<String>> bootstrapServersList;

    /**
     * Id to pass to the server when making requests; used for server-side logging.
     */
    private String clientId;

    /**
     * Additional properties used to configure the client.
     */
    private Map<String, String> properties = new HashMap<String, String>();

    private final Consumer consumer = new Consumer();

    private final Producer producer = new Producer();

    private final Listener listener = new Listener();

    private final Ssl ssl = new Ssl();

    private final Template template = new Template();

    private Map<String, Object> buildCommonProperties(final List<String> bootstrapServers) {
        Map<String, Object> properties = new HashMap<String, Object>();
        if (bootstrapServers != null) {
            properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG,
                    bootstrapServers);
        }
        if (this.clientId != null) {
            properties.put(CommonClientConfigs.CLIENT_ID_CONFIG, this.clientId);
        }
        if (this.ssl.getKeyPassword() != null) {
            properties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, this.ssl.getKeyPassword());
        }
        if (this.ssl.getKeystoreLocation() != null) {
            properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,
                    resourceToPath(this.ssl.getKeystoreLocation()));
        }
        if (this.ssl.getKeystorePassword() != null) {
            properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,
                    this.ssl.getKeystorePassword());
        }
        if (this.ssl.getTruststoreLocation() != null) {
            properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
                    resourceToPath(this.ssl.getTruststoreLocation()));
        }
        if (this.ssl.getTruststorePassword() != null) {
            properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
                    this.ssl.getTruststorePassword());
        }
        if (!CollectionUtils.isEmpty(this.properties)) {
            properties.putAll(this.properties);
        }
        return properties;
    }

    /**
     * Create an initial map of consumer properties from the state of this instance.
     * <p>
     * This allows you to add additional properties, if necessary, and override the
     * default kafkaConsumerFactory bean.
     * @return the consumer properties initialized with the customizations defined on this
     * instance
     */
    public Map<String, Object> buildConsumerProperties(final List<String> bootstrapServers) {
        Map<String, Object> properties = buildCommonProperties(bootstrapServers);
        properties.putAll(this.consumer.buildProperties());
        return properties;
    }

    /**
     * Create an initial map of producer properties from the state of this instance.
     * <p>
     * This allows you to add additional properties, if necessary, and override the
     * default kafkaProducerFactory bean.
     * @return the producer properties initialized with the customizations defined on this
     * instance
     */
    public Map<String, Object> buildProducerProperties(final List<String> bootstrapServers) {
        Map<String, Object> properties = buildCommonProperties(bootstrapServers);
        properties.putAll(this.producer.buildProperties());
        return properties;
    }

    private static String resourceToPath(Resource resource) {
        try {
            return resource.getFile().getAbsolutePath();
        }
        catch (IOException ex) {
            throw new IllegalStateException(
                    "Resource '" + resource + "' must be on a file system", ex);
        }
    }

    @Data
    public static class Consumer {

        private final Ssl ssl = new Ssl();

        /**
         * Frequency in milliseconds that the consumer offsets are auto-committed to Kafka
         * if 'enable.auto.commit' true.
         */
        private Integer autoCommitInterval;

        /**
         * What to do when there is no initial offset in Kafka or if the current offset
         * does not exist any more on the server.
         */
        private String autoOffsetReset;

        /**
         * Comma-delimited list of host:port pairs to use for establishing the initial
         * connection to the Kafka cluster.
         */
        private List<String> bootstrapServers;

        /**
         * Id to pass to the server when making requests; used for server-side logging.
         */
        private String clientId;

        /**
         * If true the consumer's offset will be periodically committed in the background.
         */
        private Boolean enableAutoCommit;

        /**
         * Maximum amount of time in milliseconds the server will block before answering
         * the fetch request if there isn't sufficient data to immediately satisfy the
         * requirement given by "fetch.min.bytes".
         */
        private Integer fetchMaxWait;

        /**
         * Minimum amount of data the server should return for a fetch request in bytes.
         */
        private Integer fetchMinSize;

        /**
         * Unique string that identifies the consumer group this consumer belongs to.
         */
        private String groupId;

        /**
         * Expected time in milliseconds between heartbeats to the consumer coordinator.
         */
        private Integer heartbeatInterval;

        /**
         * Deserializer class for keys.
         */
        private Class<?> keyDeserializer = StringDeserializer.class;

        /**
         * Deserializer class for values.
         */
        private Class<?> valueDeserializer = StringDeserializer.class;

        /**
         * Maximum number of records returned in a single call to poll().
         */
        private Integer maxPollRecords;

        /**
         * The maximum delay between invocations of poll()
         */
        private Integer maxPollIntervalMs;

        public Map<String, Object> buildProperties() {
            Map<String, Object> properties = new HashMap<String, Object>();
            if (this.autoCommitInterval != null) {
                properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,
                        this.autoCommitInterval);
            }
            if (this.autoOffsetReset != null) {
                properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                        this.autoOffsetReset);
            }
            if (this.bootstrapServers != null) {
                properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                        this.bootstrapServers);
            }
            if (this.clientId != null) {
                properties.put(ConsumerConfig.CLIENT_ID_CONFIG, this.clientId);
            }
            if (this.enableAutoCommit != null) {
                properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
                        this.enableAutoCommit);
            }
            if (this.fetchMaxWait != null) {
                properties.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG,
                        this.fetchMaxWait);
            }
            if (this.fetchMinSize != null) {
                properties.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, this.fetchMinSize);
            }
            if (this.groupId != null) {
                properties.put(ConsumerConfig.GROUP_ID_CONFIG, this.groupId);
            }
            if (this.heartbeatInterval != null) {
                properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,
                        this.heartbeatInterval);
            }
            if (this.keyDeserializer != null) {
                properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                        this.keyDeserializer);
            }
            if (this.ssl.getKeyPassword() != null) {
                properties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG,
                        this.ssl.getKeyPassword());
            }
            if (this.ssl.getKeystoreLocation() != null) {
                properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,
                        resourceToPath(this.ssl.getKeystoreLocation()));
            }
            if (this.ssl.getKeystorePassword() != null) {
                properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,
                        this.ssl.getKeystorePassword());
            }
            if (this.ssl.getTruststoreLocation() != null) {
                properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
                        resourceToPath(this.ssl.getTruststoreLocation()));
            }
            if (this.ssl.getTruststorePassword() != null) {
                properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
                        this.ssl.getTruststorePassword());
            }
            if (this.valueDeserializer != null) {
                properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                        this.valueDeserializer);
            }
            if (this.maxPollRecords != null) {
                properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,
                        this.maxPollRecords);
            }
            if (this.maxPollIntervalMs != null) {
                properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,
                        this.maxPollIntervalMs);
            }
            return properties;
        }

    }

    @Data
    public static class Producer {

        private final Ssl ssl = new Ssl();

        /**
         * Number of acknowledgments the producer requires the leader to have received
         * before considering a request complete.
         */
        private String acks;

        /**
         * Number of records to batch before sending.
         */
        private Integer batchSize;

        /**
         * Comma-delimited list of host:port pairs to use for establishing the initial
         * connection to the Kafka cluster.
         */
        private List<String> bootstrapServers;

        /**
         * Total bytes of memory the producer can use to buffer records waiting to be sent
         * to the server.
         */
        private Long bufferMemory;

        /**
         * Id to pass to the server when making requests; used for server-side logging.
         */
        private String clientId;

        /**
         * Compression type for all data generated by the producer.
         */
        private String compressionType;

        /**
         * Serializer class for keys.
         */
        private Class<?> keySerializer = StringSerializer.class;

        /**
         * Serializer class for values.
         */
        private Class<?> valueSerializer = StringSerializer.class;

        /**
         * When greater than zero, enables retrying of failed sends.
         */
        private Integer retries;

        public Map<String, Object> buildProperties() {
            Map<String, Object> properties = new HashMap<String, Object>();
            if (this.acks != null) {
                properties.put(ProducerConfig.ACKS_CONFIG, this.acks);
            }
            if (this.batchSize != null) {
                properties.put(ProducerConfig.BATCH_SIZE_CONFIG, this.batchSize);
            }
            if (this.bootstrapServers != null) {
                properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                        this.bootstrapServers);
            }
            if (this.bufferMemory != null) {
                properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, this.bufferMemory);
            }
            if (this.clientId != null) {
                properties.put(ProducerConfig.CLIENT_ID_CONFIG, this.clientId);
            }
            if (this.compressionType != null) {
                properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,
                        this.compressionType);
            }
            if (this.keySerializer != null) {
                properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                        this.keySerializer);
            }
            if (this.retries != null) {
                properties.put(ProducerConfig.RETRIES_CONFIG, this.retries);
            }
            if (this.ssl.getKeyPassword() != null) {
                properties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG,
                        this.ssl.getKeyPassword());
            }
            if (this.ssl.getKeystoreLocation() != null) {
                properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,
                        resourceToPath(this.ssl.getKeystoreLocation()));
            }
            if (this.ssl.getKeystorePassword() != null) {
                properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,
                        this.ssl.getKeystorePassword());
            }
            if (this.ssl.getTruststoreLocation() != null) {
                properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
                        resourceToPath(this.ssl.getTruststoreLocation()));
            }
            if (this.ssl.getTruststorePassword() != null) {
                properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
                        this.ssl.getTruststorePassword());
            }
            if (this.valueSerializer != null) {
                properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                        this.valueSerializer);
            }
            return properties;
        }

    }

    @Data
    public static class Template {

        /**
         * Default topic to which messages will be sent.
         */
        private String defaultTopic;

    }

    @Data
    public static class Listener {

        /**
         * Listener AckMode; see the spring-kafka documentation.
         */
        private ContainerProperties.AckMode ackMode;

        /**
         * Number of threads to run in the listener containers.
         */
        private Integer concurrency;

        /**
         * Timeout in milliseconds to use when polling the consumer.
         */
        private Long pollTimeout;

        /**
         * Number of records between offset commits when ackMode is "COUNT" or
         * "COUNT_TIME".
         */
        private Integer ackCount;

        /**
         * Time in milliseconds between offset commits when ackMode is "TIME" or
         * "COUNT_TIME".
         */
        private Long ackTime;

    }

    @Data
    public static class Ssl {

        /**
         * Password of the private key in the key store file.
         */
        private String keyPassword;

        /**
         * Location of the key store file.
         */
        private Resource keystoreLocation;

        /**
         * Store password for the key store file.
         */
        private String keystorePassword;

        /**
         * Location of the trust store file.
         */
        private Resource truststoreLocation;

        /**
         * Store password for the trust store file.
         */
        private String truststorePassword;

    }

}

