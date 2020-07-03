
package com.javatest.kafkaconsumer;

import java.util.Arrays;

import java.util.Properties;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaConsumerMain {


    public static void main(String[] args) {
        String groupId = "gid_page_visits3";
        String topic = "page_visits3";


        Properties props = new Properties();

        String brokerList = "localhost:9092,localhost:9092,localhost:9092";
        props.setProperty("bootstrap.servers", brokerList);
        props.setProperty("group.id", groupId);
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("session.timeout.ms", "30000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");


        KafkaConsumer<Integer, byte[]> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(topic));

        // 等待读取kafka队列中的所有消息, 生产环境中不会这样
        try {
            while (true) {
                ConsumerRecords<Integer, byte[]> records = consumer.poll(100);
                System.out.println("records count: " + records.count());
                for (ConsumerRecord<Integer, byte[]> record : records) {
                    System.out.printf("offset: %d, key: %d", record.offset(), record.key());
                }

            }
        } finally {
            consumer.close();
        }
    }

}

