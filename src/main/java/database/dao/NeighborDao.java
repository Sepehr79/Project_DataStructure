package database.dao;

import beans.City;
import database.beans.Way;
import database.interfaces.IDAO;

import java.sql.PreparedStatement;
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
    private PreparedStatement insertWay;
    private PreparedStatement deleteWay;
    private PreparedStatement getWay;
    private PreparedStatement getWays;
    private PreparedStatement updateWay;
    private PreparedStatement resetWays;

    public NeighborDao(){
        super();
        try {
            insertWay = getConnection().prepareStatement("insert into neighbors(cityName, neighborName, distance)values (?, ?, ?)");
            deleteWay = getConnection().prepareStatement("delete from neighbors where cityName = ? and neighborName = ?");
            getWay = getConnection().prepareStatement("select * from Neighbors where cityName = ? and neighborName = ?");
            getWays = getConnection().prepareStatement("select * from Neighbors");
            updateWay = getConnection().prepareStatement("update neighbors set cityName = ?, neighborName = ?, distance = ?" +
                    "where cityName = ? and neighborName = ?");
            resetWays = getConnection().prepareStatement("delete from neighbors");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * insert new way object into the database
     * @param object will insert to the database
     */
    @Override
    public void insertObject(Way object) {
        try {
            insertWay.setString(1, object.getOriginCity().getName());
            insertWay.setString(2, object.getDistanceCity().getName());
            insertWay.setDouble(3, (double) object.getDistance());
            insertWay.execute();
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
            deleteWay.setString(1, exampleObject.getOriginCity().getName());
            deleteWay.setString(2, exampleObject.getDistanceCity().getName());
            deleteWay.execute();
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
            getWay.setString(1, exampleObject.getOriginCity().getName());
            getWay.setString(2, exampleObject.getDistanceCity().getName());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        try(ResultSet resultSet = getWay.executeQuery()) {
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

        try(ResultSet resultSet = getStatement().executeQuery("select * from Neighbors where " + condition + ";")) {
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

        try (ResultSet resultSet = getWays.executeQuery()){
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
            updateWay.setString(1, newObject.getOriginCity().getName());
            updateWay.setString(2, newObject.getDistanceCity().getName());
            updateWay.setDouble(3, newObject.getDistance());
            updateWay.setString(4, editingObject.getOriginCity().getName());
            updateWay.setString(5, editingObject.getDistanceCity().getName());
            updateWay.execute();
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

        try(ResultSet resultSet = getWays.executeQuery()) {
            while (resultSet.next()){
                if (resultSet.getString("cityName").equals(way.getOriginCity().getName())  &&
                resultSet.getString("neighborName").equals(way.getDistanceCity().getName())){
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
            resetWays.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
