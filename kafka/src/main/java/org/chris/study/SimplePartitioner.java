package org.chris.study;

import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;

public class SimplePartitioner extends DefaultPartitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int partition = 0;
        String partitionKey = (String) key;
        int offset = partitionKey.lastIndexOf('.');
        if (offset > 0) {
            partition = Integer.parseInt(partitionKey.substring(offset + 1)) % cluster.partitionCountForTopic(topic);
        }
        return partition;
    }
}
