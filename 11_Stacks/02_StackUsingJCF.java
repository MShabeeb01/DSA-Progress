import java.util.Stack;

public class StackB {
    // Operation: Stack using Java Collections Framework (JCF)
    public static void main(String args[]) {
        // Step 1: Initialize generic Stack of type Integer
        Stack<Integer> s = new Stack<>();

        // Step 2: Push elements onto the stack (LIFO order)
        s.push(1);
        s.push(2);
        s.push(3);

        // Step 3: Traverse and empty the stack from TOP to BOTTOM
        while (!s.isEmpty()) {
            System.out.println(s.peek()); // View the current top element
            s.pop();                     // Remove the current top element
        }
    }
}

/*
==================== SUMMARY ====================

Topic: Stack Implementation via Java Collections Framework (JCF)

Core Mechanism:
- Class: `java.util.Stack<E>`
- Underlying Concept: Subclass of `java.util.Vector` (thread-safe, dynamic array-backed).
- Behavior: Standard LIFO (Last In, First Out).

Core Methods in JCF:
1. `s.push(E item)`: Pushes an item onto the top of the stack.
2. `s.pop()`: Removes the object at the top and returns that object.
3. `s.peek()`: Looks at the object at the top without removing it.
4. `s.isEmpty()`: Tests if this stack is empty (inherited from `Vector`).

-------------------------------------------------

Execution & Memory Visualization

1. Push Sequence:
   s.push(1)      s.push(2)      s.push(3)
   +-----+        +-----+        +-----+
   |     |        |     |        |  3  | <-- TOP (Last In)
   |     |        |  2  | <--TOP |  2  |
   |  1  | <--TOP |  1  |        |  1  |
   +-----+        +-----+        +-----+

2. While Loop (Pop Sequence):
   Iteration 1: peek() -> 3, pop() removes 3
   Iteration 2: peek() -> 2, pop() removes 2
   Iteration 3: peek() -> 1, pop() removes 1
   Iteration 4: s.isEmpty() is true -> terminates

Console Output:
   3
   2
   1

-------------------------------------------------

Trace Table

---------------------------------------------------------------------------------------------------------
Step / Line      | Stack State (Bottom -> Top) | s.peek() Output | s.isEmpty() | Action Taken
---------------------------------------------------------------------------------------------------------
s.push(1)        | [1]                         | -               | false       | Inserted 1
s.push(2)        | [1, 2]                      | -               | false       | Inserted 2
s.push(3)        | [1, 2, 3]                   | -               | false       | Inserted 3
While Loop (1)   | [1, 2, 3]                   | 3               | false       | Print 3 -> s.pop() -> [1, 2]
While Loop (2)   | [1, 2]                      | 2               | false       | Print 2 -> s.pop() -> [1]
While Loop (3)   | [1]                         | 1               | false       | Print 1 -> s.pop() -> []
While Loop (4)   | []                          | -               | true        | Loop breaks
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity:
  - `push()`   : O(1) amortized
  - `pop()`    : O(1)
  - `peek()`   : O(1)
  - `isEmpty()`: O(1)
  - Full Stack Print: O(n) total
- Space Complexity: O(n) — Stores $n$ elements in contiguous dynamic array memory.

Note:
In modern production Java, `Deque<Integer> stack = new ArrayDeque<>();` is preferred over `Stack<Integer>` 
because `java.util.Stack` inherits overhead synchronization from `Vector`. For coding interviews/DSA, 
`Stack<Integer>` is universally accepted and matches standard teaching convention.
=================================================
*/
