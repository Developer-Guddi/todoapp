package com.jaza.todoapp.repository;

import com.jaza.todoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

//@EnableJpaRepositories
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}