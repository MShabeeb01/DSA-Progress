import java.util.ArrayList;

public class GraphEdgeTypes {

    // Representation of a Graph Edge
    static class Edge {
        int src;  // Source vertex
        int dest; // Destination vertex

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }

        @Override
        public String toString() {
            return "(" + src + " -> " + dest + ")";
        }
    }

    public static void main(String[] args) {
        // Number of vertices: 2 (Node 0: 'A', Node 1: 'B')
        int V = 2;

        // ==========================================================
        // 1. Uni-Directional (Directed) Edge: A -> B
        // ==========================================================
        // Traversal is strictly one-way: from source A to destination B
        ArrayList<Edge>[] uniGraph = new ArrayList[V];
        for (int i = 0; i < V; i++) uniGraph[i] = new ArrayList<>();

        uniGraph[0].add(new Edge(0, 1)); // Edge: A -> B only

        System.out.println("1. Uni-Directional Graph (A -> B):");
        System.out.println("   Adjacency from A (0): " + uniGraph[0]);
        System.out.println("   Adjacency from B (1): " + uniGraph[1]); // Empty

        // ==========================================================
        // 2. Un-Directed Edge: A — B
        // ==========================================================
        // Traversal is non-directional: A and B are mutual neighbors
        // Stored programmatically as two mutual links: (A -> B) and (B -> A)
        ArrayList<Edge>[] undirectedGraph = new ArrayList[V];
        for (int i = 0; i < V; i++) undirectedGraph[i] = new ArrayList<>();

        undirectedGraph[0].add(new Edge(0, 1)); // A -> B
        undirectedGraph[1].add(new Edge(1, 0)); // B -> A

        System.out.println("\n2. Un-Directed Graph (A — B):");
        System.out.println("   Adjacency from A (0): " + undirectedGraph[0]);
        System.out.println("   Adjacency from B (1): " + undirectedGraph[1]);

        // ==========================================================
        // 3. Bi-Directional Edge: A <-> B
        // ==========================================================
        // Explicit dual directed paths: one edge going A -> B, another B -> A
        ArrayList<Edge>[] biGraph = new ArrayList[V];
        for (int i = 0; i < V; i++) biGraph[i] = new ArrayList<>();

        biGraph[0].add(new Edge(0, 1)); // Forward directed edge
        biGraph[1].add(new Edge(1, 0)); // Backward directed edge

        System.out.println("\n3. Bi-Directional Graph (A <==> B):");
        System.out.println("   Adjacency from A (0): " + biGraph[0]);
        System.out.println("   Adjacency from B (1): " + biGraph[1]);
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 40 — Types of Graphs (Based on Edge Direction)[cite: 20]

Fundamental Graph Components:
- Vertices / Nodes ($V$): Points representing entities (e.g., A, B)[cite: 20].
- Edges ($E$): Connections/links between pairs of vertices defining traversal relationships[cite: 20].

Core Edge Classifications[cite: 20]:

1. Uni-Directional (Directed Edge / Digraph)[cite: 20]:
   - Has a defined origin and destination ($A \rightarrow B$)[cite: 20].
   - One-way traffic: Traversal is valid only from $A$ to $B$, not from $B$ to $A$[cite: 20].
   - Real-world analogy: Twitter/Instagram followers (A follows B, but B does not necessarily follow A), one-way streets.

2. Un-Directed Edge[cite: 20]:
   - Edge without arrowheads or defined polarity ($A - B$)[cite: 20].
   - Two-way connection: If vertex $A$ is connected to vertex $B$, vertex $B$ is inherently connected to $A$[cite: 20].
   - Real-world analogy: Facebook friendship (mutual relationship), two-way road between two cities.

3. Bi-Directional Edge[cite: 20]:
   - Explicit directed edges running in both directions ($A \rightleftarrows B$)[cite: 20].
   - Conceptually and programmatically behaves identically to an undirected edge[cite: 20].
   - In graph adjacency representations, an undirected edge between $(u, v)$ is implemented as two directed edges: $(u \rightarrow v)$ and $(v \rightarrow u)$[cite: 20].

-------------------------------------------------

Visual Comparison of Edge Typologies[cite: 20]

1. Uni-Directional[cite: 20]:
   ( A ) --------> ( B )[cite: 20]
   Path exists: A to B. Path does NOT exist: B to A.

2. Un-Directed[cite: 20]:
   ( A ) --------- ( B )[cite: 20]
   Mutual path: A can reach B, and B can reach A.

3. Bi-Directional[cite: 20]:
   ( A ) --------> ( B )[cite: 20]
   ( A ) <-------- ( B )[cite: 20]
   Two distinct directional edges creating mutual connectivity.

-------------------------------------------------

Graph Classification Matrix (Based on Edges)[cite: 20]

---------------------------------------------------------------------------------------------------------
Edge Type             | Notation         | Traversal Rule           | Adjacency List Storage
---------------------------------------------------------------------------------------------------------
Uni-Directional[cite: 20]      | A -> B[cite: 20]          | A to B only              | Add edge in adj[A] only
Un-Directed[cite: 20]          | A — B[cite: 20]           | A to B AND B to A        | Add edge in both adj[A] and adj[B]
Bi-Directional[cite: 20]       | A <-> B[cite: 20]         | Dual directed paths      | Add edge in both adj[A] and adj[B]
---------------------------------------------------------------------------------------------------------

Complexity Implications:
- For an Un-directed / Bi-directional graph: Degree of node = in-degree + out-degree; total edges stored in adjacency list = $2 \times |E|$.
- For a Uni-directional graph: In-degree and out-degree are independent; total edges stored in adjacency list = $|E|$.
=================================================
*/
