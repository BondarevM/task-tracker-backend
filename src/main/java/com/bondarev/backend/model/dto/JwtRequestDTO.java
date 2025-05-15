package com.bondarev.backend.model.dto;

import lombok.Data;

@Data
public class JwtRequestDTO {
    private String username;
    private String password;
}
