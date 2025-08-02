package com.todo.ToDo.models;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private String username;
    private Boolean complete;
    private String nameOfTask;
}
