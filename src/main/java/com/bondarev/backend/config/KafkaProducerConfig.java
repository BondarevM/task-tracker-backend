package com.bondarev.backend.config;

import com.bondarev.backend.model.dto.kafka.MailMessageEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServer;

    @Bean
    public ProducerFactory<String, MailMessageEvent> producerFactory(ObjectMapper objectMapper) {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServer);

        JsonSerializer<String> keySerializer = new JsonSerializer<>();
        JsonSerializer<MailMessageEvent> valueSerializer = new JsonSerializer<>(objectMapper);
        valueSerializer.setAddTypeInfo(false);


        return new DefaultKafkaProducerFactory<>(configProperties,
                keySerializer,
                valueSerializer);
    }

    @Bean
    public KafkaTemplate<String, MailMessageEvent> kafkaTemplate(ProducerFactory<String, MailMessageEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
