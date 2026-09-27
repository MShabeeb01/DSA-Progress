import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSortingDFS {

    // Representation of a Directed Edge (src -> dest)
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Helper: DFS utility pushing finished vertices onto a Stack
    public static void topSortUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis, Stack<Integer> s) {
        vis[curr] = true;

        // Recurse for all unvisited adjacent neighbors
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                topSortUtil(graph, e.dest, vis, s);
            }
        }

        // Push current vertex to stack once all its downstream dependencies are processed
        s.push(curr);
    }

    // Main Topological Sort function (handles disconnected DAG components)
    public static void topSort(ArrayList<Edge>[] graph) {
        int V = graph.length;
        boolean[] vis = new boolean[V];
        Stack<Integer> s = new Stack<>();

        // Perform DFS from every unvisited node
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                topSortUtil(graph, i, vis, s);
            }
        }

        // Print linear ordering by popping elements from the stack
        System.out.print("Topological Sort Order: ");
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Constructing a standard DAG with 6 vertices (0 to 5)
        // Edges: 5->0, 5->2, 4->0, 4->1, 2->3, 3->1
        int V = 6;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));
        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 1));

        topSort(graph);
        // Valid Output: 5 4 2 3 1 0 (or 4 5 2 3 1 0)
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 41 — Topological Sorting (using DFS)[cite: 26]

Core Definitions[cite: 26]:
1. Directed Acyclic Graph (DAG)[cite: 26]:
   - A directed graph that contains no cycles[cite: 26].
2. Topological Sorting[cite: 26]:
   - Applicable ONLY for DAGs (cannot be performed on cyclic or undirected graphs)[cite: 26].
   - It is a linear ordering of vertices such that for every directed edge u -> v, vertex u comes before v in the order[cite: 26].
   - Real-world application: Task dependency scheduling, course prerequisite resolution, software build order compilation.

Why DFS Uses an Explicit Stack:
- In standard DFS, printing a node upon first entry prints parents before exploring children.
- In graphs with multiple prerequisites, a node must be placed before its dependents.
- By pushing a vertex onto the stack only AFTER all its directed children are fully visited and returned (post-order traversal), dependent nodes end up below the prerequisite node in the stack.
- Popping the stack reverses this, placing all prerequisite nodes (u) before their targets (v)[cite: 26].

-------------------------------------------------

Topological Order Constraint Visual[cite: 26]

Edge Definition:
   ( u ) ------------> ( v )[cite: 26]

Linear Output Array / Stack Popping Order:
   +-----+-------------------+-----+
   |  u  | . . . . . . . . . |  v  |[cite: 26]
   +-----+-------------------+-----+
   (u must appear somewhere to the left of v)[cite: 26]

Example DAG Execution:
   5 ---> 0 <--- 4
   |             |
   v             v
   2 ---> 3 ---> 1

- DFS path starting at 5:
  - 5 -> 2 -> 3 -> 1 (1 has no outgoing edges -> push 1)
  - backtrack to 3 (no other edges -> push 3)
  - backtrack to 2 (no other edges -> push 2)
  - from 5, check 0 (0 has no edges -> push 0)
  - backtrack to 5 (push 5)
- DFS path from 4:
  - from 4, check 0 (already visited)
  - from 4, check 1 (already visited)
  - push 4

Stack content (top to bottom): [5, 4, 2, 3, 1, 0]
Popped output: 5 4 2 3 1 0 (valid topological order)

-------------------------------------------------

Algorithm Lifecycle:
1. Initialize a `visited[]` array and an empty `Stack`.
2. For every vertex $i \in [0, V-1]$:
   - If `!visited[i]`, invoke `topSortUtil(i)`.
3. In `topSortUtil(curr)`:
   - Mark `visited[curr] = true`.
   - For every edge `curr -> dest`:
       - If `!visited[dest]`, recursively call `topSortUtil(dest)`.
   - Once all outgoing edges from `curr` are visited, execute `stack.push(curr)`.
4. Empty the stack to obtain the final topological order.

Complexity Analysis:
- Time Complexity : O(V + E) — Standard DFS traversal visiting every vertex and outgoing edge once across all components.
- Space Complexity: O(V) — Boolean `visited` array, explicit `Stack` to store $V$ elements, and recursion call stack depth bounded by $V$.
=================================================
*/
