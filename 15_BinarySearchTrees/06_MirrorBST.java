public class MirrorBST {

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

    // Operation: Convert BST into its Mirror Image (LeetCode 226 - Invert Binary Tree) - O(n)
    public static Node createMirror(Node root) {
        // Base Case: empty tree or reached past leaf
        if (root == null) {
            return null;
        }

        // Step 1: Recursively mirror the left and right subtrees
        Node leftMirror = createMirror(root.left);
        Node rightMirror = createMirror(root.right);

        // Step 2: Swap the left and right child pointers
        root.left = rightMirror;
        root.right = leftMirror;

        return root;
    }

    // Inorder Traversal to inspect structure
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String args[]) {
        /*
                 8
               /   \
              5     10
             / \     \
            3   6     11
        */
        Node root = new Node(8);
        root.left = new Node(5);
        root.right = new Node(10);
        root.left.left = new Node(3);
        root.left.right = new Node(6);
        root.right.right = new Node(11);

        System.out.print("Original Inorder: ");
        inorder(root);
        System.out.println(); // 3 5 6 8 10 11 (Ascending order)

        // Convert to mirror image
        root = createMirror(root);

        /*
                 8
               /   \
              10    5
             /     / \
            11    6   3
        */
        System.out.print("Mirrored Inorder: ");
        inorder(root);
        System.out.println(); // 11 10 8 6 5 3 (Descending order)
    }
}

/*
==================== SUMMARY ====================

Problem: Mirror a BST (Invert Binary Search Tree / LeetCode 226)[cite: 28]

Objective:
Transform a given Binary Search Tree into its mirror reflection by swapping the left and right 
subtrees of every node throughout the tree[cite: 28].

Key Property of a Mirrored BST:
- Original BST Invariant: Left Subtree < Root < Right Subtree.
- Mirrored BST Invariant: Left Subtree > Root > Right Subtree.
- Inorder traversal on the original BST produces values sorted in **Ascending** order.
- Inorder traversal on the mirrored BST produces values sorted in **Descending** order.

Algorithmic Steps (Postorder / Bottom-Up Recursion):
1. Base Case:
   - If `root == null`, return `null`.
2. Recursive Calls:
   - Compute mirrored left subtree: `Node leftMirror = createMirror(root.left);`.
   - Compute mirrored right subtree: `Node rightMirror = createMirror(root.right);`.
3. Pointer Swap:
   - Swap the pointers:
     `root.left = rightMirror;`
     `root.right = leftMirror;`
4. Return `root`.

-------------------------------------------------

Mirror Reflection Visual

Original BST[cite: 28]:                Mirrored Image[cite: 28]:
          8                     |               8
        /   \                   |             /   \
       5     10                 |           10     5
      / \     \        =====>   |          /      / \
     3   6     11               |         11     6   3
                                |
Inorder: 3 5 6 8 10 11          |   Inorder: 11 10 8 6 5 3

-------------------------------------------------

Step-by-Step Trace Table

----------------------------------------------------------------------------------------------------------------------
Node (`root.data`) | `leftMirror` Subtree | `rightMirror` Subtree | New `root.left` | New `root.right` | Subtree Root
----------------------------------------------------------------------------------------------------------------------
3                  | null                 | null                  | null            | null             | Node(3)
6                  | null                 | null                  | null            | null             | Node(6)
5                  | Node(3)              | Node(6)               | Node(6)         | Node(3)          | Node(5)
11                 | null                 | null                  | null            | null             | Node(11)
10                 | null                 | Node(11)              | Node(11)        | null             | Node(10)
8                  | Node(5)              | Node(10)              | Node(10)        | Node(5)          | Node(8)
----------------------------------------------------------------------------------------------------------------------
Final Root Returned: Node(8) with inverted children.

Complexity Analysis:
- Time Complexity : O(n) — Every node in the tree of size $n$ is visited and its child pointers swapped exactly once.
- Space Complexity: O(H) — Space consumed by the recursive call stack, where $H$ is the tree height ($O(\log n)$ balanced, $O(n)$ skewed).
=================================================
*/
