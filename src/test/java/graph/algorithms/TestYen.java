package graph.algorithms;

import graph.Edge;
import graph.Graph;
import graph.Path;
import graph.Vertex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestYen {

    @Test
    void calculateKShortestPaths() {
        this.initializeGraph();
        Yen yen = new Yen(graph);
        LinkedList<Vertex> path1 = new LinkedList<>(List.of(vertices.get(0), vertices.get(3), vertices.get(2), vertices.get(5)));
        LinkedList<Vertex> path2 = new LinkedList<>(List.of(vertices.get(0), vertices.get(3), vertices.get(4), vertices.get(5)));
        LinkedList<Vertex> path3 = new LinkedList<>(List.of(vertices.get(0), vertices.get(1), vertices.get(2), vertices.get(5)));
        List<Path> expected = List.of(new Path(path1, 5), new Path(path2, 7), new Path(path3, 8));

        List<Path> result = yen.calculateKShortestPaths(vertices.get(0), vertices.get(5), 3);

        assertEquals(expected, result);
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