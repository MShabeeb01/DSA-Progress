import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSortBFS {

    // Representation of a Directed Edge (src -> dest)
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Graph Construction using Adjacency List matching the slide diagram[cite: 26]
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        /*
           5 ---> 0 <--- 4[cite: 26]
           |             |[cite: 26]
           v             v[cite: 26]
           2 ---> 3 ---> 1[cite: 26]
        */
        graph[5].add(new Edge(5, 0)); //[cite: 26]
        graph[5].add(new Edge(5, 2)); //[cite: 26]
        graph[4].add(new Edge(4, 0)); //[cite: 26]
        graph[4].add(new Edge(4, 1)); //[cite: 26]
        graph[2].add(new Edge(2, 3)); //[cite: 26]
        graph[3].add(new Edge(3, 1)); //[cite: 26]
    }

    // Helper: Calculate the in-degree of all vertices[cite: 26]
    public static void calcIndeg(ArrayList<Edge>[] graph, int[] indeg) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                indeg[e.dest]++; // Incoming edge detected at e.dest[cite: 26]
            }
        }
    }

    // ==========================================================
    // Kahn's Algorithm (Topological Sort using BFS) -> O(V + E)[cite: 26]
    // ==========================================================
    public static void topSortKahn(ArrayList<Edge>[] graph) { //[cite: 26]
        int V = graph.length;
        int[] indeg = new int[V]; //[cite: 26]
        calcIndeg(graph, indeg); //[cite: 26]

        Queue<Integer> q = new LinkedList<>();

        // Step 1: Add all vertices with in-degree == 0 into the Queue[cite: 26]
        for (int i = 0; i < V; i++) {
            if (indeg[i] == 0) { //[cite: 26]
                q.add(i);
            }
        }

        System.out.print("Topological Sort (Kahn's / BFS): "); //[cite: 26]

        // Step 2: Standard BFS traversal[cite: 26]
        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + " "); // Process current vertex

            // Step 3: Decrement in-degree for all neighboring destinations[cite: 26]
            for (int i = 0; i < graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                indeg[e.dest]--; //[cite: 26]

                // If in-degree becomes 0, all dependencies are cleared -> Enqueue[cite: 26]
                if (indeg[e.dest] == 0) { //[cite: 26]
                    q.add(e.dest);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int V = 6;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);

        topSortKahn(graph); //[cite: 26]
        // Valid Output: 4 5 0 2 3 1 (or 5 4 0 2 3 1 / 5 4 2 3 1 0)
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 42 — Topological Sort using BFS (Kahn's Algorithm)[cite: 26]

Core Definitions[cite: 26]:
1. In-Degree[cite: 26]:
   - The total number of incoming edges pointing towards a particular vertex[cite: 26].
   - Represents the count of unfulfilled prerequisites/dependencies that must be completed first.
2. Out-Degree[cite: 26]:
   - The total number of outgoing edges starting from a vertex to other vertices[cite: 26].

Kahn's Algorithm Intuition:
- A vertex with `in-degree == 0` has ZERO dependencies and can be processed immediately[cite: 26].
- When a node is processed and "removed", we reduce the in-degree of all its downstream neighbors by 1[cite: 26].
- Any neighbor whose in-degree drops to 0 now has all its prerequisites resolved and enters the BFS queue[cite: 26].

-------------------------------------------------

Step-by-Step Degree Analysis from the Lecture Graph[cite: 26]

Graph Structure[cite: 26]:
       5 ---> 0 <--- 4[cite: 26]
       |             |[cite: 26]
       v             v[cite: 26]
       2 ---> 3 ---> 1[cite: 26]

Initial In-Degrees[cite: 26]:
- Vertex 0: In-Degree = 2 (from 5, 4)[cite: 26]
- Vertex 1: In-Degree = 2 (from 4, 3)[cite: 26]
- Vertex 2: In-Degree = 1 (from 5)[cite: 26]
- Vertex 3: In-Degree = 1 (from 2)[cite: 26]
- Vertex 4: In-Degree = 0 (No incoming edges)[cite: 26]
- Vertex 5: In-Degree = 0 (No incoming edges)[cite: 26]

Execution Flow[cite: 26]:
1. Queue initialized with in-degree 0 nodes: Q = [4, 5][cite: 26]
2. Pop 4 -> Print 4:
   - Decrement neighbor 0: in-degree[0] becomes 1
   - Decrement neighbor 1: in-degree[1] becomes 1
3. Pop 5 -> Print 5:
   - Decrement neighbor 0: in-degree[0] becomes 0 -> Push 0 to Q[cite: 26]
   - Decrement neighbor 2: in-degree[2] becomes 0 -> Push 2 to Q[cite: 26]
   - Q = [0, 2]
4. Pop 0 -> Print 0 (no outgoing edges)
5. Pop 2 -> Print 2:
   - Decrement neighbor 3: in-degree[3] becomes 0 -> Push 3 to Q[cite: 26]
   - Q = [3]
6. Pop 3 -> Print 3:
   - Decrement neighbor 1: in-degree[1] becomes 0 -> Push 1 to Q[cite: 26]
   - Q = [1]
7. Pop 1 -> Print 1 (no outgoing edges)

Output Sequence: 4 -> 5 -> 0 -> 2 -> 3 -> 1[cite: 26]

-------------------------------------------------

DFS vs. BFS (Kahn's) Topological Sort

---------------------------------------------------------------------------------------------------------
Feature                  | DFS Approach (Chapter 41)           | BFS / Kahn's Approach (Chapter 42)[cite: 26]
---------------------------------------------------------------------------------------------------------
Data Structure Used      | Explicit Stack + Visited Array      | Queue + In-Degree Array[cite: 26]
Processing Timing        | Post-order (pushed when backtracking)| Pre-order (printed when in-degree hits 0)[cite: 26]
Cycle Detection Utility  | Requires active recursion stack array | Can count popped elements (`count < V` -> cycle)
Intuition                | Dive deep, resolve dependencies backwards | Resolve zero-dependency nodes forward[cite: 26]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(V + E) — In-degree calculation takes O(V + E); every vertex enters/leaves the queue once, and all outgoing edges are traversed once[cite: 26].
- Space Complexity: O(V) — Memory for the `indeg` array and the BFS `Queue`[cite: 26].
=================================================
*/
