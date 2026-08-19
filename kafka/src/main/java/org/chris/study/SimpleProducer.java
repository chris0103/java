package org.chris.study;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;

public class SimpleProducer {

    private static KafkaProducer<String, String> producer;

    public static void main(String[] args) {
        int argsCount = args.length;
        if (argsCount != 2) {
            throw new IllegalArgumentException("Please provide topic name and Message count as arguments");
        }
        // Topic name and the message count to be published is passed from the command line
        String topic = args[0];
        String count = args[1];
        int messageCount = Integer.parseInt(count);
        System.out.println("Topic Name - " + topic);
        System.out.println("Message Count - " + messageCount);
        SimpleProducer simpleProducer = new SimpleProducer();
        simpleProducer.publishMessage(topic, messageCount);
    }

    public SimpleProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("request.required.acks", "1");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"alice\" password=\"alice\";");
        producer = new KafkaProducer<String, String>(props);
    }

    private void publishMessage(String topic, int messageCount) {
        for (int mCount = 0; mCount < messageCount; mCount++) {
            String runtime = new Date().toString();
            String msg = "Message Publishing Time - " + runtime;
            System.out.println(msg);

            // Creates a KeyedMessage instance
            ProducerRecord<String, String> data = new ProducerRecord<String, String>(topic, msg);

            // Publish the message
            producer.send(data);
        }
        // Close producer connection with broker.
        producer.close();
    }
}
