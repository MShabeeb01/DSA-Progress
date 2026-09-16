public class PostorderTraversal {

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

        // Operation: Postorder Traversal (Left Subtree -> Right Subtree -> Root) - O(n)
        public static void postorder(Node root) {
            // Base Case: If current node is null, return to caller
            if (root == null) {
                return;
            }

            // Step 1: Recursively traverse Left Subtree
            postorder(root.left);

            // Step 2: Recursively traverse Right Subtree
            postorder(root.right);

            // Step 3: Visit / Print Current Root Node
            System.out.print(root.data + " ");
        }
    }

    public static void main(String args[]) {
        // Preorder sequence with -1 as null marker
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree.idx = -1;
        Node root = BinaryTree.buildTree(nodes);

        System.out.print("Postorder Traversal: ");
        BinaryTree.postorder(root);
        System.out.println();
        // Output: 4 5 2 6 3 1
    }
}

/*
==================== SUMMARY ====================

Topic: Binary Tree Postorder Traversal (Depth-First Search - DFS)

Traversal Order:
  1. Left Subtree  (Traverse entire left branch recursively)
  2. Right Subtree (Traverse entire right branch recursively)
  3. Root          (Visit & process current node)

Core Recursive Logic:
- Base Case:
    `if (root == null) return;`
- Step 1:
    `postorder(root.left);`
- Step 2:
    `postorder(root.right);`
- Step 3:
    `System.out.print(root.data + " ");`

Key Practical Applications:
- Bottom-up computations: Calculating tree height, subtree sizes, tree diameter.
- Deleting or freeing nodes in non-garbage-collected languages (e.g., C/C++), where child nodes 
  must be freed before deleting the parent.

-------------------------------------------------

Tree Structure & Traversal Order Visual

Constructed Tree:

             1  <-- [Visited Last]
           /   \
          2     3
         / \     \
        4   5     6

Visit Flow:
- Start at 1 -> Go to left child 2 -> Go to left child 4
- Node 4 has no children -> Print 4
- Go to right child of 2 -> Node 5 has no children -> Print 5
- Both children of 2 processed -> Print 2
- Go to right child of 1 -> Node 3 (left is null)
- Go to right child of 3 -> Node 6 has no children -> Print 6
- Both children of 3 processed -> Print 3
- Both subtrees of 1 processed -> Print 1

Output: 4 -> 5 -> 2 -> 6 -> 3 -> 1

-------------------------------------------------

Recursive Call Stack & Trace Table

---------------------------------------------------------------------------------------------------------
Call Stack Frame | Node Processed | Action Taken          | Sub-calls Triggered          | Printed Output
---------------------------------------------------------------------------------------------------------
postorder(1)     | Node(1)        | Traverse Left         | postorder(2)                 | -
postorder(2)     | Node(2)        | Traverse Left         | postorder(4)                 | -
postorder(4)     | Node(4)        | Traverse Left         | postorder(null)              | -
postorder(null)  | null           | Base case (return)    | None                         | -
postorder(4)     | Node(4)        | Traverse Right        | postorder(null)              | -
postorder(null)  | null           | Base case (return)    | None                         | -
postorder(4)     | Node(4)        | Both done -> Print    | None                         | 4
postorder(2)     | Node(2)        | Traverse Right        | postorder(5)                 | -
postorder(5)     | Node(5)        | Traverse Left         | postorder(null)              | -
postorder(null)  | null           | Base case (return)    | None                         | -
postorder(5)     | Node(5)        | Traverse Right        | postorder(null)              | -
postorder(null)  | null           | Base case (return)    | None                         | -
postorder(5)     | Node(5)        | Both done -> Print    | None                         | 5
postorder(2)     | Node(2)        | Both done -> Print    | None                         | 2
postorder(1)     | Node(1)        | Traverse Right        | postorder(3)                 | -
postorder(3)     | Node(3)        | Traverse Left         | postorder(null)              | -
postorder(null)  | null           | Base case (return)    | None                         | -
postorder(3)     | Node(3)        | Traverse Right        | postorder(6)                 | -
postorder(6)     | Node(6)        | Traverse Left         | postorder(null)              | -
postorder(null)  | null           | Base case (return)    | None                         | -
postorder(6)     | Node(6)        | Traverse Right        | postorder(null)              | -
postorder(null)  | null           | Base case (return)    | None                         | -
postorder(6)     | Node(6)        | Both done -> Print    | None                         | 6
postorder(3)     | Node(3)        | Both done -> Print    | None                         | 3
postorder(1)     | Node(1)        | Both done -> Print    | None                         | 1
---------------------------------------------------------------------------------------------------------
Final Output Sequence: 4 5 2 6 3 1

Complexity Analysis:
- Time Complexity : O(n) — Each of the $n$ nodes is visited exactly once.
- Space Complexity: O(h) — Height of the tree $h$ determines the call stack depth ($O(\log n)$ balanced, $O(n)$ worst-case skewed).
=================================================
*/
