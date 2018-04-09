package com.example.userandtaskexam.repository;

import com.example.userandtaskexam.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findTasksByUser_id(int id);

    List<Task> findTasksByStartData(String date);
}
