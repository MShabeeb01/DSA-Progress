public class LinkedList {

    // Nested Node class representing individual elements
    public static class Node {
        int data;   // Value payload stored in the node
        Node next;  // Pointer/reference to the next Node

        public Node(int data) {
            this.data = data;
            this.next = null; // Newly initialized node points to null
        }
    }

    // Pointers to mark boundaries of the Linked List
    public static Node head; // Points to the very first node (entry point)
    public static Node tail; // Points to the very last node

    public static void main(String args[]) {
        LinkedList ll = new LinkedList(); // Instantiate the Linked List object
    }
}

/*
==================== SUMMARY ====================

Core Concepts (Head & Tail):

1. Inner Class `Node`:
   - Marked `static` so it can be instantiated without requiring an outer class instance.
   - Encapsulates `data` and the reference pointer `next`.

2. Boundary Pointers:
   - `head`: Always points to the first node in the linked list. If `head == null`, the list is empty.
   - `tail`: Always points to the last node in the linked list (`tail.next == null`).

3. Memory State at Initialization:
   - When `LinkedList ll = new LinkedList();` is executed:
     - `head` is implicitly initialized to `null`.
     - `tail` is implicitly initialized to `null`.

-------------------------------------------------

Pointer Diagram

Empty List State:
  head = null
  tail = null

Populated List State (Single Node):
           +-------------+
  head --->| data | next |---> null
  tail --->+-------------+

Populated List State (Multiple Nodes):
           +-------------+      +-------------+      +-------------+
  head --->| data | next |--->  | data | next |--->  | data | next |---> null
           +-------------+      +-------------+      +-------------+
                                                            ^
                                                   tail ----|

-------------------------------------------------

Complexity:
- Initialization Time Complexity : O(1)
- Space Complexity               : O(1)
=================================================
*/
