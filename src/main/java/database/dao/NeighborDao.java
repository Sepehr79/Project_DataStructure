package database.dao;

import beans.City;
import database.beans.Way;
import database.interfaces.IDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * dao for neighbors table
 */
public class NeighborDao extends DAO implements IDAO<Way> {

    /**
     * constructor
     */
    public NeighborDao(){
        super();
    }

    /**
     * insert new way object into the database
     * @param object will insert to the database
     */
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

    /**
     * delete rows from database by example object
     * @param exampleObject example to remove objects that are same to the argument object
     */
    @Override
    public void deleteObjects(Way exampleObject) {

        try {
            getStatement().execute(String.format("delete from neighbors where cityName = '%s' and neighborName = '%s';",
                    exampleObject.getOriginCity().getName(), exampleObject.getDistanceCity().getName()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * gets unique object by example input object
     * @param exampleObject condition of gets witch object
     * @return way instance
     */
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

    /**
     * gets objects by simple sql condition
     * @param condition
     * example of correct condition:
     *            age < 90 and age > 10
     * @return list of objects
     */
    @Override
    public List<Way> getObjects(String condition) {
        List<Way> ways = new LinkedList<>();

        try {
            ResultSet resultSet = getStatement().executeQuery("select * from Neighbors where " + condition + ";");

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

    /**
     * simple 'select * from <table name>'
     * @return list of objects
     */
    public List<Way> getObjects(){

        List<Way> ways = new LinkedList<>();

        try {
            ResultSet resultSet = getStatement().executeQuery("select * from Neighbors;");

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

    /**
     * update object by example input object
     * @param editingObject that will update
     * @param newObject that will update to the database
     */
    @Override
    public void updateObject(Way editingObject, Way newObject) {

        try {
            getStatement().execute(String.format("update neighbors set cityName = '%s', neighborName = '%s', distance = %f" +
                    " where cityName = '%s' and neighborName = '%s';", newObject.getOriginCity().getName(),
                    newObject.getDistanceCity().getName(), newObject.getDistance(),
                    editingObject.getOriginCity().getName(), editingObject.getDistanceCity().getName()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * checks if object is in the database
     * @param way instance of way
     * @return true if way is in database
     */
    public boolean hasWay(Way way){

        try {
            ResultSet resultSet = getStatement().executeQuery("select * from Neighbors");

            while (resultSet.next()){
                if (resultSet.getString("cityName").equals(way.getOriginCity().getName())  &&
                resultSet.getString("neighborName").equals(way.getDistanceCity().getName())){
                    closeDataBase();
                    return true;
                }

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }


    /**
     * delete all rows from database
     */
    @Override
    public void resetDataBase() {
        try {
            getStatement().execute("delete from neighbors");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


    }
}
