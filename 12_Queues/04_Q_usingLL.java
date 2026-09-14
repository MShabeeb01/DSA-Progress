public class QueueUsingLinkedList {
    // Operation: Implement Queue using Singly Linked List (O(1) Add, Remove, Peek)
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class Queue {
        static Node head = null; // Head acts as FRONT (removals occur here)
        static Node tail = null; // Tail acts as REAR (additions occur here)

        // Check if queue is empty - O(1)
        public static boolean isEmpty() {
            return head == null && tail == null;
        }

        // Add / Enqueue: Insert element at tail - O(1)
        public static void add(int data) {
            Node newNode = new Node(data);

            // If queue is currently empty, both head and tail point to new node
            if (head == null) {
                head = tail = newNode;
                return;
            }

            // Append to current tail and update tail pointer
            tail.next = newNode;
            tail = newNode;
        }

        // Remove / Dequeue: Remove and return head node - O(1)
        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }

            int frontVal = head.data;

            // Single node case: list becomes empty
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }

            return frontVal;
        }

        // Peek / Front: View data at head without removing - O(1)
        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }

            return head.data;
        }
    }

    public static void main(String args[]) {
        Queue q = new Queue();

        q.add(1);
        q.add(2);
        q.add(3);

        // Dequeue and print all elements in FIFO order
        while (!q.isEmpty()) {
            System.out.println(q.peek()); // 1 -> 2 -> 3
            q.remove();
        }
    }
}

/*
==================== SUMMARY ====================

Problem: Queue Implementation using Linked List

Objective:
Implement a FIFO Queue using a singly linked list such that both Enqueue (`add`) 
and Dequeue (`remove`) execute in strict $O(1)$ time without size limitations.

Pointer Assignment Strategy:
- FRONT of Queue = `head` of Linked List (Deletion at head is $O(1)$).
- REAR of Queue  = `tail` of Linked List (Insertion at tail is $O(1)$).

Why not the reverse?
- If `head` were the rear and `tail` were the front:
  - Adding at head would be $O(1)$.
  - But removing at tail would require an $O(n)$ scan to find the node before tail.
  - Thus, `head` = FRONT and `tail` = REAR guarantees $O(1)$ for both operations.

Algorithmic Breakdown:
1. `isEmpty()`:
   - True when `head == null && tail == null`.
2. `add(data)`:
   - Create `newNode = new Node(data)`.
   - If empty: `head = tail = newNode`.
   - Otherwise: `tail.next = newNode` and update `tail = newNode`.
3. `remove()`:
   - Store value `frontVal = head.data`.
   - If `head == tail` (only one node left): set `head = tail = null`.
   - Otherwise: advance `head = head.next`.
4. `peek()`:
   - Return `head.data` if not empty.

-------------------------------------------------

Pointer Flow Visual

1. Adding nodes 2, 3, 4:

   add(2):
     [ 2 ] -> null
     ^   ^
   head  tail

   add(3):
     [ 2 ] --------> [ 3 ] -> null
       ^               ^
      head            tail

   add(4):
     [ 2 ] --------> [ 3 ] --------> [ 4 ] -> null
       ^                               ^
      head (FRONT)                    tail (REAR)

2. Executing remove():
   - Saved front = 2
   - Advance head: head = head.next (points to 3)

     [ 3 ] --------> [ 4 ] -> null
       ^               ^
      head            tail

-------------------------------------------------

Step-by-Step Trace Table

---------------------------------------------------------------------------------------------------------
Operation   | Data | `head` Points To | `tail` Points To | List Structure             | Output / Return
---------------------------------------------------------------------------------------------------------
Init        | -    | null             | null             | empty                      | -
add(1)      | 1    | Node(1)          | Node(1)          | [1] -> null                | -
add(2)      | 2    | Node(1)          | Node(2)          | [1] -> [2] -> null         | -
add(3)      | 3    | Node(1)          | Node(3)          | [1] -> [2] -> [3] -> null  | -
peek()      | -    | Node(1)          | Node(3)          | [1] -> [2] -> [3] -> null  | Returns 1
remove()    | -    | Node(2)          | Node(3)          | [2] -> [3] -> null         | Returns 1
remove()    | -    | Node(3)          | Node(3)          | [3] -> null                | Returns 2
remove()    | -    | null             | null             | empty                      | Returns 3
isEmpty()   | -    | null             | null             | empty                      | Returns true
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity:
  - `add()`    : O(1)
  - `remove()` : O(1)
  - `peek()`   : O(1)
  - `isEmpty()`: O(1)
- Space Complexity: O(n) — Dynamically allocated nodes without pre-allocated buffer overhead or wasted memory.
=================================================
*/
