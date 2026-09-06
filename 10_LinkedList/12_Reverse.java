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

    // Operation: Reverse a Linked List (Iterative Approach - 3 Pointer Technique)
    public void reverse() {
        Node prev = null;       // Tracks the previous node (initially null)
        Node curr = tail = head; // Current node being processed; original head becomes new tail
        Node next;              // Temporarily stores the next node reference

        // Traverse and reverse pointers until curr reaches null
        while (curr != null) {
            // Step 1: Store next node to prevent losing the rest of the list
            next = curr.next;

            // Step 2: Reverse current node's pointer to point backwards
            curr.next = prev;

            // Step 3: Advance prev forward to curr
            prev = curr;

            // Step 4: Advance curr forward to next
            curr = next;
        }

        // Step 5: Update head pointer to prev (the old tail)
        head = prev;
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

        // Construct list: 1 -> 2 -> 3 -> 4 -> 5 -> null
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        tail = head.next.next.next.next;

        System.out.print("Original List: ");
        ll.print(); // 1 -> 2 -> 3 -> 4 -> 5 -> null

        ll.reverse();

        System.out.print("Reversed List: ");
        ll.print(); // 5 -> 4 -> 3 -> 2 -> 1 -> null
    }
}

/*
==================== SUMMARY ====================

Operation: reverse() [Iterative 3-Pointer Approach]

Goal:
Transform:   1 -> 2 -> 3 -> 4 -> 5 -> null
Into: null <- 1 <- 2 <- 3 <- 4 <- 5 (head becomes 5, tail becomes 1)

Pointers Required:
1. `prev` : Tracks the already reversed portion of the list. Starts at `null`.
2. `curr` : The active node currently having its link flipped backwards. Starts at `head`.
3. `next` : Safeguards the rest of the unreversed chain before breaking `curr.next`.

The 4-Step Loop Body:
1. `next = curr.next;`  // Save remaining chain
2. `curr.next = prev;`   // Reverse link backward
3. `prev = curr;`        // Move prev up
4. `curr = next;`        // Move curr up

Boundary Update:
- At termination (`curr == null`), `prev` points to the last valid node.
- Reassign `head = prev;`.
- Notice `tail` was updated initially to the original `head`.

-------------------------------------------------

Pointer Reversal Visualization

Initial State:
  prev = null
  curr = [1] -> [2] -> [3] -> ...

During Loop Step 2 (`curr.next = prev`):
  prev <--- [1] (curr)      [2] (next) ---> [3] ---> ...

Advancing Pointers (Steps 3 & 4):
            prev            curr
             |               |
             v               v
  null <--- [1]             [2] ---> [3] ---> ...

Final Loop Iteration:
                                          prev            curr
                                           |               |
                                           v               v
  null <--- [1] <--- [2] <--- [3] <--- [4] <--- [5]      null

After `head = prev`:
  head ---> [5] -> [4] -> [3] -> [2] -> [1] -> null <--- tail

-------------------------------------------------

Step-by-Step Trace Table

List: 1 -> 2 -> 3 -> 4 -> 5 -> null

---------------------------------------------------------------------------------------------------------
Iteration | curr Node | Step 1 (next) | Step 2 (curr.next) | Step 3 (prev) | Step 4 (curr) | State of List
---------------------------------------------------------------------------------------------------------
Start     | Node(1)   | -             | -                  | null          | Node(1)       | 1 -> 2 -> 3 -> 4 -> 5
1         | Node(1)   | Node(2)       | null               | Node(1)       | Node(2)       | null <- 1  2 -> 3...
2         | Node(2)   | Node(3)       | Node(1)            | Node(2)       | Node(3)       | null <- 1 <- 2  3...
3         | Node(3)   | Node(4)       | Node(2)            | Node(3)       | Node(4)       | ... <- 2 <- 3  4...
4         | Node(4)   | Node(5)       | Node(3)            | Node(4)       | Node(5)       | ... <- 3 <- 4  5...
5         | Node(5)   | null          | Node(4)            | Node(5)       | null          | ... <- 4 <- 5
Terminates| null      | -             | -                  | Node(5)       | null          | head = Node(5)
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Single traversal visiting each of the n nodes once.
- Space Complexity: O(1) — In-place pointer modifications; no auxiliary nodes allocated.
=================================================
*/
