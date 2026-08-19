package org.chris.study;

import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * Kafka simple consumer.
 *
 * @author Chris
 */
public class KafkaSimpleConsumer {

    private static final int NO_AUTH = 1;
    private static final int SASL_PLAINTEXT = 2;

    private static String brokerList;
    private static String topic;
    private static String username;
    private static String password;
    private static int auth = NO_AUTH;

    private static void loadLocal() {
        brokerList = "localhost:9092";
        topic = "test-topic";
        username = "chris";
        password = "123456";
        auth = NO_AUTH;
    }

    private static void loadLocalSasl() {
        brokerList = "localhost:9092";
        topic = "test";
        username = "bob";
        password = "bob";
        auth = SASL_PLAINTEXT;
    }

    public static void main(String[] args) {
        loadLocalSasl();
        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        if (auth == SASL_PLAINTEXT) {
            consumerProperties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
            consumerProperties.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
            consumerProperties.put(SaslConfigs.SASL_JAAS_CONFIG,
                    "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" + username + "\" password=\"" + password + "\";");
        }

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties)) {
            consumer.subscribe(Collections.singletonList(topic));
            consume(consumer);
        }
    }

    private static void consume(KafkaConsumer<String, String> consumer) {
        //循环消费消息。
        while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                //必须在下次Poll之前消费完这些数据, 且总耗时不得超过SESSION_TIMEOUT_MS_CONFIG。
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Consume partition:%d offset:%d - %s%n", record.partition(), record.offset(), record.value());
                }
            } catch (Exception e) {
                System.out.println("consumer error!");
            }
        }
    }
}
