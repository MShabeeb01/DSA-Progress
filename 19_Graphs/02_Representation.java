import java.util.ArrayList;

public class StoringAGraph {

    // Helper edge class for Adjacency List and Edge List
    static class Edge {
        int src;
        int dest;
        int wt; // Optional weight

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }

        @Override
        public String toString() {
            return "(" + src + " -> " + dest + ", wt: " + wt + ")";
        }
    }

    public static void main(String[] args) {
        int V = 4; // Vertices: 0, 1, 2, 3

        // ==========================================================
        // 1. Adjacency List (Array of ArrayLists / Lists) - Most Common
        // ==========================================================
        // Space: O(V + E)
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] adjList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }

        // Adding edges: 0-1, 0-2, 1-3
        adjList[0].add(new Edge(0, 1, 1));
        adjList[0].add(new Edge(0, 2, 1));
        adjList[1].add(new Edge(1, 3, 1));

        System.out.println("--- 1. Adjacency List ---");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " neighbors: " + adjList[i]);
        }

        // ==========================================================
        // 2. Adjacency Matrix (2D Array)
        // ==========================================================
        // Space: O(V^2), Edge lookup: O(1)
        int[][] adjMatrix = new int[V][V];
        adjMatrix[0][1] = 1;
        adjMatrix[0][2] = 1;
        adjMatrix[1][3] = 1;

        System.out.println("\n--- 2. Adjacency Matrix ---");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }

        // ==========================================================
        // 3. Edge List (Linear Collection of All Edges)
        // ==========================================================
        // Space: O(E), Useful for Kruskal's MST, Bellman-Ford
        ArrayList<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(0, 1, 1));
        edgeList.add(new Edge(0, 2, 1));
        edgeList.add(new Edge(1, 3, 1));

        System.out.println("\n--- 3. Edge List ---");
        System.out.println(edgeList);

        // ==========================================================
        // 4. 2D Matrix (Implicit Graph / Grid)
        // ==========================================================
        // Used in Flood Fill, Number of Islands, Maze Traversal
        // Cells represent vertices; adjacent cells (up/down/left/right) are implicit edges
        char[][] grid = {
            {'1', '1', '0'},
            {'0', '1', '0'},
            {'0', '0', '1'}
        };

        System.out.println("\n--- 4. 2D Matrix (Implicit Graph Grid) ---");
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 40 — Graph Representations: Storing a Graph[cite: 17]

Overview of the 4 Primary Representation Models[cite: 17]:

1. Adjacency List[cite: 17]:
   - Structure: An array (or list) of collections: `ArrayList<Edge>[] graph = new ArrayList[V]`[cite: 17].
   - Each index $i$ represents vertex $i$ and stores a list of all its outgoing edges/neighbors[cite: 17].
   - Primary Advantage: Space-efficient for sparse graphs ($O(V + E)$). Optimal for graph traversals (BFS, DFS)[cite: 17].

2. Adjacency Matrix[cite: 17]:
   - Structure: A 2D boolean or integer matrix `int[][] matrix = new int[V][V]`[cite: 17].
   - `matrix[u][v] = 1` (or weight) indicates an edge exists from $u$ to $v$; `0` indicates no edge[cite: 17].
   - Primary Advantage: Instant $O(1)$ edge existence verification (`isNeighbor(u, v)`).
   - Drawback: Consumes $O(V^2)$ memory regardless of edge density; scanning neighbors takes $O(V)$.

3. Edge List[cite: 17]:
   - Structure: A simple unordered list or array storing all edge instances: `List<Edge> edges`[cite: 17].
   - Primary Advantage: Highly compact storage ($O(E)$); perfect for algorithms that sort or iterate over all edges sequentially (e.g., Kruskal's Minimum Spanning Tree, Bellman-Ford).

4. 2D Matrix / Grid (Implicit Graph)[cite: 17]:
   - Structure: A standard 2D grid/array where vertices and edges are not explicitly defined as classes[cite: 17].
   - Graph Semantics: Every coordinate $(r, c)$ acts as a vertex[cite: 17].
   - Implicit Edges: Movements to adjacent neighbors (up, down, left, right: $(r \pm 1, c \pm 1)$) act as edges governed by validity boundaries[cite: 17].
   - Common Use: Grid-based BFS/DFS, Flood Fill, Shortest Path in Maze, Island Counting[cite: 17].

-------------------------------------------------

Graph Representations Comparison Table[cite: 17]

-------------------------------------------------------------------------------------------------------------------------
Representation       | Space Complexity | Find Neighbors of $u$ | Check if $(u, v)$ is Edge | Best Application
-------------------------------------------------------------------------------------------------------------------------
Adjacency List   | O(V + E)         | O(deg(u))             | O(deg(u))                 | Standard BFS/DFS, Dijkstra, General graphs[cite: 17]
Adjacency Matrix   | O(V^2)           | O(V)                  | O(1)                      | Dense graphs ($E \approx V^2$), Warshall/Floyd
Edge List          | O(E)             | O(E)                  | O(E)                      | Kruskal's MST, Bellman-Ford
2D Implicit Grid   | O(R * C)         | O(1) (up to 4/8 dirs) | O(1)                      | Matrix pathfinding, Flood Fill[cite: 17]
-------------------------------------------------------------------------------------------------------------------------

Complexity Note:
- Most competitive coding and interview graph problems utilize either the Adjacency List for node-edge graphs or the Implicit 2D Matrix for grid layouts[cite: 17].
=================================================
*/
