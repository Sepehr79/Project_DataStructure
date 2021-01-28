package algorithm.dijkstra;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDijkstraImp {
    private DijkstraNode<String> nodeA;
    private DijkstraNode<String> nodeB;
    private DijkstraNode<String> nodeC;
    private DijkstraNode<String> nodeD;
    private DijkstraNode<String> nodeE;
    private DijkstraNode<String> nodeF;

    /**
     * Before performing any test
     */
    @Before
    public void definitionNodes(){
        nodeA = new DijkstraNode<>("A");
        nodeB = new DijkstraNode<>("B");
        nodeC = new DijkstraNode<>("C");
        nodeD = new DijkstraNode<>("D");
        nodeE = new DijkstraNode<>("E");
        nodeF = new DijkstraNode<>("F");
    }

    /**
     *
     */
    @Test
    public void testDijkstraImp(){
        nodeA.addTwoRoadAdjacent(nodeB, 10);
        nodeA.addTwoRoadAdjacent(nodeC, 15);

        nodeC.addTwoRoadAdjacent(nodeE, 13);
        nodeC.addTwoRoadAdjacent(nodeF, 10);

        nodeB.addTwoRoadAdjacent(nodeE, 14);
        nodeB.addTwoRoadAdjacent(nodeF, 8);

        DijkstraTree<String> tree = new DijkstraTree<>();
        tree.addAllNodes(nodeA, nodeB, nodeC, nodeE);

        // calculate shortest path from nodeA
        new Dijkstra<String>().calculateShortestPathFromSource(tree, nodeE);

        // shortest path from nodeA to nodeE
        Assert.assertEquals(nodeA.getDistance(), 24, .1);

        // calculate shortest path from nodeE
        new Dijkstra<String>().calculateShortestPathFromSource(tree, nodeF);

        // shortest path from nodeF to nodeE
        Assert.assertEquals(nodeE.getDistance(), 22, .1);

    }
}
