package com.bondarev.auth_service.repository;

import com.bondarev.auth_service.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
