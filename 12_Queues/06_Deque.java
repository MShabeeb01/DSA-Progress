import java.util.Deque;
import java.util.LinkedList;

public class DequeExample {
    // Operation: Deque (Double Ended Queue) Fundamentals and JCF Usage
    public static void main(String[] args) {
        // Deque is an interface in Java; implemented via LinkedList or ArrayDeque
        Deque<Integer> deque = new LinkedList<>();

        // Add operations at both ends
        deque.addFirst(1); // [1]
        deque.addFirst(2); // [2, 1]
        deque.addLast(3);  // [2, 1, 3]
        deque.addLast(4);  // [2, 1, 3, 4]

        System.out.println("Current Deque: " + deque); // [2, 1, 3, 4]

        // Peek / Get operations
        System.out.println("First element: " + deque.getFirst()); // 2
        System.out.println("Last element: " + deque.getLast());   // 4

        // Remove operations from both ends
        deque.removeFirst(); // Removes 2 -> [1, 3, 4]
        deque.removeLast();  // Removes 4 -> [1, 3]

        System.out.println("Deque after removals: " + deque); // [1, 3]
    }
}

/*
==================== SUMMARY ====================

Topic: Deque (Double-Ended Queue)

Definition:
A Deque (pronounced "deck") is a generalized linear data structure that permits insertion, 
deletion, and inspection of elements from both ends (First/Head and Last/Tail).

Key Property:
- Acts as a hybrid data structure:
  - Can function strictly as a Stack (LIFO) by using only one end (addFirst + removeFirst).
  - Can function strictly as a Queue (FIFO) by using opposite ends (addLast + removeFirst).

Core Operations:
1. Insert:
   - `addFirst(e)`: Inserts element at the front.
   - `addLast(e)` : Inserts element at the rear.
2. Delete:
   - `removeFirst()`: Retrieves and removes element from the front.
   - `removeLast()` : Retrieves and removes element from the rear.
3. Access:
   - `getFirst()`: Inspects the front element without removing it.
   - `getLast()` : Inspects the rear element without removing it.

-------------------------------------------------

Deque Bidirectional Flow Visual

                 addFirst(e)                    addLast(e)
                     |                              |
                     v                              v
              +-------------+-------------+-------------+
  FRONT <---> |   Element   |   Element   |   Element   | <---> REAR
              +-------------+-------------+-------------+
                     |                              |
                     v                              v
               removeFirst()                   removeLast()

-------------------------------------------------

Method Mapping Table (Exception vs Null-Safe)

---------------------------------------------------------------------------------------------------------
Operation   | Position | Throws Exception (if empty) | Returns Special Value (null / false)
---------------------------------------------------------------------------------------------------------
Insert      | Front    | addFirst(e)                 | offerFirst(e)
Insert      | Rear     | addLast(e)                  | offerLast(e)
Remove      | Front    | removeFirst()               | pollFirst()
Remove      | Rear     | removeLast()                | pollLast()
Examine     | Front    | getFirst()                  | peekFirst()
Examine     | Rear     | getLast()                   | peekLast()
---------------------------------------------------------------------------------------------------------

Step-by-Step Trace Table

---------------------------------------------------------------------------------------------------------
Step / Action       | Target End | Value | Deque State (Front -> Rear) | Return / Output
---------------------------------------------------------------------------------------------------------
deque.addFirst(1)   | Front      | 1     | [1]                         | -
deque.addFirst(2)   | Front      | 2     | [2, 1]                      | -
deque.addLast(3)    | Rear       | 3     | [2, 1, 3]                   | -
deque.addLast(4)    | Rear       | 4     | [2, 1, 3, 4]                | -
deque.getFirst()    | Front      | -     | [2, 1, 3, 4]                | Returns 2
deque.getLast()     | Rear       | -     | [2, 1, 3, 4]                | Returns 4
deque.removeFirst() | Front      | -     | [1, 3, 4]                   | Removes 2
deque.removeLast()  | Rear       | -     | [1, 3]                      | Removes 4
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(1) for all core operations (`addFirst`, `addLast`, `removeFirst`, `removeLast`, `getFirst`, `getLast`).
- Space Complexity: O(n) to store $n$ elements.
=================================================
*/
