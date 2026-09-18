public class BinarySearchTreeBasics {

    // Node structure for Binary Search Tree
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

    // Helper: Insert a node into BST maintaining the BST invariant - O(H)
    public static Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        }

        if (val < root.data) {
            root.left = insert(root.left, val);
        } else if (val > root.data) {
            root.right = insert(root.right, val);
        }

        return root;
    }

    // Inorder Traversal of BST: Always produces strictly non-decreasing sorted order
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        int[] values = {4, 2, 5, 1, 3, 6};
        Node root = null;

        for (int val : values) {
            root = insert(root, val);
        }

        System.out.print("BST Inorder Traversal (Sorted Output): ");
        inorder(root);
        System.out.println();
        // Output: 1 2 3 4 5 6
    }
}

/*
==================== SUMMARY ====================

Topic: Introduction to Binary Search Trees (BST)

Definition & Core Properties:
A Binary Search Tree is a specialized node-based binary tree data structure that satisfies three fundamental rules:
1. Left Subtree Invariant: Every node in the left subtree has a key strictly less than the root's key (Left Subtree < Root)[cite: 23].
2. Right Subtree Invariant: Every node in the right subtree has a key strictly greater than the root's key (Right Subtree > Root)[cite: 23].
3. Recursive Subtree Invariant: Both the left and right subtrees must themselves be valid Binary Search Trees with no duplicate values[cite: 23].

Key Mathematical Consequence:
- Inorder Traversal (Left -> Root -> Right) of a valid BST will ALWAYS output elements in strictly ascending / sorted order.

-------------------------------------------------

BST Structural Representation Visual

Constructed from values: [4, 2, 5, 1, 3, 6]

               4  (Root)
             /   \
   ( < 4 )  2     5  ( > 4 )
           / \     \
 ( < 2 )  1   3     6  ( > 5 )
            ( > 2 )

Evaluation:
- Left subtree of 4 contains {1, 2, 3}, all of which are < 4.
- Right subtree of 4 contains {5, 6}, all of which are > 4.
- Left subtree of 2 contains {1} (< 2), right contains {3} (> 2).
- Right subtree of 5 contains {6} (> 5).
Every subtree preserves the invariant cleanly.

-------------------------------------------------

Comparison: Standard Binary Tree vs. Binary Search Tree

---------------------------------------------------------------------------------------------------------
Feature                 | General Binary Tree              | Binary Search Tree (BST)
---------------------------------------------------------------------------------------------------------
Ordering Rule           | None (Arbitrary placement)       | Left < Root < Right[cite: 23]
Search Time Complexity  | O(n) (Must check all nodes)      | O(h) = O(log n) average, O(n) worst case
Inorder Traversal Output| Arbitrary sequence               | Strictly Sorted (Ascending Order)
Duplicate Handling      | Easily supports duplicate keys   | Disallows duplicates by default[cite: 23]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Search / Insert / Delete Time Complexity:
  - Average Case (Balanced BST): O(log n) — Depth divides search space in half each step.
  - Worst Case (Skewed Tree): O(n) — When values are inserted in sorted order (e.g., 1 -> 2 -> 3 -> 4).
- Space Complexity: O(h) — Recursive stack space proportional to height $h$ ($O(\log n)$ to $O(n)$).
=================================================
*/
