package com.todo.ToDo.models;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private Long user_id;
    private Boolean complete;
    private String nameOfTask;
}
