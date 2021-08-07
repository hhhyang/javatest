package com.javatest.framework.model.consumer;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessageListener implements AcknowledgingMessageListener<String, byte[]> {

    private final MeterRegistry meterRegistry;

    @Autowired
    public KafkaMessageListener(MeterRegistry meterRegistry) {

        this.meterRegistry = meterRegistry;
    }

    @Override
    public void onMessage(final ConsumerRecord<String, byte[]> data, Acknowledgment acknowledgment) {

        try {

            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}

