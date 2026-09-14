import java.util.Stack;

public class DuplicateParentheses {
    // Operation: Check for Duplicate / Redundant Parentheses in a Balanced Expression
    public static boolean isDuplicate(String str) {
        Stack<Character> s = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // Closing parenthesis encountered
            if (ch == ')') {
                int count = 0;

                // Pop everything between the opening '(' and current ')'
                while (s.peek() != '(') {
                    s.pop();
                    count++;
                }

                // If count < 1, no meaningful operand/operator was inside the pair -> Duplicate
                if (count < 1) {
                    return true;
                } else {
                    s.pop(); // Pop the opening bracket '('
                }
            } else {
                // Push opening brackets, operands, and operators
                s.push(ch);
            }
        }

        return false; // No duplicate parentheses found
    }

    public static void main(String args[]) {
        String str1 = "((a+b))"; // Contains duplicate/redundant brackets
        String str2 = "(a+(b)/c)"; // Valid without duplicate wrapper
        String str3 = "((a+b)+(c+d))"; // Valid grouping

        System.out.println(isDuplicate(str1)); // true
        System.out.println(isDuplicate(str2)); // false
        System.out.println(isDuplicate(str3)); // false
    }
}

/*
==================== SUMMARY ====================

Problem: Duplicate Parentheses

Objective:
Given a balanced expression string containing parentheses, operands, and operators, 
determine if it contains duplicate (redundant) parentheses.
Example: "((a+b))" has redundant brackets -> returns true.
Example: "(a+b)" has meaningful brackets -> returns false.

Core Concept (Stack LIFO Tracking):
- Any valid non-redundant parenthesis pair must enclose at least one operand or operator (count >= 1).
- If an opening '(' is immediately popped upon encountering ')' without any elements in between 
  (count == 0), the parenthesis pair is redundant.

Algorithmic Steps:
1. Traverse the string character by character:
   - If character is NOT ')':
     - Push it onto the stack (includes '(', operands, operators).
   - If character is ')':
     - Initialize `count = 0`.
     - Pop from stack until reaching the corresponding '(':
       `s.pop(); count++;`
     - Evaluate `count`:
       - If `count < 1`: Found consecutive redundant brackets (e.g., "()" or nested "((...))" where inner pair was already consumed) -> return `true`.
       - If `count >= 1`: Valid enclosure -> pop the matching '(' and proceed.
2. If loop terminates without detecting empty brackets, return `false`.

-------------------------------------------------

Visual Walkthrough

Example 1: str = "((a+b))"

1. Push until first ')':
   Stack: [ '(', '(', 'a', '+', 'b' ]

2. Hit first ')':
   - Pop 'b', '+', 'a' -> count = 3 (count >= 1)
   - Pop '('
   Stack remaining: [ '(' ]

3. Hit second ')':
   - Immediate top is '(' -> while loop doesn't execute -> count = 0
   - count < 1 -> Duplicate detected! -> returns true

-------------------------------------------------

Step-by-Step Trace Table

Test String: str = "((a+b))"

---------------------------------------------------------------------------------------------------------
Index `i` | Char `ch` | Action Taken                       | Elements Popped | Stack State after Action
---------------------------------------------------------------------------------------------------------
0         | '('       | s.push('(')                        | -               | ['(']
1         | '('       | s.push('(')                        | -               | ['(', '(']
2         | 'a'       | s.push('a')                        | -               | ['(', '(', 'a']
3         | '+'       | s.push('+')                        | -               | ['(', '(', 'a', '+']
4         | 'b'       | s.push('b')                        | -               | ['(', '(', 'a', '+', 'b']
5         | ')'       | Pop until '(', count = 3 (>=1)     | 'b', '+', 'a'   | ['(', '(']
          |           | s.pop() to remove inner '('        | '('             | ['(']
6         | ')'       | Top is '(', count = 0 (< 1)        | None            | ['('] -> Returns true!
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Each character is pushed onto the stack once and popped at most once.
- Space Complexity: O(n) — Auxiliary stack stores at most $n$ characters.
=================================================
*/
