package algorithm.dijkstra;

import exceptions.NodeNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDijkstraTree {

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

    @Test
    public void testRemoveNodeFromTree(){

        nodeA.addTwoRoadAdjacent(nodeB, 10);
        nodeA.addTwoRoadAdjacent(nodeC, 15);

        nodeB.addTwoRoadAdjacent(nodeD, 12);
        nodeB.addTwoRoadAdjacent(nodeF, 15);

        nodeC.addTwoRoadAdjacent(nodeE, 10);

        nodeD.addTwoRoadAdjacent(nodeE, 2);
        nodeD.addTwoRoadAdjacent(nodeF, 1);

        nodeF.addTwoRoadAdjacent(nodeE, 5);

        DijkstraTree<String> tree = new DijkstraTree<>();

        tree.addAllNodes(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF);

        // before removing
        Assert.assertEquals(tree.getNodes().size(), 6);
        Assert.assertEquals(nodeA.getAdjacentNodes().size(), 2);

        tree.removeNode(new DijkstraNode<>("B"));// removes node B from tree

        // after removing from tree
        Assert.assertEquals(tree.getNodes().size(), 5);

        // after removing from nodes
        Assert.assertEquals(nodeA.getAdjacentNodes().size(), 1);
    }

    @Test public void testGetNode(){
        nodeA.addTwoRoadAdjacent(nodeB, 10);
        nodeA.addTwoRoadAdjacent(nodeC, 15);

        nodeB.addTwoRoadAdjacent(nodeD, 12);
        nodeB.addTwoRoadAdjacent(nodeF, 15);

        nodeC.addTwoRoadAdjacent(nodeE, 10);

        nodeD.addTwoRoadAdjacent(nodeE, 2);
        nodeD.addTwoRoadAdjacent(nodeF, 1);

        nodeF.addTwoRoadAdjacent(nodeE, 5);

        DijkstraTree<String> tree = new DijkstraTree<>();

        tree.addAllNodes(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF);

        Assert.assertEquals(tree.getNode(new DijkstraNode<>("B")), nodeB);

        try{
            tree.getNode(new DijkstraNode<>("not in tree"));
            Assert.fail();
        }catch (Exception exception){
            Assert.assertTrue(exception instanceof NodeNotFoundException);
        }
    }

}
