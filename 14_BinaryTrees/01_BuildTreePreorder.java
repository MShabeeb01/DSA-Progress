public class BinaryTreesB {

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
        static int idx = -1; // Static index pointer to traverse nodes array

        // Operation: Build Binary Tree from Preorder Sequence with null (-1) markers - O(n)
        public static Node buildTree(int nodes[]) {
            idx++;

            // Base case: null node represented by -1 or end of bounds
            if (idx >= nodes.length || nodes[idx] == -1) {
                return null;
            }

            // Step 1: Create Root Node
            Node newNode = new Node(nodes[idx]);

            // Step 2: Recursively construct Left Subtree
            newNode.left = buildTree(nodes);

            // Step 3: Recursively construct Right Subtree
            newNode.right = buildTree(nodes);

            return newNode;
        }
    }

    public static void main(String args[]) {
        // Preorder sequence where -1 denotes a null child
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        // Reset index before building (essential when running multiple builds)
        BinaryTree.idx = -1;
        Node root = BinaryTree.buildTree(nodes);

        // Verification: display root value
        System.out.println("Root Node Data: " + root.data); // Output: 1
    }
}

/*
==================== SUMMARY ====================

Problem: Build Tree from Preorder Traversal Sequence

Objective:
Given an array containing the preorder traversal sequence of a binary tree where `-1` represents 
a `null` pointer, reconstruct the original binary tree structure and return its root node.

Preorder Traversal Pattern:
  [ Root ] -> [ Left Subtree ] -> [ Right Subtree ]

Core Recursive Concept:
- A global or static pointer `idx` tracks the current token.
- At every recursive call:
  1. Increment `idx`: `idx++`.
  2. Base Case: If `nodes[idx] == -1`, return `null`.
  3. Node Creation: Create a new node with value `nodes[idx]`.
  4. Left Child: The immediately following sequence builds the left subtree (`newNode.left = buildTree(nodes)`).
  5. Right Child: After the entire left subtree completes, the subsequent elements build the right subtree (`newNode.right = buildTree(nodes)`).

-------------------------------------------------

Tree Construction & Structure Visual

Input Array:
[1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1]

Reconstructed Binary Tree:

             1
           /   \
          2     3
         / \     \
        4   5     6

Subtree Breakdown:
- Node 1: left -> Subtree rooted at 2, right -> Subtree rooted at 3
- Node 2: left -> 4 (null, null), right -> 5 (null, null)
- Node 3: left -> null (-1), right -> 6 (null, null)

-------------------------------------------------

Recursive Call Stack & Pointer Trace

----------------------------------------------------------------------------------------------------------------------
`idx` | `nodes[idx]` | Current Action                     | Return Value        | Attached To
----------------------------------------------------------------------------------------------------------------------
0     | 1            | Create Node(1)                     | -                   | Root
1     | 2            | Create Node(2)                     | -                   | Node(1).left
2     | 4            | Create Node(4)                     | -                   | Node(2).left
3     | -1           | Base case hit                      | null                | Node(4).left = null
4     | -1           | Base case hit                      | null                | Node(4).right = null
-     | -            | Subtree 4 complete                 | Node(4)             | Node(2).left assigned
5     | 5            | Create Node(5)                     | -                   | Node(2).right
6     | -1           | Base case hit                      | null                | Node(5).left = null
7     | -1           | Base case hit                      | null                | Node(5).right = null
-     | -            | Subtree 5 complete                 | Node(5)             | Node(2).right assigned
-     | -            | Subtree 2 complete                 | Node(2)             | Node(1).left assigned
8     | 3            | Create Node(3)                     | -                   | Node(1).right
9     | -1           | Base case hit                      | null                | Node(3).left = null
10    | 6            | Create Node(6)                     | -                   | Node(3).right
11    | -1           | Base case hit                      | null                | Node(6).left = null
12    | -1           | Base case hit                      | null                | Node(6).right = null
-     | -            | Subtree 6 complete                 | Node(6)             | Node(3).right assigned
-     | -            | Subtree 3 complete                 | Node(3)             | Node(1).right assigned
----------------------------------------------------------------------------------------------------------------------
Final Return: Node(1) (Full tree intact)

Complexity Analysis:
- Time Complexity : O(n) — Each element of the array of size $n$ is processed exactly once.
- Space Complexity: O(h) — Auxiliary space consumed by the recursive call stack, where $h$ is the tree height ($O(\log n)$ for balanced trees, $O(n)$ in the worst-case skewed tree).
=================================================
*/
