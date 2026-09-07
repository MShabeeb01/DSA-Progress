public class DoublyLinkedList {

    // Node blueprint for Doubly Linked List (DLL)
    public static class Node {
        int data;
        Node next; // Pointer to the next node in the list
        Node prev; // Pointer to the previous node in the list

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    // Operation 1: Insert at the beginning of a Doubly Linked List
    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++;

        // Edge Case: Empty List
        if (head == null) {
            head = tail = newNode;
            return;
        }

        // Link newNode with current head
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    // Operation 2: Remove from the beginning of a Doubly Linked List
    public int removeFirst() {
        if (head == null) {
            System.out.println("DLL is empty");
            return Integer.MIN_VALUE;
        }

        int val = head.data;
        size--;

        // Edge Case: Only 1 node
        if (head.next == null) {
            head = tail = null;
            return val;
        }

        head = head.next;
        head.prev = null; // Sever reverse link
        return val;
    }

    // Print forward traversal
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

        dll.addFirst(3);
        dll.addFirst(2);
        dll.addFirst(1);

        dll.print(); // null <-> 1 <-> 2 <-> 3 <-> null
        System.out.println("Size: " + DoublyLinkedList.size); // 3

        System.out.println("Removed: " + dll.removeFirst()); // 1
        dll.print(); // null <-> 2 <-> 3 <-> null
        System.out.println("Size: " + DoublyLinkedList.size); // 2
    }
}

/*
==================== SUMMARY ====================

Topic: Doubly Linked List (DLL) Architecture

Core Concept:
A Doubly Linked List is a linear data structure where each node contains three fields:
1. `data` : The payload / value stored.
2. `next` : Reference link to the succeeding node (or `null` if last).
3. `prev` : Reference link to the preceding node (or `null` if first).

Node Representation:
       +------+------+------+
       | prev | data | next |
       +------+------+------+

Key Advantages over Singly Linked List (SLL):
- Bidirectional Traversal: Can be traversed forward and backward easily.
- Deletion Efficiency: A node can be deleted in O(1) time if its direct reference 
  is known (no need to traverse from head to find `prev`).

Trade-offs:
- Memory Overhead: Extra reference pointer (`prev`) per node.
- Maintenance: Every insertion and deletion requires updating two pointer links instead of one.

-------------------------------------------------

Structural Visualization

Singly Linked List (SLL):
  head ---> [data | next] ----------> [data | next] ---> null

Doubly Linked List (DLL):
           +-------------+     +-------------+
           | 1 |next|prev|     | 2 |next|prev|
           +-------------+     +-------------+
  null <--- [prev]             <--- [prev]
            [next] ----------->     [next] ---> null

Schematic:
  null <---> [ 1 ] <---> [ 2 ] <---> [ 3 ] <---> null
              ^                       ^
             head                    tail

-------------------------------------------------

Comparison: SLL vs DLL

---------------------------------------------------------------------------------------------------------
Feature                    | Singly Linked List (SLL)         | Doubly Linked List (DLL)
---------------------------------------------------------------------------------------------------------
Pointers per Node          | 1 (`next`)                       | 2 (`prev`, `next`)
Traversal Direction        | Forward only                     | Bidirectional (Forward & Backward)
Memory Requirement         | Lower (1 pointer per node)       | Higher (2 pointers per node)
removeLast() with tail ref | O(n) (must find second-to-last)  | O(1) (`tail = tail.prev; tail.next = null`)
Pointer Complexity         | Simple updates                   | Must coordinate both `next` and `prev`
---------------------------------------------------------------------------------------------------------

Complexity Snapshot:
- addFirst()    : O(1) Time | O(1) Space
- removeFirst() : O(1) Time | O(1) Space
- Traversal     : O(n) Time | O(1) Space
=================================================
*/
