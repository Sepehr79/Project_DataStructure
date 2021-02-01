package database.dao;

import beans.City;
import database.interfaces.IDAO;

import java.util.List;

public class NeighborDao extends DAO implements IDAO<City> {

    @Override
    public void insertObject(City object) {

    }

    @Override
    public void deleteObjects(City exampleObject) {

    }

    @Override
    public City getObject(City exampleObject) {
        return null;
    }

    @Override
    public List<City> getObjects(String condition) {
        return null;
    }

    @Override
    public void updateObject(City editingObject, City newObject) {

    }
}
