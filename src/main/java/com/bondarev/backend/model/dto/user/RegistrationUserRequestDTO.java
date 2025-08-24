package com.bondarev.backend.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationUserRequestDTO {
    private String username;
    private String password;
    private String confirmedPassword;
    private String email;
}
