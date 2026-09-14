import java.util.LinkedList;
import java.util.Queue;
// Alternatively: import java.util.ArrayDeque;

public class QueueB {
    // Operation: Queue using Java Collections Framework (JCF)
    public static void main(String args[]) {
        // Queue is an interface in Java, so it cannot be instantiated directly with 'new Queue()'
        // Implementing class options: LinkedList or ArrayDeque
        Queue<Integer> q = new LinkedList<>();
        // Queue<Integer> q = new ArrayDeque<>(); // Modern alternative

        // Enqueue elements: FIFO order
        q.add(1);
        q.add(2);
        q.add(3);

        // Dequeue and display elements from front to rear
        while (!q.isEmpty()) {
            System.out.println(q.peek()); // View front element
            q.remove();                  // Remove front element
        }
    }
}

/*
==================== SUMMARY ====================

Topic: Queue using Java Collections Framework (JCF)

Core Architecture:
- `java.util.Queue` is an **interface**, not a concrete class.
- Because an interface has no method bodies, you cannot instantiate it directly:
    `Queue<Integer> q = new Queue<>();` // Compilation Error!
- Concrete implementing classes in standard Java:
  1. `java.util.LinkedList`: Implements both `List` and `Queue` (doubly-linked list backed).
  2. `java.util.ArrayDeque`: Resizable array-backed double-ended queue (faster with less GC overhead).

JCF Queue Method Equivalents:
-----------------------------------------------------------------------------------------
Operation Type | Throws Exception (if full/empty) | Returns Special Value (null / false)
-----------------------------------------------------------------------------------------
Insert         | `add(e)`                         | `offer(e)`
Remove         | `remove()`                       | `poll()`
Examine        | `element()`                      | `peek()`
-----------------------------------------------------------------------------------------

-------------------------------------------------

Queue Memory & Flow Visual

Sequence: q.add(1) -> q.add(2) -> q.add(3)

     [ FRONT ]                            [ REAR ]
     +---------+       +---------+       +---------+
     |    1    | ----> |    2    | ----> |    3    | ----> null
     +---------+       +---------+       +---------+
          |
          v
   q.peek() reads 1
   q.remove() extracts 1

While Loop Iteration:
- Loop 1: peek() -> 1 | remove() -> queue becomes [2, 3]
- Loop 2: peek() -> 2 | remove() -> queue becomes [3]
- Loop 3: peek() -> 3 | remove() -> queue becomes []
- Loop 4: isEmpty() is true -> terminates

Console Output:
1
2
3

-------------------------------------------------

Step-by-Step Trace Table

---------------------------------------------------------------------------------------------------------
Step / Line      | Queue State (Front -> Rear) | q.peek() Output | q.isEmpty() | Action Taken
---------------------------------------------------------------------------------------------------------
q.add(1)         | [1]                         | -               | false       | Enqueued 1
q.add(2)         | [1, 2]                      | -               | false       | Enqueued 2
q.add(3)         | [1, 2, 3]                   | -               | false       | Enqueued 3
While Loop (1)   | [1, 2, 3]                   | 1               | false       | Prints 1 -> q.remove() -> [2, 3]
While Loop (2)   | [2, 3]                      | 2               | false       | Prints 2 -> q.remove() -> [3]
While Loop (3)   | [3]                         | 3               | false       | Prints 3 -> q.remove() -> []
While Loop (4)   | []                          | -               | true        | Loop breaks
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity:
  - `add()` / `offer()`   : O(1)
  - `remove()` / `poll()` : O(1)
  - `peek()`              : O(1)
  - `isEmpty()`           : O(1)
- Space Complexity: O(n) — Linear memory to hold $n$ elements.
=================================================
*/
