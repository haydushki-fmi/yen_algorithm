package graph;

public class Vertex implements Comparable<Vertex> {
    private final String label;
    private final String id;

    public String getLabel() {
        return label;
    }

    public String getId() {
        return id;
    }

    public Vertex(String label, String id) {
        this.label = label;
        this.id = id;
    }

    @Override
    public int compareTo(Vertex vertex) {
        return id.compareTo(vertex.id);
    }

    @Override
    public String toString() {
        return label;
    }
}
