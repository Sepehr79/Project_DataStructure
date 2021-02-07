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

    /**
     * open database when new object of instances created
     */
    public DAO(){
        openDataBase();
    }

    /**
     * close database objects of instance collected by garbage
     */
    @Override
    public void finalize(){
        closeDataBase();
    }

    /**
     * open data base
     */
    public void openDataBase(){
        try {
            connection = SqliteConnection.getConnection();
            statement = connection.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * close database
     */
    public void closeDataBase(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
