package practice;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.min;
import static java.lang.Math.max;

public class dining {

    private static int cowNum;
    private static int edgeNum;
    private static int foodNum;
    private static ArrayList<Node>[] graph;
    private static ArrayList<Node> food;

    public static int[] dijkstra(int source) {
        int[] distances = new int[cowNum];
        Set<Integer> settled = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(distances, Integer.MAX_VALUE);
        pq.add(new Node(source, 0));
        distances[source] = 0;

        while (settled.size() != cowNum) {
            if (pq.isEmpty()) {
                return distances;
            }

            int shortestNode = pq.remove().destination;
            settled.add(shortestNode);

            for (int i = 0; i < graph[shortestNode].size(); i++) {
                Node adjacentNode = graph[shortestNode].get(i);

                if (!settled.contains(adjacentNode.destination)) {
                    int edgeDistance = adjacentNode.cost;
                    int newDistance = distances[shortestNode] + edgeDistance;

                    if (newDistance < distances[adjacentNode.destination]) {
                        distances[adjacentNode.destination] = newDistance;
                        pq.add(new Node(adjacentNode.destination, distances[adjacentNode.destination]));
                    }
                }
            }
        }

        return distances;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("dining.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        cowNum = parseInt(st.nextToken());
        edgeNum = parseInt(st.nextToken());
        foodNum = parseInt(st.nextToken());

        graph = new ArrayList[cowNum];
        food = new ArrayList<>();
        for (int i = 0; i < cowNum; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(in.readLine());
            int from = parseInt(st.nextToken()) - 1;
            int to = parseInt(st.nextToken()) - 1;
            int cost = parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        for (int i = 0; i < foodNum; i++) {
            st = new StringTokenizer(in.readLine());
            int location = parseInt(st.nextToken()) - 1;
            int cost = parseInt(st.nextToken());

            food.add(new Node(location, cost));
        }

        int[] distances = dijkstra(cowNum - 1);
        graph[cowNum - 1].clear();
        for (Node hay : food) {
            graph[hay.destination].add(new Node(cowNum - 1, distances[hay.destination] - hay.cost));
        }
        int[] finalDistances = dijkstra(cowNum - 1);

        for (int i = 0; i < cowNum - 1; i++) {
            if (distances[i] == finalDistances[i]) {
                out.println("0");
            } else {
                out.println("1");
            }
        }
        out.close();
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
