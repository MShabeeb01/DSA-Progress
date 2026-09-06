public class LinkedList {

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;

    // Operation: Find & Remove Nth Node from End (Iterative Approach)
    public void deleteNthfromEnd(int n) {
        // Step 1: Calculate total size of the linked list
        int sz = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            sz++;
        }

        // Corner Case: If n is equal to size, we need to remove the head node
        if (n == sz) {
            head = head.next; // removeFirst logic
            return;
        }

        // Step 2: Find previous node to the target node
        // Target index from beginning (0-based) is (sz - n)
        // Previous node index is (sz - n - 1)
        int i = 1;
        int iToFind = sz - n;
        Node prev = head;

        while (i < iToFind) {
            prev = prev.next;
            i++;
        }

        // Step 3: Unlink the nth node from end
        prev.next = prev.next.next;
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        // Building list: 1 -> 2 -> 3 -> 4 -> 5 -> null
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.print("Original List: ");
        ll.print(); // 1 -> 2 -> 3 -> 4 -> 5 -> null

        int n = 2; // Delete 2nd node from end (node 4)
        ll.deleteNthfromEnd(n);

        System.out.print("After Deletion: ");
        ll.print(); // 1 -> 2 -> 3 -> 5 -> null
    }
}

/*
==================== SUMMARY ====================

Operation: deleteNthfromEnd(int n) [LeetCode 19 Equivalent]

Core Mathematical Relation:
- Total size of list = `sz`
- The `n`-th node from the **end** is at index `(sz - n)` from the **beginning** (0-based).
- To delete a node at index `(sz - n)`, we must reach its predecessor `prev` at index `(sz - n - 1)`.

Algorithm Steps:
1. Size Calculation:
   - Traverse the list completely to count total nodes (`sz`).
2. Edge Case Check (`n == sz`):
   - The node to remove is the `head` itself.
   - Simply execute `head = head.next;`.
3. Locate `prev` Node:
   - Start from `head` and advance `(sz - n - 1)` times.
4. Skip Target Node:
   - Reassign `prev.next = prev.next.next;`.

-------------------------------------------------

Pointer Reassignment Visual

List: 1 -> 2 -> 3 -> 4 -> 5 -> null | Target: n = 2 from end
Total Size (sz) = 5
Index to remove = sz - n = 5 - 2 = 3 (Node with value 4)
Target's predecessor = index (sz - n - 1) = 2 (Node with value 3)

       head                 prev              target
        |                    |                  |
        v                    v                  v
     +-----+              +-----+            +-----+            +-----+
     |  1  | ---> ... --> |  3  | ---------> |  4  | ---------> |  5  | ---> null
     +-----+              +-----+            +-----+            +-----+
                             \                                     ^
                              \                                   /
                               \---[ prev.next = prev.next.next ]/

Result: 1 -> 2 -> 3 -> 5 -> null

-------------------------------------------------

Step-by-Step Trace

List: 1 -> 2 -> 3 -> 4 -> 5 -> null | n = 2

-------------------------------------------------------------------------------------------------
Step | Action                  | Variable States                 | Explanation
-------------------------------------------------------------------------------------------------
1    | Count Size              | sz = 5                          | Traverse head to null
2    | Check `n == sz`         | 2 == 5 (false)                  | Target is not the head node
3    | Find `prev` Node        | iToFind = 5 - 2 = 3             | Stop at node before target
     | Loop i = 1 to 2         | prev stops at Node(3)           | prev points to Node(3)
4    | Bypass target node      | prev.next = prev.next.next      | Node(3).next connects to Node(5)
-------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(sz) — First traversal counts size O(sz), second traversal reaches prev O(sz). Overall O(sz).
- Space Complexity: O(1) — No auxiliary data structures created.
=================================================
*/
