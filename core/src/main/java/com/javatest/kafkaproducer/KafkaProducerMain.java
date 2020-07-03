package com.javatest.kafkaproducer;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.PartitionInfo;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class KafkaProducerMain {

    public static void main(String[] args) {

        Properties props = new Properties();

        String brokerList = "localhost:9092,localhost:9092,localhost:9092";
        props.setProperty("bootstrap.servers", brokerList);
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.setProperty("partitioner.class", "com.javatest.kafkaproducer.PartitionerDemo");
        props.setProperty("acks", "1");

        Producer<Integer, byte[]> producer = new KafkaProducer<>(props);

        String topic = "page_visits3";
        List<PartitionInfo> list = producer.partitionsFor(topic);
        for (PartitionInfo info : list) {
            int partitionId = info.partition();
            System.out.println("partitionId: " + partitionId);

            Node node = info.leader();
            System.out.println("leader: " + node.host() + " " + node.id());

            Node[] nodes = info.inSyncReplicas();
            for (Node n : nodes) {
                System.out.println("isr: " + n.host() + " " + n.id());
            }

            Node[] nodes1 = info.replicas();
            for (Node n : nodes1) {
                System.out.println("replicas: " + n.host() + " " + n.id());
            }

            System.out.println("===============");

        }

        KafkaTask kafkaTask = new KafkaTask(producer, topic, 30_000, 1_000);

        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        es.scheduleAtFixedRate(kafkaTask, 0L, 1L, TimeUnit.SECONDS);


        while (!es.isTerminated()) {

        }

        producer.close();

    }


}
