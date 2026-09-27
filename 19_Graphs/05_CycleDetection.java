import java.util.ArrayList;

public class CycleDetectionUndirected {

    // Representation of an Edge in an undirected graph
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Helper: DFS traversal tracking current node and parent node
    public static boolean detectCycleUtil(ArrayList<Edge>[] graph, boolean[] visited, int curr, int par) {
        visited[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);

            // Case 3: Neighbor is visited and is NOT the parent -> Cycle detected!
            if (visited[e.dest] && e.dest != par) {
                return true;
            }

            // Case 1: Neighbor is unvisited -> Recurse into it with curr as the new parent
            if (!visited[e.dest]) {
                if (detectCycleUtil(graph, visited, e.dest, curr)) {
                    return true;
                }
            }

            // Case 2: Neighbor is visited and IS the parent (e.dest == par) -> Do nothing, continue
        }

        return false;
    }

    // Main function handling disconnected graph components
    public static boolean detectCycle(ArrayList<Edge>[] graph) {
        int V = graph.length;
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                // Parent of starting node is -1
                if (detectCycleUtil(graph, visited, i, -1)) {
                    return true; // Cycle exists in this component
                }
            }
        }

        return false; // No cycle found in any component
    }

    public static void main(String[] args) {
        // ==========================================
        // Graph 1: With Cycle (Left slide diagram)
        // Vertices: 0, 1, 2, 3, 4, 5, 6
        // ==========================================
        int V1 = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] cycleGraph = new ArrayList[V1];
        for (int i = 0; i < V1; i++) cycleGraph[i] = new ArrayList<>();

        // Edges: 0-1, 0-2, 1-3, 2-4, 3-4, 3-5, 4-5, 5-6
        cycleGraph[0].add(new Edge(0, 1)); cycleGraph[1].add(new Edge(1, 0));
        cycleGraph[0].add(new Edge(0, 2)); cycleGraph[2].add(new Edge(2, 0));
        cycleGraph[1].add(new Edge(1, 3)); cycleGraph[3].add(new Edge(3, 1));
        cycleGraph[2].add(new Edge(2, 4)); cycleGraph[4].add(new Edge(4, 2));
        cycleGraph[3].add(new Edge(3, 4)); cycleGraph[4].add(new Edge(4, 3)); // Cycle here (1-3-4-2-0)
        cycleGraph[3].add(new Edge(3, 5)); cycleGraph[5].add(new Edge(5, 3));
        cycleGraph[4].add(new Edge(4, 5)); cycleGraph[5].add(new Edge(5, 4)); // Cycle here (3-4-5)
        cycleGraph[5].add(new Edge(5, 6)); cycleGraph[6].add(new Edge(6, 5));

        System.out.println("Graph 1 (cycle) has cycle?    " + detectCycle(cycleGraph)); // true

        // ==========================================
        // Graph 2: No Cycle (Right slide diagram)
        // Vertices: 0, 1, 2, 3, 4, 6
        // ==========================================
        int V2 = 7; // Max vertex label is 6
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] noCycleGraph = new ArrayList[V2];
        for (int i = 0; i < V2; i++) noCycleGraph[i] = new ArrayList<>();

        // Edges: 0-1, 0-2, 1-3, 2-4, 2-6 (Tree structure)
        noCycleGraph[0].add(new Edge(0, 1)); noCycleGraph[1].add(new Edge(1, 0));
        noCycleGraph[0].add(new Edge(0, 2)); noCycleGraph[2].add(new Edge(2, 0));
        noCycleGraph[1].add(new Edge(1, 3)); noCycleGraph[3].add(new Edge(3, 1));
        noCycleGraph[2].add(new Edge(2, 4)); noCycleGraph[4].add(new Edge(4, 2));
        noCycleGraph[2].add(new Edge(2, 6)); noCycleGraph[6].add(new Edge(6, 2));

        System.out.println("Graph 2 (no cycle) has cycle? " + detectCycle(noCycleGraph)); // false
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 41 — Cycle Detection in Undirected Graph (DFS)[cite: 23]

Core Principle:
In an undirected graph, an edge connects two nodes mutually (A to B and B to A)[cite: 23]. 
When traversing via DFS from parent node `par` to child node `curr`, an automatic back-link 
to `par` will always be encountered[cite: 23]. Therefore, encountering a visited node does NOT automatically 
mean there is a cycle[cite: 23]—it is only a cycle if that visited node is NOT the parent[cite: 23]!

The 3 Exhaustive Conditions for Neighbor `dest`:
1. `!visited[dest]`:
   - Normal forward branch.
   - Recurse deeply: `detectCycleUtil(graph, visited, dest, curr)`[cite: 23].
2. `visited[dest] && dest == par`:
   - Trivial reverse edge back to the direct caller node.
   - Safe; ignore and continue exploration.
3. `visited[dest] && dest != par`:
   - Found an alternative path that circles back to an already visited ancestor.
   - **Cycle Detected!** Return `true` immediately[cite: 23].

-------------------------------------------------

Visual Comparison of the Two Slide Graphs[cite: 23]

1. Graph with Cycle:[cite: 23]
         (1) ----- (3)
        /           | \
      (0)           |  (5) --- (6)[cite: 23]
        \           | /
         (2) ----- (4)
   - Cycles present: (0-1-3-4-2-0), (3-4-5-3)[cite: 23].
   - Tracing: DFS visits 0 -> 1 -> 3 -> 4. At node 4, neighbors are 2, 3 (parent), and 5.
     If it visits 2, from 2 it sees neighbor 0 (visited, but not parent 4) -> Cycle detected[cite: 23]!

2. Graph with No Cycle:[cite: 23]
         (1) ----- (3)
        /
      (0)
        \
         (2) ----- (4)
           \
            (6)[cite: 23]
   - A tree structure (connected, acyclic)[cite: 23].
   - Every visited neighbor encountered is strictly the node's parent[cite: 23].
   - DFS completes cleanly with result: `false`[cite: 23].

-------------------------------------------------

Step-by-Step Decision Matrix

---------------------------------------------------------------------------------------------------------
Neighbor State                  | Is Neighbor Equal to Parent? | Action Taken
---------------------------------------------------------------------------------------------------------
Not Visited (`!visited[dest]`)  | Irrelevant                   | Call DFS recursively with `par = curr`
Visited (`visited[dest]`)       | Yes (`dest == par`)          | Continue (Ignore parent back-edge)
Visited (`visited[dest]`)       | No (`dest != par`)           | Return TRUE (Cycle Found)[cite: 23]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(V + E) — Standard DFS visiting all vertices and edge lists once across all components[cite: 23].
- Space Complexity: O(V) — Boolean `visited` array plus call stack depth bounded by $V$ in the worst-case skewed tree.
=================================================
*/
