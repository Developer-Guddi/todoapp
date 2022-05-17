package com.jaza.todoapp.service;


import com.jaza.todoapp.model.Task;
import com.jaza.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersistentTaskService implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Override
    public Task findOne(Long id) {
         return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        taskRepository.findById(task.getId()).orElseThrow(() ->new RuntimeException("Task with this taskId not present"));
        return taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        Task taskToBeDeleted =taskRepository.findById(id).orElseThrow(() ->new RuntimeException("Task with this taskId not present"));
        taskRepository.delete(taskToBeDeleted);
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
}