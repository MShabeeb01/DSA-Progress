class Node { // Definition of a Singly Linked List Node

    int data;  // Value stored in the current node
    Node next; // Reference variable (pointer) storing address of the next Node

    // Constructor to initialize a newly created node
    public Node(int data) {
        this.data = data;
        this.next = null; // Unlinked by default upon allocation
    }
}

/*
==================== SUMMARY ====================

Core Concepts:
1. Linked List:
   - A linear data structure composed of distinct elements called `Node`s.
   - Nodes are connected via references (pointers) rather than stored contiguously in memory.

2. Node Components:
   - `data`: Holds the actual primitive or object payload.
   - `next`: A reference variable pointing to the memory location of the subsequent `Node`.

3. Termination:
   - The terminal node of a singly linked list always points to `null`, indicating the end of the sequence.

-------------------------------------------------

Visual Representation

  +-----------+          +-----------+          +-----------+
  |   data    |          |   data    |          |   data    |
  +-----------+   --->   +-----------+   --->   +-----------+   --->   null
  | next(ptr) |          | next(ptr) |          | next(ptr) |
  +-----------+          +-----------+          +-----------+
     Node 1                 Node 2                 Node 3

-------------------------------------------------

Complexity Analysis:
- Node Creation Time Complexity : O(1)
- Node Space Complexity         : O(1) auxiliary per node
=================================================
*/
