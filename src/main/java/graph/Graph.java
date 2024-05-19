package graph;

import java.util.List;

public class Graph {
    private final List<Edge> edges;

    public List<Edge> getEdges() {
        return edges;
    }

    public Graph(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return this.edges.toString();
    }
}
