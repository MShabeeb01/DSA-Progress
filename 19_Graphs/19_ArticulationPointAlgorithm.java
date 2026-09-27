import java.util.ArrayList;

public class ArticulationPointAlgorithm {

    // Representation of an Edge in an undirected graph
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Helper: DFS utility using Tarjan's discovery and lowest discovery time logic
    public static void dfs(ArrayList<Edge>[] graph, int curr, int par, 
                           int[] dt, int[] low, int[] time, 
                           boolean[] vis, boolean[] isAP) {
        vis[curr] = true;
        dt[curr] = low[curr] = ++time[0];
        int children = 0; // Tracks number of independent DFS children

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            int neighbor = e.dest;

            // Case 1: Direct parent back-edge -> Ignore
            if (neighbor == par) {
                continue;
            }

            // Case 2: Neighbor already visited -> Back-edge (Cycle detected)
            if (vis[neighbor]) {
                low[curr] = Math.min(low[curr], dt[neighbor]);
            } 
            // Case 3: Neighbor is unvisited -> Forward tree-edge
            else {
                dfs(graph, neighbor, curr, dt, low, time, vis, isAP);
                low[curr] = Math.min(low[curr], low[neighbor]);
                children++;

                // Condition 1: Non-root node is an Articulation Point
                if (par != -1 && low[neighbor] >= dt[curr]) {
                    isAP[curr] = true;
                }
            }
        }

        // Condition 2: Root node of DFS is an Articulation Point if it has > 1 disconnected children
        if (par == -1 && children > 1) {
            isAP[curr] = true;
        }
    }

    // ==========================================================
    // Tarjan's Algorithm for Articulation Points -> Time Complexity: O(V + E)
    // ==========================================================
    public static void getArticulationPoints(ArrayList<Edge>[] graph, int V) {
        int[] dt = new int[V];       // Discovery Time
        int[] low = new int[V];      // Lowest Discovery Time reachable
        int[] time = {0};            // Global timer
        boolean[] vis = new boolean[V];
        boolean[] isAP = new boolean[V]; // Tracks identified Articulation Points (avoids duplicates)

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(graph, i, -1, dt, low, time, vis, isAP);
            }
        }

        System.out.println("Articulation Points (Cut Vertices):");
        for (int i = 0; i < V; i++) {
            if (isAP[i]) {
                System.out.println("Vertex: " + i);
            }
        }
    }

    public static void main(String[] args) {
        int V = 5; // Vertices: 0, 1, 2, 3, 4[cite: 17]
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        // Undirected edges matching slide diagram:[cite: 17]
        // 0-1, 0-2, 1-2, 0-3, 3-4[cite: 17]
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 2);
        addEdge(graph, 0, 3);
        addEdge(graph, 3, 4);

        getArticulationPoints(graph, V);
        /*
          Output:
          Articulation Points (Cut Vertices):
          Vertex: 0
          Vertex: 3
        */
    }

    private static void addEdge(ArrayList<Edge>[] graph, int u, int v) {
        graph[u].add(new Edge(u, v));
        graph[v].add(new Edge(v, u));
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 45 (Supplemental) — Articulation Point (Tarjan's Algorithm)[cite: 17]

Formal Definition:
- A vertex in an undirected connected graph is an **articulation point** (or **cut vertex**) if removing it (and edges connected through it) increases the number of connected components of the graph[cite: 17].
- Real-world relevance: Identifying single points of failure in network topology, router communication, and power supply backbones.

The 2 Conditions for a Vertex `curr` to be an Articulation Point:

1. Case 1: Root Vertex (`par == -1`):
   - The starting root of the DFS tree is an articulation point if and only if it has **more than 1 child** (`children > 1`) in the DFS tree.
   - If the root has two or more independent branches that cannot reach each other without going through the root, deleting the root breaks them apart.

2. Case 2: Non-Root Vertex (`par != -1`):
   - A non-root vertex is an articulation point if it has a child `neighbor` such that:
     `low[neighbor] >= dt[curr]`
   - Meaning: The subtree rooted at `neighbor` cannot reach any ancestor of `curr` without passing through `curr`. Hence, removing `curr` completely disconnects that subtree.

Why `isAP[]` Boolean Array is Used:
- A single vertex might satisfy `low[neighbor] >= dt[curr]` for multiple children.
- Using a boolean flag prevents reporting the same vertex multiple times.

-------------------------------------------------

Slide Graph Structure & Vertex Analysis[cite: 17]

Vertices: {0, 1, 2, 3, 4}[cite: 17]

        (1) -------- (0) -------- (3)[cite: 17]
          \         /               \
           \       /                 \
            \     /                   \
             (2)                     (4)[cite: 17]

Testing Removals:
- Remove Vertex 0:
  - Edges (0, 1), (0, 2), (0, 3) get removed.
  - Leaves components {1, 2} and {3, 4} separated -> **Vertex 0 is an Articulation Point**!
- Remove Vertex 3:
  - Edges (0, 3) and (3, 4) get removed.
  - Leaves {0, 1, 2} and isolated {4} separated -> **Vertex 3 is an Articulation Point**!
- Remove Vertices 1, 2, or 4:
  - Graph remains connected -> Not articulation points.

-------------------------------------------------

Bridge vs. Articulation Point Comparison

---------------------------------------------------------------------------------------------------------
Feature                  | Bridge (Tarjan's)                   | Articulation Point (Tarjan's)[cite: 17]
---------------------------------------------------------------------------------------------------------
Target Type              | Critical **Edge** (Cut-Edge)[cite: 16]         | Critical **Vertex** (Cut-Vertex)[cite: 17]
Condition for Child      | `low[neighbor] > dt[curr]`          | `low[neighbor] >= dt[curr]` (non-root)
Root Handling            | No special rule for root            | Requires check: `par == -1 && children > 1`
Back-Edge Update Metric  | `low[curr] = min(low[curr], dt[v])` | `low[curr] = min(low[curr], dt[v])`
Impact of Removal        | Removes a single connection         | Removes vertex and ALL attached edges[cite: 17]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(V + E) — Single DFS pass evaluating discovery times and low values across vertices and edges.
- Space Complexity: O(V) — Memory arrays for `dt[]`, `low[]`, `vis[]`, `isAP[]`, and recursion stack depth bounded by $V$.
=================================================
*/
