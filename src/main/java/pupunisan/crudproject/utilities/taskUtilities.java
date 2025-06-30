/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupunisan.crudproject.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pupunisan.crudproject.config.entity.TaskEntity;

/**
 *
 * @author markj
 */
public class taskUtilities {

    
    public static boolean taskTitleExists(Connection conn, String title) throws SQLException {
        String query = "SELECT 1 FROM tasks WHERE LOWER(title) = LOWER(?) LIMIT 1";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); 
            }
        }
    }

   
    public static boolean isTaskValid(TaskEntity task) {
        return task.getTitle() != null && !task.getTitle().trim().isEmpty()
            && task.getDueDate() != null && !task.getDueDate().trim().isEmpty();
    }

    
    public static String formatStatus(String status) {
        if (status == null || status.isEmpty()) return "Pending";
        return status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
    }

   
    public static boolean isValidDate(String date) {
        try {
            java.sql.Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

    
