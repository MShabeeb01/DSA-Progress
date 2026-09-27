import java.util.ArrayList;

public class BellmanFordAlgorithm {

    // Representation of a Directed Weighted Edge
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    // Graph Construction matching the slide diagram[cite: 19]
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        /*
           0 --(2)--> 1 <---(-1)--- 4
           |          |             ^
          (4)        (-4)          (4)
           v          v             |
           +--------> 2 ----(2)---> 3[cite: 19]
        */
        graph[0].add(new Edge(0, 1, 2));[cite: 19]
        graph[0].add(new Edge(0, 2, 4));[cite: 19]
        graph[1].add(new Edge(1, 2, -4));[cite: 19]
        graph[2].add(new Edge(2, 3, 2));[cite: 19]
        graph[3].add(new Edge(3, 4, 4));[cite: 19]
        graph[4].add(new Edge(4, 1, -1));[cite: 19]
    }

    // ==========================================================
    // Bellman-Ford Algorithm (Dynamic Programming) -> O(V * E)[cite: 19]
    // Computes shortest paths from source to all vertices (negative edges supported)[cite: 19]
    // ==========================================================
    public static void bellmanFord(ArrayList<Edge>[] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];

        // Step 1: Initialize distances from source
        for (int i = 0; i < V; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        // Step 2: Relax all edges (V - 1) times[cite: 19]
        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (int k = 0; k < graph[u].size(); k++) {
                    Edge e = graph[u].get(k);
                    int v = e.dest;
                    int wt = e.wt;

                    // Relaxation step: dist[u] != Integer.MAX_VALUE avoids overflow
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }

        // Step 3: Check for Negative Weight Cycles (Optional 1 extra iteration)
        for (int u = 0; u < V; u++) {
            for (int k = 0; k < graph[u].size(); k++) {
                Edge e = graph[u].get(k);
                int v = e.dest;
                int wt = e.wt;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                    System.out.println("Warning: Graph contains a Negative Weight Cycle!");
                    return;
                }
            }
        }

        // Print final shortest distances
        System.out.println("Shortest distances from source node (" + src + "):");
        for (int i = 0; i < V; i++) {
            System.out.println("Node " + i + " : " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5; // Vertices: 0, 1, 2, 3, 4[cite: 19]
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);

        int src = 0;
        bellmanFord(graph, src);
        /*
          Execution Trace from Source 0:
          Node 0 : 0
          Node 1 : 2   (0 -> 1)
          Node 2 : -2  (0 -> 1 -> 2: 2 + (-4) = -2, shorter than direct edge 0 -> 2 with wt 4)
          Node 3 : 0   (0 -> 1 -> 2 -> 3: -2 + 2 = 0)
          Node 4 : 4   (0 -> 1 -> 2 -> 3 -> 4: 0 + 4 = 4)
        */
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 43 — Bellman-Ford Algorithm (Dynamic Programming)[cite: 19]

Core Purpose:
- Finds the **shortest paths from a single source to all vertices** in a weighted graph[cite: 19].
- Crucial advantage over Dijkstra: It successfully handles graphs with **negative weight edges**[cite: 19].
- Algorithmic Strategy: Dynamic Programming (DP) based edge relaxation[cite: 19].

Why Repeat Relaxation Exactly (V - 1) Times:
- In any simple path without cycles in a graph with $V$ vertices, there can be at most $V - 1$ edges.
- Each outer relaxation step guarantees that shortest paths with up to $i$ edges are correctly finalized.
- Therefore, repeating edge relaxation $V - 1$ times guarantees that all shortest paths are completely discovered across the entire graph.

Negative Weight Cycle Detection:
- If we perform a $V$-th relaxation iteration and any distance still reduces (`dist[u] + wt < dist[v]`), the graph contains a **negative weight cycle**.
- A negative weight cycle means total distance can decrease infinitely, making a finite shortest path undefined.

-------------------------------------------------

Slide Graph Structure Visual[cite: 19]

Vertices: {0, 1, 2, 3, 4}[cite: 19]

        (0) ---- 2 ----> (1) <--- -1 --- (4)
         |                |               ^
         4               -4               4
         |                |               |
         v                v               |
         +-------------> (2) ---- 2 ----> (3)[cite: 19]

Checking Negative Cycle for Loop (1 -> 2 -> 3 -> 4 -> 1):
  Weight sum = (-4) + 2 + 4 + (-1) = 1 (Positive sum >= 0)
  Because the cycle sum is positive, the algorithm converges cleanly without an infinite negative loop.

-------------------------------------------------

Dijkstra vs. Bellman-Ford Comparison

---------------------------------------------------------------------------------------------------------
Feature                  | Dijkstra's Algorithm                | Bellman-Ford Algorithm[cite: 19]
---------------------------------------------------------------------------------------------------------
Strategy                 | Greedy Approach                     | Dynamic Programming (DP)[cite: 19]
Negative Edge Weights    | Fails (may give wrong answers)      | Works correctly[cite: 19]
Negative Cycle Detection | Cannot detect                       | Can detect during the V-th relaxation
Time Complexity          | O(E * log V)                        | O(V * E)[cite: 19]
Edge Structure           | Usually Adjacency List + Heap       | Can run directly over a flat Edge List
Best Suited For          | Fast lookups on non-negative graphs | Networks with costs/discounts (negative weights)[cite: 19]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(V * E) — Outer loop runs $V - 1$ times; inner loops inspect every directed edge $E$ once per round[cite: 19].
- Space Complexity: O(V) — Memory for the single 1D `dist` array.
=================================================
*/
