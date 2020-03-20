import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

class GraphTest {

    @Test
    void dijkstra() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 24);
        graph.addEdge(0, 3, 20);
        graph.addEdge(2, 0, 3);
        graph.addEdge(3, 2, 12);

        assertArrayEquals(new int[]{0, 24, 3, 15}, graph.dijkstra(0));
    }

    @Test
    void floydWarshall() {
        return;

//        Graph graph = new Graph(4);
//        graph.addEdge(0, 1, 5);
//        graph.addEdge(0, 3, 10);
//        graph.addEdge(1, 2, 3);
//
//        graph.floydWarshall();
//
//        ArrayList<Graph.Node>[] edges = graph.getGraph();
//        int i = 0;
//        for (ArrayList<Graph.Node> node : edges) {
//            for (Graph.Node edge : node) {
//                System.out.println("Source: " + i + " Destination: " + edge.destination + " Cost: " + edge.cost);
//            }
//            i++;
//        }
    }

    @Test
    void topologicalSort() {
        Graph g = new Graph(6, false);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        assertArrayEquals(new int[]{5, 4, 2, 3, 1, 0}, g.topologicalSort());
    }
}