public class DeleteNodeBST {

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

    // Helper: Find Inorder Successor (Leftmost node in the right subtree)
    public static Node findInorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Operation: Delete a node from BST (LeetCode 450) - O(H)
    public static Node delete(Node root, int val) {
        if (root == null) {
            return null;
        }

        // Step 1: Search for the node to delete
        if (root.data > val) {
            root.left = delete(root.left, val);
        } else if (root.data < val) {
            root.right = delete(root.right, val);
        } else {
            // Step 2: Target Node found (root.data == val)

            // Case 1: No child (Leaf Node)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: One child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Two children
            // Find Inorder Successor (leftmost node in right subtree)
            Node IS = findInorderSuccessor(root.right);
            // Overwrite current node's data with Inorder Successor's value
            root.data = IS.data;
            // Delete the Inorder Successor from the right subtree
            root.right = delete(root.right, IS.data);
        }

        return root;
    }

    // Inorder Traversal to verify BST sorted ordering
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Helper to insert nodes
    public static Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        }
        if (val < root.data) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    public static void main(String[] args) {
        /*
                 8
               /   \
              5     10
             / \     \
            3   6     11
           / \         \
          1   4         14
        */
        int values[] = {8, 5, 3, 1, 4, 6, 10, 11, 14};
        Node root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        System.out.print("Original BST (Inorder): ");
        inorder(root);
        System.out.println(); // 1 3 4 5 6 8 10 11 14

        // Case 1: Delete Leaf Node (4)
        root = delete(root, 4);
        System.out.print("After deleting 4 (Leaf): ");
        inorder(root);
        System.out.println();

        // Case 2: Delete Node with 1 Child (10)
        root = delete(root, 10);
        System.out.print("After deleting 10 (1 Child): ");
        inorder(root);
        System.out.println();

        // Case 3: Delete Node with 2 Children (5)
        root = delete(root, 5);
        System.out.print("After deleting 5 (2 Children): ");
        inorder(root);
        System.out.println();
    }
}

/*
==================== SUMMARY ====================

Problem: LeetCode 450 - Delete a Node in a BST[cite: 26]

Objective:
Given the root of a Binary Search Tree and a key, delete the node with the given key while 
preserving all BST properties[cite: 26].

The 3 Deletion Cases[cite: 26]:
1. Case 1: No child (Leaf Node)[cite: 26]
   - Action: Simply discard the node by returning `null` to its parent.
2. Case 2: One child[cite: 26]
   - Action: Replace the target node with its non-null child (return `root.left` or `root.right`).
3. Case 3: Two children[cite: 26]
   - Action: 
     a. Find the Inorder Successor (the smallest node in the target node's right subtree).
     b. Replace the target node's value with the Inorder Successor's value.
     c. Recursively delete the Inorder Successor from the right subtree (which falls into Case 1 or Case 2).

Why Inorder Successor?
- The Inorder Successor is strictly greater than all nodes in the left subtree and smaller than 
  all remaining nodes in the right subtree, preserving the BST invariant.

-------------------------------------------------

Deletion Scenarios Visual

Original Tree[cite: 26]:
          8
        /   \
       5     10
      / \     \
     3   6     11
    / \         \
   1   4         14

1. Case 1: Delete 4 (Leaf Node)[cite: 26]
   - 4 is disconnected -> Parent Node(3).right becomes null.

2. Case 2: Delete 10 (One Child)[cite: 26]
   - Node(10) has only right child Node(11).
   - Parent Node(8).right connects directly to Node(11).

3. Case 3: Delete 5 (Two Children)[cite: 26]
   - Subtree rooted at 5: left child 3, right child 6.
   - Inorder Successor of 5 = leftmost node in right subtree = Node(6).
   - Node 5 takes value 6 -> Delete original Node(6) from right subtree.
   - 6 now roots the subtree with left child 3 and right child null.

-------------------------------------------------

Step-by-Step Trace Table

----------------------------------------------------------------------------------------------------------------------
Target Key | Case Triggered     | Inorder Successor | Replaced By / Action      | New Subtree Root
----------------------------------------------------------------------------------------------------------------------
4          | Case 1: 0 Children[cite: 26] | -                 | Returns null to Node(3)   | null
10         | Case 2: 1 Child[cite: 26]    | -                 | Returns Node(11) to 8     | Node(11)
5          | Case 3: 2 Children[cite: 26] | 6                 | Value replaced with 6     | Node(6)
----------------------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(H) — Where $H$ is the height of the BST ($O(\log n)$ average for balanced BST, $O(n)$ worst-case for skewed tree).
- Space Complexity: O(H) — Recursive call stack depth bounded by tree height $H$.
=================================================
*/
