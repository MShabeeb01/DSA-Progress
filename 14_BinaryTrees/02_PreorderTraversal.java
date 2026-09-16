public class PreorderTraversal {

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

        // Operation: Preorder Traversal (Root -> Left Subtree -> Right Subtree) - O(n)
        public static void preorder(Node root) {
            // Base Case: If node is empty/null, return back to caller
            if (root == null) {
                return;
            }

            // Step 1: Visit / Print Root Node
            System.out.print(root.data + " ");

            // Step 2: Recursively traverse Left Subtree
            preorder(root.left);

            // Step 3: Recursively traverse Right Subtree
            preorder(root.right);
        }
    }

    public static void main(String args[]) {
        // Preorder sequence with -1 as null marker
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree.idx = -1;
        Node root = BinaryTree.buildTree(nodes);

        System.out.print("Preorder Traversal: ");
        BinaryTree.preorder(root);
        System.out.println();
        // Output: 1 2 4 5 3 6
    }
}

/*
==================== SUMMARY ====================

Topic: Binary Tree Preorder Traversal (Depth-First Search - DFS)

Traversal Order:
  1. Root (Visit & process current node)
  2. Left Subtree (Traverse entire left branch recursively)
  3. Right Subtree (Traverse entire right branch recursively)

Core Recursive Logic:
- Base Case:
    `if (root == null) return;`
- Step 1:
    `System.out.print(root.data + " ");`
- Step 2:
    `preorder(root.left);`
- Step 3:
    `preorder(root.right);`

-------------------------------------------------

Tree Structure & Traversal Order Visual

Constructed Tree:

             1  <-- [1st: Root]
           /   \
          2     3
         / \     \
        4   5     6

Visit Sequence:
- Start at Root -> Print 1
- Go Left to 2  -> Print 2
- Go Left to 4  -> Print 4 (left is null, right is null -> return to 2)
- Go Right to 5 -> Print 5 (left is null, right is null -> return to 1)
- Go Right to 3 -> Print 3 (left is null)
- Go Right to 6 -> Print 6 (left is null, right is null -> finished)

Output: 1 -> 2 -> 4 -> 5 -> 3 -> 6

-------------------------------------------------

Recursive Call Stack & Trace Table

---------------------------------------------------------------------------------------------------------
Call Stack Frame | Node Processed | Action Taken          | Sub-calls Triggered          | Printed Output
---------------------------------------------------------------------------------------------------------
preorder(1)      | Node(1)        | Print 1               | preorder(2), preorder(3)     | 1
preorder(2)      | Node(2)        | Print 2               | preorder(4), preorder(5)     | 2
preorder(4)      | Node(4)        | Print 4               | preorder(null), preorder(null)| 4
preorder(null)   | null           | Base case (return)    | None                         | -
preorder(null)   | null           | Base case (return)    | None                         | -
preorder(5)      | Node(5)        | Print 5               | preorder(null), preorder(null)| 5
preorder(null)   | null           | Base case (return)    | None                         | -
preorder(null)   | null           | Base case (return)    | None                         | -
preorder(3)      | Node(3)        | Print 3               | preorder(null), preorder(6)  | 3
preorder(null)   | null           | Base case (return)    | None                         | -
preorder(6)      | Node(6)        | Print 6               | preorder(null), preorder(null)| 6
preorder(null)   | null           | Base case (return)    | None                         | -
preorder(null)   | null           | Base case (return)    | None                         | -
---------------------------------------------------------------------------------------------------------
Final Output Sequence: 1 2 4 5 3 6

Complexity Analysis:
- Time Complexity : O(n) — Every node is visited exactly once.
- Space Complexity: O(h) — Height of the tree $h$ determines recursive call stack depth ($O(\log n)$ balanced, $O(n)$ skewed).
=================================================
*/
