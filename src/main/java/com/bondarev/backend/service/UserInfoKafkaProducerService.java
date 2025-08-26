package com.bondarev.backend.service;

import com.bondarev.backend.model.dto.kafka.MailMessageEvent;
import com.bondarev.backend.model.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.bondarev.backend.model.Constants.EMAIL_SENDING_TASKS;

@Service
@AllArgsConstructor
public class UserInfoKafkaProducerService {
    private final KafkaTemplate<String, MailMessageEvent> kafkaTemplate;

    public void sendUserInfoToKafka(UserDTO userDTO) {
        MailMessageEvent message = createMessage(userDTO);
        kafkaTemplate.send(EMAIL_SENDING_TASKS, message);
    }

    private MailMessageEvent createMessage(UserDTO userDTO) {
        return MailMessageEvent
                .builder()
                .mail(userDTO.getEmail())
                .title("Task tracker app")
                .message(""" 
                        Hi %s,
                        Welcome to task tracker application!🎉 We’re excited to have you on board."""
                        .formatted(userDTO.getUsername()))
                .build();
    }

}
