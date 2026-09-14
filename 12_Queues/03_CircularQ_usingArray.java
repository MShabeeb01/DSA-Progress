public class CircularQueueUsingArray {
    // Operation: Circular Queue Implementation using Array (O(1) Add & Remove)
    static class Queue {
        static int arr[];
        static int size;
        static int rear;
        static int front;

        Queue(int n) {
            arr = new int[n];
            size = n;
            rear = -1;
            front = -1;
        }

        // Check if queue is empty - O(1)
        public static boolean isEmpty() {
            return rear == -1 && front == -1;
        }

        // Check if queue is full - O(1)
        public static boolean isFull() {
            return (rear + 1) % size == front;
        }

        // Add / Enqueue: Insert element circularly at rear - O(1)
        public static void add(int data) {
            if (isFull()) {
                System.out.println("Queue is full");
                return;
            }

            // Adding 1st element
            if (front == -1) {
                front = 0;
            }

            rear = (rear + 1) % size; // Circular increment
            arr[rear] = data;
        }

        // Remove / Dequeue: Remove element circularly from front - O(1)
        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }

            int result = arr[front];

            // Single element remaining condition (reset to empty)
            if (rear == front) {
                rear = front = -1;
            } else {
                front = (front + 1) % size; // Circular increment
            }

            return result;
        }

        // Peek / Front: View front element without removing - O(1)
        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }

            return arr[front];
        }
    }

    public static void main(String args[]) {
        Queue q = new Queue(3);

        q.add(1);
        q.add(2);
        q.add(3);

        System.out.println(q.remove()); // 1
        q.add(4);                       // 4 wraps around to index 0
        System.out.println(q.remove()); // 2
        q.add(5);                       // 5 wraps around to index 1

        while (!q.isEmpty()) {
            System.out.println(q.remove()); // 3 -> 4 -> 5
        }
    }
}

/*
==================== SUMMARY ====================

Topic: Circular Queue Implementation using Arrays

Motivation & Advantage over Linear Array Queue:
- Linear Queue Problem: After elements are removed, unused empty space is left at the front 
  of the array while `rear` hits `size - 1` (cannot insert even if space is available), 
  or it requires costly $O(n)$ shifting on every dequeue.
- Circular Queue Fix: Uses modulo arithmetic `(idx + 1) % size` to wrap pointers back to 
  index `0`, achieving true **$O(1)$ Enqueue and $O(1)$ Dequeue** without shifting elements.

Core Pointer Formulas:
1. Wrap-around Increment:
   - `rear = (rear + 1) % size`
   - `front = (front + 1) % size`
2. Queue Full Condition:
   - `(rear + 1) % size == front`
3. Queue Empty Condition:
   - `rear == -1 && front == -1`
4. Last Element Removed Condition:
   - `rear == front` -> Reset both `rear = front = -1`.

-------------------------------------------------

Circular Wrap-around Visual

Buffer Size = 3:

Step 1: add(1), add(2), add(3)
   Index:     0     1     2
           +-----+-----+-----+
           |  1  |  2  |  3  |
           +-----+-----+-----+
              ^           ^
            front        rear
   Queue is Full: (2 + 1) % 3 == 0 (front)

Step 2: remove() -> returns 1
   front advances: (0 + 1) % 3 = 1
   Index:     0     1     2
           +-----+-----+-----+
           |  -  |  2  |  3  |
           +-----+-----+-----+
                    ^     ^
                  front  rear

Step 3: add(4) -> wraps to index 0 via (2 + 1) % 3 = 0
   Index:     0     1     2
           +-----+-----+-----+
           |  4  |  2  |  3  |
           +-----+-----+-----+
              ^     ^
            rear  front

-------------------------------------------------

Trace Table

Capacity = 3

---------------------------------------------------------------------------------------------------------
Operation   | Element | `front` | `rear` | Array State        | Status / Output
---------------------------------------------------------------------------------------------------------
Init        | -       | -1      | -1     | [ - , - , - ]      | Queue Empty
add(1)      | 1       |  0      |  0     | [ 1 , - , - ]      | First element sets front = 0
add(2)      | 2       |  0      |  1     | [ 1 , 2 , - ]      | rear = (0+1)%3 = 1
add(3)      | 3       |  0      |  2     | [ 1 , 2 , 3 ]      | rear = (1+1)%3 = 2 (Full)
remove()    | -       |  1      |  2     | [ - , 2 , 3 ]      | Returns 1, front = (0+1)%3 = 1
add(4)      | 4       |  1      |  0     | [ 4 , 2 , 3 ]      | rear wraps: (2+1)%3 = 0
remove()    | -       |  2      |  0     | [ 4 , - , 3 ]      | Returns 2, front = (1+1)%3 = 2
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity:
  - `add()`    : O(1)
  - `remove()` : O(1)
  - `peek()`   : O(1)
  - `isEmpty()`: O(1)
  - `isFull()` : O(1)
- Space Complexity: O(n) — Fixed array buffer of size $n$.
=================================================
*/
