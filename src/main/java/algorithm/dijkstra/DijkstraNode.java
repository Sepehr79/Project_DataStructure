package algorithm.dijkstra;

import lombok.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"shortestPath", "distance", "adjacentNodes"})
public class DijkstraNode<DT> {

    private @NonNull DT value;

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
}
