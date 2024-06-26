package graph;

public class Edge {
    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final double weight;

    public Edge(String id, Vertex source, Vertex destination, double weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " -> " + destination + " (" + weight + ")";
    }

    public boolean compareBySourceAndDestination(Vertex vertex, Vertex destination) {
        return source.equals(vertex) && this.destination.equals(destination);
    }
}
