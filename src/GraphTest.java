import org.junit.jupiter.api.Test;

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
}