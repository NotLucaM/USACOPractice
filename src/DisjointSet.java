public class DisjointSet {

    private int vertices;
    private int edges;
    private Edge[] edge;

    DisjointSet(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        edge = new Edge[this.edges];
        for (int i = 0; i < this.edges; i++) {
            edge[i] = new Edge();
        }
    }

    int find(subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    void union(subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[yroot].rank < subsets[xroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[xroot].parent = yroot;
            subsets[yroot].rank++;
        }
    }

    public void addEdge(int source, int destination) {
        edge[source].src = source;
        edge[destination].dest = destination;
    }

    private static class Edge {
        int src, dest;
    }

    private static class subset {
        int parent;
        int rank;
    }

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet(5, 4);
        set.addEdge(0, 1);
        set.addEdge(1, 2);
        set.addEdge(2, 0);
        set.addEdge(3, 4);

//        System.out.println(set.find());
    }
}
