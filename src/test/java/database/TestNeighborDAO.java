package database;

import beans.City;
import database.beans.Way;
import database.dao.CityDao;
import database.dao.NeighborDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class TestNeighborDAO {

    // managing database
    private final CityDao cityDao = new CityDao();
    private final NeighborDao neighborDao = new NeighborDao();

    /**
     * add cities before tests
     */
    @Before
    public void insertCities(){
        cityDao.insertObject(new City("isfahan", 15000000));
        cityDao.insertObject(new City("tehran", 25000000));
        cityDao.insertObject(new City("qom", 750000));
        cityDao.insertObject(new City("gilan", 1200000));
    }

    /**
     * remove cities after tests
     */
    @After
    public void deleteCities(){
        cityDao.deleteObjects(new City("isfahan"));
        cityDao.deleteObjects(new City("tehran"));
        cityDao.deleteObjects(new City("qom"));
        cityDao.deleteObjects(new City("gilan"));
    }

    @Test
    public void insertNeighborCity(){
        // insert way to database
        neighborDao.insertObject(new Way(new City("isfahan"), new City("tehran"), 520F));

        // get way from database
        Way way = neighborDao.getObject(new Way(new City("isfahan"), new City("tehran")));

        // assertion test
        Assert.assertEquals(way.getDistance(), 520F, .1);

        // remove way from database
        neighborDao.deleteObjects(new Way(new City("isfahan"), new City("tehran")));
    }

    @Test
    public void testDeleteObject(){
        // insert
        neighborDao.insertObject(new Way(new City("isfahan"), new City("tehran"), 520F));

        // delete
        neighborDao.deleteObjects(new Way(new City("isfahan"), new City("tehran")));

        // get deleted way from database
        Way way = null;
        try {
            way = neighborDao.getObject(new Way(new City("isfahan"), new City("tehran")));
        }catch (Exception exception){
            Assert.assertTrue(exception instanceof SQLException);
        }
    }

}
