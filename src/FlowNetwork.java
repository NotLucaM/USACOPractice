import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class FlowNetwork {

    private ArrayList<FlowEdge>[] network;
    private int numberOfVertices;
    private int numberOfEdges;

    public FlowNetwork(int vertices) {
        network = new ArrayList[vertices];
        this.numberOfVertices = vertices;
        this.numberOfEdges = 0;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public void addEdge(int start, int end, int capacity) {
        network[start].add(new FlowEdge(start, end, capacity));
    }

    public void addEdge(FlowEdge edge) {
        network[edge.source].add(edge);
    }

    public Iterable<FlowEdge> adjacentTo(int vertex) {
        return network[vertex];
    }

    public Iterable<FlowEdge> getEdges() {
        ArrayList<FlowEdge> edges = new ArrayList<>();
        for (ArrayList<FlowEdge> vertex : network) {
            edges.addAll(vertex);
        }
        return edges;
    }

    int maxFlow(int source, int target) {
        // TODO: implement
        throw new NotImplementedException();
    }

    public static class FlowEdge {

        private int source;
        private int target;
        private int capacity;
        private int flow;

        public FlowEdge(int source, int target, int capacity) {
            this.source = source;
            this.target = target;
            this.capacity = capacity;
            this.flow = 0;
        }

        public int getSource() {
            return source;
        }

        public int getTarget() {
            return target;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getFlow() {
            return flow;
        }

        public int residualCapacity(int vertex) {
            if (vertex == source) {
                return flow;
            } else if (vertex == target) {
                return capacity - flow;
            } else {
                throw new IllegalArgumentException("Vertex must either be source or target");
            }
        }

        public void addResidualFlow(int vertex, int delta) {
            if (vertex == source) {
                flow -= delta;
            } else if (vertex == target) {
                flow += delta;
            } else {
                throw new IllegalArgumentException("Vertex must either be source or target");
            }
        }

        public String toString() {
            return String.format("%d->%d %d %d", source, target, capacity, flow);
        }
    }
}
