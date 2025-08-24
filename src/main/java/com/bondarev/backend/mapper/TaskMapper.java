package com.bondarev.backend.mapper;

import com.bondarev.backend.model.dto.task.TaskResponseDTO;
import com.bondarev.backend.model.entity.Task;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDTO entityToDto(Task source);

}
