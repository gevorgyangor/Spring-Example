package com.example.userandtaskexam.rest;

import com.example.userandtaskexam.model.Task;
import com.example.userandtaskexam.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskRest {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> tasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable int id) {
        return taskRepository.findOne(id);
    }

    @GetMapping("/{date}")
    public List<Task> startDate(@PathVariable String date) {
        List<Task> tasks = taskRepository.findTasksByStartData(date);
        if (tasks == null) {
            ResponseEntity.badRequest().body("Tasks with " + date + " does not exist");
        }
        return tasks;
    }

    @GetMapping("/{userId}")
    private List<Task> getTasksByUserId(@PathVariable int userId) {
        List<Task> tasks = taskRepository.findTasksByUser_id(userId);
        if (tasks == null) {
            ResponseEntity.badRequest().body("Tasks with " + userId + " does not exist");
        }
        return tasks;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        try {
            taskRepository.delete(id);
            return ResponseEntity.ok("Deleted Task");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Task with " + id + " does not exist");
        }
    }
}
