package algorithm.dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DijkstraNode<DT> {

    private DT value;

    private List<DijkstraNode<DT>> shortestPath = new LinkedList<>();

    private Float distance = Float.MAX_VALUE;

    Map<DijkstraNode<DT>, Float> adjacentNodes = new HashMap<>();

    public void addOneRoadAdjacent(DijkstraNode<DT> destination, float distance) {
        adjacentNodes.put(destination, distance);
    }

    public void addTwoRoadAdjacent(DijkstraNode<DT> destination, float distance){
        adjacentNodes.put(destination, distance);
        destination.getAdjacentNodes().put(this, distance);
    }

    public void removeAdjacentNode(DijkstraNode<DT> node){
        adjacentNodes.remove(node);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder(value.toString() + "\n");
        str.append("neighbor nodes:\n");
        for (Map.Entry<DijkstraNode<DT>, Float> entry: adjacentNodes.entrySet())
            str.append("-> ").append(entry.getKey().getValue()).append(" distance: ").append(entry.getValue()).append("\n");

        return str.toString();
    }

    public DT getValue() {
        return value;
    }

    public void setValue(DT value) {
        this.value = value;
    }

    public List<DijkstraNode<DT>> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<DijkstraNode<DT>> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Map<DijkstraNode<DT>, Float> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<DijkstraNode<DT>, Float> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public DijkstraNode(DT value, List<DijkstraNode<DT>> shortestPath, Float distance, Map<DijkstraNode<DT>, Float> adjacentNodes) {
        this.value = value;
        this.shortestPath = shortestPath;
        this.distance = distance;
        this.adjacentNodes = adjacentNodes;
    }

    public DijkstraNode(DT value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object object){
        DijkstraNode<DT> dijkstraNode = (DijkstraNode<DT>) object;
        return this.getValue().equals(((DijkstraNode<?>) object).value);
    }

    @Override
    public int hashCode(){
        return this.getValue().hashCode();
    }
}
