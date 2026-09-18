public class PrintInRangeBST {

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

    // Operation: Print all keys within range [k1, k2] in sorted order - O(H + k)
    public static void printInRange(Node root, int k1, int k2) {
        if (root == null) {
            return;
        }

        // Case 1: root.data lies strictly within [k1, k2]
        // Values can exist on both left and right sides
        if (root.data >= k1 && root.data <= k2) {
            printInRange(root.left, k1, k2);
            System.out.print(root.data + " ");
            printInRange(root.right, k1, k2);
        }
        // Case 2: root.data is strictly smaller than k1
        // All valid range values must lie strictly in the right subtree
        else if (root.data < k1) {
            printInRange(root.right, k1, k2);
        }
        // Case 3: root.data is strictly greater than k2
        // All valid range values must lie strictly in the left subtree
        else {
            printInRange(root.left, k1, k2);
        }
    }

    // Helper: Insert value into BST
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

        int k1 = 5, k2 = 12; //[cite: 27]
        System.out.print("Nodes in range [" + k1 + ", " + k2 + "]: ");
        printInRange(root, k1, k2);
        System.out.println();
        // Output: 5 6 8 10 11
    }
}

/*
==================== SUMMARY ====================

Problem: Print in Range in BST

Objective:
Given the root of a BST and two range limits $k1$ and $k2$ ($k1 \le k2$), print all node values 
that satisfy $k1 \le \text{node.data} \le k2$ in ascending sorted order[cite: 27].

Pruning Cases (Leveraging BST Invariant):
1. Both Subtrees Possible ($k1 \le \text{root.data} \le k2$):
   - Traverse left subtree: `printInRange(root.left, k1, k2)`.
   - Process/Print `root.data`.
   - Traverse right subtree: `printInRange(root.right, k1, k2)`.
2. Left Subtree Pruned ($\text{root.data} < k1$):
   - All values in the left subtree are smaller than `root.data`, hence strictly smaller than $k1$.
   - Entire left branch can be ignored; only check the right subtree: `printInRange(root.right, k1, k2)`.
3. Right Subtree Pruned ($\text{root.data} > k2$):
   - All values in the right subtree are greater than `root.data`, hence strictly greater than $k2$.
   - Entire right branch can be ignored; only check the left subtree: `printInRange(root.left, k1, k2)`.

-------------------------------------------------

Range Decision & Pruning Visual

Tree:
                 8  <-- Inside [5, 12] (Explore Both)[cite: 27]
               /   \
(Explore Both)5     10  <-- Inside [5, 12] (Explore Both)[cite: 27]
             / \     \
(3 < 5: Skip)3   6    11  <-- Inside [5, 12][cite: 27]
            / \   \     \
           1   4   (ok)  14 <-- (14 > 12: Skip)[cite: 27]
       (All < 5)

Branch Filtering:
- At Node(8): 8 is in [5, 12] -> explore left (5) and right (10)[cite: 27].
- At Node(5): 5 is in [5, 12] -> explore left (3) and right (6)[cite: 27].
- At Node(3): 3 < 5 -> left subtree {1, 4} is pruned; explore only right (4 < 5 pruned).
- At Node(6): 6 is in [5, 12] -> printed.
- At Node(10): 10 is in [5, 12] -> explore right (11)[cite: 27].
- At Node(11): 11 is in [5, 12] -> right child 14 > 12 (pruned)[cite: 27].

Output: 5 -> 6 -> 8 -> 10 -> 11

-------------------------------------------------

Step-by-Step Trace Table

Range: k1 = 5, k2 = 12[cite: 27]

----------------------------------------------------------------------------------------------------------------------
Node (`root.data`) | Range Check               | Action / Pruning Strategy                   | Output
----------------------------------------------------------------------------------------------------------------------
8                  | 5 <= 8 <= 12              | Search Left, Print 8, Search Right          | -
5                  | 5 <= 5 <= 12              | Search Left, Print 5, Search Right          | -
3                  | 3 < 5                     | Prune Left; Call `printInRange(root.right)` | -
4                  | 4 < 5                     | Prune Left; Call `printInRange(root.right)` | -
null               | null                      | Base case                                   | -
5                  | -                         | Print 5 (Backtrack from Left)               | 5
6                  | 5 <= 6 <= 12              | Print 6 (Leaf node)                         | 6
8                  | -                         | Print 8 (Backtrack from Left)               | 8
10                 | 5 <= 10 <= 12             | Search Left (null), Print 10, Search Right  | 10
11                 | 5 <= 11 <= 12             | Search Left (null), Print 11, Search Right  | 11
14                 | 14 > 12                   | Prune Right; Call `printInRange(root.left)` | -
----------------------------------------------------------------------------------------------------------------------
Final Printed Sequence: 5 6 8 10 11

Complexity Analysis:
- Time Complexity : O(H + k) — Where $H$ is the height of the BST and $k$ is the number of nodes in the range. Only branches containing valid elements are traversed.
- Space Complexity: O(H) — Recursive call stack bounded by the tree height $H$.
=================================================
*/
