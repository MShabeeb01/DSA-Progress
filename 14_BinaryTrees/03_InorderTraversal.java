public class InorderTraversal {

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

        // Operation: Inorder Traversal (Left Subtree -> Root -> Right Subtree) - O(n)
        public static void inorder(Node root) {
            // Base Case: If node is empty/null, return back to caller
            if (root == null) {
                return;
            }

            // Step 1: Recursively traverse Left Subtree
            inorder(root.left);

            // Step 2: Visit / Print Root Node
            System.out.print(root.data + " ");

            // Step 3: Recursively traverse Right Subtree
            inorder(root.right);
        }
    }

    public static void main(String args[]) {
        // Preorder sequence with -1 as null marker
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree.idx = -1;
        Node root = BinaryTree.buildTree(nodes);

        System.out.print("Inorder Traversal: ");
        BinaryTree.inorder(root);
        System.out.println();
        // Output: 4 2 5 1 3 6
    }
}

/*
==================== SUMMARY ====================

Topic: Binary Tree Inorder Traversal (Depth-First Search - DFS)

Traversal Order:
  1. Left Subtree (Traverse entire left branch recursively)
  2. Root (Visit & process current node)
  3. Right Subtree (Traverse entire right branch recursively)

Core Recursive Logic:
- Base Case:
    `if (root == null) return;`
- Step 1:
    `inorder(root.left);`
- Step 2:
    `System.out.print(root.data + " ");`
- Step 3:
    `inorder(root.right);`

Key Characteristic:
- When applied to a Binary Search Tree (BST), Inorder Traversal retrieves all node keys 
  in strictly non-decreasing (sorted) order.

-------------------------------------------------

Tree Structure & Traversal Order Visual

Constructed Tree:

             1
           /   \
          2     3
         / \     \
        4   5     6

Visit Flow:
- Start at 1 -> Go to left child 2 -> Go to left child 4
- Node 4 has no left child -> Print 4
- Return to 2 -> Print 2
- Go to right child 5 (no left child) -> Print 5
- Entire left subtree of 1 done -> Return to 1 -> Print 1
- Go to right subtree 3 (no left child) -> Print 3
- Go to right child 6 (no left child) -> Print 6

Output: 4 -> 2 -> 5 -> 1 -> 3 -> 6

-------------------------------------------------

Recursive Call Stack & Trace Table

---------------------------------------------------------------------------------------------------------
Call Stack Frame | Node Processed | Action Taken          | Sub-calls Triggered         | Printed Output
---------------------------------------------------------------------------------------------------------
inorder(1)       | Node(1)        | Traverse Left         | inorder(2)                  | -
inorder(2)       | Node(2)        | Traverse Left         | inorder(4)                  | -
inorder(4)       | Node(4)        | Traverse Left         | inorder(null)               | -
inorder(null)    | null           | Base case (return)    | None                        | -
inorder(4)       | Node(4)        | Print 4 & Go Right    | inorder(null)               | 4
inorder(null)    | null           | Base case (return)    | None                        | -
inorder(2)       | Node(2)        | Print 2 & Go Right    | inorder(5)                  | 2
inorder(5)       | Node(5)        | Traverse Left         | inorder(null)               | -
inorder(null)    | null           | Base case (return)    | None                        | -
inorder(5)       | Node(5)        | Print 5 & Go Right    | inorder(null)               | 5
inorder(null)    | null           | Base case (return)    | None                        | -
inorder(1)       | Node(1)        | Print 1 & Go Right    | inorder(3)                  | 1
inorder(3)       | Node(3)        | Traverse Left         | inorder(null)               | -
inorder(null)    | null           | Base case (return)    | None                        | -
inorder(3)       | Node(3)        | Print 3 & Go Right    | inorder(6)                  | 3
inorder(6)       | Node(6)        | Traverse Left         | inorder(null)               | -
inorder(null)    | null           | Base case (return)    | None                        | -
inorder(6)       | Node(6)        | Print 6 & Go Right    | inorder(null)               | 6
inorder(null)    | null           | Base case (return)    | None                        | -
---------------------------------------------------------------------------------------------------------
Final Output Sequence: 4 2 5 1 3 6

Complexity Analysis:
- Time Complexity : O(n) — Every node is visited exactly once.
- Space Complexity: O(h) — Call stack depth bounded by tree height $h$ ($O(\log n)$ balanced, $O(n)$ skewed).
=================================================
*/
