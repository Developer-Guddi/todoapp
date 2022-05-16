package com.jaza.todoapp.service;

import com.jaza.todoapp.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TaskService {
    Task findOne(Long id);
    List<Task> findAll();
    Task save(Task task);

    Task update(Task task);

    void delete(Long id);

    boolean exists(Long id);
}
