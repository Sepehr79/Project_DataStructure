package beans;

import algorithm.dijkstra.Dijkstra;
import algorithm.dijkstra.DijkstraNode;
import algorithm.dijkstra.DijkstraTree;
import exceptions.WrongInputException;
import org.junit.Assert;
import org.junit.Test;

public class CityTest {

    @Test
    public void testCityEquation(){
        City city1 = new City("isfahan", 1500);
        City city2 = new City("isfahan", 2000);

        // cities are equals by names
        Assert.assertEquals(city1, city2);
    }

    @Test
    public void testSetPopulation(){
        City city1 = new City("isfahan", 2500);

        try {
            city1.setPopulation(-1);
            Assert.fail();
        }catch (Exception exception){
            Assert.assertTrue(exception instanceof WrongInputException);
        }
    }

    @Test
    public void testSetName(){
        City city = new City("isfahan");

        try {
            city.setName("dwq#dsa");
            Assert.fail();
        }catch (Exception exception){
            Assert.assertTrue(exception instanceof WrongInputException);
        }

        try {
            city.setName("dw5dsa");
            Assert.fail();
        }catch (Exception exception){
            Assert.assertTrue(exception instanceof WrongInputException);
        }
    }

    @Test
    public void testDijkstraWithCities(){
        DijkstraNode<City> isfahan = new DijkstraNode<>(new City("isfahan"));
        DijkstraNode<City> tehran = new DijkstraNode<>(new City("tehran"));
        DijkstraNode<City> karaj = new DijkstraNode<>(new City("karaj"));
        DijkstraNode<City> qom = new DijkstraNode<>(new City("qom"));

        // test equation
        Assert.assertEquals(qom, new DijkstraNode<>(new City("qom")));

        isfahan.addTwoRoadAdjacent(karaj, 490);
        isfahan.addTwoRoadAdjacent(qom, 320);

        qom.addTwoRoadAdjacent(tehran, 75);
        karaj.addTwoRoadAdjacent(tehran, 90);

        DijkstraTree<City> tree = new DijkstraTree<>();

        tree.addAllNodes(isfahan, tehran);
        tree.addNode(karaj);
        tree.addNode(qom);

        new Dijkstra<City>().calculateShortestPathFromSource(tree, isfahan);

        Assert.assertEquals(tehran.getDistance(), 395, .1);

        Assert.assertEquals(tehran.getAdjacentNodes().size(), 2);

        //Assert.assertNotNull(tehran.getAdjacentNodes().get(isfahan));FIXME

        new Dijkstra<City>().calculateShortestPathFromSource(tree, karaj);

        Assert.assertEquals(qom.getDistance(), 165, .1);

        Assert.assertEquals(qom.getAdjacentNodes().size(), 2);
    }

}
