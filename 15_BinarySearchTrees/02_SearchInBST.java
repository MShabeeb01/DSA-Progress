public class SearchInBST {

    // Node representation for Binary Search Tree
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

    // Operation: Search in a Binary Search Tree (LeetCode 700) - O(H)
    public static boolean search(Node root, int key) {
        // Base Case 1: Key not present in BST
        if (root == null) {
            return false;
        }

        // Base Case 2: Key found at current node
        if (root.data == key) {
            return true;
        }

        // Recursive Step 1: If key is smaller than root, search only left subtree
        if (root.data > key) {
            return search(root.left, key);
        } 
        // Recursive Step 2: If key is larger than root, search only right subtree
        else {
            return search(root.right, key);
        }
    }

    public static void main(String args[]) {
        /*
                 4
               /   \
              2     5
             / \     \
            1   3     6
        */
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.right = new Node(6);

        int key1 = 3;
        int key2 = 7;

        System.out.println("Search " + key1 + ": " + (search(root, key1) ? "Found" : "Not Found")); // Found
        System.out.println("Search " + key2 + ": " + (search(root, key2) ? "Found" : "Not Found")); // Not Found
    }
}

/*
==================== SUMMARY ====================

Topic: Search Operation in a Binary Search Tree (BST)

Core Intuition:
- In an arbitrary Binary Tree or unsorted array, locating an element requires linear scanning (O(n))[cite: 24].
- A Binary Search Tree makes search significantly more efficient by leveraging its structural invariant:
    - Elements smaller than the root lie exclusively in the left subtree.
    - Elements greater than the root lie exclusively in the right subtree.
- At every comparison, half of the remaining search space is eliminated (similar to Binary Search on sorted arrays).

Algorithmic Steps:
1. Base Cases:
   - If `root == null`, return `false` (key does not exist).
   - If `root.data == key`, return `true` (key located).
2. Branch Pruning:
   - If `key < root.data`: Prune right subtree; search left via `search(root.left, key)`.
   - If `key > root.data`: Prune left subtree; search right via `search(root.right, key)`.

-------------------------------------------------

Search Elimination Trace Visual

Target: key = 3[cite: 24]

Step 1: Start at Root (4)
        4   <-- Compare: 3 < 4 -> Discard entire right subtree {5, 6}
      /   \
     2     [5, 6] (Pruned)
    / \
   1   3

Step 2: Move Left to Node (2)
        2   <-- Compare: 3 > 2 -> Discard entire left subtree {1}
       / \
     [1]  3
     (Pruned)

Step 3: Move Right to Node (3)
        3   <-- Compare: 3 == 3 -> Match found! Return true.

-------------------------------------------------

Step-by-Step Trace Table

Target Key: 3

---------------------------------------------------------------------------------------------------------
Current Node (`root.data`) | Comparison (`key vs root.data`) | Decision / Action     | Next Node Visited
---------------------------------------------------------------------------------------------------------
4                          | 3 < 4                           | Discard Right Subtree | Node(2)
2                          | 3 > 2                           | Discard Left Subtree  | Node(3)
3                          | 3 == 3                          | Match Found           | - (Returns true)
---------------------------------------------------------------------------------------------------------

Complexity Comparison Table:

---------------------------------------------------------------------------------------------------------
Data Structure / Scenario      | Search Time Complexity  | Space Complexity (Call Stack)
---------------------------------------------------------------------------------------------------------
Unsorted Array / Linear Tree   | O(n)                    | O(1) iterative / O(n) recursive
Balanced BST (Average Case)    | O(log n)                | O(log n) recursive (O(1) iterative)
Skewed BST (Worst Case)        | O(n)                    | O(n) recursive
---------------------------------------------------------------------------------------------------------
=================================================
*/
