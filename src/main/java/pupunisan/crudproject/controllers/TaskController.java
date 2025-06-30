/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupunisan.crudproject.controllers;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pupunisan.crudproject.config.DbConnector;
import pupunisan.crudproject.config.entity.TaskEntity;

/**
 *
 * @author markj
 */
public class TaskController {
    private final DbConnector dbConnector;

    public TaskController() {
        this.dbConnector = new DbConnector();
    }

    public boolean addTask(TaskEntity task) throws SQLException {
        String query = "INSERT INTO tasks (title, description, due_date, status, priority) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, Date.valueOf(task.getDueDate()));
            stmt.setString(4, task.getStatus());
            stmt.setBoolean(5, task.isPriority());

            stmt.executeUpdate();
            return true;
        }
    }

    public List<TaskEntity> getAllTasks() throws SQLException {
        String query = "SELECT * FROM tasks";
        List<TaskEntity> tasks = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                TaskEntity task = new TaskEntity();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getDate("due_date").toString());
                task.setStatus(rs.getString("status"));
                task.setPriority(rs.getBoolean("priority"));
                tasks.add(task);
            }
        }

        return tasks;
    }

    public boolean updateTask(TaskEntity task) throws SQLException {
        String query = "UPDATE tasks SET title = ?, description = ?, due_date = ?, status = ?, priority = ? WHERE id = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, Date.valueOf(task.getDueDate()));
            stmt.setString(4, task.getStatus());
            stmt.setBoolean(5, task.isPriority());
            stmt.setInt(6, task.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteTask(int taskId) throws SQLException {
        String query = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, taskId);
            return stmt.executeUpdate() > 0;
        }
    }
}

    
    

