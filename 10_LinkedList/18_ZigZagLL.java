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

    // Operation: Reorder a Linked List into Zig-Zag form
    // Input : L(1) -> L(2) -> L(3) -> ... -> L(n-1) -> L(n)
    // Output: L(1) -> L(n) -> L(2) -> L(n-1) -> L(3) -> ...
    public void zigZag() {
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Find mid (last node of the 1st half)
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;

        // Step 2: Reverse 2nd half of the list
        Node curr = mid.next;
        mid.next = null; // Split list into two halves
        Node prev = null;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node leftHead = head;  // Head of 1st half
        Node rightHead = prev; // Head of reversed 2nd half
        Node nextL, nextR;

        // Step 3: Alternate merge (Zig-Zag fold)
        while (leftHead != null && rightHead != null) {
            // Save next references
            nextL = leftHead.next;
            nextR = rightHead.next;

            // Zig-Zag link assignments
            leftHead.next = rightHead;
            rightHead.next = nextL;

            // Shift pointers forward
            leftHead = nextL;
            rightHead = nextR;
        }
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

        ll.zigZag();

        System.out.print("Zig-Zag Form:  ");
        ll.print(); // 1 -> 5 -> 2 -> 4 -> 3 -> null
    }
}

/*
==================== SUMMARY ====================

Operation: Zig-Zag Linked List (LeetCode 143: Reorder List)

Problem Statement:
Given a list: $L_1 \rightarrow L_2 \rightarrow L_3 \dots \rightarrow L_{n-1} \rightarrow L_n$
Reorder it to: $L_1 \rightarrow L_n \rightarrow L_2 \rightarrow L_{n-1} \rightarrow L_3 \dots$

3-Step Core Strategy:
1. Find the Mid Node:
   - `slow = head`, `fast = head.next`
   - Stops `slow` at the last node of the first half (e.g., node 3 in a 5-node list).
2. Reverse 2nd Half:
   - Sever link: `mid.next = null`.
   - Iteratively reverse list starting from `curr = mid.next` using the 3-pointer method.
   - Result: `rightHead` points to the new head of the reversed second half.
3. Alternate / Zig-Zag Merge:
   - Cross-link nodes using temporary pointers:
     - `leftHead.next = rightHead;`
     - `rightHead.next = nextL;`
   - Advance `leftHead = nextL` and `rightHead = nextR`.

-------------------------------------------------

Pointer Flow & Transformation Diagram

Original:
  1 -> 2 -> 3 -> 4 -> 5 -> null

Step 1 & 2: Split and Reverse
  Left Half:  1 -> 2 -> 3 -> null
  Right Half: 5 -> 4 -> null  (reversed from 4 -> 5)

Step 3: Alternating Merge
      leftHead        nextL
         |              |
         v              v
        [1]            [2] ---> [3]
          \            ^
           \          /
            v        /
           [5] -----'
            ^        ^
            |        |
        rightHead  nextR

Result:
  1 -> 5 -> 2 -> 4 -> 3 -> null

-------------------------------------------------

Step-by-Step Trace

Initial List: 1 -> 2 -> 3 -> 4 -> 5 -> null

---------------------------------------------------------------------------------------------------------
Phase         | Pointers Involved              | Actions Taken              | Resulting Segments
---------------------------------------------------------------------------------------------------------
1. Find Mid   | slow = 3, fast = 5 (null)      | mid = Node(3)              | Left boundary: [1, 2, 3]
2. Split & Rev| curr = Node(4), mid.next = null| Reverse [4 -> 5]           | leftHead:  1 -> 2 -> 3
              | prev = Node(5)                 | rightHead = Node(5)        | rightHead: 5 -> 4
3. Merge 1    | leftHead = 1, rightHead = 5    | 1 -> 5 -> 2                | 1 -> 5 -> 2 -> 3
4. Merge 2    | leftHead = 2, rightHead = 4    | 2 -> 4 -> 3                | 1 -> 5 -> 2 -> 4 -> 3
5. Terminate  | rightHead = null               | Loop terminates            | Final list assembled
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Finding mid takes O(n), reversing second half takes O(n), and alternate merging takes O(n).
- Space Complexity: O(1) — In-place pointer rewiring without allocating any additional nodes.
=================================================
*/
