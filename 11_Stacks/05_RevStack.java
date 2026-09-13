import java.util.Stack;

public class ReverseStackExample {

    // Helper: Push an element at the bottom of the stack recursively
    public static void pushAtBottom(Stack<Integer> s, int data) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }
        int top = s.pop();
        pushAtBottom(s, data);
        s.push(top);
    }

    // Operation: Reverse a Stack in-place using Recursion (No extra data structure)
    public static void reverseStack(Stack<Integer> s) {
        // Base Case: If stack is empty, nothing to reverse
        if (s.isEmpty()) {
            return;
        }

        // Pop the top element and hold it in the call stack frame
        int top = s.pop();

        // Recursively reverse the remaining stack
        reverseStack(s);

        // Insert the held top element at the bottom of the reversed stack
        pushAtBottom(s, top);
    }

    // Helper to display stack elements without permanently emptying it
    public static void printStack(Stack<Integer> s) {
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }

    public static void main(String args[]) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);

        // Reverse the stack in-place
        reverseStack(s);

        // Print reversed stack: top to bottom will now be 1 -> 2 -> 3
        printStack(s);
    }
}

/*
==================== SUMMARY ====================

Problem: Reverse a Stack using Recursion

Objective:
Reverse the contents of a stack in-place without using another auxiliary data structure 
(like an additional Stack, Queue, or Array).

Core Algorithmic Technique (Double Recursion):
1. `reverseStack(s)`:
   - Traverses downward by popping and holding every element on the function call stack 
     until the stack becomes empty.
   - On the return journey (unwinding), it inserts each held element into the *bottom* 
     of the stack instead of pushing it back onto the top.
2. `pushAtBottom(s, data)`:
   - Recursively clears the stack, pushes `data` at the empty base, and then pushes 
     the existing elements back on top.

-------------------------------------------------

Recursive Unwinding & Reversal Lifecycle

Target: reverseStack on [1, 2, 3] (Bottom: 1, Top: 3)

--- Phase 1: Popping & Storing ---
reverseStack(s): pop 3 -> calls reverseStack(s = [1, 2])
reverseStack(s): pop 2 -> calls reverseStack(s = [1])
reverseStack(s): pop 1 -> calls reverseStack(s = [])
reverseStack(s): s.isEmpty() == true -> return

--- Phase 2: Inserting at Bottom ---
Return to Level 1: pushAtBottom(s, 1) -> Stack is []        => Stack becomes: [1]
Return to Level 2: pushAtBottom(s, 2) -> Stack is [1]       => Stack becomes: [2, 1]
Return to Level 3: pushAtBottom(s, 3) -> Stack is [2, 1]    => Stack becomes: [3, 2, 1]

Resulting Stack Transformation:
      Before Reversal                   After Reversal
         +-----+                            +-----+
    TOP  |  3  |                       TOP  |  1  |
         |  2  |                            |  2  |
         |  1  |                            |  3  |
         +-----+                            +-----+

-------------------------------------------------

Step-by-Step Trace Table

---------------------------------------------------------------------------------------------------------
Recursion Level | Popped `top` | Stack State at Call | Action on Return        | Stack State After Return
---------------------------------------------------------------------------------------------------------
Level 1         | 3            | [1, 2]              | pushAtBottom(s, 3)      | [3, 2, 1] (Top: 1)
Level 2         | 2            | [1]                 | pushAtBottom(s, 2)      | [2, 1] (Top: 1)
Level 3         | 1            | []                  | pushAtBottom(s, 1)      | [1] (Top: 1)
Level 4 (Base)  | -            | []                  | Return base case        | []
---------------------------------------------------------------------------------------------------------
Output via printStack(): 1, 2, 3

Complexity Analysis:
- Time Complexity : O(n^2) — `reverseStack` makes $n$ calls, and each call triggers `pushAtBottom` which takes up to $O(n)$ time.
- Space Complexity: O(n) — Uses $O(n)$ frames on the implicit recursion call stack (no explicit auxiliary data structure).
=================================================
*/
