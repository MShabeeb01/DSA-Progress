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

    // Operation: Detect and Remove Loop/Cycle in a Linked List
    public static void removeCycle() {
        // Step 1: Detect cycle using Floyd's Algorithm
        Node slow = head;
        Node fast = head;
        boolean exists = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;         // +1
            fast = fast.next.next;    // +2

            if (slow == fast) {
                exists = true;        // Cycle found
                break;
            }
        }

        // If no cycle exists, simply return
        if (!exists) {
            return;
        }

        // Step 2: Find the starting point of the loop & tracking the last node
        slow = head;             // a) Reset slow to head
        Node prev = null;        // Tracks the node right before fast (last node in loop)

        // Corner Case: Cycle starts at the head itself (slow == fast initially)
        if (slow == fast) {
            while (fast.next != head) {
                fast = fast.next;
            }
            fast.next = null; // Break loop
            return;
        }

        // b) Move both slow and fast by +1 until they meet
        while (slow != fast) {
            prev = fast;         // Keep track of the last node (previous of fast)
            slow = slow.next;    // +1
            fast = fast.next;    // +1
        }

        // Step 3: Break the cycle by unlinking the last node
        prev.next = null;
    }

    // Traversal method to verify loop removal
    public static void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Create nodes: 1 -> 2 -> 3 -> 4 -> 5
        head = new Node(1);
        Node temp = new Node(2);
        head.next = temp;
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Form loop: Node 5 connects back to Node 3
        head.next.next.next.next.next = head.next.next; 

        // Remove loop
        removeCycle();

        // Print list to verify cycle removal
        print(); // Output: 1 -> 2 -> 3 -> 4 -> 5 -> null
    }
}

/*
==================== SUMMARY ====================

Operation: removeCycle() [Break Cycle in Linked List]

Core Strategy (3 Steps):
1. Detect Cycle:
   - Use Tortoise & Hare: `slow (+1)`, `fast (+2)`.
   - If `slow == fast`, a cycle exists.
2. Find the Starting Point of the Cycle:
   - Reset `slow = head`.
   - Move both `slow` and `fast` by **+1** step simultaneously.
   - Maintain a pointer `prev = fast` to capture the last node before `fast` moves.
   - The node where they meet is the start of the cycle.
3. Break the Cycle:
   - `last node.next = null` -> `prev.next = null`.

-------------------------------------------------

Pointer Movement Visualization

Initial Detection Meeting Point:
            [1] ---> [2] ---> [3] (Cycle Start) ---> [4]
                               ^                      |
                               |                      v
                              [last node (prev)] <--- [5] (Meeting Point: fast)

Reset & Traverse at +1 Speed:
- slow reset to [1]
- fast at [5]
- Move 1:
  - slow moves to [2]
  - prev = [5], fast moves to [3]
- Move 2:
  - slow moves to [3]
  - prev = [3] ... wait, prev holds [5], fast moves from [5] to [3]
  - slow == fast at Node(3)! Loop ends.

Break Step:
  prev (Node 5).next = null;

Result:
  1 -> 2 -> 3 -> 4 -> 5 -> null

-------------------------------------------------

Step-by-Step Execution Trace

List: 1 -> 2 -> 3 -> 4 -> 5 -> (loops back to 3)

---------------------------------------------------------------------------------------------------------
Phase                    | slow Pointer | fast Pointer | prev Pointer | Explanation
---------------------------------------------------------------------------------------------------------
1. Detection             | Meets fast   | Meets slow   | -            | slow == fast (collision confirmed)
2. Reset                 | Node(1)      | Meeting Node | null         | slow reset to head; fast stays
3. Step-by-Step (+1)     | Node(2)      | Next Node    | fast (old)   | Advance both pointers by 1
4. Reaching Cycle Start  | Node(3)      | Node(3)      | Node(5)      | slow == fast; prev holds Node(5)
5. Breaking Loop         | Node(3)      | Node(3)      | Node(5)      | prev.next = null (isolates loop)
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Linear time for detecting, finding cycle start, and breaking the loop.
- Space Complexity: O(1) — Uses only constant reference pointers (`slow`, `fast`, `prev`).
=================================================
*/
