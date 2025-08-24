package com.bondarev.backend.controller;

import com.bondarev.backend.model.dto.task.TaskRequestDTO;
import com.bondarev.backend.model.dto.task.TaskResponseDTO;
import com.bondarev.backend.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public List<TaskResponseDTO> getUserTasks() {
        return taskService.getUserTasks();
    }

    @PutMapping("/task")
    public TaskResponseDTO updateTask(@RequestBody @Valid TaskRequestDTO request) {
        return taskService.updateTask(request);
    }

    @PostMapping("/task")
    public TaskResponseDTO createTask(@RequestBody @Valid TaskRequestDTO request) {
        return taskService.createTask(request);
    }
}
