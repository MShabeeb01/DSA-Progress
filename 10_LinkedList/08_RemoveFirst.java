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

    // Operation: removeFirst in a Singly Linked List
    public int removeFirst() {
        // Base Case 1: Empty list (Underflow)
        if (size == 0) {
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }

        // Base Case 2: Only one element exists
        if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }

        // General Case: Core operation -> head = head.next
        int val = head.data;
        head = head.next; // Shifting head to point to the 2nd node
        size--;

        return val;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        // Setup list: [2] -> [3] -> null
        head = new Node(2);
        head.next = new Node(3);
        tail = head.next;
        size = 2;

        int deletedVal = ll.removeFirst();
        System.out.println("Deleted Node Data: " + deletedVal); // Output: 2
        System.out.println("New Head Data: " + head.data);      // Output: 3
    }
}

/*
==================== SUMMARY ====================

Core Operation: removeFirst (Singly Linked List)

The Golden Line:
                    head = head.next;

Mechanism:
1. Identifying the Target:
   - In a singly linked list, `head` always references the first element.
2. Unlinking the First Node:
   - Reassigning `head = head.next` points `head` directly to the second node.
   - The original first node is left with no incoming references from the program.
3. Garbage Collection:
   - The Java Virtual Machine (JVM) automatic Garbage Collector (GC) frees the orphaned node from Heap memory.

-------------------------------------------------

Pointer Reassignment Visual

Initial State (2 Nodes):
       Head
         |
         v
       +----+-----+      +----+------+
       | 2  | next| ---> | 3  | null |
       +----+-----+      +----+------+
                              ^
                              |
                             Tail

Execute: head = head.next;

                          Head
                            |
                            v
       +----+-----+      +----+------+
       | 2  | next|      | 3  | null |
       +----+-----+      +----+------+
         |                    ^
         v                    |
       (GC)                  Tail

Result:
  head ---> [3 | null] <--- tail

-------------------------------------------------

Trace Table

Initial List: [2] -> [3] -> null (head = Node(2), size = 2)

-----------------------------------------------------------------------------------------
Step | Pointer Before  | Action Executed   | Pointer After   | Garbage Collected | size
-----------------------------------------------------------------------------------------
1    | head -> Node(2) | val = head.data   | head -> Node(2) | None              | 2
2    | head -> Node(2) | head = head.next  | head -> Node(3) | Node(2) isolated  | 2
3    | head -> Node(3) | size--            | head -> Node(3) | Node(2) purged    | 1
-----------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(1) — Direct reference shift; zero traversal required.
- Space Complexity: O(1) — In-place pointer modification.
=================================================
*/
