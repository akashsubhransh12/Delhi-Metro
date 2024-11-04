import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        int vertices = scanner.nextInt();
        Graph graph = new Graph(vertices);

        System.out.println("Enter the number of edges:");
        int edges = scanner.nextInt();

        for (int i = 0; i < edges; i++) {
            System.out.println("Enter the source, destination, distance, and cost (source destination distance cost):");
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int distance = scanner.nextInt();
            int cost = scanner.nextInt();
            graph.addEdge(source, destination, distance, cost);
        }

        System.out.println("Enter the starting vertex:");
        int startVertex = scanner.nextInt();
        System.out.println("Enter the ending vertex:");
        int endVertex = scanner.nextInt();

        graph.dijkstra(startVertex, endVertex);
        scanner.close();
    }
}
