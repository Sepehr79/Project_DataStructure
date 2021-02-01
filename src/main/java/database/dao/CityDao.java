package database.dao;

import beans.City;
import database.config.SqliteConnection;
import database.interfaces.IDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CityDao implements IDAO<City> {

    private static Statement statement;

    static {
        try {
            Connection connection = SqliteConnection.getConnection();
            statement = connection.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public CityDao(){

    }

    @Override
    public void insertObject(City city) {
        try {
            statement.execute(String.format("insert into City(name, population) values('%s', %d);", city.getName(), city.getPopulation()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteObjects(City exampleObject) {
        try {
            statement.execute(String.format("delete from City where name = '%s';", exampleObject.getName()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public City getObject(City exampleObject) {
        City city = null;

        try {
            ResultSet resultSet = statement.executeQuery(String.format("select * from City where name = '%s';", exampleObject.getName()));
            resultSet.next();

            city = new City(resultSet.getString("name"), resultSet.getInt("population"));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        return city;
    }

    @Override
    public List<City> getObjects(String condition) {
        List<City> cities = new LinkedList<>();

        try {
            ResultSet resultSet = statement.executeQuery("select * from City where " + condition + ";");

            while (resultSet.next()){
                String name = resultSet.getString("name");
                int population = resultSet.getInt("population");

                cities.add(new City(name, population));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return cities;
    }

    @Override
    public void updateObject(City editingObject, City newObject) {
        try {
            statement.execute(String.format("update City set name = '%s', population = %d where name = '%s';",
                    newObject.getName(), newObject.getPopulation(), editingObject.getName()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
