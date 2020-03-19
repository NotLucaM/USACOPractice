import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class Graph {

    private ArrayList<Node>[] graph;
    private int vertices;
    private int edges;

    public Graph(int vertices) {
        this.graph = new ArrayList[vertices];
        this.vertices = vertices;
        this.edges = 0;

        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int destination, int cost) {
        graph[source].add(new Node(destination, cost));
        graph[destination].add(new Node(source, cost));
        edges++;
    }

    public int edges() {
        return edges;
    }

    public int[] dijkstra(int source) {
        int[] distances = new int[vertices];
        Set<Integer> settled = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(distances, Integer.MAX_VALUE);
        pq.add(new Node(source, 0));
        distances[source] = 0;

        while (settled.size() != vertices) {
            if (pq.isEmpty()) {
                return distances;
            }

            int u = pq.remove().destination;
            settled.add(u);

            for (int i = 0; i < graph[u].size(); i++) {
                Node v = graph[u].get(i);

                if (!settled.contains(v.destination)) {
                    int edgeDistance = v.cost;
                    int newDistance = distances[u] + edgeDistance;

                    if (newDistance < distances[v.destination]) {
                        distances[v.destination] = newDistance;
                    }

                    pq.add(new Node(v.destination, distances[v.destination]));
                }
            }
        }

        return distances;
    }

    private static class Node implements Comparable<Node> {
        private int destination;
        private int cost;

        public Node(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node that) {
            return this.cost - that.cost;
        }
    }
}
