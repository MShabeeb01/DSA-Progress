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

    // Traversal and print method
    public void print() {
        // Handle empty list edge case
        if (head == null) {
            System.out.println("null");
            return;
        }

        // Initialize temporary pointer at head (never move head directly)
        Node temp = head;

        // Traverse until end of list (null) is reached
        while (temp != null) {
            System.out.print(temp.data + " "); // Print current node value
            temp = temp.next;                  // Move pointer to next node
        }
        System.out.println();
    }

    public static void main(String args[]) {
        LinkedList ll = new LinkedList();

        // Building list: 1 -> 2 -> 3 -> 4 -> null
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        tail = head.next.next.next;

        ll.print(); // Output: 1 2 3 4
    }
}

/*
==================== SUMMARY ====================

Operation: print() [Linked List Traversal]

Why Use a `temp` Pointer?
- If we move `head = head.next` during traversal, we permanently lose reference 
  to the start of the linked list, causing memory leaks and breaking future operations.
- Using `Node temp = head` allows us to iterate through all nodes while keeping `head` intact.

Algorithm Steps:
1. Corner Case:
   - If `head == null`, the list is empty; return immediately.
2. Initialize Iterator:
   - `Node temp = head;`
3. Traversal Loop:
   - Condition: `while (temp != null)`
   - Print current node's payload: `System.out.print(temp.data + " ")`
   - Advance iterator forward: `temp = temp.next;`
4. End:
   - Once `temp == null`, all nodes have been visited.

-------------------------------------------------

Pointer Walkthrough Visualization

Initial State:
  temp = head
   |
   v
  [1] ---> [2] ---> [3] ---> [4] ---> null
  head                       tail

Iteration 1: Print 1 -> temp = temp.next
            |
            v
  [1] ---> [2] ---> [3] ---> [4] ---> null

Iteration 2: Print 2 -> temp = temp.next
                     |
                     v
  [1] ---> [2] ---> [3] ---> [4] ---> null

Iteration 3: Print 3 -> temp = temp.next
                              |
                              v
  [1] ---> [2] ---> [3] ---> [4] ---> null

Iteration 4: Print 4 -> temp = temp.next
                                     |
                                     v
  [1] ---> [2] ---> [3] ---> [4] ---> null (Loop terminates)

-------------------------------------------------

Step-by-Step Trace

List: 1 -> 2 -> 3 -> 4 -> null

-----------------------------------------------------------------------------------------
Iteration | temp (Pointer At) | temp != null | Printed Output | Next Assignment
-----------------------------------------------------------------------------------------
1         | Node(1)           | true         | 1              | temp = temp.next (Node 2)
2         | Node(2)           | true         | 2              | temp = temp.next (Node 3)
3         | Node(3)           | true         | 3              | temp = temp.next (Node 4)
4         | Node(4)           | true         | 4              | temp = temp.next (null)
5         | null              | false        | -              | Exit loop
-----------------------------------------------------------------------------------------
Final Printed: 1 2 3 4

Complexity Analysis:
- Time Complexity : O(n) — Visits each of the n nodes exactly once.
- Space Complexity: O(1) — Uses only a single auxiliary reference variable `temp`.
=================================================
*/
