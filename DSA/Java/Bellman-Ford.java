import java.util.Arrays;

class BellmanFordAlgorithm {
    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static void bellmanFord(Edge[] edges, int vertices, int startVertex) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        // Relax all edges |V| - 1 times
        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {
                if (distances[edge.source] != Integer.MAX_VALUE && 
                    distances[edge.source] + edge.weight < distances[edge.destination]) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edges) {
            if (distances[edge.source] != Integer.MAX_VALUE &&
                distances[edge.source] + edge.weight < distances[edge.destination]) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // Print distances
        printSolution(distances, startVertex);
    }

    static void printSolution(int[] distances, int startVertex) {
        System.out.println("Vertex Distance from Source " + startVertex);
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Vertex " + i + " : " + (distances[i] == Integer.MAX_VALUE ? "∞" : distances[i]));
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        Edge[] edges = {
            new Edge(0, 1, -1),
            new Edge(0, 2, 4),
            new Edge(1, 2, 3),
            new Edge(1, 3, 2),
            new Edge(1, 4, 2),
            new Edge(3, 2, 5),
            new Edge(3, 1, 1),
            new Edge(4, 3, -3)
        };

        bellmanFord(edges, vertices, 0);
    }
}
