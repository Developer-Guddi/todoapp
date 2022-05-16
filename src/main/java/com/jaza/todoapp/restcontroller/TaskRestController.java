package com.jaza.todoapp.restcontroller;

import com.jaza.todoapp.model.Task;
import com.jaza.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

//    @Qualifier
    @Autowired
    TaskService taskService;

 //   @Autowired
//    public TaskRestController(TaskService toDoService) {
//        this.toDoService = toDoService;
//    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    public ResponseEntity<Void> createTask(@RequestBody Task task){
//        Task taskCreated = taskService.save(task);
//        URI uri = URI.create("/tasks/"+ taskCreated.getId());
//        return ResponseEntity.created(uri).build();
//    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.save(task);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllTasks")
    private List<Task> getAllTasks() {

        return taskService.findAll();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable ("id") Long id){
        Task task = taskService.findOne(id);
        return new ResponseEntity<>(task ,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task){
        task.setId(id);
        task= taskService.update(task);
        return ResponseEntity.ok(task);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

}