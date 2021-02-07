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
import java.util.List;


public class TestNeighborDAO {

    // managing database
    private final CityDao cityDao = new CityDao();
    private final NeighborDao neighborDao = new NeighborDao();

    /**
     * add cities before tests
     */
    @Before
    public void insertCities(){
        cityDao.resetDataBase();
        neighborDao.resetDataBase();

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

        Assert.assertFalse(neighborDao.hasWay(new Way(new City("isfahan"), new City("tehran"))));
    }

    @Test
    public void testDeleteObject(){
        // insert
        neighborDao.insertObject(new Way(new City("isfahan"), new City("tehran"), 520F));

        Assert.assertTrue(neighborDao.hasWay(new Way(new City("isfahan"), new City("tehran"))));

        // delete
        neighborDao.deleteObjects(new Way(new City("isfahan"), new City("tehran")));

        Assert.assertFalse(neighborDao.hasWay(new Way(new City("isfahan"), new City("tehran"))));
    }

    @Test
    public void testGetObjects() throws SQLException {

        // insert to the database
        neighborDao.insertObject(new Way(new City("isfahan"), new City("tehran"), 520F));
        neighborDao.insertObject(new Way(new City("qom"), new City("gilan"), 345F));

        List<Way> ways = neighborDao.getObjects("distance > 200 and distance < 600");

        Assert.assertEquals(ways.size(), 2);

        Assert.assertEquals(ways.get(0).getOriginCity().getName(), "isfahan");
        Assert.assertEquals(ways.get(1).getOriginCity().getName(), "qom");

        // delete from database
        neighborDao.getStatement().execute("delete from Neighbors");

        Assert.assertFalse(neighborDao.hasWay(new Way(new City("isfahan"), new City("tehran"))));
        Assert.assertFalse(neighborDao.hasWay(new Way(new City("qom"), new City("gilan"))));
    }

    @Test
    public void testUpdateWay(){

        neighborDao.insertObject(new Way(new City("isfahan"), new City("tehran"), 520F));

        neighborDao.updateObject(new Way(new City("isfahan"), new City("tehran")),
                new Way(new City("tehran"), new City("qom"), 25F));

        Assert.assertFalse(neighborDao.hasWay(new Way(new City("isfahan"), new City("tehran"), 520F)));

        Assert.assertTrue(neighborDao.hasWay(new Way(new City("tehran"), new City("qom"))));
    }

    @Test
    public void testInsertFalseObject(){
        neighborDao.insertObject(new Way(new City("fakeCity"), new City("fakeCity"), 25F));
        Assert.assertFalse(neighborDao.hasWay(new Way(new City("fakeCity"), new City("faceCity"))));
    }

}
