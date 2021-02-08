package helper;

import algorithm.dijkstra.DijkstraNode;
import algorithm.dijkstra.DijkstraTree;
import beans.City;
import exceptions.EmptyTreeException;
import org.junit.Assert;
import org.junit.Test;

public class TestCityAdder {
    @Test
    public void testAddCities() throws EmptyTreeException {
        DijkstraTree<City> tree = new DijkstraTree<>();

        CityAdder cityAdder = new CityAdder();
        cityAdder.addCities(tree);

        Assert.assertEquals(tree.getNodes().size(), 10);
        for (DijkstraNode<City> cityDijkstraNode: tree.getNodes())
            System.out.println(cityDijkstraNode);
    }
}
