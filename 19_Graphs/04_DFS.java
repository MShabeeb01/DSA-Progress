import java.util.ArrayList;

public class DepthFirstSearch {

    // Representation of a Graph Edge
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Graph Construction using Adjacency List matching the lecture diagram
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        /*
                 (1) ---- (3)
                /          |  \
              (0)          |   (5) ---- (6)
                \          |  /
                 (2) ---- (4)
        */

        // 0-Vertex
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        // 1-Vertex
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));

        // 2-Vertex
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));

        // 3-Vertex
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));

        // 4-Vertex
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        // 5-Vertex
        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));

        // 6-Vertex
        graph[6].add(new Edge(6, 5));
    }

    // ==========================================================
    // Depth First Search (DFS) -> Time Complexity: O(V + E)
    // Philosophy: "Keep going to the 1st neighbor"
    // ==========================================================
    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited) {
        // Step 1: Visit and print the current node
        System.out.print(curr + " ");
        visited[curr] = true;

        // Step 2: Recurse deeply into all unvisited neighbors
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    public static void main(String[] args) {
        int V = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);

        boolean[] visited = new boolean[V];

        System.out.print("DFS Traversal Order: ");
        dfs(graph, 0, visited);
        System.out.println();
        // Output: 0 1 3 4 2 5 6 (or depending on edge exploration sequence)
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 40 — Depth First Search (DFS)[cite: 22]

Core Intuition & Philosophy:
- Core Rule: "Keep going to the 1st neighbor"[cite: 22].
- Rather than exploring horizontally level-by-level (like BFS), DFS dives as deep as possible down a path before backtracking (analogous to Preorder tree traversal).
- It relies on the Call Stack (recursion) or an explicit LIFO Stack to remember decision points for backtracking.

DFS Algorithmic Lifecycle:
1. Mark the current vertex `curr` as visited: `visited[curr] = true`.
2. Process/print `curr`.
3. Iterate through all outgoing edges `curr -> dest`:
   - If `dest` has not been visited (`!visited[dest]`), immediately make a recursive call `dfs(graph, dest, visited)`.
4. When all branches from a vertex are exhausted, it naturally returns (backtracks) to continue other branches.

-------------------------------------------------

DFS Execution Path Trace Visual

Graph Layout:
       (1) ---- (3)
      /          |  \
    (0)          |   (5) ---- (6)[cite: 22]
      \          |  /
       (2) ---- (4)

Path Progression (Starting at 0):
1. Start at 0 -> mark 0 visited -> move to first neighbor 1
2. At 1       -> mark 1 visited -> move to first unvisited neighbor 3
3. At 3       -> mark 3 visited -> move to first unvisited neighbor 4
4. At 4       -> mark 4 visited -> move to first unvisited neighbor 2
5. At 2       -> mark 2 visited -> neighbors (0, 4) already visited -> backtrack to 4
6. At 4       -> next unvisited neighbor is 5 -> move to 5
7. At 5       -> mark 5 visited -> move to first unvisited neighbor 6
8. At 6       -> mark 6 visited -> all neighbors visited -> finish traversal

Resulting Path:
  0 -> 1 -> 3 -> 4 -> 2 -> 5 -> 6

-------------------------------------------------

BFS vs. DFS Comparison Matrix

---------------------------------------------------------------------------------------------------------
Feature                  | Breadth First Search (BFS)          | Depth First Search (DFS)[cite: 22]
---------------------------------------------------------------------------------------------------------
Exploration Strategy     | Level-by-level exploration          | Deep path branch exploration[cite: 22]
Core Data Structure      | Queue (FIFO)                        | Recursion Stack / LIFO Stack
Shortest Path Property   | Guarantees shortest path in unweighted | Does not guarantee shortest path
Memory Consumption       | Proportional to tree width          | Proportional to tree depth (recursion depth)
Best Used For            | Finding shortest unweighted paths   | Cycle detection, Topological sort, Path existence
---------------------------------------------------------------------------------------------------------

Disconnected Component Handling:
  for (int i = 0; i < V; i++) {
      if (!visited[i]) {
          dfs(graph, i, visited);
      }
  }

Complexity Analysis:
- Time Complexity : O(V + E) — Visits every node once ($O(V)$) and scans each adjacency list edge once ($O(E)$).
- Space Complexity: O(V) — Requires $O(V)$ auxiliary space for the recursion call stack (worst case line graph) plus $O(V)$ for the `visited` array.
=================================================
*/
