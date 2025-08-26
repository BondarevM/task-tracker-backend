package com.bondarev.backend.model.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailMessageEvent {
    private String mail;
    private String title;
    private String message;
}
