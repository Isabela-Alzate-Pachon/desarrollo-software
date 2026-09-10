package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "root";
 
    public static Connection getConnection() throws SQLException {
        // No es necesario llamar a Class.forName con el driver moderno, pero puedes hacerlo si quieres:
        // Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
}
