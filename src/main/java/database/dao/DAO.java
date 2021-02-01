package database.dao;

import database.config.SqliteConnection;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Getter
public abstract class DAO {

    private Statement statement;
    private Connection connection;

    public void openDataBase(){
        try {
            connection = SqliteConnection.getConnection();
            statement = connection.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void closeDataBase(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public abstract void resetDataBase();

}
