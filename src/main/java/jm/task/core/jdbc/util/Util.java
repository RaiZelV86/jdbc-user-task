package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // set up a database connection
    private static final String url = "jdbc:mysql://localhost:3306/ProjectHabsida";
    private static final String user = "root";
    private static final String password = "Mfghost66";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при подключении к БД", e);
        }
    }
}
