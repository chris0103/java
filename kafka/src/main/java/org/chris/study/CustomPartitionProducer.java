package org.chris.study;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class CustomPartitionProducer {

    private static KafkaProducer<String, String> producer;

    public static void main(String[] args) {
        int argsCount = args.length;
        if (argsCount == 0 || argsCount == 1) {
            throw new IllegalArgumentException("Please provide topic name and Message count as arguments");
        }
        // Topic name and the message count to be published is passed from the command line
        String topic = args[0];
        String count = args[1];
        int messageCount = Integer.parseInt(count);
        System.out.println("Topic Name - " + topic);
        System.out.println("Message Count - " + messageCount);
        CustomPartitionProducer simpleProducer = new CustomPartitionProducer();
        simpleProducer.publishMessage(topic, messageCount);
    }

    public CustomPartitionProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "kafka.examples.ch4.SimplePartitioner");
        props.put("request.required.acks", "1");
        producer = new KafkaProducer<String, String>(props);
    }

    private void publishMessage(String topic, int messageCount) {
        Random random = new Random();
        for (int mCount = 0; mCount < messageCount; mCount++) {

            String clientIP = "192.168.14." + random.nextInt(255);
            String accessTime = new Date().toString();
            String message = accessTime + ",kafka.apache.org," + clientIP;
            System.out.println(message);

            // Creates a KeyedMessage instance
            ProducerRecord<String, String> data = new ProducerRecord<String, String>(topic, clientIP, message);

            // Publish the message
            producer.send(data);
        }
        // Close producer connection with broker.
        producer.close();
    }
}
