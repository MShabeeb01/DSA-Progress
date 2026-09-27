import java.util.ArrayList;

public class MinimumSpanningTreeBasics {

    // Representation of an Edge in an Undirected Weighted Graph[cite: 20]
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }

        @Override
        public String toString() {
            return "(" + src + " - " + dest + ", wt: " + wt + ")";
        }
    }

    public static void main(String[] args) {
        // Vertex mapping matching slide diagram:[cite: 20]
        // A=0, B=1, C=2, D=3, E=4, F=5, G=6[cite: 20]
        int V = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        // Helper lambda to insert undirected weighted edges[cite: 20]
        addEdge(graph, 0, 1, 7);  // A - B (7)[cite: 20]
        addEdge(graph, 0, 3, 5);  // A - D (5)[cite: 20]
        addEdge(graph, 1, 2, 8);  // B - C (8)[cite: 20]
        addEdge(graph, 1, 3, 9);  // B - D (9)[cite: 20]
        addEdge(graph, 1, 4, 7);  // B - E (7)[cite: 20]
        addEdge(graph, 2, 4, 5);  // C - E (5)[cite: 20]
        addEdge(graph, 3, 4, 15); // D - E (15)[cite: 20]
        addEdge(graph, 3, 5, 6);  // D - F (6)[cite: 20]
        addEdge(graph, 4, 5, 8);  // E - F (8)[cite: 20]
        addEdge(graph, 4, 6, 9);  // E - G (9)[cite: 20]
        addEdge(graph, 5, 6, 11); // F - G (11)[cite: 20]

        System.out.println("Graph initialized with " + V + " vertices.");
        System.out.println("Any valid Minimum Spanning Tree (MST) for this graph will have:");
        System.out.println(" - Exactly (V - 1) = " + (V - 1) + " edges.");
        System.out.println(" - 0 cycles (Acyclic Tree structure).");
        System.out.println(" - The minimum possible total sum of edge weights.");
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int wt) {
        graph[u].add(new Edge(u, v, wt));
        graph[v].add(new Edge(v, u, wt));
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 43 — Minimum Spanning Tree (MST) Concept[cite: 20]

Formal Definition:
- A Minimum Spanning Tree (MST) or minimum weight spanning tree is a **subset of the edges** of a connected, edge-weighted undirected graph that connects all the vertices together, **without any cycles** and with the **minimum possible total edge weight**[cite: 20].

Core Mathematical Properties of an Spanning Tree:
1. Vertices Count:
   - Must span across all $V$ vertices present in the original graph[cite: 20].
2. Edges Count:
   - For any tree with $V$ vertices, it must contain exactly $V - 1$ edges.
3. Acyclic & Connected:
   - Contains no loops/cycles[cite: 20].
   - Every vertex is reachable from every other vertex.
4. Minimality:
   - Out of all possible spanning trees that can be formed from a graph, the MST has the lowest possible edge weight summation $\sum \text{wt}(e)$[cite: 20].

-------------------------------------------------

Slide Graph Structure Visual[cite: 20]

Vertices: A, B, C, D, E, F, G ($V = 7$)[cite: 20]

         (A) ------- 7 ------- (B) ------- 8 ------- (C)[cite: 20]
          |                   / |                     |
          |                 /   |                     |
          5               9     7                     5[cite: 20]
          |             /       |                     |
          |           /         |                     |
         (D) -------- 15 ------ (E)                   |[cite: 20]
          |                   /  |                    |
          |                 /    |                    |
          6               8      9                    |[cite: 20]
          |             /        |                    |
          |           /          |                    |
         (F) -------- 11 ------ (G) ------------------+ (connects to E)[cite: 20]

MST Construction Preview:
- An MST will select exactly $7 - 1 = 6$ edges.
- High-cost redundant cycle-forming edges (such as D-E with wt 15 and F-G with wt 11) are excluded in favor of smaller alternatives (A-D: 5, C-E: 5, D-F: 6, A-B: 7, B-E: 7, E-G: 9)[cite: 20].

-------------------------------------------------

MST Algorithms Comparison

---------------------------------------------------------------------------------------------------------
Algorithm             | Strategy                         | Core Data Structure | Time Complexity
---------------------------------------------------------------------------------------------------------
Prim's Algorithm      | Vertex-based greedy expansion    | PriorityQueue (Heap)| O(E * log V)
Kruskal's Algorithm   | Edge-based greedy set union      | Disjoint Set (DSU)  | O(E * log E)
---------------------------------------------------------------------------------------------------------

Real-World Applications:
- Telecommunications & Network Design: Laying fiber optic cables connecting multiple cities with the minimum total cable distance.
- Circuit Board Design (VLSI): Minimizing wiring between electrical pins without short circuits (cycles).
- Pipeline Distribution: Designing water, gas, or electrical grids at lowest infrastructure cost.
=================================================
*/
