package algorithm.dijkstra;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class DijkstraTree<DT> {

    private Set<DijkstraNode<DT>> nodes = new HashSet<>();

    public void addNode(DijkstraNode<DT> dijkstraNode){
        nodes.add(dijkstraNode);
    }

    @SafeVarargs
    public final void addAllNodes(DijkstraNode<DT>... dijkstraNodes){
        nodes.addAll(Arrays.asList(dijkstraNodes));
    }

    public void resetDijkstraTree(){
        for(DijkstraNode<DT> node: nodes){
            node.setDistance(Float.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
        }
    }

    public void removeNode(DijkstraNode<DT> node){
        // remove from graph
        nodes.remove(node);

        // remove from neighbors
        for (DijkstraNode<DT> neighbors: nodes)
            neighbors.removeAdjacentNode(node);
    }

}
