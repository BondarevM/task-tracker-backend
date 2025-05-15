package com.bondarev.backend.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseExceptionDTO {
    private Integer code;
    private String message;
    private Throwable cause;
}
