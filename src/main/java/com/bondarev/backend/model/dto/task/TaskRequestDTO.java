package com.bondarev.backend.model.dto.task;

import com.bondarev.backend.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO {
    @NotBlank(message = "The title cannot be blank")
    @Size(max = 100, message = "The title cannot exceed 100 characters.")
    private String title;
    @NotBlank(message = "The body of task cannot be blank")
    @Size(max = 1000, message = "The body of task cannot exceed 1000 characters")
    private String text;
    private TaskStatus status;
}
