package com.bondarev.backend.controller;

import com.bondarev.backend.model.dto.JwtRequestDTO;
import com.bondarev.backend.model.dto.JwtResponseDTO;
import com.bondarev.backend.model.dto.user.RegistrationUserRequestDTO;
import com.bondarev.backend.model.dto.user.UserDTO;
import com.bondarev.backend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/user")
    public UserDTO userData() {
        return authService.getUserInfo();
    }

    @PostMapping("/user")
    public UserDTO registration(@RequestBody RegistrationUserRequestDTO registrationUserRequestDTO) {
        return authService.registration(registrationUserRequestDTO);
    }

    @PostMapping("/login")
    public JwtResponseDTO createAuthToken(@RequestBody JwtRequestDTO authRequest) {
        return authService.authenticate(authRequest);

    }
}