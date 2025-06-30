/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupunisan.crudproject.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author markj
 */
public class DataInitializer {
    private static DbConnector dbConnector;

    public DataInitializer(DbConnector dbConnector) {
        DataInitializer.dbConnector = dbConnector;
    }
    
    public void createUsersTable() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS tasks(
                     id SERIAL PRIMARY KEY,
                     title VARCHAR(255) NOT NULL,
                     description TEXT,
                     due_date DATE NOT NULL,
                     status VARCHAR(100),
                     priority BOOLEAN DEFAULT FALSE
                     )""";
        try (Connection conn = dbConnector.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;  // Rethrow exception to ensure calling code is aware of the failure
        }
    }
    
    
}
