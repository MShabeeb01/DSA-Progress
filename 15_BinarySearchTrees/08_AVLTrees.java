public class AVLTree {

    // Node representation containing height metadata
    static class Node {
        int data, height;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.height = 1; // Leaf node starts with height 1
        }
    }

    public static Node root;

    // Helper: Safely obtain the height of a node
    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    // Helper: Calculate Balance Factor (BF = height(left) - height(right))
    public static int getBalance(Node root) {
        if (root == null) {
            return 0;
        }
        return height(root.left) - height(root.right);
    }

    // Helper: Right Rotate subtree rooted with y (LL Imbalance resolution)
    public static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights (y must be updated first as it is now below x)
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root of the rotated subtree
        return x;
    }

    // Helper: Left Rotate subtree rooted with x (RR Imbalance resolution)
    public static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights (x must be updated first as it is now below y)
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root of the rotated subtree
        return y;
    }

    // Operation: Insert into AVL Tree with Self-Balancing - O(log n)
    public static Node insert(Node root, int key) {
        // Step 1: Standard BST insertion
        if (root == null) {
            return new Node(key);
        }

        if (key < root.data) {
            root.left = insert(root.left, key);
        } else if (key > root.data) {
            root.right = insert(root.right, key);
        } else {
            return root; // Duplicate keys are not permitted in standard AVL
        }

        // Step 2: Update ancestor height
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Step 3: Get balance factor to check for violations
        int bf = getBalance(root);

        // Step 4: If unbalanced, handle one of the 4 rotation cases

        // Case 1: Left-Left (LL) Case
        if (bf > 1 && key < root.left.data) {
            return rightRotate(root);
        }

        // Case 2: Right-Right (RR) Case
        if (bf < -1 && key > root.right.data) {
            return leftRotate(root);
        }

        // Case 3: Left-Right (LR) Case
        if (bf > 1 && key > root.left.data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Case 4: Right-Left (RL) Case
        if (bf < -1 && key < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root; // Tree is already balanced
    }

    // Traversal: Preorder to verify tree hierarchy
    public static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args) {
        /*
           Sequential Insertion: 10, 20, 30, 40, 50, 25
           In a normal BST, sorted insertions degenerate into an O(n) skewed linked list.
           In an AVL Tree, self-balancing rotations keep height at O(log n).
        */
        root = insert(root, 10);
        root = insert(root, 20);
        root = insert(root, 30);
        root = insert(root, 40);
        root = insert(root, 50);
        root = insert(root, 25);

        /*
           Constructed Balanced AVL Tree:
                     30
                   /    \
                 20      40
                /  \       \
               10  25      50
        */
        System.out.print("Preorder Traversal of AVL Tree: ");
        preorder(root);
        System.out.println();
        // Expected Preorder: 30 20 10 25 40 50
    }
}

/*
==================== SUMMARY ====================

Topic: AVL Tree (Adelson-Velsky and Landis Tree)

Definition:
An AVL Tree is a self-balancing Binary Search Tree (BST) where the difference between 
the heights of the left and right subtrees (known as the Balance Factor) for ANY node 
cannot exceed 1.

Balance Factor (BF):
    BF = height(left_subtree) - height(right_subtree)
    Valid states for every node: BF ∈ {-1, 0, +1}
    If |BF| > 1, the subtree is unbalanced and requires immediate tree rotation.

The 4 Imbalance Cases and Rotation Fixes:

1. Left-Left (LL) Heavy:
   - Condition: `bf > 1 && key < root.left.data`
   - Fix: Perform a single **Right Rotation** on the root.

2. Right-Right (RR) Heavy:
   - Condition: `bf < -1 && key > root.right.data`
   - Fix: Perform a single **Left Rotation** on the root.

3. Left-Right (LR) Heavy:
   - Condition: `bf > 1 && key > root.left.data`
   - Fix: Perform a **Left Rotation** on `root.left`, followed by a **Right Rotation** on `root`.

4. Right-Left (RL) Heavy:
   - Condition: `bf < -1 && key < root.right.data`
   - Fix: Perform a **Right Rotation** on `root.right`, followed by a **Left Rotation** on `root`.

-------------------------------------------------

Rotation Mechanics Visual

1. Right Rotation (Clockwise) on y:
        y                          x
       / \      Right Rotate      / \
      x   T3   =============>    T1  y
     / \                            / \
    T1  T2                         T2  T3

2. Left Rotation (Counter-Clockwise) on x:
      x                            y
     / \        Left Rotate       / \
    T1  y      =============>    x   T3
       / \                      / \
      T2  T3                   T1  T2

-------------------------------------------------

Step-by-Step Insertion Lifecycle Trace

Input: [10, 20, 30, 40, 50, 25]

---------------------------------------------------------------------------------------------------------
Insertion | Tree State Before Fix              | Unbalance Detected | Applied Fix        | Resulting Root
---------------------------------------------------------------------------------------------------------
10        | 10                                 | None (BF = 0)      | None               | 10
20        | 10 -> 20                           | None (BF = -1)     | None               | 10
30        | 10 -> 20 -> 30                     | At 10 (BF = -2, RR)| LeftRotate(10)     | 20
40        | 20 has L(10), R(30 -> 40)          | None (BF = -1)     | None               | 20
50        | Subtree 30 -> 40 -> 50             | At 30 (BF = -2, RR)| LeftRotate(30)     | 20
25        | 20: L(10), R(40: L(30: R(25)), R(50)| At 20 (BF = -2, RL)| RightRotate(40) then| 30
          |                                    |                    | LeftRotate(20)     |
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Height Bound     : Strict $H \le 1.44 \log_2(n)$
- Search Time      : O(log n) guaranteed (no skewing possible)
- Insertion Time   : O(log n) (1 search path traversal + at most 2 rotations)
- Deletion Time    : O(log n) (may require up to $O(\log n)$ rotations up the ancestor chain)
- Space Complexity : O(n) to store $n$ nodes with height attributes; recursive stack bounded by $O(\log n)$.
=================================================
*/
