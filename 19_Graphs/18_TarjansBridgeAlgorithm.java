import java.util.ArrayList;

public class TarjansBridgeAlgorithm {

    // Representation of an Edge in an undirected graph
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Helper: DFS utility using Tarjan's discovery time and lowest discovery time logic
    public static void dfs(ArrayList<Edge>[] graph, int curr, int par, int[] dt, int[] low, int[] time, boolean[] vis) {
        vis[curr] = true;
        dt[curr] = low[curr] = ++time[0];

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            int neighbor = e.dest;

            // Case 1: The neighbor is the parent -> Ignore edge back to immediate caller
            if (neighbor == par) {
                continue;
            }

            // Case 2: The neighbor is already visited -> Back-edge found
            if (vis[neighbor]) {
                low[curr] = Math.min(low[curr], dt[neighbor]);
            }
            // Case 3: The neighbor is unvisited -> Forward tree-edge
            else {
                dfs(graph, neighbor, curr, dt, low, time, vis);
                low[curr] = Math.min(low[curr], low[neighbor]);

                // Bridge Condition: If lowest reachable time of neighbor is strictly greater than dt[curr]
                if (low[neighbor] > dt[curr]) {
                    System.out.println("Bridge Edge: " + curr + " --- " + neighbor);
                }
            }
        }
    }

    // ==========================================================
    // Tarjan's Algorithm for Finding Bridges -> Time Complexity: O(V + E)
    // ==========================================================
    public static void getBridges(ArrayList<Edge>[] graph, int V) {
        int[] dt = new int[V];   // Discovery Time of vertices
        int[] low = new int[V];  // Lowest Discovery Time reachable
        int[] time = {0};        // Global time counter
        boolean[] vis = new boolean[V];

        System.out.println("Identified Bridges in Graph:");
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(graph, i, -1, dt, low, time, vis);
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
        addEdge(graph, 0, 3); // Bridge[cite: 17]
        addEdge(graph, 3, 4); // Bridge[cite: 17]

        getBridges(graph, V);
        /*
          Output:
          Bridge Edge: 3 --- 4[cite: 17]
          Bridge Edge: 0 --- 3[cite: 17]
        */
    }

    private static void addEdge(ArrayList<Edge>[] graph, int u, int v) {
        graph[u].add(new Edge(u, v));
        graph[v].add(new Edge(v, u));
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 45 (Supplemental) — Bridge in Graphs (Tarjan's Algorithm)[cite: 17]

Core Definition[cite: 17]:
- A **Bridge** (or cut-edge) is an edge whose deletion increases the graph's total number of connected components[cite: 17].
- If a bridge edge is removed, the graph gets disconnected into two or more separate subgraphs[cite: 17].

Key Tracking Arrays in Tarjan's Algorithm:
1. `dt[]` (Discovery Time):
   - Stores the time/step at which a vertex was first visited during DFS traversal.
2. `low[]` (Lowest Discovery Time):
   - Stores the lowest discovery time of any ancestor vertex reachable from the subtree rooted at `curr` via at most one back-edge.

The 3 Edge Categories During DFS:
1. **Parent Edge (`neighbor == par`)**:
   - Trivial reverse undirected edge back to the direct caller node; ignore it.
2. **Back-Edge (`vis[neighbor] == true`)**:
   - Leads to an ancestor already visited earlier in the DFS branch (cycle detected).
   - Update lowest reach: `low[curr] = Math.min(low[curr], dt[neighbor])`.
3. **Forward Tree-Edge (`!vis[neighbor]`)**:
   - Recurse into child: `dfs(graph, neighbor, curr, ...)`.
   - On backtracking, inherit child's lowest reach: `low[curr] = Math.min(low[curr], low[neighbor])`.
   - **Bridge Check:**
     If `low[neighbor] > dt[curr]`:
     The child `neighbor` has no back-edge to reach `curr` or any ancestor of `curr`. 
     Hence, removing edge `curr --- neighbor` completely isolates the child's subtree.
     -> Edge `(curr, neighbor)` is a **Bridge**!

-------------------------------------------------

Slide Graph Structure & Bridge Analysis[cite: 17]

Vertices: {0, 1, 2, 3, 4}[cite: 17]

        (1) -------- (0) -------- (3)[cite: 17]
          \         /               \
           \       /                 \
            \     /                   \
             (2)                     (4)[cite: 17]

Component & Edge Analysis:
- Triangle Component {0, 1, 2}:
  - Edges: (0, 1), (1, 2), (2, 0).
  - Removing any edge leaves the component connected via alternate cycle paths -> NOT bridges.
- Edge (0, 3):
  - Removing (0, 3) separates {0, 1, 2} from {3, 4} (components: 1 -> 2) -> **BRIDGE**[cite: 17]!
- Edge (3, 4):
  - Removing (3, 4) isolates node 4 into its own component -> **BRIDGE**[cite: 17]!

-------------------------------------------------

Step-by-Step Decision Matrix

---------------------------------------------------------------------------------------------------------
Condition                    | Edge Type             | Action Taken
---------------------------------------------------------------------------------------------------------
`neighbor == par`            | Parent Return Edge    | Continue (Ignore)
`vis[neighbor] == true`      | Back-Edge (Cycle)     | `low[curr] = Math.min(low[curr], dt[neighbor])`
`!vis[neighbor]`             | Forward Tree-Edge     | Recurse, update `low[curr] = Math.min(low[curr], low[neighbor])`
`low[neighbor] > dt[curr]`   | Critical Cut-Edge     | Report `(curr, neighbor)` as a **Bridge**
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(V + E) — Single DFS traversal checking each vertex and visiting each undirected edge twice.
- Space Complexity: O(V) — Memory for `dt[]`, `low[]`, `vis[]`, and the recursion stack depth bounded by $V$.
=================================================
*/
