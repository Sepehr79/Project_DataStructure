package database.api;

import algorithm.dijkstra.DijkstraNode;
import algorithm.dijkstra.DijkstraTree;
import beans.City;
import database.beans.Way;
import database.dao.CityDao;
import database.dao.NeighborDao;
import lombok.Getter;

import java.util.Map;

@Getter
public class DataBaseAPI {

    private final CityDao cityDao = new CityDao();

    private final NeighborDao neighborDao = new NeighborDao();

    private void insertCities(DijkstraTree<City> tree){
        for (DijkstraNode<City> cityDijkstraNode: tree.getNodes()){
            cityDao.insertObject(cityDijkstraNode.getValue());
        }
    }

    private void insertNeighbors(DijkstraTree<City> tree){
        for (DijkstraNode<City> cityDijkstraNode: tree.getNodes()){
            for (Map.Entry<DijkstraNode<City>, Float> entry: cityDijkstraNode.getAdjacentNodes().entrySet()){
                neighborDao.insertObject(new Way(cityDijkstraNode.getValue(), entry.getKey().getValue(), entry.getValue()));
            }
        }
    }

    public void insertTree(DijkstraTree<City> cityDijkstraTree){
        this.resetDataBase();
        insertCities(cityDijkstraTree);
        insertNeighbors(cityDijkstraTree);
    }

    public DijkstraTree<City> getCitiesFromDataBase(){
        DijkstraTree<City> tree = new DijkstraTree<>();
        for(City city: cityDao.getObjects()){
            tree.getNodes().add(new DijkstraNode<>(city));
        }
        for (Way way: neighborDao.getObjects()){
            tree.getNode(new DijkstraNode<City>(way.getOriginCity())).addOneRoadAdjacent(tree.getNode(new DijkstraNode<>(way.getDistanceCity())), way.getDistance());
        }
        return tree;
    }

    public void resetDataBase(){
        cityDao.resetDataBase();
        neighborDao.resetDataBase();
    }
}
