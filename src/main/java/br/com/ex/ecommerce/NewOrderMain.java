package br.com.ex.ecommerce;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) {

        KafkaProducer producer = new KafkaProducer<String, String>(properties());
        String value = "1, ALISSON, Maquina de Lavar Roupa";
        ProducerRecord p = new ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", value, value);
        try {
            producer.send(p, (data, ex) -> {
             if (ex != null) {
                 ex.printStackTrace();
                 return;
             }
             System.out.println(data.topic() + " - " + data.offset() + " - " + data.timestamp());
            }).get();
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
