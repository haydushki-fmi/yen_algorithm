package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;
            sc.useDelimiter("\n");

            line = readFromUser(sc, "Enter the number of edges, start, target vertex and K (separated by space):\t");
            sendLine(out, line);
            String[] splits = line.split(" ");
            int numberOfEdges = Integer.parseInt(splits[0]);

            System.out.println("Edge format: <from> <to> <weight>.");

            for (int i = 0; i < numberOfEdges; i++) {
                line = readFromUser(sc, "Enter edge " + i + ":\t");
                sendLine(out, line);
            }

            sc.close();

            System.out.println("Result from server: " + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFromUser(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    private static void sendLine(PrintWriter out, String line) {
        out.println(line);
        out.flush();
    }
}
