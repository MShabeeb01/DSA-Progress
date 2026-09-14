public class QueueUsingArray {
    // Operation: Queue Implementation using a Fixed-Size Linear Array
    static class Queue {
        static int arr[];
        static int size;
        static int rear;

        // Constructor to initialize array and pointers
        Queue(int n) {
            arr = new int[n];
            size = n;
            rear = -1; // -1 indicates an empty queue
        }

        // Check if queue is empty - O(1)
        public static boolean isEmpty() {
            return rear == -1;
        }

        // Add / Enqueue: Insert element at rear - O(1)
        public static void add(int data) {
            // Check for queue overflow
            if (rear == size - 1) {
                System.out.println("Queue is full");
                return;
            }

            rear = rear + 1;
            arr[rear] = data;
        }

        // Remove / Dequeue: Remove and return front element - O(n)
        public static int remove() {
            // Check for queue underflow
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }

            int front = arr[0]; // Front is always at index 0

            // Shift all subsequent elements one step left
            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i + 1];
            }

            rear = rear - 1; // Decrement rear pointer after removal
            return front;
        }

        // Peek / Front: View front element without removing - O(1)
        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }

            return arr[0]; // Front element always resides at index 0
        }
    }

    public static void main(String args[]) {
        Queue q = new Queue(5);

        q.add(1);
        q.add(2);
        q.add(3);

        // Print and empty queue: FIFO order (1 -> 2 -> 3)
        while (!q.isEmpty()) {
            System.out.println(q.peek());
            q.remove();
        }
    }
}

/*
==================== SUMMARY ====================

Topic: Linear Queue Implementation using Arrays

Core Logic & Pointer Dynamics:
- Fixed Front: `front` is fixed at index `0`.
- Dynamic Rear: `rear` marks the last valid element's index.
  - Initial state: `rear = -1` (Empty).
  - Overflow condition: `rear == size - 1`.
  - Underflow condition: `rear == -1`.

Core Operations Breakdown:
1. `isEmpty()`:
   - Evaluates whether `rear == -1`. Time Complexity: O(1).
2. `add(data)`:
   - Check if `rear == size - 1` (Queue Full).
   - If not, advance pointer `rear = rear + 1` and insert: `arr[rear] = data`.
   - Time Complexity: O(1).
3. `remove()`:
   - Front item is retrieved from `arr[0]`.
   - Shifting penalty: All remaining elements from index `1` to `rear` must shift left 
     by one slot (`arr[i] = arr[i + 1]`).
   - Decrement `rear = rear - 1`.
   - Time Complexity: O(n) due to linear shifting.
4. `peek()`:
   - Directly returns `arr[0]`. Time Complexity: O(1).

-------------------------------------------------

Array State & Shifting Visual

Queue capacity = 4:

1. After add(10), add(20), add(30):
      index:    0     1     2     3
             +-----+-----+-----+-----+
             | 10  | 20  | 30  |     |
             +-----+-----+-----+-----+
               ^                 ^
             front              rear (2)

2. Executing remove():
   - Saved front = arr[0] = 10
   - Shift elements left:
       arr[0] = arr[1] (20 moves to index 0)
       arr[1] = arr[2] (30 moves to index 1)
   - Decrement rear: rear becomes 1

      index:    0     1     2     3
             +-----+-----+-----+-----+
             | 20  | 30  |  -  |     |
             +-----+-----+-----+-----+
               ^           ^
             front        rear (1)

-------------------------------------------------

Trace Table

Capacity = 3

---------------------------------------------------------------------------------------------------------
Operation   | Input Data | `rear` Before | `rear` After | Array State `arr` | Return Value / Output
---------------------------------------------------------------------------------------------------------
Init        | -          | -             | -1           | [ , , ]           | -
add(1)      | 1          | -1            | 0            | [1, , ]           | -
add(2)      | 2          | 0             | 1            | [1, 2, ]          | -
add(3)      | 3          | 1             | 2            | [1, 2, 3]         | -
add(4)      | 4          | 2             | 2            | [1, 2, 3]         | "Queue is full" (Overflow)
peek()      | -          | 2             | 2            | [1, 2, 3]         | Returns 1
remove()    | -          | 2             | 1            | [2, 3, ]          | Returns 1 (shifted elements)
peek()      | -          | 1             | 1            | [2, 3, ]          | Returns 2
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity:
  - `add()`   : O(1)
  - `remove()`: O(n) (Bottleneck caused by array element shifting)
  - `peek()`  : O(1)
  - `isEmpty()`: O(1)
- Space Complexity: O(n) — Pre-allocated contiguous block of memory of size $n$.

Limitation & Optimization:
- The linear array implementation suffers from an expensive $O(n)$ dequeue step.
- To achieve $O(1)$ remove, the **Circular Queue** approach is used with modulo arithmetic `(idx + 1) % size`.
=================================================
*/
