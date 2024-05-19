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

class YenTest {

    @Test
    void calculateKShortestPaths() {
        this.initializeGraph();
        Yen yen = new Yen(graph);
        LinkedList<Vertex> path1 = new LinkedList<>(List.of(nodes.get(0), nodes.get(3), nodes.get(2), nodes.get(5)));
        LinkedList<Vertex> path2 = new LinkedList<>(List.of(nodes.get(0), nodes.get(3), nodes.get(4), nodes.get(5)));
        LinkedList<Vertex> path3 = new LinkedList<>(List.of(nodes.get(0), nodes.get(1), nodes.get(2), nodes.get(5)));
        List<Path> expected = List.of(new Path(path1, 5), new Path(path2, 7), new Path(path3, 8));

        List<Path> result = yen.calculateKShortestPaths(nodes.get(0), nodes.get(5), 3);

        assertEquals(expected, result);
    }

    private List<Vertex> nodes;
    private List<Edge> edges;
    private Graph graph;

    private void initializeGraph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

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