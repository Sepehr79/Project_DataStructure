package database.dao;

import beans.City;
import database.beans.Way;
import database.interfaces.IDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class NeighborDao extends DAO implements IDAO<Way> {

    public NeighborDao(){
        super();
    }

    @Override
    public void insertObject(Way object) {
        try {
            getStatement().execute(String.format("insert into neighbors(cityName, neighborName, distance)values ('%s', '%s', %f);",
                    object.getOriginCity().getName(),
                    object.getDistanceCity().getName(), (double) object.getDistance()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteObjects(Way exampleObject) {
        try {
            getStatement().execute(String.format("delete from neighbors where cityName = '%s' and neighborName = '%s';",
                    exampleObject.getOriginCity().getName(), exampleObject.getDistanceCity().getName()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Way getObject(Way exampleObject) {
        Way way = null;

        try {
            ResultSet resultSet = getStatement().executeQuery(String.format("select * from Neighbors where cityName = '%s' and" +
                            " neighborName = '%s';",
                    exampleObject.getOriginCity().getName(), exampleObject.getDistanceCity().getName()));

            resultSet.next();

            way = new Way(new City(resultSet.getString("cityName")),
                    new City(resultSet.getString("neighborName")),
                    (float) resultSet.getDouble("distance"));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return way;
    }

    @Override
    public List<Way> getObjects(String condition) {
        List<Way> ways = new LinkedList<>();

        try {
            ResultSet resultSet = getStatement().executeQuery("select * from neighbors where " + ";");

            while (resultSet.next()){
                Way way = new Way(new City(resultSet.getString("cityName")),
                        new City(resultSet.getString("neighborName")),
                        (float) resultSet.getDouble("distance"));

                ways.add(way);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return ways;
    }

    @Override
    public void updateObject(Way editingObject, Way newObject) {
        try {
            getStatement().execute(String.format("update neighbors set cityName = '%s', neighborName = '%s', distance = %d" +
                    " where cityName = '%s', neighborName = '%s';", newObject.getOriginCity().getName(),
                    newObject.getDistanceCity().getName(), newObject.getDistance(),
                    editingObject.getOriginCity().getName(), editingObject.getDistanceCity().getName()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
