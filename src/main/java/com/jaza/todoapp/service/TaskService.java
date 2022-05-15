package com.jaza.todoapp.service;


import com.jaza.todoapp.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {
    private final Map<Long, Task> taskCache = new HashMap<>();
    private Long taskCounter = 1L;

    public TaskService() {
        Task task1 = new Task(taskCounter++, "Go Shopping");
        Task task2 = new Task(taskCounter++, "Watch Movie");

        taskCache.put(task1.getId(), task1);
        taskCache.put(task2.getId(), task2);
    }
    public Task findOne(Long id) {
        return taskCache.get(id);
    }

    public List<Task> findAll() {

        return new ArrayList<>( taskCache.values());
    }
    public Task save(Task task) {
        task.setId(taskCounter++);
        taskCache.put(task.getId(), task);
        return task;
    }

    public Task update(Long id, Task task) {
        if (!exists(id)) {
            throw new RuntimeException("Task with id = " + id + " does not exist.");
        }

        Task mTask = taskCache.get(id);
        mTask.setName(task.getName());

        return mTask;
    }

    public void delete(Long id) {
        taskCache.remove(id);
    }

    public boolean exists(Long id) {
        return taskCache.containsKey(id);
    }

}