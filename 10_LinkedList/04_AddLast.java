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

    public void addLast(int data) {
        // Step 1: Create new node
        Node newNode = new Node(data);

        // Corner Case: If list is empty, both head and tail point to new node
        if (head == null) {
            head = tail = newNode;
            return;
        }

        // Step 2: tail next = newNode (link)
        tail.next = newNode;

        // Step 3: tail = newNode
        tail = newNode;
    }

    public static void main(String args[]) {
        LinkedList ll = new LinkedList();
        ll.addLast(1);
        ll.addLast(2);
    }
}

/*
==================== SUMMARY ====================

Operation: addLast(int data)
Appends a new element at the end (tail) of the Singly Linked List.

Algorithm Steps:
1. Create a new node:
   - `Node newNode = new Node(data);`
2. Handle Empty List Edge Case:
   - If `head == null`, list contains no elements yet.
   - Set `head = tail = newNode` and return.
3. Link Current Tail:
   - Set `tail.next = newNode;` connecting the last node to the new node.
4. Update Tail Pointer:
   - Set `tail = newNode;` shifting the tail pointer to mark the new boundary.

-------------------------------------------------

Visual Representation

Initial List:
  head ---> [ 1 | null ]
  tail -----^

Execute: ll.addLast(2);

Step 1: Create newNode
  newNode ---> [ 2 | null ]

Step 2: Link tail.next to newNode
  head ---> [ 1 | next ] ---> [ 2 | null ] <--- newNode
              ^
      tail ---|

Step 3: Shift tail to newNode
  head ---> [ 1 | next ] ---> [ 2 | null ] <--- tail

-------------------------------------------------

Step-by-Step Trace

-------------------------------------------------------------------------------------------------
Step | Action / Call  | Condition (`head == null`) | Pointer Updates           | Resulting State
-------------------------------------------------------------------------------------------------
1    | ll.addLast(1); | true (Empty list)          | head = tail = newNode     | [1] -> null
2    | ll.addLast(2); | false                      | tail.next = newNode (2)   | [1] -> [2] -> null
     |                |                            | tail = newNode (2)        | 
-------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(1) — Constant time due to direct access via the `tail` pointer.
- Space Complexity: O(1) — Allocates only a single node in memory.
=================================================
*/
