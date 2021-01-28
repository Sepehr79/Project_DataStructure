package algorithm.dijkstra;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDijkstraNode {

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
     * Make sure that there is no any exceptions
     */
    @Test
    public void testDefinitionWithoutException(){

        nodeA.addTwoRoadAdjacent(nodeB, 10);
        nodeA.addTwoRoadAdjacent(nodeC, 15);

        nodeB.addTwoRoadAdjacent(nodeD, 12);
        nodeB.addTwoRoadAdjacent(nodeF, 15);

        nodeC.addTwoRoadAdjacent(nodeE, 10);

        nodeD.addTwoRoadAdjacent(nodeE, 2);
        nodeD.addTwoRoadAdjacent(nodeF, 1);

        nodeF.addTwoRoadAdjacent(nodeE, 5);

        DijkstraTree<String> graph = new DijkstraTree<>();

        graph.addAllNodes(nodeA, nodeB, nodeC, nodeD, nodeE);
        graph.addNode(nodeF);

        // size of graph
        Assert.assertEquals(graph.getNodes().size(), 6);

        // size of neighbor nodes of nodeB
        Assert.assertEquals(nodeB.getAdjacentNodes().size(), 3);

    }

    @Test
    public void testEquationBetweenNodes(){
        // nodes with same value
        nodeA = new DijkstraNode<>("I am similar");
        nodeB = new DijkstraNode<>("I am similar");

        Assert.assertEquals(nodeA, nodeB);
    }

    @Test
    public void testOneRoadAdjacent(){
        nodeA.addOneRoadAdjacent(nodeB, 25);

        Assert.assertEquals(nodeA.getAdjacentNodes().size(), 1);
        Assert.assertEquals(nodeB.getAdjacentNodes().size(), 0);
    }

    @Test
    public void testRemoveAdjacentNode(){
        nodeA.addOneRoadAdjacent(nodeB, 15);
        nodeA.addTwoRoadAdjacent(nodeC, 20);

        nodeA.removeAdjacentNode(new DijkstraNode<>("B"));

        Assert.assertEquals(nodeA.getAdjacentNodes().size(), 1);
        Assert.assertEquals(nodeA.getAdjacentNodes().get(new DijkstraNode<>("C")), 20, .1);
    }

}
