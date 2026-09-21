import java.util.LinkedList;
import java.util.Queue;

public class CompleteBinaryTreeCheck {

    // Node representation for Binary Tree
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Operation: Check if a Binary Tree is a Complete Binary Tree (CBT) - LeetCode 958[cite: 24]
    // A CBT has all levels completely filled except possibly the last, 
    // which is filled strictly from left to right without gaps.[cite: 24]
    public static boolean isCompleteBinaryTree(Node root) {
        if (root == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Flag to mark if a null node has been encountered
        boolean seenNull = false;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr == null) {
                // Once a null node is encountered, all subsequent nodes MUST be null[cite: 24]
                seenNull = true;
            } else {
                // If we encounter a valid node after seeing a null, there is a gap -> Not a CBT[cite: 24]
                if (seenNull) {
                    return false;
                }
                // Add children unconditionally (including null) to preserve BFS spatial order
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Tree 1: Valid CBT (Filled left-to-right at last level)[cite: 24]
        //         1
        //       /   \
        //      2     3
        //     / \   /
        //    4   5 6
        Node tree1 = new Node(1);
        tree1.left = new Node(2);
        tree1.right = new Node(3);
        tree1.left.left = new Node(4);
        tree1.left.right = new Node(5);
        tree1.right.left = new Node(6);

        // Tree 2: Invalid CBT (Gap in last level: left child missing on node 3)[cite: 24]
        //         1
        //       /   \
        //      2     3
        //     / \     \
        //    4   5     6
        Node tree2 = new Node(1);
        tree2.left = new Node(2);
        tree2.right = new Node(3);
        tree2.left.left = new Node(4);
        tree2.left.right = new Node(5);
        tree2.right.right = new Node(6);

        System.out.println("Tree 1 is CBT: " + isCompleteBinaryTree(tree1)); // true
        System.out.println("Tree 2 is CBT: " + isCompleteBinaryTree(tree2)); // false
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 37 — Complete Binary Tree (CBT) & Heaps Foundation[cite: 20, 24]

Definition of a Complete Binary Tree (CBT)[cite: 24]:
A Binary Tree is a Complete Binary Tree if:
1. Every level, except possibly the last level, is completely filled with nodes[cite: 24].
2. At the last level, all nodes are placed as far left as possible (no gaps between nodes)[cite: 24].

Why Heaps Require CBT:
- A Heap is conceptually a tree, but stored physically as a contiguous array[cite: 20, 24].
- A CBT guarantees that when stored in an array by level-order traversal, there are **no null holes/gaps**[cite: 24].
- Zero-gap indexing allows parent-child navigation via simple arithmetic:
    - Left Child   = 2 * i + 1
    - Right Child  = 2 * i + 2
    - Parent Node  = (i - 1) / 2

-------------------------------------------------

Visual Analysis of Examples[cite: 24]

1. Tree 1: Valid CBT (Level 0 & 1 full, Level 2 packed left-to-right)[cite: 24]
         ( )
        /   \
      ( )   ( )      Level 1: Completely full[cite: 24]
      / \   /
    ( ) ( )( )       Level 2: Filled left-to-right, no gaps[cite: 24]
    Array: [0, 1, 2, 3, 4, 5] -> Contiguous, valid heap representation.

2. Tree 2: NOT a CBT (Right child present without left sibling)[cite: 24]
         ( )
        /   \
      ( )   ( )
      /       \
    ( )       ( )    Left child of right parent missing -> Gapped![cite: 24]
    Array: [0, 1, 2, 3, null, null, 6] -> Contains gaps, cannot map to contiguous array.

3. Tree 3: NOT a CBT (Node exists at deeper level before earlier level fills)[cite: 24]
         ( )
        /   \
      ( )   ( )
      /
    ( )
    /
  ( )                Level 2 is not filled before Level 3 begins.

-------------------------------------------------

CBT Evaluation Table for Lecture Slide Examples[cite: 24]

---------------------------------------------------------------------------------------------------------
Diagram Example        | Level Fill Status      | Last Level Orientation        | Is CBT?[cite: 24]
---------------------------------------------------------------------------------------------------------
Top Left Tree          | Levels 0, 1 Full       | Leaf nodes on extreme left    | YES (CBT)[cite: 24]
Top Middle Tree        | Levels 0, 1 Full       | Gap in right subtree          | NO  (Not CBT)[cite: 24]
Top Right / Zig-zag    | Intermediate gaps      | Unbalanced branch depths      | NO  (Not CBT)[cite: 24]
Bottom Left Tree       | Level 1 missing right  | Level 2 populated             | NO  (Not CBT)[cite: 24]
Bottom Middle Tree     | All levels full        | Full binary tree              | YES (CBT)[cite: 24]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Breadth-First Search visits every node at most once[cite: 24].
- Space Complexity: O(w) = O(n) — Queue stores up to $O(n)$ nodes at the maximum level width[cite: 24].
=================================================
*/
