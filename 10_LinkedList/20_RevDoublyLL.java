public class DoublyLinkedList {

    public static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    // Helper: Add first to construct the list
    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    // Operation: Reverse a Doubly Linked List (DLL)
    // 3 Variables, 4 Steps per iteration (with both next and prev link swaps)
    public void reverse() {
        // 3 Variables setup
        Node curr = head;
        Node prev = null;
        Node next;

        // Traverse and reverse pointers for every node
        while (curr != null) {
            // Step 1: Store next node to avoid losing reference to remaining list
            next = curr.next;

            // Step 2: Swap pointers (curr.next becomes prev, curr.prev becomes next)
            curr.next = prev;
            curr.prev = next;

            // Step 3: Advance prev to current node
            prev = curr;

            // Step 4: Advance curr to next node
            curr = next;
        }

        // Update head to prev (the last processed non-null node)
        head = prev;
    }

    // Traversal method
    public void print() {
        Node temp = head;
        System.out.print("null <-> ");
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        // Building list: 1 <-> 2 <-> 3 <-> 4 <-> null
        dll.addFirst(4);
        dll.addFirst(3);
        dll.addFirst(2);
        dll.addFirst(1);

        System.out.print("Original DLL: ");
        dll.print(); // null <-> 1 <-> 2 <-> 3 <-> 4 <-> null

        dll.reverse();

        System.out.print("Reversed DLL: ");
        dll.print(); // null <-> 4 <-> 3 <-> 2 <-> 1 <-> null
    }
}

/*
==================== SUMMARY ====================

Operation: reverse() [Reverse a Doubly Linked List]

Core Strategy:
Reversing a DLL requires swapping both `next` and `prev` pointers for each node 
along the chain, ensuring the bidirectional properties remain valid.

The 3 Variables:
1. `Node curr = head;`  // Active node being manipulated
2. `Node prev = null;`  // Tracks preceding node (new next target)
3. `Node next;`         // Safeguard reference to remaining chain

The 4-Step Iteration Body:
- Step 1: `next = curr.next;`  // Preserve reference to the next node
- Step 2: Pointer Swap:
          `curr.next = prev;`  // Redirect forward link backwards
          `curr.prev = next;`  // Redirect backward link forwards
- Step 3: `prev = curr;`       // Shift prev pointer forward
- Step 4: `curr = next;`       // Shift curr pointer forward

Termination:
- When `curr == null`, loop terminates.
- Update `head = prev;` (the previous tail node is the new head).

-------------------------------------------------

Pointer Reversal Visualization

Single Node Swap:
Before:
            prev           next
             ^              ^
             |              |
  null <--- [prev | 1 | next] ---> [node 2]

After Step 2 Swap (`curr.next = prev`, `curr.prev = next`):
            next           prev
             ^              ^
             |              |
  [node 2] <--- [prev | 1 | next] ---> null

Full Chain Inversion:
Original:
  null <---> [ 1 ] <---> [ 2 ] <---> [ 3 ] <---> null
              ^                       ^
             head                    tail

Reversed:
  null <---> [ 3 ] <---> [ 2 ] <---> [ 1 ] <---> null
              ^                       ^
             head                    tail

-------------------------------------------------

Step-by-Step Trace Table

List: 1 <-> 2 <-> 3 <-> null

-----------------------------------------------------------------------------------------------------------------
Iteration | curr Node | Step 1 (next) | Step 2 (curr.next / curr.prev) | Step 3 (prev) | Step 4 (curr)
-----------------------------------------------------------------------------------------------------------------
Start     | Node(1)   | -             | -                              | null          | Node(1)
1         | Node(1)   | Node(2)       | next: null,   prev: Node(2)    | Node(1)       | Node(2)
2         | Node(2)   | Node(3)       | next: Node(1), prev: Node(3)   | Node(2)       | Node(3)
3         | Node(3)   | null          | next: Node(2), prev: null      | Node(3)       | null
End       | null      | -             | Loop breaks                    | head = prev   | head = Node(3)
-----------------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Single pass visiting each of the n nodes once.
- Space Complexity: O(1) — Constant space; in-place pointer swaps with no extra memory allocation.
=================================================
*/
