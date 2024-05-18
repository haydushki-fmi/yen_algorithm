import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.algorithms.Dijkstra;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDijkstra {
    @Test
    void testDijkstraCalculatesShortestPath() {
        this.initializeGraph();
        Dijkstra dijkstra = new Dijkstra(graph);

        LinkedList<Vertex> expectedPath = new LinkedList<>(List.of(nodes.get(0), nodes.get(3), nodes.get(2), nodes.get(5)));

        LinkedList<Vertex> path = dijkstra.getShortestPath(nodes.get(0), nodes.get(5), null);

        assertEquals(path, expectedPath);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }
    }

    @Test
    void testDijkstraCalculatesShortestPathWithExcludedEdge() {
        this.initializeGraph();
        Dijkstra dijkstra = new Dijkstra(graph);
        LinkedList<Vertex> expectedPath = new LinkedList<>(List.of(nodes.get(0), nodes.get(3), nodes.get(4), nodes.get(5)));

        LinkedList<Vertex> path = dijkstra.getShortestPath(nodes.get(0), nodes.get(5), edges.get(6));

        assertEquals(path, expectedPath);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }
    }

    private List<Vertex> nodes;
    private List<Edge> edges;
    private Graph graph;

    private void initializeGraph() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();

        nodes.add(new Vertex("C", "C"));
        nodes.add(new Vertex("D", "D"));
        nodes.add(new Vertex("F", "F"));
        nodes.add(new Vertex("E", "E"));
        nodes.add(new Vertex("G", "G"));
        nodes.add(new Vertex("H", "H"));

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

    private void addEdge(String laneId, int sourceLocNo, int destLocNo,
                         int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }
}
