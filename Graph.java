import java.util.*;

class Edge {
    int destination;
    int distance;
    int cost;

    Edge(int destination, int distance, int cost) {
        this.destination = destination;
        this.distance = distance;
        this.cost = cost;
    }
}

public class Graph {
    private final int vertices;
    private final List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int distance, int cost) {
        adjacencyList.get(source).add(new Edge(destination, distance, cost));
        // If undirected graph, add the reverse edge
        adjacencyList.get(destination).add(new Edge(source, distance, cost));
    }

    public void dijkstra(int startVertex, int endVertex) {
        int[] minDistance = new int[vertices];
        int[] minCost = new int[vertices];
        boolean[] visited = new boolean[vertices];
        Arrays.fill(minDistance, Integer.MAX_VALUE);
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minDistance[startVertex] = 0;
        minCost[startVertex] = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(v -> minDistance[v]));
        priorityQueue.add(startVertex);

        while (!priorityQueue.isEmpty()) {
            int currentVertex = priorityQueue.poll();
            visited[currentVertex] = true;

            for (Edge edge : adjacencyList.get(currentVertex)) {
                if (visited[edge.destination]) continue;

                int newDistance = minDistance[currentVertex] + edge.distance;
                int newCost = minCost[currentVertex] + edge.cost;

                if (newDistance < minDistance[edge.destination] || (newDistance == minDistance[edge.destination] && newCost < minCost[edge.destination])) {
                    minDistance[edge.destination] = newDistance;
                    minCost[edge.destination] = newCost;
                    priorityQueue.add(edge.destination);
                }
            }
        }

        System.out.println("Minimum distance from " + startVertex + " to " + endVertex + " is " + minDistance[endVertex] + " km with a cost of " + minCost[endVertex]);
    }
}
