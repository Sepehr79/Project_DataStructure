package database.dao;

import beans.City;
import database.interfaces.IDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * dao for city table
 */
public class CityDao extends DAO implements IDAO<City> {

    private PreparedStatement insertCity;
    private PreparedStatement deleteCities;
    private PreparedStatement getCity;
    private PreparedStatement getCities;
    private PreparedStatement updateCity;
    private PreparedStatement resetCities;

    public CityDao(){
        super();
        try {
            insertCity = getConnection().prepareStatement("insert into City(name, population) values(?, ?)");
            deleteCities = getConnection().prepareStatement("delete from City where name = ?");
            getCity = getConnection().prepareStatement("select * from City where name = ?");
            getCities = getConnection().prepareStatement("select * from City");
            updateCity = getConnection().prepareStatement("update City set name = ?, population = ? where name = ?");
            resetCities = getConnection().prepareStatement("delete from City");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void insertObject(City city) {
        try {
            insertCity.setString(1, city.getName());
            insertCity.setInt(2, city.getPopulation());
            insertCity.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteObjects(City exampleObject) {
        try {
            deleteCities.setString(1, exampleObject.getName());
            deleteCities.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public City getObject(City exampleObject) {
        City city = null;
        try {
            getCity.setString(1, exampleObject.getName());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        try(ResultSet resultSet = getCity.executeQuery()) {
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
            ResultSet resultSet = getStatement().executeQuery("select * from City where " + condition + ";");

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

    public List<City> getObjects(){
        List<City> cities = new LinkedList<>();

        try(ResultSet resultSet = getCities.executeQuery()) {
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
            updateCity.setString(1, newObject.getName());
            updateCity.setInt(2, newObject.getPopulation());
            updateCity.setString(3, editingObject.getName());
            updateCity.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void resetDataBase() {
        try {
            resetCities.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
