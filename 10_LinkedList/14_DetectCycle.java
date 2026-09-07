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

    // Operation: Detect a Loop/Cycle in a Linked List (Floyd's Cycle Finding Algorithm)
    public static boolean isCycle() {
        Node slow = head; // Moves 1 step at a time (+1)
        Node fast = head; // Moves 2 steps at a time (+2)

        // Ensure fast and fast.next are valid to prevent NullPointerException
        while (fast != null && fast.next != null) {
            slow = slow.next;         // slow +1
            fast = fast.next.next;    // fast +2

            // If slow and fast pointers meet, a cycle exists
            if (slow == fast) {
                return true; // Cycle detected
            }
        }

        // Fast pointer reached end (null) -> No cycle exists
        return false;
    }

    public static void main(String[] args) {
        // Constructing a linked list with a cycle:
        // 1 -> 2 -> 3 -> (points back to 1)
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = head; // Creating loop: 3 points back to 1

        System.out.println("Is cycle present: " + isCycle()); // Output: true
    }
}

/*
==================== SUMMARY ====================

Operation: isCycle() [Floyd's Cycle-Finding Algorithm / Tortoise and Hare]

Core Concept:
1. Pointers Used:
   - `slow`: Advances by 1 node per iteration (`slow = slow.next`).
   - `fast`: Advances by 2 nodes per iteration (`fast = fast.next.next`).
2. Mathematical Principle:
   - If no loop exists, `fast` reaches `null` or `fast.next == null`, confirming linearity.
   - If a loop exists, both pointers enter the cycle. The relative distance between them decreases 
     by 1 unit on every step until `slow == fast` (collision point).

Loop Termination Condition:
- `while (fast != null && fast.next != null)`:
  - `fast != null` guards against even-length list termination.
  - `fast.next != null` guards against odd-length list termination before advancing two steps.

-------------------------------------------------

Pointer Movement Visualization (Cycle Present)

List: 1 -> 2 -> 3 -> [loops back to 1]

Initial State:
  slow = head (1)
  fast = head (1)

Iteration 1:
  slow moves to 2
  fast moves to 3 (1 -> 2 -> 3)
  slow != fast

Iteration 2:
  slow moves to 3
  fast moves to 2 (from 3 -> 1 -> 2)
  slow != fast

Iteration 3:
  slow moves to 1 (from 3 -> 1)
  fast moves to 1 (from 2 -> 3 -> 1)
  slow == fast  ===> Match found! Return true.

-------------------------------------------------

Trace Table

Cycle: 1 -> 2 -> 3 -> 1 (head = 1)

-----------------------------------------------------------------------------------------
Iteration | slow Position | fast Position | slow == fast | Loop Condition Status
-----------------------------------------------------------------------------------------
Start     | Node(1)       | Node(1)       | Ignored (0)  | fast != null && fast.next != null
1         | Node(2)       | Node(3)       | false        | Continues
2         | Node(3)       | Node(2)       | false        | Continues
3         | Node(1)       | Node(1)       | true         | Returns true (Cycle Detected)
-----------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — If no cycle, fast takes n/2 steps. If a cycle exists, collision occurs within cycle length iterations.
- Space Complexity: O(1) — Uses only two pointer reference variables (`slow` and `fast`).
=================================================
*/
