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

    // Helper method to add at head
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    // Insert at a specific index in the middle of the list
    public void add(int idx, int data) {
        // Special Case: Inserting at the beginning
        if (idx == 0) {
            addFirst(data);
            return;
        }

        Node newNode = new Node(data); // Create new node
        Node temp = head;              // Iterator to reach (idx - 1)
        int i = 0;

        // Traverse to the previous node (idx - 1)
        while (i < idx - 1) {
            temp = temp.next;
            i++;
        }

        // Step 1: Link new node to the next node (temp.next)
        newNode.next = temp.next;

        // Step 2: Link previous node (temp) to new node
        temp.next = newNode;
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

        // Initial list: 1 -> 2 -> 3 -> 4 -> null
        ll.addFirst(4);
        ll.addFirst(3);
        ll.addFirst(2);
        ll.addFirst(1);

        System.out.print("Original List: ");
        ll.print(); // 1 -> 2 -> 3 -> 4 -> null

        // Insert value 9 at index 2 (between 2 and 3)
        ll.add(2, 9);

        System.out.print("After Insertion: ");
        ll.print(); // 1 -> 2 -> 9 -> 3 -> 4 -> null
    }
}

/*
==================== SUMMARY ====================

Operation: add(int idx, int data) [Add in the Middle of LL]

Core Strategy:
To insert a node at index `idx`, you must stop the traversal at the node 
immediately before it (`idx - 1`), denoted as `temp` (or `prev`).

Algorithm Steps:
1. Special Case:
   - If `idx == 0`, invoke `addFirst(data)` because there is no previous node.
2. Traverse to `idx - 1`:
   - Start with `temp = head` and a counter `i = 0`.
   - Loop `while (i < idx - 1)`, moving `temp = temp.next` and incrementing `i`.
3. Pointer Reassignment (Crucial Order):
   - Step 1: `newNode.next = temp.next;` 
     (Connect new node to the downstream list first so the rest of the list isn't lost)
   - Step 2: `temp.next = newNode;` 
     (Direct the preceding node's pointer to the new node)

-------------------------------------------------

Pointer Reassignment Visualization

Target: Insert 9 at idx = 2
List:   [1] (idx 0) ---> [2] (idx 1) ---> [3] (idx 2) ---> [4] (idx 3) -> null

Step 1: Traverse until i = idx - 1 (Node 2)
  temp (prev)
      |
      v
    [ 2 ] ----------> [ 3 ]
             \
      Step (1)\       ^
               v     / Step (1)
             [ 9 (newNode) ]

Step 2: Update temp.next to newNode
  temp (prev)
      |
      v
    [ 2 ] --Step (2)-> [ 9 ] ----------> [ 3 ]

Result: 1 -> 2 -> 9 -> 3 -> 4 -> null

-------------------------------------------------

Step-by-Step Execution Trace

Call: ll.add(2, 9);
List: 1 -> 2 -> 3 -> 4 -> null

---------------------------------------------------------------------------------------------------
Step | Loop (i) | Condition (i < idx - 1) | temp Location | Action Taken
---------------------------------------------------------------------------------------------------
1    | -        | -                       | head (Node 1) | Initialized temp = head, i = 0
2    | i = 0    | 0 < 1 (true)            | Node 1 -> 2   | temp = temp.next, i becomes 1
3    | i = 1    | 1 < 1 (false)           | Node 2        | Loop exits; temp points to Node(2)
4    | -        | -                       | Node 2        | newNode.next = temp.next (Points 9 to 3)
5    | -        | -                       | Node 2        | temp.next = newNode (Points 2 to 9)
---------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Requires traversing up to `idx - 1` nodes (worst-case at the end: O(n)).
- Space Complexity: O(1) — Allocates memory for only one new node.
=================================================
*/
