package com.jaza.todoapp.restcontroller;

import com.jaza.todoapp.model.Task;
import com.jaza.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TaskRestController {
    private TaskService toDoService;

    @Autowired
    public TaskRestController(TaskService toDoService) {
        this.toDoService = toDoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/ToDos")
    public ResponseEntity<Void> createTask(@RequestBody Task task){
        Task taskCreated = toDoService.save(task);
         URI uri = URI.create("/ToDos/"+ taskCreated.getId());
        return ResponseEntity.created(uri).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/ToDos/{id}")
    public ResponseEntity<Task> getTask(@PathVariable ("id") Long id){
        Task task = toDoService.findOne(id);
        return new ResponseEntity<>(task ,HttpStatus.OK);
    }

}
