package algorithm.dijkstra;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Dijkstra<DT> {

    private DijkstraNode<DT> getLowestDistanceNode(Set<DijkstraNode<DT>> unsettledNodes) {
        DijkstraNode<DT> lowestDistanceNode = null;
        float lowestDistance = Float.MAX_VALUE;
        for (DijkstraNode<DT> node: unsettledNodes) {
            float nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void CalculateMinimumDistance(DijkstraNode<DT> evaluationNode, float edgeWeigh, DijkstraNode<DT> sourceNode) {
        float sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<DijkstraNode<DT>> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public DijkstraTree calculateShortestPathFromSource(DijkstraTree<DT> graph, DijkstraNode<DT> source) {
        graph.resetDijkstraTree();

        source.setDistance((float) 0);

        Set<DijkstraNode<DT>> settledNodes = new HashSet<>();
        Set<DijkstraNode<DT>> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            DijkstraNode<DT> currentNode = this.getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< DijkstraNode<DT>, Float> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) {
                DijkstraNode<DT> adjacentNode = adjacencyPair.getKey();
                float edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

}
