package com.bondarev.backend.service;

import com.bondarev.backend.exception.common.EntityNotFoundException;
import com.bondarev.backend.mapper.UserMapper;
import com.bondarev.backend.model.dto.JwtRequestDTO;
import com.bondarev.backend.model.dto.JwtResponseDTO;
import com.bondarev.backend.model.dto.user.RegistrationUserRequestDTO;
import com.bondarev.backend.model.dto.user.UserDTO;
import com.bondarev.backend.model.entity.User;
import com.bondarev.backend.repository.UserRepository;
import com.bondarev.backend.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(User.class, username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

    public UserDTO getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(User.class, username));

        return userMapper.entityToDto(user);
    }

    public JwtResponseDTO authenticate(JwtRequestDTO authRequest) {
        String username = authRequest.getUsername();
        User user = findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(User.class, username));

        String token = jwtTokenUtil.generateToken(user);
        return JwtResponseDTO.builder()
                .token(token)
                .build();
    }


    public UserDTO registration(RegistrationUserRequestDTO request) {
        User user = userMapper.dtoToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.entityToDto(savedUser);
    }

    private Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
