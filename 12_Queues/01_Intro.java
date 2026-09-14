// Operation: Queue Fundamentals and Concept Overview
public class QueueBasics {

    // Representation of Queue Interface / Core Methods
    public interface Queue<T> {
        // Enqueue: Add element to the back (Rear) - O(1)
        void add(T data);

        // Dequeue: Remove element from the front - O(1)
        T remove();

        // Front / Peek: View element at the front - O(1)
        T peek();

        // Check if queue has no elements - O(1)
        boolean isEmpty();
    }
}

/*
==================== SUMMARY ====================

Topic: Queue Data Structure Fundamentals

Principle:
- FIFO: First In, First Out (or LILO: Last In, Last Out)
- Elements are inserted at one end (REAR / Back) and removed from the other end (FRONT / Head).
- Real-world analogy: A ticket counter line — the first person to join the queue is the first served.

Core Operations:
1. Add / Enqueue:
   - Inserts an element at the REAR of the queue.
   - Time Complexity: O(1)
   - Edge Case: "Queue Overflow" if implemented with a fixed-capacity buffer that is full.

2. Remove / Dequeue:
   - Removes and returns the element at the FRONT of the queue.
   - Time Complexity: O(1) (in optimal Linked List / Circular Array implementations).
   - Edge Case: "Queue Underflow" if remove is called on an empty queue.

3. Peek / Front:
   - Retrieves the element at the FRONT without removing it.
   - Time Complexity: O(1)
   - Edge Case: Returns null / sentinel or throws an exception if the queue is empty.

-------------------------------------------------

Queue FIFO Flow Visual

Enqueue elements: 10, then 20, then 30

                 [ ENQUEUE (add) ]
                         |
                         v
   FRONT                     REAR
  +------+  +------+  +------+
  |  10  |  |  20  |  |  30  |
  +------+  +------+  +------+
     |
     v
  [ DEQUEUE (remove) ]

1. `peek()`  -> returns 10 (element currently at FRONT)
2. `remove()` -> removes and returns 10
3. Queue becomes:
   FRONT               REAR
  +------+  +------+
  |  20  |  |  30  |
  +------+  +------+

-------------------------------------------------

Operations Comparison Table

---------------------------------------------------------------------------------------------------------
Operation | Alias / Term    | Operates On  | Time Complexity | Underflow/Overflow Risk
---------------------------------------------------------------------------------------------------------
Add       | Enqueue         | REAR / Back  | O(1)            | Overflow (Fixed Array)
Remove    | Dequeue         | FRONT / Head | O(1)            | Underflow (Empty Queue)
Peek      | Front           | FRONT / Head | O(1)            | Underflow (Empty Queue)
isEmpty   | Check Empty     | Whole Queue  | O(1)            | None
---------------------------------------------------------------------------------------------------------

Standard Implementations in Java:
- Fixed Array: Simple, but standard shifting yields $O(n)$ removal; requires circular indexing for $O(1)$.
- Circular Array: Overcomes linear shift penalty to deliver $O(1)$ add and remove.
- Linked List: `head` acts as FRONT and `tail` acts as REAR, giving $O(1)$ operations with dynamic scaling.
- Java Collections Framework (JCF): `Queue<Integer> q = new LinkedList<>();` or `new ArrayDeque<>();`.
=================================================
*/
