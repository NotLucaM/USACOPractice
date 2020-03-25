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

    public int[] dijkstra(int source) {
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

        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(in.readLine());
            int from = parseInt(st.nextToken()) - 1;
            int to = parseInt(st.nextToken()) - 1;
            int cost = parseInt(st.nextToken()) - 1;

            graph[from].add(new Node(to, cost));
        }

        for (int i = 0; i < foodNum; i++) {
            st = new StringTokenizer(in.readLine());
            int location = parseInt(st.nextToken()) - 1;
            int cost = parseInt(st.nextToken()) - 1;

            food.add(new Node(location, cost));
        }


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
