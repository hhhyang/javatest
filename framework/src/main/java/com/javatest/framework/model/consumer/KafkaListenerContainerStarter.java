package com.javatest.framework.model.consumer;

import com.javatest.framework.commons.utils.Streams;
import com.javatest.framework.model.configuration.KafkaConfig;
import com.javatest.framework.model.configuration.KafkaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@Slf4j
public class KafkaListenerContainerStarter implements CommandLineRunner {

    private static final String BEAN_NAME_PREFIX = "SDN-TOPO-";

    private final KafkaMessageListener listener;
    private final KafkaConfig kafkaConfig;

    private final AtomicInteger beanIndex = new AtomicInteger(0);

    public KafkaListenerContainerStarter(KafkaConfig kafkaConfig, KafkaMessageListener listener) {
        this.kafkaConfig = kafkaConfig;
        this.listener = listener;
    }

    @Override
    public void run(String... strings) throws Exception {

        for (Map.Entry<String, KafkaProperties> entry : kafkaConfig.getKafka().entrySet()) {

            KafkaProperties kafkaProperties = entry.getValue();
            if (kafkaProperties == null) {
                continue;
            }
            List<String> topics = Streams.ofNullable(kafkaProperties.getTopics())
                    .filter(t -> !StringUtils.isEmpty(t))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(topics)
                    && !CollectionUtils.isEmpty(kafkaProperties.getBootstrapServersList())) {

                for (List<String> bootstrapServers : kafkaProperties.getBootstrapServersList()) {
                    if (!CollectionUtils.isEmpty(bootstrapServers)) {
                        ConcurrentMessageListenerContainer<String, String> container;
                        container = createContainer(bootstrapServers, kafkaProperties, topics);
                        log.info("start device kafka MessageListenerContainer for bootstrap servers: {}, topics: {}",
                                bootstrapServers, topics);
                        container.start();
                    }
                }

            }
        }
    }

    private <K, V> ConcurrentMessageListenerContainer<K, V> createContainer(final List<String> bootstrapServers,
                                                                            final KafkaProperties kafkaProps,
                                                                            final List<String> topics) {

        ConsumerFactory<K, V> consumerFactory = kafkaConsumerFactory(bootstrapServers, kafkaProps);

        ConcurrentMessageListenerContainer container;
        container = new ConcurrentMessageListenerContainer<>(consumerFactory, containerProperties(kafkaProps, topics));

        // 设置并发度
        KafkaProperties.Listener listenerProps = kafkaProps.getListener();
        if (listenerProps.getConcurrency() != null) {
            container.setConcurrency(listenerProps.getConcurrency());
        }

        container.setBeanName(BEAN_NAME_PREFIX + beanIndex.getAndIncrement());

        return container;
    }

    private <K, V> ConsumerFactory<K, V> kafkaConsumerFactory(final List<String> bootstrapServers,
                                                              final KafkaProperties kafkaProps) {
        return new DefaultKafkaConsumerFactory<>(kafkaProps.buildConsumerProperties(bootstrapServers));
    }

    private ContainerProperties containerProperties(final KafkaProperties kafkaProps, List<String> topics) {
        KafkaProperties.Listener listenerProps = kafkaProps.getListener();
        ContainerProperties containerProps = new ContainerProperties(topics.toArray(new String[0]));
        containerProps.setMessageListener(listener);
        if (listenerProps.getAckMode() != null) {
            containerProps.setAckMode(listenerProps.getAckMode());
        }
        if (listenerProps.getAckCount() != null) {
            containerProps.setAckCount(listenerProps.getAckCount());
        }
        if (listenerProps.getAckTime() != null) {
            containerProps.setAckTime(listenerProps.getAckTime());
        }
        if (listenerProps.getPollTimeout() != null) {
            containerProps.setPollTimeout(listenerProps.getPollTimeout());
        }

        return containerProps;
    }



}

