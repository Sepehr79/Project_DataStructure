package database.api;

import algorithm.dijkstra.DijkstraNode;
import algorithm.dijkstra.DijkstraTree;
import beans.City;
import database.beans.Way;
import database.dao.CityDao;
import database.dao.NeighborDao;

import java.util.Map;

public class DataBaseAPI {

    private final CityDao cityDao = new CityDao();

    private final NeighborDao neighborDao = new NeighborDao();

    /**
     * insert tree to the database
     * @param cityDijkstraTree will insert to the database
     */
    public void insertTree(DijkstraTree<City> cityDijkstraTree){
        this.resetDataBase();
        insertCities(cityDijkstraTree);
        insertNeighbors(cityDijkstraTree);
    }

    /**
     * @return dijkstra tree of cities
     */
    public DijkstraTree<City> getTreeFromDataBase(){
        DijkstraTree<City> tree = new DijkstraTree<>();

        // insert cities to the tree
        for(City city: cityDao.getObjects()){
            tree.getNodes().add(new DijkstraNode<>(city));
        }

        // creating interdependence between cities
        for (Way way: neighborDao.getObjects()){
            tree.getNode(new DijkstraNode<City>(way.getOriginCity()))
                    .addOneRoadAdjacent(tree.getNode(new DijkstraNode<>(way.getDistanceCity())), way.getDistance());
        }

        return tree;
    }

    /**
     * delete all data from database
     */
    public void resetDataBase(){
        cityDao.resetDataBase();
        neighborDao.resetDataBase();
    }

    /**
     * insert cities into the city table
     * @param tree dijkstra tree of cites that wil insert its values as cities
     */
    private void insertCities(DijkstraTree<City> tree){
        for (DijkstraNode<City> cityDijkstraNode: tree.getNodes()){
            cityDao.insertObject(cityDijkstraNode.getValue());
        }
    }

    /**
     * defining relationships between cities in the database
     * @param tree will insert neighbors to the database
     */
    private void insertNeighbors(DijkstraTree<City> tree){
        for (DijkstraNode<City> cityDijkstraNode: tree.getNodes()){
            for (Map.Entry<DijkstraNode<City>, Float> entry: cityDijkstraNode.getAdjacentNodes().entrySet()){
                neighborDao.insertObject(new Way(cityDijkstraNode.getValue(), entry.getKey().getValue(), entry.getValue()));
            }
        }
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public NeighborDao getNeighborDao() {
        return neighborDao;
    }
}
