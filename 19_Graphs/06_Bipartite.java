import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraphBFS {

    // Representation of a Graph Edge
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // ==========================================================
    // Check if Graph is Bipartite using BFS (Graph Coloring)
    // Time Complexity: O(V + E)
    // ==========================================================
    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        int V = graph.length;
        int[] color = new int[V];
        Arrays.fill(color, -1); // -1 means uncolored

        Queue<Integer> q = new LinkedList<>();

        // Loop over all vertices to handle disconnected graph components
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                // Assign first color (0) to starting component vertex
                q.add(i);
                color[i] = 0;

                while (!q.isEmpty()) {
                    int curr = q.remove();

                    // Check all neighbors of the current vertex
                    for (int j = 0; j < graph[curr].size(); j++) {
                        Edge e = graph[curr].get(j);

                        // Case 1: Neighbor is not colored yet
                        if (color[e.dest] == -1) {
                            int nextColor = (color[curr] == 0) ? 1 : 0; // Opposite color
                            color[e.dest] = nextColor;
                            q.add(e.dest);
                        }
                        // Case 2: Neighbor already has the SAME color as current -> Not Bipartite!
                        else if (color[e.dest] == color[curr]) {
                            return false;
                        }
                        // Case 3: Neighbor has DIFFERENT color -> Valid, continue
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // ==========================================
        // Example 1: Bipartite Graph (Even Cycle: Length 4)
        // 0 --- 1
        // |     |
        // 3 --- 2
        // ==========================================
        int V1 = 4;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph1 = new ArrayList[V1];
        for (int i = 0; i < V1; i++) graph1[i] = new ArrayList<>();

        graph1[0].add(new Edge(0, 1)); graph1[1].add(new Edge(1, 0));
        graph1[1].add(new Edge(1, 2)); graph1[2].add(new Edge(2, 1));
        graph1[2].add(new Edge(2, 3)); graph1[3].add(new Edge(3, 2));
        graph1[3].add(new Edge(3, 0)); graph1[0].add(new Edge(0, 3));

        System.out.println("Graph 1 (Even cycle) is Bipartite? " + isBipartite(graph1)); // true

        // ==========================================
        // Example 2: Non-Bipartite Graph (Odd Cycle: Length 3)
        // 0 --- 1
        //  \   /
        //    2
        // ==========================================
        int V2 = 3;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph2 = new ArrayList[V2];
        for (int i = 0; i < V2; i++) graph2[i] = new ArrayList<>();

        graph2[0].add(new Edge(0, 1)); graph2[1].add(new Edge(1, 0));
        graph2[1].add(new Edge(1, 2)); graph2[2].add(new Edge(2, 1));
        graph2[2].add(new Edge(2, 0)); graph2[0].add(new Edge(0, 2));

        System.out.println("Graph 2 (Odd cycle) is Bipartite?  " + isBipartite(graph2)); // false
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 41 — Bipartite Graph[cite: 24]

Formal Definition[cite: 24]:
- A Bipartite Graph is a graph whose vertices can be divided into two independent sets, $U$ and $V$, such that every edge $(u, v)$ either connects a vertex from $U$ to $V$ or from $V$ to $U$[cite: 24].
- In other words, for every edge $(u, v)$, either $u \in U$ and $v \in V$, or $u \in V$ and $v \in U$[cite: 24].
- There is **no edge** that connects vertices belonging to the same set[cite: 24].

Graph Coloring Formulation (2-Color Problem):
- A graph is bipartite if and only if it can be colored using at most **2 colors** such that no two adjacent vertices share the same color.
- If an adjacent neighbor already has the same color as the current vertex, a conflict occurs, meaning the graph is not bipartite.

Key Cycle Invariant Theorems:
1. **Acyclic Graphs (Trees):** Always Bipartite.
2. **Even Length Cycles:** Always Bipartite (alternating 2 colors resolves cleanly).
3. **Odd Length Cycles:** Never Bipartite (the final closing edge will connect two nodes of the same color).

-------------------------------------------------

Bipartite Independent Sets Visual[cite: 24]

Set 1 (U)[cite: 24]              Set 2 (V)[cite: 24]
  ( Blue )                  ( Orange )
  +----+                      +----+
  | u1 |--------------------->| v1 |
  |    |                      |    |
  | u2 |--------------------->| v2 |
  +----+                      +----+
   (No internal edges           (No internal edges
    between u1 & u2)[cite: 24]  between v1 & v2)[cite: 24]

Every edge strictly bridges across the partition ($U \leftrightarrow V$)[cite: 24].

-------------------------------------------------

Neighbor Coloring Decision Matrix (BFS)

---------------------------------------------------------------------------------------------------------
Neighbor Color State (`color[dest]`) | Relation to `color[curr]` | Action Taken
---------------------------------------------------------------------------------------------------------
Uncolored (`-1`)                     | N/A                       | Assign opposite color: `1 - color[curr]`, push to Queue
Colored                              | Equal (`== color[curr]`)  | Conflict! Graph is NOT Bipartite (Return `false`)
Colored                              | Different (`!= color[curr]`)| Valid 2-coloring, continue exploration
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(V + E) — Standard BFS traversing all vertices and edge lists once across all components.
- Space Complexity: O(V) — Auxiliary space for the `color` array and Queue storage.
=================================================
*/
