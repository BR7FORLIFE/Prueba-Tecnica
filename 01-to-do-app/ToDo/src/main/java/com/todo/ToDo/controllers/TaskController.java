package com.todo.ToDo.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.ToDo.models.Task;
import com.todo.ToDo.models.UserModel;
import com.todo.ToDo.services.TaskServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServices taskServices;

    @PostMapping("/list/{user_id}")
    public ResponseEntity<List<Task>> listOfTask(@PathVariable Long user_id) {
        List<Task> allTasks = taskServices.listOfTask(user_id);
        return ResponseEntity.ok().body(allTasks);
    }

    @PostMapping("/create/{task}")
    public ResponseEntity<Void> createTask(@RequestBody Task task) {
        taskServices.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getTask/{task_id}")
    public ResponseEntity<Task> taskById(@PathVariable Long taskId) {
        Task task = taskServices.taskById(taskId);
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/delete/{task_id}")
    public ResponseEntity<Void> removeTaskById(@PathVariable Long taskId, @AuthenticationPrincipal UserModel userModel) {
        taskServices.removeTaskById(taskId, userModel);
        return ResponseEntity.ok().build();
    }

}
