package helper;

import algorithm.dijkstra.DijkstraNode;
import algorithm.dijkstra.DijkstraTree;
import beans.City;
import exceptions.EmptyTreeException;

public class CityAdder {

    /**
     * add sample graph to the tree, tree must be empty
     * @param tree graph will add to it
     * @throws EmptyTreeException if size of tree is not empty
     */
    public void addCities(DijkstraTree<City> tree) throws EmptyTreeException {
        if (tree.getNodes().size() > 0){
            throw new EmptyTreeException("Input Tree must be empty");
        }

        DijkstraNode<City> yazd = new DijkstraNode<City>(new City("yazd", 80000));
        DijkstraNode<City> isfahan = new DijkstraNode<>(new City("isfahan", 2000000));
        DijkstraNode<City> shahrkord = new DijkstraNode<>(new City("shahrkord", 190000));
        DijkstraNode<City> semnan = new DijkstraNode<>(new City("semnan", 185000));
        DijkstraNode<City> kashan = new DijkstraNode<>(new City("kashan", 304000));
        DijkstraNode<City> tehran = new DijkstraNode<>(new City("tehran", 8500000));
        DijkstraNode<City> qom = new DijkstraNode<>(new City("qom", 1200000));
        DijkstraNode<City> arak = new DijkstraNode<>(new City("arak", 520000));
        DijkstraNode<City> hamedan = new DijkstraNode<>(new City("hamedan", 170000));
        DijkstraNode<City> qazvin = new DijkstraNode<>(new City("qazvin", 93000));

        isfahan.addTwoRoadAdjacent(shahrkord, 103);
        isfahan.addTwoRoadAdjacent(yazd, 311);
        isfahan.addTwoRoadAdjacent(kashan, 217);
        isfahan.addTwoRoadAdjacent(qom, 281);

        kashan.addTwoRoadAdjacent(qom, 108);
        kashan.addTwoRoadAdjacent(tehran, 245);

        qom.addTwoRoadAdjacent(tehran, 145);
        qom.addTwoRoadAdjacent(semnan, 281);
        qom.addTwoRoadAdjacent(arak, 131);

        tehran.addTwoRoadAdjacent(semnan, 222);
        tehran.addTwoRoadAdjacent(qazvin, 147);
        tehran.addTwoRoadAdjacent(hamedan, 316);

        arak.addTwoRoadAdjacent(hamedan, 189);

        tree.addAllNodes(yazd, isfahan, shahrkord, semnan, kashan, tehran, qom, arak, hamedan, qazvin);
    }

}
