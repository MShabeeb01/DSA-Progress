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
    public static int size; // Tracks the total number of nodes in the list

    // Insert at beginning and increment size
    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++; // Increment node count

        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    // Insert at end and increment size
    public void addLast(int data) {
        Node newNode = new Node(data);
        size++; // Increment node count

        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        tail = newNode;
    }

    // Insert at arbitrary index and increment size
    public void add(int idx, int data) {
        if (idx == 0) {
            addFirst(data);
            return;
        }

        Node newNode = new Node(data);
        size++; // Increment node count

        Node temp = head;
        int i = 0;

        while (i < idx - 1) {
            temp = temp.next;
            i++;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        ll.addFirst(2);
        ll.addFirst(1);
        ll.addLast(3);
        ll.addLast(4);
        ll.add(2, 9);

        // Fetching size is now O(1) instead of traversing O(n)
        System.out.println("Size of Linked List: " + LinkedList.size); // Output: 5
    }
}

/*
==================== SUMMARY ====================

Size of a Linked List Tracking:

Why Maintain a `size` Variable?
- Without a tracker, finding the length requires traversing every node from `head` to `null`, taking O(n) time.
- By maintaining a class-level variable `size`:
  - Fetching the length becomes an instantaneous O(1) operation.
  - Automatically maintained:
    - Increment (`size++`) on every `add()` / `addFirst()` / `addLast()`.
    - Decrement (`size--`) on every `remove()` / `removeFirst()` / `removeLast()`.

-------------------------------------------------

State Transition Diagram

Initial:
  head = null, tail = null, size = 0

Action 1: addFirst(2)
  [2] -> null
  size = 1

Action 2: addFirst(1)
  [1] -> [2] -> null
  size = 2

Action 3: addLast(3)
  [1] -> [2] -> [3] -> null
  size = 3

Action 4: addLast(4)
  [1] -> [2] -> [3] -> [4] -> null
  size = 4

Action 5: add(2, 9)
  [1] -> [2] -> [9] -> [3] -> [4] -> null
  size = 5

-------------------------------------------------

Step-by-Step Trace

-----------------------------------------------------------------------------------------
Operation Called | Node Added | Structural State                 | `size` Updated Value
-----------------------------------------------------------------------------------------
ll.addFirst(2)   | Node(2)    | [2] -> null                      | 1
ll.addFirst(1)   | Node(1)    | [1] -> [2] -> null               | 2
ll.addLast(3)    | Node(3)    | [1] -> [2] -> [3] -> null        | 3
ll.addLast(4)    | Node(4)    | [1] -> [2] -> [3] -> [4] -> null | 4
ll.add(2, 9)     | Node(9)    | [1] -> [2] -> [9] -> [3] -> [4]  | 5
-----------------------------------------------------------------------------------------

Complexity Analysis:
- Getting Size Time Complexity : O(1) — Direct variable read.
- Space Complexity             : O(1) — Stored in a single primitive `int` variable.
=================================================
*/
