package com.javatest.kafkaproducer;


import java.util.Random;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaTask implements Runnable {
    Random rnd = new Random();

    Producer<Integer, byte[]> producer;

    int msgCnt = 100;
    int msgLen = 1_000;

    Integer id = 0;
    String topic = "";

    public KafkaTask(final Producer<Integer, byte[]> producer, final String topic, final int msgCnt, final int msgLen) {
        this.producer = producer;
        this.msgCnt = msgCnt;
        this.topic = topic;
        this.msgLen = msgLen;
    }

    public void run() {
        long start = System.currentTimeMillis();

        byte[] msg = new byte[msgLen];

        for (int i = 0; i < msgCnt; i++, id++) {

            rnd.nextBytes(msg);

            ProducerRecord<Integer, byte[]> record = new ProducerRecord<>(topic, id, msg);

            Future<RecordMetadata> future = producer.send(record);
            /*
            try {
                RecordMetadata metadata = future.get();
                System.out.println(String.format("partition: %d, offset: %d", metadata.partition(), metadata.offset()));
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
            */
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时: " + (end - start) + " ms");
    }
}
