package com.bondarev.backend.service;

import com.bondarev.backend.exception.EntityNotFoundException;
import com.bondarev.backend.mapper.UserMapper;
import com.bondarev.backend.model.dto.RegistrationUserDTO;
import com.bondarev.backend.model.entity.User;
import com.bondarev.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
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
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(User.class, username));


        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

    public void createNewUser(RegistrationUserDTO registrationUserDTO){
        User user = userMapper.dtoToUser(registrationUserDTO);
        user.setPassword(passwordEncoder.encode(registrationUserDTO.getPassword()));
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
