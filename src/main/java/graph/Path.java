package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a path in the graph: A list of vertices along with weight.
 */
public class Path {
    private final LinkedList<Vertex> vertices;
    private final double weight;

    public LinkedList<Vertex> getVertices() {
        return vertices;
    }

    public double getWeight() {
        return weight;
    }

    public Path(LinkedList<Vertex> path, double weight) {
        this.vertices = path;
        this.weight = weight;
    }

    /**
     * Concatenates two paths into one.
     *
     * @param other       Path to concatenate with.
     * @param removeFirst Boolean to indicate if the first node from the second path should be excluded.
     * @return The path made from the two paths.
     */
    public Path concat(Path other, boolean removeFirst) {
        if (this.vertices == null) {
            return other;
        }

        if (other.vertices == null) {
            return this;
        }

        LinkedList<Vertex> newPath = new LinkedList<>(this.vertices);
        if (removeFirst) {
            for (int i = 1; i < other.vertices.size(); i++) {
                newPath.add(other.vertices.get(i));
            }
        } else {
            newPath.addAll(other.vertices);
        }

        return new Path(newPath, this.getWeight() + other.getWeight());
    }

    public List<Edge> getEdges(Graph graph) {
        LinkedList<Edge> edges = new LinkedList<>();
        for (int i = 0; i < this.vertices.size() - 1; i++) {
            int finalI = i;
            edges.add(graph.getEdges().stream().filter(edge -> edge.compareBySourceAndDestination(this.vertices.get(finalI), this.vertices.get(finalI + 1))).toList().getFirst());
        }

        return edges;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Path) {
            return this.vertices.equals(((Path) obj).vertices);
        } else return false;
    }

    @Override
    public String toString() {
        if (this.vertices == null) {
            return "EMPTY PATH";
        }
        return vertices.toString();
    }
}

