package com.bondarev.backend.repository;

import com.bondarev.backend.model.entity.Task;
import com.bondarev.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findTaskByTitleAndOwner(String title, User owner);

    List<Task> findByOwner(User owner);
}
