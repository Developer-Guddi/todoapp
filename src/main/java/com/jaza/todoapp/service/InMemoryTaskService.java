package com.jaza.todoapp.service;


import com.jaza.todoapp.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InMemoryTaskService implements TaskService {
    private final Map<Long, Task> taskCache = new HashMap<>();
    private Long taskCounter = 1L;

    public InMemoryTaskService() {
        Task task1 = new Task(taskCounter++, "Go Shopping");
        Task task2 = new Task(taskCounter++, "Watch Movie");

        taskCache.put(task1.getId(), task1);
        taskCache.put(task2.getId(), task2);
    }
    @Override
    public Task findOne(Long id) {
        return taskCache.get(id);
    }

    @Override
    public List<Task> findAll() {

        return new ArrayList<>( taskCache.values());
    }

    @Override
    public Task save(Task task) {
        task.setId(taskCounter++);
        taskCache.put(task.getId(), task);
        return task;
    }

    @Override
    public Task update(Task task) {
        if (!exists(task.getId())) {
            throw new RuntimeException("Task with id = " + task.getId() + " does not exist.");
        }

        Task mTask = taskCache.get(task.getId());
        mTask.setName(task.getName());

        return mTask;
    }

    @Override
    public void delete(Long id) {
        taskCache.remove(id);
    }

    @Override
    public boolean exists(Long id) {
        return taskCache.containsKey(id);
    }

}