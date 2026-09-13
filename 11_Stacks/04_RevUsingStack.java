import java.util.Stack;

public class ReverseStringStack {
    // Operation: Reverse a String using a Stack
    public static String reverseString(String str) {
        // Step 1: Initialize a stack of characters
        Stack<Character> s = new Stack<>();
        int idx = 0;

        // Step 2: Push each character of the string onto the stack
        while (idx < str.length()) {
            s.push(str.charAt(idx));
            idx++;
        }

        // Step 3: Pop characters from stack and build reversed string
        StringBuilder result = new StringBuilder("");
        while (!s.isEmpty()) {
            char curr = s.pop();
            result.append(curr);
        }

        return result.toString();
    }

    public static void main(String args[]) {
        String str = "abc";
        String result = reverseString(str);
        System.out.println("Reversed String: " + result); // Output: "cba"
    }
}

/*
==================== SUMMARY ====================

Problem: Reverse a String using Stack

Objective:
Given an input string `str`, invert the order of its characters using a Stack data structure.

Core Concept (LIFO Application):
- The Last-In, First-Out (LIFO) property naturally reverses sequential elements.
- The first character entered will sit at the bottom of the stack, and the last character 
  will be at the top.
- Successively popping elements reads the string in exact reverse order.

Algorithmic Breakdown:
1. Push Phase:
   - Scan string left-to-right using index `idx`.
   - Call `s.push(str.charAt(idx))` to place each character on top of the stack.
2. Pop / Build Phase:
   - Use `StringBuilder` for $O(1)$ amortized append operations (avoids $O(n^2)$ immutable String concatenation).
   - While `!s.isEmpty()`, pop the top character `char curr = s.pop()` and append it to `result`.
3. Return:
   - Convert `StringBuilder` to standard String via `result.toString()`.

-------------------------------------------------

LIFO Reversal Visual

Input: str = "abc"

Step 1: Push characters
   push('a')        push('b')        push('c')
   +-----+          +-----+          +-----+
   |     |          |     |          | 'c' | <-- TOP
   |     |          | 'b' | <-- TOP  | 'b' |
   | 'a' | <-- TOP  | 'a' |          | 'a' |
   +-----+          +-----+          +-----+

Step 2: Pop characters
   pop() -> 'c'  --> result = "c"
   pop() -> 'b'  --> result = "cb"
   pop() -> 'a'  --> result = "cba"

Output: "cba"

-------------------------------------------------

Step-by-Step Trace Table

Input: str = "abc"

---------------------------------------------------------------------------------------------------------
Phase | Step / Action | Pointer / Stack State (Bottom -> Top) | Appended Char | StringBuilder State
---------------------------------------------------------------------------------------------------------
Push  | idx = 0       | ['a']                                 | -             | ""
      | idx = 1       | ['a', 'b']                            | -             | ""
      | idx = 2       | ['a', 'b', 'c']                       | -             | ""
      | idx = 3       | Loop ends (idx == length)             | -             | ""
Pop   | Iteration 1   | ['a', 'b']                            | 'c'           | "c"
      | Iteration 2   | ['a']                                 | 'b'           | "cb"
      | Iteration 3   | []                                    | 'a'           | "cba"
      | Termination   | s.isEmpty() == true                   | -             | Returns "cba"
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — One pass to push all $n$ characters into the stack ($O(n)$) and one pass to pop them into StringBuilder ($O(n)$).
- Space Complexity: O(n) — Uses an auxiliary Stack of size $n$ and a StringBuilder buffer of size $n$.
=================================================
*/
