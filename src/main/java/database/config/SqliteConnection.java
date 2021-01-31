package database.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {

    private static Connection connection = null;


    private SqliteConnection(){}

    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src//main//resources//db");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
    }

}
