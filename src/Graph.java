import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class Graph {

    private ArrayList<Node>[] graph;
    private int vertices;
    private int edges;
    private boolean bidirectional;

    public Graph(int vertices) {
        this(vertices, true);
    }

    public Graph(int vertices, boolean bidirectional) {
        this.graph = new ArrayList[vertices];
        this.vertices = vertices;
        this.edges = 0;
        this.bidirectional = bidirectional;

        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int destination) {
        addEdge(source, destination, 1);
    }

    public void addEdge(int source, int destination, int cost) {
        graph[source].add(new Node(destination, cost));
        if (bidirectional) {
            graph[destination].add(new Node(source, cost));
        }
        edges++;
    }

    public int edges() {
        return edges;
    }

    public int dijkstra(int source, int destination) {
        return dijkstra(source)[destination];
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

    public void floydWarshall() { // TODO, implement floydWarshall
        throw new NotImplementedException();
//        for (int k = 0; k < vertices; k++) {
//            for (int i = 0; i < vertices; i++) {
//                for (int j = 0; j < vertices; j++) {
//                    if (graph[i].get(k).cost + graph[k].get(j).cost < graph[i].get(j).cost) {
//                        graph[i].get(j).cost = graph[i].get(k).cost + graph[k].get(j).cost;
//                    }
//                }
//            }
//        }
    }

    private void topologicalSort(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        Node i;

        for (Node node : graph[v]) {
            i = node;
            if (!visited[i.destination]) {
                topologicalSort(i.destination, visited, stack);
            }
        }

        stack.push(v);
    }

    public int[] topologicalSort() {
        Stack<Integer> stack = new Stack<>();

        boolean[] visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSort(i, visited, stack);
            }
        }

        int[] sorted = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            sorted[i] = stack.pop();
        }
        return sorted;
    }

    public ArrayList<Node>[] getGraph() {
        return graph;
    }

    public static class Node implements Comparable<Node> {
        int destination;
        int cost;

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
