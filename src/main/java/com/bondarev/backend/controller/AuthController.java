package com.bondarev.backend.controller;

import com.bondarev.backend.model.dto.JwtRequestDTO;
import com.bondarev.backend.model.dto.JwtResponseDTO;
import com.bondarev.backend.model.dto.RegistrationUserDTO;
import com.bondarev.backend.service.UserService;
import com.bondarev.backend.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/secured")
    public String securedData() {
        return "Secured data";
    }

    @GetMapping("/api/info")
    public String userData(Principal principal) {
        return principal.getName();
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDTO authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationUserDTO registrationUserDTO) {
        userService.createNewUser(registrationUserDTO);
        return ResponseEntity.ok("kek");
    }
}