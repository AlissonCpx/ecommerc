package br.com.ex.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;


public class EmailService {

    private void parse(ConsumerRecord<String, String> t) {
        System.out.printf("Processando send email, cheching for fraud");
        System.out.println(t.key());
        System.out.println(t.value());
        System.out.printf(String.valueOf(t.partition()));
        System.out.println(t.offset());
        System.out.println("Email Sent");
    }

    public static void main(String[] args) {

        EmailService emailService = new EmailService();

        try (KafkaService ks = new KafkaService(EmailService.class.getSimpleName(), "ECOMMERCE_SEND_EMAIL", emailService::parse)) {
            ks.run();
        }

    }


}
