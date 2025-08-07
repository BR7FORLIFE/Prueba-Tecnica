package com.todo.ToDo.interfaces;

import java.util.List;

import com.todo.ToDo.models.Task;
import com.todo.ToDo.models.UserModel;

public interface TaskInterface {
    List<Task> listOfTask(Long user_id);
    void createTask(Task task);
    Task taskById(Long taskId);
    void removeTaskById(Long taskId,UserModel userModel);
}
