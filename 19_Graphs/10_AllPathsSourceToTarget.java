import java.util.ArrayList;

public class AllPathsSourceToTarget {

    // Representation of a Directed Edge (src -> dest)
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
           5 ---> 0 ---> 3 ---> 1
           |             ^      ^
           v             |      |
           2 ------------+      4
        */
        graph[0].add(new Edge(0, 3));
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 1));
        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));
        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));
    }

    // ==========================================================
    // Backtracking / DFS: Print All Paths from Source to Target
    // Time Complexity: Exponential in worst case, O(V^V)
    // ==========================================================
    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dest, String path) {
        // Base Case: Target vertex reached
        if (src == dest) {
            System.out.println(path + dest);
            return;
        }

        // Recurse for all outgoing directed neighbors
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            // Append current step and continue downward
            printAllPaths(graph, e.dest, dest, path + src + " -> ");
        }
    }

    public static void main(String[] args) {
        int V = 6; // Vertices: 0, 1, 2, 3, 4, 5
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);

        // Parameters from slide: s = 5, d = 1
        int src = 5;
        int dest = 1;

        System.out.println("All paths from " + src + " to " + dest + ":");
        printAllPaths(graph, src, dest, "");
        /*
          Expected Paths:
          5 -> 0 -> 3 -> 1
          5 -> 2 -> 3 -> 1
        */
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 42 — All Paths from Source to Target (Directed Graph)[cite: 17]

Problem Statement:
- Given a directed graph (specifically a DAG)[cite: 17], a source node $s$, and a destination node $d$[cite: 17], print/find all possible unique paths leading from $s$ to $d$.
- Parameters in lecture slide: $s = 5$, $d = 1$[cite: 17].

Algorithmic Strategy (DFS with Path Backtracking):
1. Traversal: Start DFS from the source node `src`, accumulating nodes visited in a path string/list.
2. Base Condition:
   - When `src == dest`, a full valid route has been completed. Print or store the current accumulated path and return.
3. Neighbors Exploration:
   - Iterate over each outgoing edge `src -> neighbor`.
   - Pass the accumulated route `path + src + " -> "` and recurse with `neighbor` as the new source.
4. Cycle Guard / Visited Array Note:
   - For general graphs containing cycles, a `boolean[] visited` array must be marked `true` before traversing neighbors and unmarked (`visited[curr] = false`) during backtracking so other paths can reuse that node.
   - For Directed Acyclic Graphs (DAGs) like the one shown, a node cannot loop back into itself, so simple recursive branch propagation is safe and visits paths without infinite looping.

-------------------------------------------------

Slide Graph Trace Visual (s = 5, d = 1)[cite: 17]

Graph Topology:
       (5)           (4)
      /   \         /   \
     v     v       v     v
   (2)     (0)    (0)    (1)
     \     /
      v   v
       (3)
        |
        v
       (1)[cite: 17]

Exploration Paths from Node 5:
- Branch 1: 5 -> 0 -> 3 -> 1 (Destination reached!)[cite: 17]
- Branch 2: 5 -> 2 -> 3 -> 1 (Destination reached!)[cite: 17]

Total unique paths found: 2

-------------------------------------------------

Step-by-Step Call Stack Lifecycle

---------------------------------------------------------------------------------------------------------
Call Stack Frame                 | Current Node | Accumulated Path String | Action
---------------------------------------------------------------------------------------------------------
printAllPaths(5, 1, "")          | 5            | ""                      | Explore neighbor 0
printAllPaths(0, 1, "5 -> ")     | 0            | "5 -> "                 | Explore neighbor 3
printAllPaths(3, 1, "5 -> 0 -> ")| 3            | "5 -> 0 -> "            | Explore neighbor 1
printAllPaths(1, 1, ... )        | 1 (dest)     | "5 -> 0 -> 3 -> 1"      | Print Path & Return
(Backtrack to Node 5)            | 5            | ""                      | Explore neighbor 2
printAllPaths(2, 1, "5 -> ")     | 2            | "5 -> "                 | Explore neighbor 3
printAllPaths(3, 1, "5 -> 2 -> ")| 3            | "5 -> 2 -> "            | Explore neighbor 1
printAllPaths(1, 1, ... )        | 1 (dest)     | "5 -> 2 -> 3 -> 1"      | Print Path & Return
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(V^V) worst-case exponential — In a complete DAG, the number of paths between two vertices can grow exponentially ($2^{V-1}$).
- Space Complexity: O(V) auxiliary recursion stack space — Limited by the maximum depth of any single acyclic path (at most $V$ frames on the stack).
=================================================
*/
