import java.util.Stack;

public class StackB {
    // Operation: Push an element at the bottom of a Stack using Recursion
    public static void pushAtBottom(Stack<Integer> s, int data) {
        // Base Case: If stack is empty, push data directly onto the bottom
        if (s.isEmpty()) {
            s.push(data);
            return;
        }

        // Recursive Step: Pop top element and hold in call stack frame
        int top = s.pop();

        // Recurse deeper until reaching the bottom of the stack
        pushAtBottom(s, data);

        // Backtracking Step: Push held top element back onto stack
        s.push(top);
    }

    public static void main(String args[]) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);

        // Insert 4 at the very bottom of stack [1, 2, 3]
        pushAtBottom(s, 4);

        // Print resulting stack from top to bottom
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}

/*
==================== SUMMARY ====================

Problem: Push at the Bottom of a Stack

Objective:
Insert an element at the very bottom (base) of a stack using $O(1)$ auxiliary explicit memory 
by leveraging the system call stack via recursion.

Core Concept (Recursive Backtracking):
1. Downward Phase (Popping & Storing):
   - At each recursive level, pop the current top: `int top = s.pop()`.
   - Store `top` in the local execution frame of that function call.
2. Base Case:
   - When `s.isEmpty()` evaluates to true, the stack is completely cleared.
   - Execute `s.push(data)`. This places `data` at the bottom.
3. Backtracking Phase (Pushing Back):
   - As recursive calls return up the call stack, push back the saved values: `s.push(top)`.
   - Restores all elements in their original relative order above `data`.

-------------------------------------------------

Call Stack & Stack Transformation Visual

Target: pushAtBottom(s, 4) into stack [1, 2, 3] (top is 3)

--- Phase 1: Unwinding (Down to Base Case) ---
Call 1: pop 3 -> hold top=3 -> call pushAtBottom(s, 4) with s = [1, 2]
Call 2: pop 2 -> hold top=2 -> call pushAtBottom(s, 4) with s = [1]
Call 3: pop 1 -> hold top=1 -> call pushAtBottom(s, 4) with s = []
Call 4: s.isEmpty() is true -> s.push(4) -> Stack now: [4] -> return

--- Phase 2: Backtracking (Restoring Elements) ---
Return to Call 3: push held top=1 -> Stack now: [4, 1]
Return to Call 2: push held top=2 -> Stack now: [4, 1, 2]
Return to Call 1: push held top=3 -> Stack now: [4, 1, 2, 3]

Stack State:
       Initial Stack                  Final Stack
       +-----+                        +-----+
  TOP  |  3  |                   TOP  |  3  |
       |  2  |                        |  2  |
       |  1  |                        |  1  |
       +-----+                        |  4  | <-- Pushed at bottom
                                      +-----+

-------------------------------------------------

Step-by-Step Trace Table

---------------------------------------------------------------------------------------------------------
Recursion Level | Stack Before Call | Action Taken / Value Popped  | Stack After Action | Return Step
---------------------------------------------------------------------------------------------------------
Level 1         | [1, 2, 3]         | top = 3, recursive call      | [1, 2]             | Pushes 3 back
Level 2         | [1, 2]            | top = 2, recursive call      | [1]                | Pushes 2 back
Level 3         | [1]               | top = 1, recursive call      | []                 | Pushes 1 back
Level 4 (Base)  | []                | isEmpty() -> s.push(4)       | [4]                | Returns to Level 3
---------------------------------------------------------------------------------------------------------
Final Stack Order (Bottom -> Top): [4, 1, 2, 3]
Console Print (s.pop() sequence): 3, 2, 1, 4

Complexity Analysis:
- Time Complexity : O(n) — Traverses each of the $n$ stack elements down to the base case and back up.
- Space Complexity: O(n) — Uses $n$ frames on the implicit recursion call stack.
=================================================
*/
