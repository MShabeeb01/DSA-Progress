import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {

    // Node representation for Binary Tree
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Binary Tree builder helper
    static class BinaryTree {
        static int idx = -1;

        public static Node buildTree(int nodes[]) {
            idx++;
            if (idx >= nodes.length || nodes[idx] == -1) {
                return null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }

        // Operation: Level Order Traversal (BFS) with line breaks using null delimiter - O(n)
        public static void levelOrder(Node root) {
            if (root == null) {
                return;
            }

            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null); // Delimiter to indicate the end of the current level

            while (!q.isEmpty()) {
                Node currNode = q.remove();

                if (currNode == null) {
                    System.out.println(); // Current level ends -> move to next line
                    if (q.isEmpty()) {
                        break; // All levels completely processed
                    } else {
                        q.add(null); // Re-add delimiter for the next level
                    }
                } else {
                    System.out.print(currNode.data + " ");

                    // Enqueue left child if it exists
                    if (currNode.left != null) {
                        q.add(currNode.left);
                    }

                    // Enqueue right child if it exists
                    if (currNode.right != null) {
                        q.add(currNode.right);
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        // Preorder sequence with -1 as null marker
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree.idx = -1;
        Node root = BinaryTree.buildTree(nodes);

        System.out.println("Level Order Traversal:");
        BinaryTree.levelOrder(root);
        /*
         Output:
         1 
         2 3 
         4 5 6 
        */
    }
}

/*
==================== SUMMARY ====================

Problem: Binary Tree Level Order Traversal (LeetCode 102 / Breadth-First Search)

Objective:
Visit and process nodes level-by-level from top to bottom, and left to right within each level.

Core Concept: Queue (FIFO Dynamics with Sentinel Delimiter)
- Unlike DFS traversals (Preorder, Inorder, Postorder) which use recursive call stacks (LIFO), 
  Level Order uses an explicit Queue (FIFO) to explore nodes at the current depth before moving to the next.
- Sentinel Marker (`null`):
  - Inserting `null` into the queue signifies the boundary of the current level.
  - When `null` is removed:
    - Print a newline.
    - If elements remain in the queue, push another `null` to mark the end of the next layer.

Algorithmic Steps:
1. Base check: If `root == null`, return.
2. Initialize `Queue<Node> q = new LinkedList<>()`.
3. Add `root` followed by `null` marker: `q.add(root); q.add(null);`.
4. While `!q.isEmpty()`:
   - `curr = q.remove();`
   - If `curr == null`:
     - Print newline `\n`.
     - If queue is empty, terminate.
     - Else, `q.add(null)`.
   - Else:
     - Print `curr.data`.
     - If `curr.left != null`, `q.add(curr.left)`.
     - If `curr.right != null`, `q.add(curr.right)`.

-------------------------------------------------

Level-by-Level Tree Breakdown Visual

Constructed Tree:

      Level 1:           1
                       /   \
      Level 2:        2     3
                     / \     \
      Level 3:      4   5     6

Layer Output:
  Line 1: 1
  Line 2: 2 3
  Line 3: 4 5 6

-------------------------------------------------

Step-by-Step Trace Table

-----------------------------------------------------------------------------------------------------------------
Step | `curr` Removed | Is Null? | Printed Output | Children Added to Queue | Queue State (Front -> Rear)
-----------------------------------------------------------------------------------------------------------------
Init | -              | -        | -              | root (1), null          | [1, null]
1    | Node(1)        | No       | "1 "           | Node(2), Node(3)        | [null, 2, 3]
2    | null           | Yes      | "\n"           | null                    | [2, 3, null]
3    | Node(2)        | No       | "2 "           | Node(4), Node(5)        | [3, null, 4, 5]
4    | Node(3)        | No       | "3 "           | Node(6)                 | [null, 4, 5, 6]
5    | null           | Yes      | "\n"           | null                    | [4, 5, 6, null]
6    | Node(4)        | No       | "4 "           | None                    | [5, 6, null]
7    | Node(5)        | No       | "5 "           | None                    | [6, null]
8    | Node(6)        | No       | "6 "           | None                    | [null]
9    | null           | Yes      | "\n"           | None (q is now empty)   | [] -> Terminate
-----------------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Every node is inserted and removed from the queue exactly once.
- Space Complexity: O(w) = O(n) — In the worst-case (a complete binary tree), the queue holds the maximum width $w$ of the tree, which is roughly $n/2$ leaf nodes at the bottom level.
=================================================
*/
