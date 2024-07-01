package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/rentacarsystem";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "gonca1";
    private Connection connection = null;
    private static  DB instance = null;

    public Connection getConnection() {
        return connection;
    }

    private DB() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DB();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();

    }
}
