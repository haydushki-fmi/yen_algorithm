package server;

import graph.Edge;
import graph.Graph;
import graph.Path;
import graph.Vertex;
import graph.algorithms.Yen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            server = new ServerSocket(PORT);
            server.setReuseAddress(true);

            while (true) {
                Socket client = server.accept();

                System.out.println("New client connected:\t" + client.getInetAddress().getHostAddress());

                // Create a new thread for this client
                ClientHandler clientSock
                        = new ClientHandler(client);
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final List<Vertex> vertices;
        private final List<Edge> edges;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            vertices = new ArrayList<>();
            edges = new ArrayList<>();
        }

        public void run() {
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                out = new PrintWriter(
                        clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));

                String line;
                boolean firstLine = true;
                int numberOfEdges = 0;
                String startVertex = "";
                String targetVertex = "";
                int k = 0;

                while ((line = in.readLine()) != null) {
                    if (firstLine) {
                        String[] splits = line.split(" ");

                        numberOfEdges = Integer.parseInt(splits[0]);

                        startVertex = splits[1];
                        targetVertex = splits[2];
                        addVertex(startVertex);
                        addVertex(targetVertex);

                        k = Integer.parseInt(splits[3]);

                        firstLine = false;
                        continue;
                    }

                    String[] splits = line.split(" ");
                    addVertex(splits[0]);
                    addVertex(splits[1]);
                    addEdge(splits[0] + " -> " + splits[1], splits[0], splits[1], Double.parseDouble(splits[2]));
                    numberOfEdges--;

                    if (numberOfEdges == 0) {
                        break;
                    }
                }

                Graph graph = new Graph(edges);
                Yen yen = new Yen(graph);
//                out.println("Graph: " + graph.toString());
                List<Path> result = yen.calculateKShortestPaths(getVertex(startVertex), getVertex(targetVertex), k);
                out.println(result.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void addVertex(String id) {
            vertices.add(new Vertex(id, id));
        }

        private Vertex getVertex(String id) {
            return vertices.stream().filter(v -> v.getId().equals(id)).findFirst().orElse(null);
        }

        private void addEdge(String edgeId, String source, String destination,
                             double weight) {
            Edge lane = new Edge(edgeId, getVertex(source), getVertex(destination), weight);
            edges.add(lane);
        }
    }
}
