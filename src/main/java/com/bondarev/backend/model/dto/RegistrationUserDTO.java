package com.bondarev.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationUserDTO {
    private String username;
    private String password;
    private String confirmedPassword;
    private String email;
}
