package com.bondarev.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequestDTO {
    @NotBlank(message = "The body of task cannot be blank")
    private String username;
    @NotBlank(message = "The body of task cannot be blank")
    private String password;
}
