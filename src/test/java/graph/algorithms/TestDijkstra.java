package graph.algorithms;

import graph.Edge;
import graph.Graph;
import graph.Path;
import graph.Vertex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDijkstra {
    @Test
    void testDijkstraCalculatesShortestPath() {
        this.initializeGraph();
        Dijkstra dijkstra = new Dijkstra(graph);

        LinkedList<Vertex> expectedPath = new LinkedList<>(List.of(vertices.get(0), vertices.get(3), vertices.get(2), vertices.get(5)));

        Path path = dijkstra.getShortestPath(vertices.get(0), vertices.get(5), null);

        assertEquals(expectedPath, path.getVertices());
        assertEquals(5, path.getWeight());

        for (Vertex vertex : path.getVertices()) {
            System.out.println(vertex);
        }
    }

    @Test
    void testDijkstraCalculatesShortestPathWithExcludedEdge() {
        this.initializeGraph();
        Dijkstra dijkstra = new Dijkstra(graph);
        LinkedList<Vertex> expectedPath = new LinkedList<>(List.of(vertices.get(0), vertices.get(3), vertices.get(4), vertices.get(5)));

        Path path = dijkstra.getShortestPath(vertices.get(0), vertices.get(5), new HashSet<>(List.of(edges.get(6))));

        assertEquals(expectedPath, path.getVertices());
        assertEquals(7, path.getWeight());

        for (Vertex vertex : path.getVertices()) {
            System.out.println(vertex);
        }
    }

    private List<Vertex> vertices;
    private List<Edge> edges;
    private Graph graph;

    private void initializeGraph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();

        vertices.add(new Vertex("C", "C"));
        vertices.add(new Vertex("D", "D"));
        vertices.add(new Vertex("F", "F"));
        vertices.add(new Vertex("E", "E"));
        vertices.add(new Vertex("G", "G"));
        vertices.add(new Vertex("H", "H"));

        addEdge("CD", 0, 1, 3);
        addEdge("CE", 0, 3, 2);
        addEdge("DF", 1, 2, 4);
        addEdge("FG", 2, 4, 2);
        addEdge("FH", 2, 5, 1);
        addEdge("ED", 3, 1, 1);
        addEdge("EF", 3, 2, 2);
        addEdge("EG", 3, 4, 3);
        addEdge("GH", 4, 5, 2);
        graph = new Graph(edges);
    }

    private void addEdge(String edgeId, int sourceIndex, int destinationIndex, double weight) {
        Edge edge = new Edge(edgeId, vertices.get(sourceIndex), vertices.get(destinationIndex), weight);
        edges.add(edge);
    }
}
