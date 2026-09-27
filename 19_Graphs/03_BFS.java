import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    // Representation of a Graph Edge
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Graph Construction using Adjacency List
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        /*
              (0)
             /   \
           (1)   (2)
          /  \     \
        (3)  (4)   (5)
               \   /
                (6)
        */

        // 0-Vertex
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        // 1-Vertex
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        graph[1].add(new Edge(1, 4));

        // 2-Vertex
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 5));

        // 3-Vertex
        graph[3].add(new Edge(3, 1));

        // 4-Vertex
        graph[4].add(new Edge(4, 1));
        graph[4].add(new Edge(4, 6));

        // 5-Vertex
        graph[5].add(new Edge(5, 2));
        graph[5].add(new Edge(5, 6));

        // 6-Vertex
        graph[6].add(new Edge(6, 4));
        graph[6].add(new Edge(6, 5));
    }

    // ==========================================================
    // Breadth First Search (BFS) -> O(V + E)[cite: 16]
    // ==========================================================
    public static void bfs(ArrayList<Edge>[] graph, int startNode) { //[cite: 16]
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];

        // Step 1: Add source node and mark it visited
        q.add(startNode);
        visited[startNode] = true;

        System.out.print("BFS Traversal Order: ");

        // Step 2: Loop until queue is empty
        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + " ");

            // Step 3: Enqueue all unvisited neighbors
            for (int i = 0; i < graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                if (!visited[e.dest]) {
                    visited[e.dest] = true; // Mark visited immediately upon enqueuing
                    q.add(e.dest);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int V = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);

        // Perform BFS starting from Node 0
        bfs(graph, 0); //[cite: 16]
        // Output: 0 1 2 3 4 5 6
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 40 — Graph Traversals: Breadth First Search (BFS)[cite: 16]

Core Traversal Philosophy:
- BFS visits vertices level by level, starting from a designated source node (similar to Level Order Traversal in Trees)[cite: 16].
- It explores all immediate immediate neighbors (distance 1) before exploring neighbors of neighbors (distance 2).

Essential Data Structures Used:
1. Queue (FIFO): Holds vertices awaiting exploration to maintain level-order discipline.
2. Visited Array (`boolean[] visited`): Crucial to prevent infinite loops caused by cycles in the graph and redundant processing.

Step-by-Step Algorithm:
1. Push the starting vertex into the Queue and mark `visited[start] = true`.
2. While the Queue is not empty:
   a. Dequeue current vertex `curr = q.remove()`.
   b. Process/print `curr`.
   c. For every edge `curr -> dest`:
      - If `!visited[dest]`:
          - Mark `visited[dest] = true`.
          - Add `dest` to Queue.

-------------------------------------------------

BFS Level Breakdown Visual

Starting at Source Vertex (0):
  Level 0:  (0)
             |
  Level 1:  (1) -------- (2)
            /   \          \
  Level 2: (3)  (4)        (5)
                  \        /
  Level 3:           (6)

Traversal Order:
  0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6

-------------------------------------------------

Handling Disconnected Graphs (Components):
If the graph contains multiple disconnected components, wrap BFS in an outer loop:
  for (int i = 0; i < V; i++) {
      if (!visited[i]) {
          bfsComponent(graph, visited, i);
      }
  }

-------------------------------------------------

Complexity Analysis:
- Time Complexity : O(V + E)
  - Every vertex $V$ is pushed and popped from the queue exactly once: O(V).
  - Every edge $E$ in the adjacency list is traversed when examining neighbors: O(E).
- Space Complexity: O(V)
  - Queue stores up to $O(V)$ vertices at peak width.
  - Visited boolean array requires size $V$: O(V).
=================================================
*/
