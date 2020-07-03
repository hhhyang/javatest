package com.javatest.kafkaproducer;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class PartitionerDemo implements Partitioner {

    public void configure(Map<String, ?> map) {

    }

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        int numPartitions = cluster.partitionsForTopic(topic).size();
        int partition = 0;
        if (key instanceof String) {
            String keyStr = (String) key;
            int offset = keyStr.lastIndexOf('.');
            if (offset > 0) {
                partition = Integer.parseInt(keyStr.substring(offset + 1)) % numPartitions;
            } else {
                partition = key.toString().length() % numPartitions;
            }
        } else if (key instanceof Integer) {
            partition = ((Integer) key) % numPartitions;
        } else {
            partition = key.toString().length() % numPartitions;
        }

        return partition;
    }

    public void close() {

    }
}
