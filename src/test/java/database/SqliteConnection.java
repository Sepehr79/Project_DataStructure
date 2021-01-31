package database;

import org.junit.Test;

import java.sql.Connection;

public class SqliteConnection {
    @Test
    public void testConnection(){
        Connection connection = database.config.SqliteConnection.getConnection();
    }
}
