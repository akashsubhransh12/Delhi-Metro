import java.util.*;

public class MetroShortestPath {
    // Class to represent a station with a name and distance
    static class Station {
        String name;
        int distance;

        Station(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }

    // Function to find the shortest path using Dijkstra's algorithm
    public static Map<String, Integer> dijkstra(Map<String, List<Station>> graph, String startStation) {
        Map<String, Integer> minDistance = new HashMap<>();
        PriorityQueue<Station> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.distance));

        // Initialize distances
        for (String station : graph.keySet()) {
            minDistance.put(station, Integer.MAX_VALUE);
        }
        minDistance.put(startStation, 0);
        pq.add(new Station(startStation, 0));

        while (!pq.isEmpty()) {
            Station current = pq.poll();
            String currentStation = current.name;

            // Explore neighbors
            List<Station> neighbors = graph.get(currentStation);
            if (neighbors == null) continue; // Skip if no neighbors exist

            for (Station neighbor : neighbors) {
                int newDist = minDistance.get(currentStation) + neighbor.distance;
                if (newDist < minDistance.get(neighbor.name)) {
                    minDistance.put(neighbor.name, newDist);
                    pq.add(new Station(neighbor.name, newDist));
                }
            }
        }
        return minDistance;
    }

    // Function to calculate fare based on distance
    public static int calculateFare(int distance) {
        final int costPerKm = 6; // Example cost per km
        return costPerKm * distance; // Calculate total cost
    }

    // Function to estimate time based on distance (assuming 1 km per 3 minutes)
    public static int estimateTime(int distance) {
        return distance * 3; // Total time in minutes
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        // Create the graph (adjacency list) for the metro stations
        Map<String, List<Station>> metroGraph = new HashMap<>();
    
        // Adding stations and example connections with distances (in km)
        metroGraph.put("DILSHAD GARDEN", Arrays.asList(new Station("SHAHDARA", 2), new Station("SEELAMPUR", 3)));
        metroGraph.put("SHAHDARA", Arrays.asList(new Station("DILSHAD GARDEN", 2), new Station("ROHINI WEST", 5)));
        metroGraph.put("ROHINI WEST", Arrays.asList(new Station("SHAHDARA", 5), new Station("RITHALA", 4)));
        metroGraph.put("RITHALA", Arrays.asList(new Station("ROHINI WEST", 4), new Station("PITAMPURA", 3)));
        metroGraph.put("PITAMPURA", Arrays.asList(new Station("RITHALA", 3), new Station("SHASTRI NAGAR", 4)));
        metroGraph.put("SHASTRI NAGAR", Arrays.asList(new Station("PITAMPURA", 4), new Station("ROHINI EAST", 5)));
        metroGraph.put("ROHINI EAST", Arrays.asList(new Station("SHASTRI NAGAR", 5), new Station("TIS HAZARI", 6)));
        metroGraph.put("TIS HAZARI", Arrays.asList(new Station("2", 6), new Station("RAJIV CHOWK", 3)));
        metroGraph.put("RAJIV CHOWK", Arrays.asList(new Station("TIS HAZARI", 3), new Station("NEW DELHI (DMRC)", 1)));
        metroGraph.put("NEW DELHI (DMRC)", Arrays.asList(new Station("RAJIV CHOWK", 1), new Station("KASHMERE GATE", 2)));
    
        while (true) {
            System.out.println("\n--- Delhi Metro System ---");
            System.out.println("1. Start");
            System.out.println("2. Check Distance");
            System.out.println("3. Calculate Shortest Path Between Two Stations");
            System.out.println("4. Price for Shortest Path");
            System.out.println("5. Estimated Time for Shortest Path");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
    
            switch (choice) {
                case 1:
                    System.out.println("Welcome to the Delhi Metro System!");
                    break;
    
                case 2:
                    System.out.print("Enter start station: ");
                    String startStation = scanner.nextLine();
                    System.out.print("Enter end station: ");
                    String endStation = scanner.nextLine();
    
                    // Get the distance using Dijkstra's algorithm
                    Map<String, Integer> shortestPaths = dijkstra(metroGraph, startStation);
                    Integer distance = shortestPaths.get(endStation);
    
                    if (distance != null && distance != Integer.MAX_VALUE) {
                        System.out.println("Distance from " + startStation + " to " + endStation + " is: " + distance + " km");
                    } else {
                        System.out.println("No path found between " + startStation + " and " + endStation);
                    }
                    break;
    
                case 3:
                    System.out.print("Enter start station: ");
                    String start = scanner.nextLine();
                    System.out.print("Enter end station: ");
                    String end = scanner.nextLine();
    
                    // Get the shortest paths
                    Map<String, Integer> shortestPathResults = dijkstra(metroGraph, start);
                    Integer shortestDistance = shortestPathResults.get(end);
    
                    if (shortestDistance != null && shortestDistance != Integer.MAX_VALUE) {
                        System.out.println("Shortest path from " + start + " to " + end + " is: " + shortestDistance + " km");
                    } else {
                        System.out.println("No path found between " + start + " and " + end);
                    }
                    break;
    
                case 4:
                    System.out.print("Enter start station: ");
                    String fareStart = scanner.nextLine();
                    System.out.print("Enter end station: ");
                    String fareEnd = scanner.nextLine();
    
                    // Get the shortest paths
                    Map<String, Integer> fareResults = dijkstra(metroGraph, fareStart);
                    Integer fareDistance = fareResults.get(fareEnd);
    
                    if (fareDistance != null && fareDistance != Integer.MAX_VALUE) {
                        int fare = calculateFare(fareDistance);
                        System.out.println("Price for the shortest path from " + fareStart + " to " + fareEnd + " is: ₹" + fare);
                    } else {
                        System.out.println("No path found between " + fareStart + " and " + fareEnd);
                    }
                    break;
    
                case 5:
                    System.out.print("Enter start station: ");
                    String timeStart = scanner.nextLine();
                    System.out.print("Enter end station: ");
                    String timeEnd = scanner.nextLine();
    
                    // Get the shortest paths
                    Map<String, Integer> timeResults = dijkstra(metroGraph, timeStart);
                    Integer timeDistance = timeResults.get(timeEnd);
    
                    if (timeDistance != null && timeDistance != Integer.MAX_VALUE) {
                        int estimatedTime = estimateTime(timeDistance);
                        System.out.println("Estimated time for the shortest path from " + timeStart + " to " + timeEnd + " is: " + estimatedTime + " minutes");
                    } else {
                        System.out.println("No path found between " + timeStart + " and " + timeEnd);
                    }
                    break;
    
                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
    
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
