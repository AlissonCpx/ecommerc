package br.com.ex.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerFunction {
    void consumerFunction(ConsumerRecord<String, String> record);
}
