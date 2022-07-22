package br.com.ex.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class FraudDetectorService {
    public static void main(String[] args) {

        FraudDetectorService fs = new FraudDetectorService();
        try (KafkaService ks = new KafkaService(FraudDetectorService.class.getSimpleName(), "ECOMMERCE_NEW_ORDER", fs::parse)) {
            ks.run();
        }
    }

    private void parse(ConsumerRecord<String, String> t) {
        System.out.printf("Processando new order, cheching for fraud");
        System.out.println(t.key());
        System.out.println(t.value());
        System.out.printf(String.valueOf(t.partition()));
        System.out.println(t.offset());
        System.out.println("Order Processed");
    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectorService.class.getSimpleName());
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");


        return properties;
    }
}
