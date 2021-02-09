package database;

import beans.City;
import database.dao.CityDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestCityDAO {

    private CityDao cityDao;

    @Before
    public void createNewInstance(){
        cityDao = new CityDao();
        cityDao.resetDataBase();
    }

    @Test
    public void testInsertingDeleting(){

        cityDao.insertObject(new City("isfahan", 1500000));

        City city = cityDao.getObject(new City("isfahan"));

        Assert.assertEquals(city.getPopulation(), 1500000);

        cityDao.deleteObjects(new City("isfahan"));
    }

    @Test
    public void testUpdatingCity(){
        cityDao.insertObject(new City("isfahan", 100));

        // update city
        cityDao.updateObject(new City("isfahan"), new City("tehran", 200));

        City city = cityDao.getObject(new City("tehran"));

        Assert.assertEquals(city.getPopulation(), 200);

        cityDao.deleteObjects(new City("tehran"));
    }

    @Test
    public void testUpdateObject(){

        cityDao.insertObject(new City("isfahan", 1500000));

        List<City> cities = cityDao.getObjects("population > 1000000 and population < 2000000");

        Assert.assertEquals(cities.size(), 1);

        cityDao.deleteObjects(new City("isfahan"));

    }

}
