package graph.algorithms;

import graph.Edge;
import graph.Graph;
import graph.Path;
import graph.Vertex;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Yen {
    private final Graph graph;

    public Yen(Graph graph) {
        this.graph = graph;
    }

    /**
     * Calculates K-shortest paths from a given source vertex to a target vertex using Yen's algorithm.
     *
     * @param source Source vertex.
     * @param target Target vertex.
     * @param k      How many shortest paths to be calculated.
     * @return A list of k-shortest paths.
     */
    public List<Path> calculateKShortestPaths(Vertex source, Vertex target, int k) {
        LinkedList<Path> paths = new LinkedList<>();
        Dijkstra dijkstra = new Dijkstra(graph);

        paths.add(dijkstra.getShortestPath(source, target, null));
        Set<Edge> exclusionCandidates = new HashSet<>(paths.getFirst().getEdges(graph));

        for (int i = 1; i < k; i++) {
            Path rootPath = paths.get(i - 1);
            Path minPath = null;
            for (int j = 0; j < rootPath.getVertices().size() - 1; j++) {
                Vertex root = paths.get(i - 1).getVertices().get(j);
                Vertex nextAfterRoot = paths.get(i - 1).getVertices().get(j + 1);
                Path untilRootPath = dijkstra.getShortestPath(source, root, null);

                // Get the edges which have been previously used in a shortest path and exclude the ones going out from the root vertex
                Set<Edge> excluded = exclusionCandidates.stream().filter(edge -> edge.getSource().equals(root)).collect(Collectors.toSet());

                // Calculate new candidate for min path
                Path newPath = untilRootPath.concat(dijkstra.getShortestPath(root, target, excluded), true);
                if (!newPath.getVertices().contains(target)) {
                    continue;
                }
                if (minPath == null) {
                    minPath = newPath;
                } else if (minPath.getWeight() > newPath.getWeight()) {
                    minPath = newPath;
                }

            }

            if (minPath != null) {
                paths.add(minPath);
                exclusionCandidates.addAll(minPath.getEdges(graph));
            }
        }

        return paths;
    }
}
