public class BuildBST {

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

    // Operation: Insert a value into BST maintaining the BST invariant - O(H)
    public static Node insert(Node root, int val) {
        // Base case: Reached an empty slot, create and return new node
        if (root == null) {
            root = new Node(val);
            return root;
        }

        // Recursive case: Navigate left or right depending on value
        if (val < root.data) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        return root;
    }

    // Operation: Inorder Traversal to verify BST structure (Left -> Root -> Right)
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        int values[] = {5, 1, 3, 4, 2, 7};
        Node root = null;

        // Build BST by inserting elements one by one
        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }

        // Verification: Inorder traversal of a valid BST must be strictly sorted
        System.out.print("Inorder Traversal: ");
        inorder(root);
        System.out.println();
        // Output: 1 2 3 4 5 7
    }
}

/*
==================== SUMMARY ====================

Topic: Build a Binary Search Tree (BST Insertion)

Objective:
Given an array of values `values[] = {5, 1, 3, 4, 2, 7}`, construct a valid Binary Search Tree 
by inserting elements sequentially from left to right[cite: 25].

Core Mechanics of BST Insertion:
- The first element inserted (`values[0] = 5`) becomes the tree's permanent root.
- For each subsequent value `val`:
  1. Start at `root`.
  2. If `val < current.data`: Move to the left child subtree.
  3. If `val > current.data`: Move to the right child subtree.
  4. Once a `null` link is encountered, instantiate a new `Node(val)` at that position.
  5. Return updated root references upwards across the recursive call chain.

-------------------------------------------------

Tree Growth & Final Structure Visual

Insertion sequence: 5 -> 1 -> 3 -> 4 -> 2 -> 7

1. Insert 5:
      5 (Root)

2. Insert 1:
      5
     /
    1

3. Insert 3:
      5
     /
    1
     \
      3

4. Insert 4:
      5
     /
    1
     \
      3
       \
        4

5. Insert 2:
      5
     /
    1
     \
      3
     / \
    2   4

6. Insert 7:
        5
      /   \
     1     7
      \
       3
      / \
     2   4

-------------------------------------------------

Step-by-Step Trace Table

values = {5, 1, 3, 4, 2, 7}

----------------------------------------------------------------------------------------------------------------------
Element | Current Tree State    | Path Traversed                 | Placement Decision          | Tree Action
----------------------------------------------------------------------------------------------------------------------
5       | null                  | -                              | First element is root       | Creates Node(5) as Root
1       | {5}                   | 5                              | 1 < 5 -> left               | Attaches as Node(5).left
3       | {5, 1}                | 5 -> 1                         | 3 < 5 (L), 3 > 1 (R)        | Attaches as Node(1).right
4       | {5, 1, 3}             | 5 -> 1 -> 3                    | 4 < 5 (L), 4 > 1 (R), > 3   | Attaches as Node(3).right
2       | {5, 1, 3, 4}          | 5 -> 1 -> 3                    | 2 < 5 (L), 2 > 1 (R), < 3   | Attaches as Node(3).left
7       | {5, 1, 3, 4, 2}       | 5                              | 7 > 5 -> right              | Attaches as Node(5).right
----------------------------------------------------------------------------------------------------------------------
Final Inorder Verification: 1 -> 2 -> 3 -> 4 -> 5 -> 7 (Strictly Sorted)

Complexity Analysis:
- Time Complexity:
  - Single Insertion : O(H) where $H$ is the height of the tree ($O(\log n)$ average, $O(n)$ worst-case skewed).
  - Building Full BST: O(n * H) -> O(n log n) average, O(n^2) worst case if input is pre-sorted.
- Space Complexity: O(H) — Recursive call stack depth bounded by tree height $H$.
=================================================
*/
