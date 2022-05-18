package com.jaza.todoapp.service;


import com.jaza.todoapp.model.Task;
import com.jaza.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
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
    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task update(Task task) {
        Task mTask = taskRepository.findById(task.getId()).orElseThrow(() ->new RuntimeException("Task with this taskId not present"));
        mTask.setName(task.getName());
        return mTask;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Task taskToBeDeleted =taskRepository.findById(id).orElseThrow(() ->new RuntimeException("Task with this taskId not present"));
        taskRepository.delete(taskToBeDeleted);
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
}