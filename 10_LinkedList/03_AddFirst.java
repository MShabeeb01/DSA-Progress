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

    public void addFirst(int data) {
        // Step 1: Create new node
        Node newNode = new Node(data);

        // Corner Case: If list is empty, both head and tail point to new node
        if (head == null) {
            head = tail = newNode;
            return;
        }

        // Step 2: newNode next = head (link)
        newNode.next = head;

        // Step 3: head = newNode
        head = newNode;
    }

    public static void main(String args[]) {
        LinkedList ll = new LinkedList();
        ll.addFirst(2);
        ll.addFirst(1);
    }
}

/*
==================== SUMMARY ====================

Operation: addFirst(int data)
Inserts a new element at the beginning (head) of the Singly Linked List.

Algorithm Steps:
1. Create a new node:
   - `Node newNode = new Node(data);`
2. Handle Empty List Edge Case:
   - If `head == null`, list has no nodes yet.
   - Set `head = tail = newNode` and return.
3. Link New Node:
   - Set `newNode.next = head;` so the new node points to the current first node.
4. Update Head Pointer:
   - Set `head = newNode;` to make the new node the official start of the list.

-------------------------------------------------

Visual Representation

Initial List:
  head ---> [ 2 | null ]
  tail -----^

Execute: ll.addFirst(1);

Step 1: Create newNode
  newNode ---> [ 1 | null ]

Step 2: Link newNode.next to current head
  newNode ---> [ 1 | next ] 
                     |
                     v
            head -> [ 2 | null ]

Step 3: Shift head to newNode
            head ---> [ 1 | next ] ---> [ 2 | null ] <--- tail

-------------------------------------------------

Step-by-Step Trace

-------------------------------------------------------------------------------------------------
Step | Action / Call   | Condition (`head == null`) | Pointer Updates          | Resulting State
-------------------------------------------------------------------------------------------------
1    | ll.addFirst(2); | true (Empty list)          | head = tail = newNode    | [2] -> null
2    | ll.addFirst(1); | false                      | newNode.next = head (2)  | [1] -> [2] -> null
     |                 |                            | head = newNode (1)       | 
-------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(1) — Direct reference assignment without traversing.
- Space Complexity: O(1) — Allocates only a single node in memory.
=================================================
*/
