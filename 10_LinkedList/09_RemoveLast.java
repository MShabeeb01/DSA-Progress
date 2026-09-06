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
    public static int size;

    // Operation: removeLast in a Singly Linked List
    public int removeLast() {
        // Base Case 1: Empty list (Underflow)
        if (size == 0) {
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }

        // Base Case 2: Only 1 node in the list
        if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }

        // Find prev: the node at index (size - 2) / node before tail
        Node prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }

        int val = tail.data; // Store tail data to return

        // Step 1: prev.next = null (unlink the current tail)
        prev.next = null;

        // Step 2: tail = prev (update tail pointer)
        tail = prev;

        size--;
        return val;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        // Building list: [1] -> [2] -> [3] -> null
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        tail = head.next.next;
        size = 3;

        int removedVal = ll.removeLast();
        System.out.println("Removed Value: " + removedVal); // Output: 3
        System.out.println("New Tail Data: " + tail.data);     // Output: 2
    }
}

/*
==================== SUMMARY ====================

Operation: removeLast (Singly Linked List)

Core Logic:
In a Singly Linked List, you cannot move backwards from `tail`.
To remove the last node, you must traverse from `head` to find `prev` (the node at index `size - 2`).

Key Steps:
1. Underflow Check (`size == 0`):
   - Return sentinel value (`Integer.MIN_VALUE`).
2. Single Element Check (`size == 1`):
   - Store `head.data`, set `head = tail = null`, `size = 0`, and return value.
3. Locate `prev`:
   - Traverse `size - 2` steps from `head` using loop `for (int i = 0; i < size - 2; i++)`.
4. Two Essential Reassignments:
   - Step (1): `prev.next = null;`  (Unlinks node 3, isolating it for GC)
   - Step (2): `tail = prev;`       (Pulls tail reference back to `prev`)
5. Decrement `size--` and return deleted value.

-------------------------------------------------

Pointer Reassignment Visual

Initial State (size = 3):
    head               prev              tail
      |                 |                 |
      v                 v                 v
   +-----+-----+     +-----+-----+     +-----+------+
   |  1  |next | --> |  2  |next | --> |  3  | null |
   +-----+-----+     +-----+-----+     +-----+------+

Execute Step (1): prev.next = null;
    head               prev              tail
      |                 |                 |
      v                 v                 v
   +-----+-----+     +-----+------+    +-----+------+
   |  1  |next | --> |  2  | null |    |  3  | null | (Disconnected)
   +-----+-----+     +-----+------+    +-----+------+

Execute Step (2): tail = prev;
    head             tail (prev)
      |                 |
      v                 v
   +-----+-----+     +-----+------+
   |  1  |next | --> |  2  | null |
   +-----+-----+     +-----+------+

-------------------------------------------------

Step-by-Step Trace

Initial: [1] -> [2] -> [3] -> null, size = 3

-------------------------------------------------------------------------------------------------------
Step | Variable / Pointer | Loop Index | Action Taken            | Memory State
-------------------------------------------------------------------------------------------------------
1    | prev = head        | -          | Initialized at Node(1)  | prev points to 1
2    | prev = prev.next   | i = 0      | Traverse forward        | prev points to Node(2)
3    | -                  | i = 1      | Loop ends (1 < 1 false) | prev successfully reached Node(2)
4    | val = tail.data    | -          | Record removed data     | val = 3
5    | prev.next = null   | -          | Break link to Node(3)   | Node(3) isolated for GC
6    | tail = prev        | -          | Update tail reference   | tail points to Node(2)
7    | size--             | -          | Decrement count         | size = 2
-------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Must traverse up to `size - 2` to locate the second-to-last node.
- Space Complexity: O(1) — In-place pointer modifications with no extra memory.
=================================================
*/
