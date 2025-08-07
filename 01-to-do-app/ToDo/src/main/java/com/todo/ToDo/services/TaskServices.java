package com.todo.ToDo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.ToDo.interfaces.TaskInterface;
import com.todo.ToDo.models.Task;
import com.todo.ToDo.models.UserModel;
import com.todo.ToDo.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServices implements TaskInterface {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> listOfTask(Long user_id) {
        return taskRepository.listOfTask(user_id);
    }

    @Override
    public void createTask(Task task) {
        taskRepository.createTask(task);
    }

    @Override
    public Task taskById(Long taskId) {
        return taskRepository.taskById(taskId);
    }

    @Override
    public void removeTaskById(Long taskId, UserModel userModel) {
        taskRepository.removeTaskById(taskId, userModel);
    }
}
