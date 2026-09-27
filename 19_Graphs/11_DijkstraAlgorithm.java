import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstrasAlgorithm {

    // Edge representation for a directed weighted graph
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

    // Pair class representing (node, path distance from source)
    static class Pair implements Comparable<Pair> {
        int node;
        int pathDist;

        public Pair(int node, int pathDist) {
            this.node = node;
            this.pathDist = pathDist;
        }

        // Min-Heap ordering: Smallest distance gets highest priority
        @Override
        public int compareTo(Pair p2) {
            return this.pathDist - p2.pathDist;
        }
    }

    // Graph Construction matching the slide diagram[cite: 18]
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        /*
           0 --(2)--> 1 --(7)--> 3 --(1)--> 5
           |          |          ^          ^
          (4)        (1)        (2)        (5)
           v          v          |          |
           +--------> 2 --(3)--> 4 ---------+[cite: 18]
        */
        graph[0].add(new Edge(0, 1, 2));[cite: 18]
        graph[0].add(new Edge(0, 2, 4));[cite: 18]
        graph[1].add(new Edge(1, 3, 7));[cite: 18]
        graph[1].add(new Edge(1, 2, 1));[cite: 18]
        graph[2].add(new Edge(2, 4, 3));[cite: 18]
        graph[3].add(new Edge(3, 5, 1));[cite: 18]
        graph[4].add(new Edge(4, 3, 2));[cite: 18]
        graph[4].add(new Edge(4, 5, 5));[cite: 18]
    }

    // ==========================================================
    // Dijkstra's Algorithm: Shortest path from source to all vertices[cite: 18]
    // Time Complexity: O(E + E * log V)
    // ==========================================================
    public static void dijkstra(ArrayList<Edge>[] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] vis = new boolean[V];

        // Step 1: Initialize distances: src = 0, all others = Infinity
        for (int i = 0; i < V; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        // PriorityQueue (Min-Heap) to pick the node with smallest tentative distance
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        // Step 2: BFS-style relaxation using Min-Heap
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();

            // Skip processing if node has already been finalized
            if (vis[curr.node]) {
                continue;
            }
            vis[curr.node] = true;

            // Step 3: Relaxation of all outgoing edges (curr.node -> dest)
            for (int i = 0; i < graph[curr.node].size(); i++) {
                Edge e = graph[curr.node].get(i);
                int u = e.src;
                int v = e.dest;
                int wt = e.wt;

                // Relaxation condition: dist[u] + wt < dist[v]
                if (dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        // Output shortest distances from the source
        System.out.println("Shortest distances from source node (" + src + "):");
        for (int i = 0; i < V; i++) {
            System.out.println("Node " + i + " : " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int V = 6;[cite: 18]
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);

        int src = 0;
        dijkstra(graph, src);
        /*
          Expected Distances from Node 0:
          Node 0 : 0
          Node 1 : 2
          Node 2 : 3  (via 0 -> 1 -> 2: 2 + 1 = 3, better than direct edge 4)
          Node 3 : 8  (via 0 -> 1 -> 2 -> 4 -> 3: 2 + 1 + 3 + 2 = 8)
          Node 4 : 6  (via 0 -> 1 -> 2 -> 4: 2 + 1 + 3 = 6)
          Node 5 : 9  (via 0 -> 1 -> 2 -> 4 -> 3 -> 5: 8 + 1 = 9)
        */
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 42 — Dijkstra's Algorithm[cite: 18]

Problem Objective:
- Computes the **shortest paths from a single source vertex to all other vertices** in a weighted graph[cite: 18].
- Works on both directed and undirected graphs with non-negative edge weights[cite: 18].
- Greedy approach: always finalizes the vertex currently known to have the minimum tentative distance.

Core Concept — Edge Relaxation:
- If a path to node `v` through node `u` is shorter than the currently recorded distance to `v`, update `dist[v]`:
    if (dist[u] + weight(u, v) < dist[v]) {
        dist[v] = dist[u] + weight(u, v);
    }

Why PriorityQueue (Min-Heap) is Used:
- A regular array requires $O(V)$ time per extraction to find the minimum distance vertex ($O(V^2)$ total).
- A Min-Heap extracts the minimum distance element in $O(\log V)$ time, bringing the overall complexity down to $O(E \log V)$.

-------------------------------------------------

Graph Topology and Relaxation Visual[cite: 18]

Graph Structure:
         (1) -------- 7 --------> (3) -------- 1 -------> (5)
        ^   \                      ^                      ^
       /     \ 1                  / 2                    / 5
      2       v                  /                      /
    (0)       (2) ------ 3 ----> (4) ------------------+[cite: 18]
      \       ^
       \--4--/[cite: 18]

Shortest Path Calculations from Source 0:
1. dist[0] = 0
2. dist[1] = 0 + 2 = 2 (Path: 0 -> 1)
3. dist[2] = min(4, dist[1] + 1) = min(4, 2 + 1) = 3 (Path: 0 -> 1 -> 2)
4. dist[4] = dist[2] + 3 = 3 + 3 = 6 (Path: 0 -> 1 -> 2 -> 4)
5. dist[3] = min(dist[1] + 7, dist[4] + 2) = min(2 + 7, 6 + 2) = 8 (Path: 0 -> 1 -> 2 -> 4 -> 3)
6. dist[5] = min(dist[3] + 1, dist[4] + 5) = min(8 + 1, 6 + 5) = 9 (Path: 0 -> 1 -> 2 -> 4 -> 3 -> 5)

-------------------------------------------------

Dijkstra's Algorithm Limitations & Alternatives

---------------------------------------------------------------------------------------------------------
Algorithm             | Graph Types Handled           | Negative Weights? | Time Complexity
---------------------------------------------------------------------------------------------------------
BFS                   | Unweighted graphs             | No                | O(V + E)
Dijkstra's Algorithm  | Directed / Undirected (DAG/DCG)| NO (fails)        | O(E * log V)
Bellman-Ford          | Directed / Undirected graphs  | YES               | O(V * E)
Floyd-Warshall        | All-Pairs Shortest Path       | YES               | O(V^3)
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(E * log V) using PriorityQueue (every edge can potentially push an updated pair into the heap of size up to $V$).
- Space Complexity: O(V + E) — Adjacency list storage $O(V + E)$, plus $O(V)$ auxiliary space for `dist[]`, `vis[]`, and the priority queue.
=================================================
*/
