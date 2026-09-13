import java.util.ArrayList;

// Operation: Stack Data Structure Implementation (Using ArrayList & LinkedList)
public class StackDS {

    // Implementation 1: Stack using ArrayList
    static class StackArrayList {
        private ArrayList<Integer> list = new ArrayList<>();

        // Check if stack is empty - O(1)
        public boolean isEmpty() {
            return list.size() == 0;
        }

        // Push: Insert element at top - O(1)
        public void push(int data) {
            list.add(data);
        }

        // Pop: Remove and return top element - O(1)
        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            int top = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return top;
        }

        // Peek: Look at top element without removing - O(1)
        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }
            return list.get(list.size() - 1);
        }
    }

    // Implementation 2: Stack using Singly Linked List
    static class StackLinkedList {
        private static class Node {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node head = null; // head represents the top of the stack

        // Check if stack is empty - O(1)
        public boolean isEmpty() {
            return head == null;
        }

        // Push: Insert element at head - O(1)
        public void push(int data) {
            Node newNode = new Node(data);
            if (!isEmpty()) {
                newNode.next = head;
            }
            head = newNode;
        }

        // Pop: Remove and return head - O(1)
        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            int top = head.data;
            head = head.next;
            return top;
        }

        // Peek: Look at head without removing - O(1)
        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }
            return head.data;
        }
    }
}

/*
==================== SUMMARY ====================

Topic: Stack Data Structure Fundamentals

Principle:
- LIFO: Last In, First Out (or FILO: First In, Last Out)
- All operations take place strictly at one end called the "TOP".

Core Operations:
1. Push:
   - Adds an element to the TOP of the stack.
   - Time Complexity: O(1)
   - Edge Case: "Stack Overflow" if stack has a fixed capacity that is exceeded.
2. Pop:
   - Removes and returns the element at the TOP of the stack.
   - Time Complexity: O(1)
   - Edge Case: "Stack Underflow" if pop is attempted on an empty stack.
3. Peek (or Top):
   - Returns the value of the TOP element without removing it.
   - Time Complexity: O(1)
   - Edge Case: Throws error or returns sentinel value if stack is empty.

-------------------------------------------------

LIFO Stack Mechanism Visual

Pushing elements [10, 20, 30]:

   push(10)          push(20)          push(30)
   |      |          |      |          |  30  | <-- TOP
   |      |          |  20  | <-- TOP  |  20  |
   |  10  | <-- TOP  |  10  |          |  10  |
   +------+          +------+          +------+

Popping an element:
   pop() -> returns 30 (element at TOP is removed)
   |      |
   |  20  | <-- TOP
   |  10  |
   +------+

-------------------------------------------------

Operation Comparison Table

---------------------------------------------------------------------------------------------------------
Operation | Action Performed               | Time Complexity | Space Complexity | Underflow/Overflow Risk
---------------------------------------------------------------------------------------------------------
push(x)   | Inserts x at the TOP           | O(1)            | O(1)             | Overflow (Fixed Array)
pop()     | Removes & returns TOP element  | O(1)            | O(1)             | Underflow (Empty Stack)
peek()    | Reads TOP element (no removal) | O(1)            | O(1)             | Underflow (Empty Stack)
isEmpty() | Checks if stack has 0 elements | O(1)            | O(1)             | None
---------------------------------------------------------------------------------------------------------

Underlying Implementations in Java:
- ArrayList / Dynamic Array: Fast cache locality, O(1) amortized push, pop from last index.
- LinkedList: No memory resizing penalties; `head` acts as the `top` for O(1) insert/delete.
- Java Collections Framework: `java.util.ArrayDeque` (recommended over legacy `java.util.Stack`).
=================================================
*/
