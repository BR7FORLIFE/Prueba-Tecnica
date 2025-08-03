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
        task.setUser_id(rs.getLong("user_id"));
        task.setNameOfTask(rs.getString("nameOfTask"));
        task.setComplete(rs.getBoolean("complete"));
        return task;
    };

    @Override
    public List<Task> listOfTask(UserModel userModel) {
        String query = "SELECT id, nameOfTask, complete FROM Tasks WHERE user_id = ?";
        return jdbcTemplate.query(query, taskRowMapper, userModel.getId());
    }

    @Override
    public void createTask(Task task, UserModel userModel) {
        String query = "INSERT INTO Tasks (user_id, complete, nameOfTask) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, task.getUser_id(), task.getComplete(), task.getNameOfTask());
    }

    @Override
    public Task taskById(Long taskId, UserModel userModel) {
        String query = "SELECT user_id, complete, nameOfTask FROM Tasks WHERE id = ? AND user_id = ?";
        return jdbcTemplate.queryForObject(query, taskRowMapper, taskId, userModel.getId());
    }

    @Override
    public void removeTaskById(Long taskId, UserModel userModel) {
        String query = "DELETE FROM Tasks WHERE id = ? AND user_id = ?";
        jdbcTemplate.update(query, taskId, userModel.getId());
    }
}
