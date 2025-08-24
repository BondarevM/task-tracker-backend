package com.bondarev.backend.service;

import com.bondarev.backend.exception.common.EntityAlreadyExistsException;
import com.bondarev.backend.exception.common.EntityNotFoundException;
import com.bondarev.backend.mapper.TaskMapper;
import com.bondarev.backend.model.dto.task.TaskRequestDTO;
import com.bondarev.backend.model.dto.task.TaskResponseDTO;
import com.bondarev.backend.model.entity.Task;
import com.bondarev.backend.model.entity.User;
import com.bondarev.backend.model.enums.TaskStatus;
import com.bondarev.backend.repository.TaskRepository;
import com.bondarev.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponseDTO> getUserTasks() {
        User currentUser = getCurrentUser();
        List<Task> tasks = taskRepository.findByOwner(currentUser);

        return tasks.stream()
                .map(taskMapper::entityToDto)
                .sorted(Comparator.comparing(TaskResponseDTO::getId))
                .toList();
    }

    public TaskResponseDTO createTask(TaskRequestDTO request) {
        User user = getCurrentUser();
        String title = request.getTitle();
        String text = request.getText();

        Optional<Task> task = taskRepository.findTaskByTitleAndOwner(title, user);
        if (task.isPresent()) {
            throw new EntityAlreadyExistsException(String.format("Task with title: '%s' already exists", title));
        }

        Task newTask = Task.builder()
                .title(title)
                .text(text)
                .status(TaskStatus.CREATED)
                .owner(user)
                .build();
        taskRepository.save(newTask);
        return taskMapper.entityToDto(newTask);
    }

    public TaskResponseDTO updateTask(TaskRequestDTO request) {
        User user = getCurrentUser();
        String title = request.getTitle();
        String text = request.getText();
        TaskStatus status = request.getStatus();

        Task task = taskRepository.findTaskByTitleAndOwner(title, user)
                .orElseThrow(() -> new EntityNotFoundException(Task.class, title));

        task.setTitle(title);
        task.setText(text);
        task.setStatus(status);

        Task savedTask = taskRepository.save(task);
        return taskMapper.entityToDto(savedTask);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(User.class, username));
    }
}
