package graph.algorithms;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.*;

public class Dijkstra {
    private final Graph graph;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unsettledNodes;
    private Map<Vertex, Vertex> parent;
    private Map<Vertex, Double> distance;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    /**
     * Calculates the shortest path from source vertex to target vertex.
     *
     * @param source Source vertex.
     * @param target Target vertex.
     * @return A LinkedList of vertices for the shortest path.
     */
    public LinkedList<Vertex> getShortestPath(Vertex source, Vertex target, Edge excluded) {
        settledNodes = new HashSet<Vertex>();
        unsettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Double>();
        parent = new HashMap<Vertex, Vertex>();

        distance.put(source, 0.0);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Vertex node = Collections.min(unsettledNodes);
            settledNodes.add(node);
            unsettledNodes.remove(node);
            findMinimalDistances(node, excluded);
        }
        return this.calculatePath(target);
    }

    /**
     * Returns the shortest path for a given vertex.
     *
     * @param target Target vertex for the shortest path.
     * @return A linked list of vertices for the shortest path. Null if path doesn't exist.
     */
    public LinkedList<Vertex> calculatePath(Vertex target) {
        if (parent.get(target) == null) {
            return null;
        }
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex current = target;
        path.add(current);
        while (parent.get(current) != null) {
            current = parent.get(current);
            path.add(current);
        }
        Collections.reverse(path);
        return path;
    }

    private void findMinimalDistances(Vertex node, Edge excluded) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target, excluded)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target, excluded));
                parent.put(target, node);
                unsettledNodes.add(target);
            }
        }

    }

    private double getDistance(Vertex node, Vertex target, Edge excluded) {
        if (excluded != null && node.equals(excluded.getSource()) && target.equals(excluded.getDestination())) {
            return Double.MAX_VALUE;
        }

        for (Edge edge : graph.getEdges()) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Error: Edge not found");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : graph.getEdges()) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private double getShortestDistance(Vertex destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

}
