package com.bondarev.backend.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class TaskTrackerBackendExceptionDTO {
    private Integer code;
    private String message;
    private Throwable cause;
    private Map<String, String> props;
}
