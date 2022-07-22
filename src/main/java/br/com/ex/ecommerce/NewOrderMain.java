package br.com.ex.ecommerce;


import java.util.UUID;

public class NewOrderMain {

    public static void main(String[] args) {

        try (KafkaDispatcher kd = new KafkaDispatcher()) {
            String key = UUID.randomUUID().toString();
            String value = "13, USUARIO, Maquina de Lavar Roupa";
            String email = "13, emaildeTeste@email.com";

            kd.send("ECOMMERCE_NEW_ORDER", key, value);
            kd.send("ECOMMERCE_SEND_EMAIL", key, email);
        }

    }



}
