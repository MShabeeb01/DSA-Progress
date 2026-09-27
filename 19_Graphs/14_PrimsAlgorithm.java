import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimsAlgorithm {

    // Representation of an Edge in an undirected weighted graph
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

    // Pair class storing (vertex, edge cost to connect to MST)
    static class Pair implements Comparable<Pair> {
        int v;    // Vertex
        int cost; // Minimum edge weight connecting this vertex to the MST

        public Pair(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        // Min-Heap based on cheapest edge cost
        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost;
        }
    }

    // Graph Construction matching the lecture slide diagram[cite: 21]
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        /*
                 (0)
               /  |  \
             10   30  15
            /     |     \
          (1)     |     (2)
            \     |     /
             40   |   50
               \  |  /
                 (3)[cite: 21]
        */
        // 0-Vertex edges
        graph[0].add(new Edge(0, 1, 10));[cite: 21]
        graph[0].add(new Edge(0, 2, 15));[cite: 21]
        graph[0].add(new Edge(0, 3, 30));[cite: 21]

        // 1-Vertex edges
        graph[1].add(new Edge(1, 0, 10));[cite: 21]
        graph[1].add(new Edge(1, 3, 40));[cite: 21]

        // 2-Vertex edges
        graph[2].add(new Edge(2, 0, 15));[cite: 21]
        graph[2].add(new Edge(2, 3, 50));[cite: 21]

        // 3-Vertex edges
        graph[3].add(new Edge(3, 0, 30));[cite: 21]
        graph[3].add(new Edge(3, 1, 40));[cite: 21]
        graph[3].add(new Edge(3, 2, 50));[cite: 21]
    }

    // ==========================================================
    // Prim's Algorithm for Minimum Spanning Tree (MST)[cite: 21]
    // Time Complexity: O(E * log V)
    // ==========================================================
    public static void prims(ArrayList<Edge>[] graph) {
        int V = graph.length;
        boolean[] inMST = new boolean[V]; // Tracks vertices included in "MST Set"[cite: 21]
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // Start with vertex 0 with initial cost 0
        pq.add(new Pair(0, 0));
        int finalMstCost = 0;

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            int u = curr.v;

            // If already included in MST Set, skip to avoid cycles
            if (inMST[u]) {
                continue;
            }

            // Include vertex u into the MST Set[cite: 21]
            inMST[u] = true;
            finalMstCost += curr.cost;

            // Explore all adjacent edges of vertex u
            for (int i = 0; i < graph[u].size(); i++) {
                Edge e = graph[u].get(i);
                // If destination neighbor is not yet in the MST Set, push it to PQ
                if (!inMST[e.dest]) {
                    pq.add(new Pair(e.dest, e.wt));
                }
            }
        }

        System.out.println("Final Minimum Spanning Tree (MST) Cost = " + finalMstCost);
    }

    public static void main(String[] args) {
        int V = 4; // Vertices: 0, 1, 2, 3[cite: 21]
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);

        prims(graph);[cite: 21]
        /*
          Expected Execution Trace:
          1. Pick node 0 (cost: 0) -> MST Set = {0}
          2. Edges available: (0-1: 10), (0-2: 15), (0-3: 30)
          3. Pick cheapest: node 1 (cost: 10) -> MST Set = {0, 1}
          4. Edges available: (0-2: 15), (0-3: 30), (1-3: 40)
          5. Pick cheapest: node 2 (cost: 15) -> MST Set = {0, 1, 2}
          6. Edges available: (0-3: 30), (1-3: 40), (2-3: 50)
          7. Pick cheapest: node 3 (cost: 30 via edge 0-3) -> MST Set = {0, 1, 2, 3}

          Total MST Cost = 10 + 15 + 30 = 55
        */
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 43 — Prim's Algorithm (MST Set)[cite: 21]

Core Idea:
- Prim's is a **greedy algorithm** used to find the Minimum Spanning Tree (MST) of a connected, undirected, weighted graph[cite: 21].
- It starts with a single vertex and continually expands the tree one edge at a time by picking the cheapest available edge connecting a visited vertex in the "MST Set" to an unvisited outside vertex[cite: 21].

The "MST Set" Concept:
- **MST Set**: A boolean tracking array (`inMST[]`) that keeps track of vertices already included in the growing spanning tree[cite: 21].
- **Cut Property**: At every step, the algorithm inspects the cut between vertices in the MST Set and vertices outside the MST Set, greedily picking the minimum weight cross-edge[cite: 21].

-------------------------------------------------

Step-by-Step Prim's Execution on the Slide Graph[cite: 21]

Graph Layout:
       (0)[cite: 21]
      / | \
    10 30  15[cite: 21]
    /   |   \
  (1)   |   (2)[cite: 21]
    \   |   /
    40  |  50[cite: 21]
      \ | /
       (3)[cite: 21]

Step 1:
  - Start at vertex 0: `inMST[0] = true`
  - Push edges from 0 into Priority Queue: (1, wt: 10), (2, wt: 15), (3, wt: 30)

Step 2:
  - Extract min from PQ: (1, wt: 10)
  - `inMST[1] = true`, MST Cost += 10
  - Push edges from 1: (3, wt: 40)

Step 3:
  - Extract min from PQ: (2, wt: 15)
  - `inMST[2] = true`, MST Cost += 15
  - Push edges from 2: (3, wt: 50)

Step 4:
  - Remaining candidates for node 3: (3, wt: 30), (3, wt: 40), (3, wt: 50)
  - Extract min from PQ: (3, wt: 30)
  - `inMST[3] = true`, MST Cost += 30
  - All $V = 4$ vertices are now included in the MST Set.

Total MST Edge Weight = 10 + 15 + 30 = 55

-------------------------------------------------

Dijkstra's Algorithm vs. Prim's Algorithm

---------------------------------------------------------------------------------------------------------
Feature                  | Dijkstra's Algorithm                | Prim's Algorithm[cite: 21]
---------------------------------------------------------------------------------------------------------
Primary Goal             | Shortest path from source to nodes  | Minimum total edge weight to span all nodes[cite: 21]
Greedy Metric            | Path distance from source (`dist[u]+wt`) | Just edge weight (`wt`) connecting to tree[cite: 21]
Graph Type               | Directed or Undirected              | Connected Undirected graphs[cite: 21]
Visited Tracking Set     | Finalized shortest distances        | "MST Set" of spanning tree nodes[cite: 21]
Data Structure           | PriorityQueue (Min-Heap)            | PriorityQueue (Min-Heap)[cite: 21]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(E * log V) — Every edge is visited and can be pushed into the Min-Heap of vertices[cite: 21].
- Space Complexity: O(V + E) — Adjacency list takes O(V + E); PriorityQueue and `inMST[]` array take O(V).
=================================================
*/
