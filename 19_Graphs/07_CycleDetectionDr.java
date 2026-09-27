import java.util.ArrayList;

public class CycleDetectionDirectedDFS {

    // Representation of a Directed Edge (src -> dest)
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Helper: DFS traversal tracking visited vertices and current recursion stack[cite: 25]
    public static boolean isCycleUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis, boolean[] stack) {
        vis[curr] = true;
        stack[curr] = true; // Mark node as active in the current path/recursion call stack

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);

            // Condition 1: Neighbor is already active in current recursion stack -> Cycle exists![cite: 25]
            if (stack[e.dest]) {
                return true;
            }

            // Condition 2: Neighbor is unvisited -> Recurse deeply
            if (!vis[e.dest] && isCycleUtil(graph, e.dest, vis, stack)) {
                return true;
            }
        }

        // Backtrack: Remove node from active recursion stack before returning
        stack[curr] = false;
        return false;
    }

    // Main function handling disconnected graph components[cite: 25]
    public static boolean isCycle(ArrayList<Edge>[] graph) {
        int V = graph.length;
        boolean[] vis = new boolean[V];
        boolean[] stack = new boolean[V]; // Tracks the active DFS recursion branch

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (isCycleUtil(graph, i, vis, stack)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // ==========================================
        // Graph 1: No Cycle (Left slide diagram)[cite: 25]
        // Edges: 0->1, 0->2, 1->3, 2->3
        // ==========================================
        int V1 = 4;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graphNoCycle = new ArrayList[V1];
        for (int i = 0; i < V1; i++) graphNoCycle[i] = new ArrayList<>();

        graphNoCycle[0].add(new Edge(0, 1));
        graphNoCycle[0].add(new Edge(0, 2));
        graphNoCycle[1].add(new Edge(1, 3));
        graphNoCycle[2].add(new Edge(2, 3));

        System.out.println("Graph 1 (Left) has cycle?  " + isCycle(graphNoCycle)); // false[cite: 25]

        // ==========================================
        // Graph 2: With Cycle (Right slide diagram)[cite: 25]
        // Edges: 1->0, 0->2, 2->3, 3->0 (Cycle: 0 -> 2 -> 3 -> 0)
        // ==========================================
        int V2 = 4;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graphCycle = new ArrayList[V2];
        for (int i = 0; i < V2; i++) graphCycle[i] = new ArrayList<>();

        graphCycle[1].add(new Edge(1, 0));
        graphCycle[0].add(new Edge(0, 2));
        graphCycle[2].add(new Edge(2, 3));
        graphCycle[3].add(new Edge(3, 0)); // Back-edge completing the cycle

        System.out.println("Graph 2 (Right) has cycle? " + isCycle(graphCycle)); // true[cite: 25]
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 41 — Cycle Detection in Directed Graph (DFS)[cite: 25]

Why the Undirected Logic Fails for Directed Graphs:
- In undirected graphs, tracking parent (`dest != par`) is sufficient because an edge goes both ways[cite: 23].
- In directed graphs, reaching an already-visited vertex does NOT necessarily mean there is a cycle[cite: 25].
  - Example: In the left diagram (0 -> 1 -> 3 and 0 -> 2 -> 3), vertex 3 is visited from both branches, but paths do not loop back[cite: 25].
- **Core Condition for a Directed Cycle:** A cycle exists if and only if a directed back-edge points to an ancestor node currently present in the **active recursion call stack**[cite: 25].

The Dual-Array Mechanism:
1. `boolean[] vis`:
   - Tracks if a node has ever been visited across the entire DFS run to prevent redundant processing.
2. `boolean[] stack` (or `recStack`):
   - Tracks only the vertices present in the **current active recursion branch**.
   - Set to `true` when a node is pushed onto the stack; reset to `false` (backtracking) when the DFS call finishes exploring all neighbors.

-------------------------------------------------

Slide Diagrams Comparison Visual[cite: 25]

1. Left Graph (No Cycle)[cite: 25]:
         (0)
        /   \
       v     v
      (1)   (2)
       \     /
        v   v
         (3)[cite: 25]
   - Path 1: 0 -> 1 -> 3. Stack becomes empty as DFS finishes 3 and 1.
   - Path 2: 0 -> 2 -> 3. Node 3 is `vis[3] == true`, but `stack[3] == false` (no longer active).
   - Verdict: No back-edge in the same path -> No cycle[cite: 25].

2. Right Graph (Cycle)[cite: 25]:
      (1) ---> (0) <---- (3)
                |        ^
                v        |
               (2) ------+[cite: 25]
   - Path: 1 -> 0 -> 2 -> 3 -> 0.
   - When DFS reaches node 0 from node 3:
     - `stack[0] == true` (Node 0 is still active in the call stack).
   - Verdict: Back-edge to an active ancestor -> Cycle detected[cite: 25]!

-------------------------------------------------

Neighbor Decision Matrix for Directed DFS

---------------------------------------------------------------------------------------------------------
Neighbor State (`e.dest`)  | Active in `stack[]`? | Action Taken
---------------------------------------------------------------------------------------------------------
`stack[e.dest] == true`    | Yes                  | Cycle Detected! Return true immediately[cite: 25]
`!vis[e.dest]`             | No                   | Recurse deeply: `isCycleUtil(graph, e.dest, vis, stack)`
`vis[e.dest] && !stack[]`  | No (Cross/Forward)   | Safe; already fully explored in a previous branch
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(V + E) — Visits every vertex once and iterates through all outgoing directed edges[cite: 25].
- Space Complexity: O(V) — Memory for `vis` array, `stack` array, and the recursion call stack (bounded by depth V).
=================================================
*/
