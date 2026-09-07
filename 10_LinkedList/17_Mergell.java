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

    // Helper 1: Find middle node (modified slow-fast: stop at mid for even sizes)
    private Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next; // Ensures slow lands on the left middle node for even lengths

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // Mid node
    }

    // Helper 2: Merge two sorted linked lists
    private Node merge(Node head1, Node head2) {
        Node mergedLL = new Node(-1); // Dummy node
        Node temp = mergedLL;

        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }

        // Attach remaining nodes
        while (head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }

        while (head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }

        return mergedLL.next; // Head of sorted merged list
    }

    // Main Merge Sort method on Linked List
    public Node mergeSort(Node head) {
        // Base Case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Find mid
        Node mid = getMid(head);

        // Step 2: Divide into Left & Right halves
        Node rightHead = mid.next;
        mid.next = null; // Break the link between halves

        Node newLeft = mergeSort(head);       // Sort left half
        Node newRight = mergeSort(rightHead); // Sort right half

        // Step 3: Conquer / Merge sorted halves
        return merge(newLeft, newRight);
    }

    public void print(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        // Building list: 5 -> 4 -> 3 -> 2 -> 1 -> null
        head = new Node(5);
        head.next = new Node(4);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);

        System.out.print("Original List: ");
        ll.print(head); // 5 -> 4 -> 3 -> 2 -> 1 -> null

        head = ll.mergeSort(head);

        System.out.print("Sorted List:   ");
        ll.print(head); // 1 -> 2 -> 3 -> 4 -> 5 -> null
    }
}

/*
==================== SUMMARY ====================

Algorithm: Merge Sort on Singly Linked List (Divide & Conquer)

Why Merge Sort for Linked Lists?
- Preferred over QuickSort because Linked Lists allow $O(1)$ pointer-based splicing 
  without shifting elements, avoiding random memory access penalties.

Three Core Steps:
1. Divide (Find Mid & Split):
   - Use slow-fast pointers: `slow = head`, `fast = head.next`.
   - `mid = slow`.
   - Split list: `rightHead = mid.next; mid.next = null;`.
2. Recursively Sort:
   - Call `mergeSort(head)` on the left half.
   - Call `mergeSort(rightHead)` on the right half.
3. Conquer (Merge):
   - Merge both sorted chains using a dummy node (`mergedLL = new Node(-1)`).

-------------------------------------------------

Divide & Conquer Tree Visualization

Initial: [5 -> 4 -> 3 -> 2 -> 1]

                    [5 -> 4 -> 3 -> 2 -> 1]
                            /     \
                (mid = 3)  /       \
                    [5 -> 4 -> 3]   [2 -> 1]
                       /    \        /    \
                 [5 -> 4]   [3]    [2]    [1]
                  /   \      |      \      /
                [5]   [4]    |     [1 -> 2] (Merged)
                  \   /      |        |
                 [4 -> 5]    |        |
                     \      /         |
                   [3 -> 4 -> 5]      |
                          \          /
                    [1 -> 2 -> 3 -> 4 -> 5] (Final Merged)

-------------------------------------------------

Trace Table

---------------------------------------------------------------------------------------------------------
Step / Call                 | Action Taken                  | Resulting Segments
---------------------------------------------------------------------------------------------------------
getMid([5, 4, 3, 2, 1])     | slow = Node(3), fast reaches  | mid = Node(3), rightHead = Node(2)
Split                       | mid.next = null               | Left: [5, 4, 3], Right: [2, 1]
mergeSort(Left)             | Recursive sort left           | Sorted Left:  [3, 4, 5]
mergeSort(Right)            | Recursive sort right          | Sorted Right: [1, 2]
merge(Left, Right)          | Two-pointer comparison merge  | Merged: [1, 2, 3, 4, 5]
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n log n)
  - Finding mid: O(n) per level
  - Merging: O(n) per level
  - Recursion tree height: log n levels -> Total: O(n log n)
- Space Complexity: O(log n) auxiliary call stack space (O(1) auxiliary data memory since merge happens via pointers).
=================================================
*/
