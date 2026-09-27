import java.util.ArrayList;
import java.util.Stack;

public class KosarajuSCC {

    // Representation of a Directed Edge (src -> dest)
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Step 1 Helper: Standard DFS to store finish times in a Stack
    public static void topSort(ArrayList<Edge>[] graph, int curr, boolean[] vis, Stack<Integer> s) {
        vis[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                topSort(graph, e.dest, vis, s);
            }
        }

        s.push(curr);
    }

    // Step 3 Helper: DFS on the Transpose (Reversed) Graph
    public static void dfsTranspose(ArrayList<Edge>[] transposeGraph, int curr, boolean[] vis) {
        vis[curr] = true;
        System.out.print(curr + " ");

        for (int i = 0; i < transposeGraph[curr].size(); i++) {
            Edge e = transposeGraph[curr].get(i);
            if (!vis[e.dest]) {
                dfsTranspose(transposeGraph, e.dest, vis);
            }
        }
    }

    // ==========================================================
    // Kosaraju's Algorithm for Strongly Connected Components (SCC)[cite: 17]
    // Time Complexity: O(V + E)
    // ==========================================================
    public static void kosaraju(ArrayList<Edge>[] graph, int V) {
        // ------------------------------------------------------
        // Step 1: Compute finish times order using DFS into Stack
        // ------------------------------------------------------
        Stack<Integer> s = new Stack<>();
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                topSort(graph, i, vis, s);
            }
        }

        // ------------------------------------------------------
        // Step 2: Create Transpose (Reversed) Graph[cite: 17]
        // ------------------------------------------------------
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] transpose = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            transpose[i] = new ArrayList<>();
        }

        for (int u = 0; u < V; u++) {
            for (int j = 0; j < graph[u].size(); j++) {
                Edge e = graph[u].get(j);
                transpose[e.dest].add(new Edge(e.dest, e.src)); // Reverse direction: v -> u
            }
        }

        // ------------------------------------------------------
        // Step 3: DFS on Transpose Graph according to Stack Order
        // ------------------------------------------------------
        for (int i = 0; i < V; i++) {
            vis[i] = false; // Reset visited array
        }

        System.out.println("Strongly Connected Components (SCCs):");
        int count = 0;

        while (!s.isEmpty()) {
            int curr = s.pop();
            if (!vis[curr]) {
                System.out.print("SCC #" + (++count) + ": ");
                dfsTranspose(transpose, curr, vis);
                System.out.println();
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

        // Directed edges matching the lecture slide diagram:[cite: 17]
        // 0 -> 3, 3 -> 4, 0 -> 2, 2 -> 1, 1 -> 0[cite: 17]
        graph[0].add(new Edge(0, 3));[cite: 17]
        graph[3].add(new Edge(3, 4));[cite: 17]
        graph[0].add(new Edge(0, 2));[cite: 17]
        graph[2].add(new Edge(2, 1));[cite: 17]
        graph[1].add(new Edge(1, 0));[cite: 17]

        kosaraju(graph, V);
        /*
          Output:
          SCC #1: 0 1 2 
          SCC #2: 3 
          SCC #3: 4
        */
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 45 (Supplemental) — Strongly Connected Component (SCC) & Kosaraju's Algorithm[cite: 17]

Core Definition:
- Strongly Connected Component (SCC): A maximal subgraph in which every vertex is reachable from every other vertex in that same component[cite: 17].
- Only meaningful in **directed graphs** (in an undirected connected graph, the entire graph is inherently an SCC)[cite: 17].

Kosaraju's Algorithm (3-Step Process):
1. **Get Nodes in Finishing Time Order (DFS)**:
   - Perform standard DFS and push finished nodes onto a Stack (similar to Topological Sort).
   - The top of the stack will be a node from an SCC that has no incoming edges from other components in the condensation graph.
2. **Transpose the Graph ($G^T$)**:
   - Reverse every directed edge in the graph ($u \rightarrow v$ becomes $v \rightarrow u$)[cite: 17].
   - Reversing edges prevents DFS from accidentally leaking out from one SCC into another, while leaving connections inside any SCC intact.
3. **DFS on Transposed Graph**:
   - Pop vertices one by one from the stack.
   - If the vertex is not yet visited, run DFS on the transposed graph. Each full traversal collects exactly one complete SCC[cite: 17].

-------------------------------------------------

Slide Graph Structure & SCC Breakdown[cite: 17]

Vertices: {0, 1, 2, 3, 4}[cite: 17]

        (1) <----- (2)
         |         ^
         v        /
        (0) -----+
         |
         v
        (3)
         |
         v
        (4)[cite: 17]

Identified SCCs:
- Component 1: {0, 1, 2} (Cycle where 0 -> 2 -> 1 -> 0, all reach each other)[cite: 17]
- Component 2: {3} (Reaches 4, but cannot get back from 4)[cite: 17]
- Component 3: {4} (Sink node, reaches nothing else)[cite: 17]

Total number of SCCs = 3[cite: 17]

-------------------------------------------------

Step-by-Step Kosaraju Execution

1. DFS Finishes Order -> Stack:
   - From 0, reaches 3 -> 4.
   - 4 finishes -> push 4.
   - 3 finishes -> push 3.
   - From 0 -> 2 -> 1 -> 0 (already visited).
   - 1 finishes -> push 1.
   - 2 finishes -> push 2.
   - 0 finishes -> push 0.
   - Stack top-to-bottom: [0, 2, 1, 3, 4].

2. Transposed Graph ($G^T$):
   - 3 -> 0, 4 -> 3, 2 -> 0, 1 -> 2, 0 -> 1.

3. Pop Stack & Run DFS on $G^T$:
   - Pop 0: DFS visits 0 -> 1 -> 2 (SCC #1: {0, 1, 2}).
   - Pop 2: Already visited.
   - Pop 1: Already visited.
   - Pop 3: DFS visits 3 (cannot visit 0 because 0 is visited) (SCC #2: {3}).
   - Pop 4: DFS visits 4 (cannot visit 3 because 3 is visited) (SCC #3: {4}).

-------------------------------------------------

Complexity Analysis:
- Time Complexity : O(V + E) — 
  - Step 1 (DFS): $O(V + E)$
  - Step 2 (Graph Transposition): $O(V + E)$
  - Step 3 (DFS on Transpose): $O(V + E)$
  - Total Time: $O(V + E)$ linear time.
- Space Complexity: O(V + E) — Auxiliary stack of size $O(V)$, visited array of size $O(V)$, and transposed graph representation taking $O(V + E)$.
=================================================
*/
