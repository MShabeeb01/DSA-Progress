import java.util.ArrayList;
import java.util.Collections;

public class KruskalsAlgorithm {

    // Representation of an Edge implements Comparable for sorting by weight
    static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int wt;

        public Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }

        @Override
        public int compareTo(Edge e2) {
            return this.wt - e2.wt; // Ascending order of edge weights[cite: 22]
        }
    }

    // Disjoint Set Union (DSU) Data Structure
    static class DSU {
        int[] parent;
        int[] rank;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i; // Every node is initially its own parent
                rank[i] = 0;
            }
        }

        // Find with Path Compression: O(alpha(V)) ~ O(1)
        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        // Union by Rank: O(alpha(V)) ~ O(1)
        public boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            // If they share the same representative, adding this edge forms a cycle
            if (rootA == rootB) {
                return false;
            }

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootB] < rank[rootA]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
            return true;
        }
    }

    // ==========================================================
    // Kruskal's Algorithm for Minimum Spanning Tree (MST)[cite: 22]
    // Time Complexity: O(E log E)
    // ==========================================================
    public static void kruskalMST(ArrayList<Edge> edges, int V) {
        // Step 1: Sort all edges in non-decreasing order of their weight[cite: 22]
        Collections.sort(edges); // O(E log E)[cite: 22]

        DSU dsu = new DSU(V);
        int mstCost = 0;
        int count = 0; // Tracks number of edges included in MST (must reach V - 1)

        ArrayList<Edge> mstEdges = new ArrayList<>();

        // Step 2: Greedily pick the smallest edges that do not create cycles[cite: 22]
        for (int i = 0; i < edges.size() && count < V - 1; i++) {
            Edge edge = edges.get(i);

            // If src and dest belong to different sets, union them (no cycle formed)
            if (dsu.union(edge.src, edge.dest)) {
                mstCost += edge.wt;
                mstEdges.add(edge);
                count++;
            }
        }

        System.out.println("Edges included in Kruskal's MST:");
        for (Edge e : mstEdges) {
            System.out.println("(" + e.src + " - " + e.dest + ") with weight " + e.wt);
        }
        System.out.println("Total Minimum Spanning Tree Cost = " + mstCost);
    }

    public static void main(String[] args) {
        int V = 4; // Vertices: 0, 1, 2, 3[cite: 22]
        ArrayList<Edge> edges = new ArrayList<>();

        // Populate edges matching the slide diagram[cite: 22]
        edges.add(new Edge(0, 1, 10)); // (0, 1) -> 10[cite: 22]
        edges.add(new Edge(0, 2, 15)); // (0, 2) -> 15[cite: 22]
        edges.add(new Edge(0, 3, 30)); // (0, 3) -> 30[cite: 22]
        edges.add(new Edge(1, 3, 40)); // (1, 3) -> 40[cite: 22]
        edges.add(new Edge(2, 3, 50)); // (2, 3) -> 50[cite: 22]

        kruskalMST(edges, V);
        /*
          Sorted Edges Evaluation:[cite: 22]
          1. (0, 1) wt 10 -> Taken (count = 1)
          2. (0, 2) wt 15 -> Taken (count = 2)
          3. (0, 3) wt 30 -> Taken (count = 3 = V - 1)
          4. (1, 3) wt 40 -> Cycle detected (0, 1, 3 already connected) -> Skipped
          5. (2, 3) wt 50 -> Cycle detected -> Skipped

          Total MST Cost = 10 + 15 + 30 = 55
        */
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 43 — Kruskal's Algorithm (MST Greedy)[cite: 22]

Core Philosophy:
- Kruskal's is an **edge-based greedy algorithm** designed to find the Minimum Spanning Tree (MST)[cite: 22].
- Rather than growing a single tree from a root node (like Prim's), Kruskal's treats every vertex as an independent forest tree and connects them by selecting the globally cheapest available edges that do not create cycles[cite: 22].

Algorithmic Lifecycle:
1. Sort all edges of the graph in non-decreasing order of their weights[cite: 22].
2. Iterate through the sorted edges one by one[cite: 22]:
   - Use **Disjoint Set Union (DSU)** to check if the endpoints `src` and `dest` belong to the same connected component.
   - If they are in different components: include the edge in the MST, add its weight, and unite their sets (`union(src, dest)`).
   - If they are already in the same component: discard the edge (it would form a cycle).
3. Terminate when exactly $V - 1$ edges have been accepted or all edges have been evaluated.

-------------------------------------------------

Slide Edge Selection & Dry Run Table[cite: 22]

Edges from slide sorted by weight:[cite: 22]
  1. (0, 1) -> Weight 10[cite: 22]
  2. (0, 2) -> Weight 15[cite: 22]
  3. (0, 3) -> Weight 30[cite: 22]
  4. (1, 3) -> Weight 40[cite: 22]
  5. (2, 3) -> Weight 50[cite: 22]

Trace:
---------------------------------------------------------------------------------------------------------
Edge     | Weight | Endpoints Components | Forms Cycle? | Status      | Running MST Cost | Edges Count
---------------------------------------------------------------------------------------------------------
(0, 1)   | 10     | {0} != {1}           | No           | Accepted    | 10               | 1
(0, 2)   | 15     | {0, 1} != {2}        | No           | Accepted    | 25               | 2
(0, 3)   | 30     | {0, 1, 2} != {3}     | No           | Accepted    | 55               | 3 (V - 1 reached)
(1, 3)   | 40     | {0, 1, 2, 3} == {0..}| Yes          | Rejected    | 55               | 3
(2, 3)   | 50     | {0, 1, 2, 3} == {0..}| Yes          | Rejected    | 55               | 3
---------------------------------------------------------------------------------------------------------

-------------------------------------------------

Prim's vs. Kruskal's Comparison

---------------------------------------------------------------------------------------------------------
Feature                  | Prim's Algorithm                    | Kruskal's Algorithm[cite: 22]
---------------------------------------------------------------------------------------------------------
Approach                 | Vertex-based greedy expansion       | Edge-based greedy set union[cite: 22]
Data Structures          | PriorityQueue (Min-Heap) + Visited  | Edge List + Disjoint Set Union (DSU)
Graph Density Preference | Better for dense graphs ($E \approx V^2$) | Better for sparse graphs ($E \ll V^2$)
Cycle Prevention         | Skips already visited nodes in MST  | Uses DSU `find()` to detect cycles
Sorting Requirement      | Relies on continuous heap extraction| Requires one initial sort of all edges[cite: 22]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(E log E) or O(E log V)
  - Sorting all $E$ edges takes $O(E \log E)$[cite: 22].
  - DSU operations (Find and Union with path compression and rank) run in nearly constant time $O(\alpha(V))$.
  - Overall time is dominated by edge sorting: $O(E \log E)$.
- Space Complexity: O(V + E) — Storing the edge list takes $O(E)$, while DSU arrays (`parent` and `rank`) take $O(V)$ space.
=================================================
*/
