public class LargestBSTInBT {

    // Node structure for Binary Tree
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Information class returned by each subtree in bottom-up recursion
    static class Info {
        boolean isBST;
        int size;
        int min;
        int max;

        public Info(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    public static int maxBST = 0; // Tracks the maximum size of a valid BST subtree

    // Operation: Size of Largest BST in Binary Tree (LeetCode 333) - O(n)
    public static Info largestBST(Node root) {
        // Base Case: Empty node is a valid BST of size 0
        if (root == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // Postorder traversal: Collect info from left and right subtrees
        Info leftInfo = largestBST(root.left);
        Info rightInfo = largestBST(root.right);

        // Current subtree size, min, and max
        int size = leftInfo.size + rightInfo.size + 1;
        int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));

        // BST Validation Condition:
        // 1. Both left and right subtrees must be valid BSTs
        // 2. root.data must be strictly greater than max value of left subtree
        // 3. root.data must be strictly less than min value of right subtree
        if (leftInfo.isBST && rightInfo.isBST 
                && root.data > leftInfo.max 
                && root.data < rightInfo.min) {

            maxBST = Math.max(maxBST, size);
            return new Info(true, size, min, max);
        }

        // If the current tree is not a BST, return isBST = false
        return new Info(false, size, min, max);
    }

    public static void main(String[] args) {
        /*
                   50
                 /    \
               30      60
              /  \    /  \
             5   20  45  70
                        /  \
                       65  80
        */
        Node root = new Node(50);
        root.left = new Node(30);
        root.left.left = new Node(5);
        root.left.right = new Node(20);

        root.right = new Node(60);
        root.right.left = new Node(45);
        root.right.right = new Node(70);
        root.right.right.left = new Node(65);
        root.right.right.right = new Node(80);

        largestBST(root);
        System.out.println("Size of Largest BST in BT = " + maxBST); // Output: 5
    }
}

/*
==================== SUMMARY ====================

Problem: Size of Largest BST in Binary Tree (LeetCode 333)

Objective:
Given a Binary Tree, find the size (number of nodes) of the largest subtree that is also a valid 
Binary Search Tree (BST).

Why Postorder Traversal?
- Validating top-down takes $O(n^2)$ time because each node repeatedly inspects its entire subtree.
- By using bottom-up **Postorder Traversal (Left -> Right -> Root)**, every node receives the 
  necessary validation metadata from its children in $O(1)$ time, reducing the total runtime to **$O(n)$**.

Information Passed Upwards (`Info` Class):
1. `isBST`: Flag indicating whether the subtree is a valid BST.
2. `size` : Number of nodes in the subtree.
3. `min`  : Minimum value in the subtree (compared against parent when parent is in right subtree).
4. `max`  : Maximum value in the subtree (compared against parent when parent is in left subtree).

Subtree Validation Conditions:
A node forms a valid BST if:
- `leftInfo.isBST == true`
- `rightInfo.isBST == true`
- `root.data > leftInfo.max`
- `root.data < rightInfo.min`

Sentinel Values for Null Nodes:
- `size = 0`, `isBST = true`
- `min = Integer.MAX_VALUE` (ensures `root.data < rightInfo.min` holds for a missing right child)
- `max = Integer.MIN_VALUE` (ensures `root.data > leftInfo.max` holds for a missing left child)

-------------------------------------------------

Tree Validation Visual

Full Tree Structure:
                 50
               /    \
             30      60  <-- Subtree rooted at 60 is a valid BST of size 5
            /  \    /  \
           5   20  45  70
                      /  \
                     65  80

1. Subtree at 30:
   - Left child = 5 (< 30) -> Valid
   - Right child = 20 (< 30) -> FAILS BST invariant (right child must be > 30)
   - Result: `isBST = false`

2. Subtree at 60:
   - Left: 45 (< 60) -> Valid BST
   - Right: Subtree at 70 (65 < 70 < 80) -> Valid BST
   - 60 > max(left) (60 > 45) and 60 < min(right) (60 < 65) -> Valid BST!
   - Size = 1 (left) + 3 (right) + 1 (root 60) = 5 nodes

3. At Root (50):
   - Left child subtree (30) is not a BST.
   - Entire tree fails to form a BST.
   - Largest BST found = Subtree at 60 with size 5.

-------------------------------------------------

Subtree Bottom-Up Evaluation Trace Table

-----------------------------------------------------------------------------------------------------------------
Node Examined | Left Info (isBST, max) | Right Info (isBST, min) | Validation Condition Met? | Resulting (isBST, size)
-----------------------------------------------------------------------------------------------------------------
5             | null (true, -INF)      | null (true, +INF)       | 5 > -INF && 5 < +INF      | (true, 1)
20            | null (true, -INF)      | null (true, +INF)       | 20 > -INF && 20 < +INF    | (true, 1)
30            | 5 (true, 5)            | 20 (true, 20)           | 30 < 20 (Fails!)          | (false, 3)
45            | null (true, -INF)      | null (true, +INF)       | 45 > -INF && 45 < +INF    | (true, 1)
65            | null (true, -INF)      | null (true, +INF)       | 65 > -INF && 65 < +INF    | (true, 1)
80            | null (true, -INF)      | null (true, +INF)       | 80 > -INF && 80 < +INF    | (true, 1)
70            | 65 (true, 65)          | 80 (true, 80)           | 70 > 65 && 70 < 80        | (true, 3)
60            | 45 (true, 45)          | 70 (true, 65)           | 60 > 45 && 60 < 65        | (true, 5) -> Max Size
50            | 30 (false, 30)         | 60 (true, 60)           | Left isBST is false       | (false, 9)
-----------------------------------------------------------------------------------------------------------------
Final Answer: 5 nodes

Complexity Analysis:
- Time Complexity : O(n) — Each node is visited once during bottom-up postorder traversal.
- Space Complexity: O(H) — Recursive call stack bounded by tree height $H$ ($O(\log n)$ balanced, $O(n)$ skewed).
=================================================
*/
