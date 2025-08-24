package com.bondarev.backend.model.dto.task;

import com.bondarev.backend.model.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponseDTO {
    private Integer id;

    private String title;

    private String text;

    private TaskStatus status;
}
