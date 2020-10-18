import java.util.*;

public class treecoloring {

    private static class Node {
        private Node parent;
        private List<Edge> children;
        private int location;

        public int DFS(boolean red) {
            int costs = 0;
            return -1;
        }

        private int DFS(boolean red, int cost) {
            if (children.isEmpty()) {
                return red ? redCost : blueCost;
            }

            for (Edge edge : children) {
                boolean nextRed = red ? true : false;
            }

            return -1;
        }

        public Node(int location) {
            this.location = location;
            children = new ArrayList<>();
        }

        public Node getRoot() {
            if (parent == null) {
                return this;
            }
            return parent.getRoot();
        }

        public void addChild(Node child, int cost) {
            children.add(new Edge(this, child, cost));
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        private static class Edge {
            private Node source;
            private Node destination;
            private int cost;

            public Edge(Node source, Node destination, int cost) {
                this.source = source;
                this.destination = destination;
                this.cost = cost;
            }
        }
    }

    private static int numberOfNodes;
    private static int redCost;
    private static int blueCost;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        numberOfNodes = in.nextInt();
        redCost = in.nextInt();
        blueCost = in.nextInt();

        Node[] nodes = new Node[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < numberOfNodes - 1; i++) {
            int source = in.nextInt() - 1;
            int destination = in.nextInt() - 1;
            int cost = in.nextInt();

            nodes[destination].setParent(nodes[source]);
            nodes[source].addChild(nodes[destination], cost);
        }

        Node root = nodes[0].getRoot();

    }
}
