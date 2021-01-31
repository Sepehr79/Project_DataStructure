package database;

import database.config.SqliteConnection;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertionTest {
    @Test
    public void testInsert() throws SQLException {
        Connection connection = SqliteConnection.getConnection();
        Statement statement = connection.createStatement();

        // insert to city table
        statement.execute("insert into City(name, Population) values('isfahan', 1500000);");

        // read from database
        ResultSet resultSet = statement.executeQuery("select * from City");

        resultSet.next();

        String cityName = resultSet.getString("name");
        int population = resultSet.getInt("Population");

        Assert.assertEquals(cityName, "isfahan");
        Assert.assertEquals(population, 1500000);


        statement.execute("delete from City;");
    }
}
