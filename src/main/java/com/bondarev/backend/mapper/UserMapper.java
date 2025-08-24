package com.bondarev.backend.mapper;

import com.bondarev.backend.model.dto.user.RegistrationUserRequestDTO;
import com.bondarev.backend.model.dto.user.UserDTO;
import com.bondarev.backend.model.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToUser(RegistrationUserRequestDTO source);

    UserDTO entityToDto(User source);
}
