package com.todo.ToDo.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.todo.ToDo.interfaces.TaskInterface;
import com.todo.ToDo.models.Task;
import com.todo.ToDo.models.UserModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class TaskRepository implements TaskInterface {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Task> taskRowMapper = (rs, rowNum) -> {
        Task task = new Task();
        task.setId(rs.getLong("id"));
        task.setNameOfTask(rs.getString("titulo"));
        task.setComplete(rs.getBoolean("completada"));
        return task;
    };

    @Override
    public List<Task> listOfTask(UserModel userModel) {
        String query = "SELECT id, titulo, completada FROM tareas WHERE usuario_id = ?";
        return jdbcTemplate.query(query, taskRowMapper, userModel.getId());
    }

    @Override
    public void createTask(Task task, UserModel userModel) {
        String query = "INSERT INTO tareas (titulo, completada, usuario_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, task.getNameOfTask(), task.getComplete(), userModel.getId());
    }

    @Override
    public Task taskById(Long taskId, UserModel userModel) {
        String query = "SELECT id, titulo, completada FROM tareas WHERE id = ? AND usuario_id = ?";
        return jdbcTemplate.queryForObject(query, taskRowMapper, taskId, userModel.getId());
    }

    @Override
    public void removeTaskById(Long taskId, UserModel userModel) {
        String query = "DELETE FROM tareas WHERE id = ? AND usuario_id = ?";
        jdbcTemplate.update(query, taskId, userModel.getId());
    }
}
