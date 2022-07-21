package br.com.ex.ecommerce;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) {

        KafkaProducer producer = new KafkaProducer<String, String>(properties());
        String value = "13, USUARIO, Maquina de Lavar Roupa";
        String key = UUID.randomUUID().toString();
        ProducerRecord p = new ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", key, value);
        try {
            Callback callback = (data, ex) -> {
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }
                System.out.println(data.topic() + " - " + data.offset() + " - " + data.timestamp());
            };
            producer.send(p, callback).get();

            String email = "Welcome! we are processing your order!";
            ProducerRecord emailRecord = new ProducerRecord<String, String>("ECOMMERCE_SEND_EMAIL", key, email);
            producer.send(emailRecord, callback).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }

}
