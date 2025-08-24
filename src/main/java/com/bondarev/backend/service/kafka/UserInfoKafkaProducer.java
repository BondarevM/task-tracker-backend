package com.bondarev.backend.service.kafka;

import com.bondarev.backend.model.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserInfoKafkaProducer {
    private final KafkaTemplate<String, UserDTO> kafkaTemplate;
    private final String TOPIC = "user-registered";

    public void sendUserInfoToKafka(UserDTO userDTO) {
        kafkaTemplate.send(TOPIC, userDTO);
    }
}
