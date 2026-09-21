import java.util.PriorityQueue;

public class Classroom {

    // Student object implementing Comparable interface to define natural ordering[cite: 23]
    static class Student implements Comparable<Student> {
        String name; //[cite: 23]
        int rank;    //[cite: 23]

        public Student(String name, int rank) { //[cite: 23]
            this.name = name; //[cite: 23]
            this.rank = rank; //[cite: 23]
        }

        // Overriding compareTo to define priority based on rank
        // Smaller rank number means higher priority (e.g., Rank 1 > Rank 4)
        @Override
        public int compareTo(Student s2) {
            return this.rank - s2.rank; 
            // If returning negative: 'this' comes before 's2' (higher priority)
            // If returning positive: 'this' comes after 's2' (lower priority)
            // For reverse order (higher rank first), use: return s2.rank - this.rank;
        }
    }

    public static void main(String args[]) { //[cite: 23]
        // PriorityQueue of custom Student objects
        PriorityQueue<Student> pq = new PriorityQueue<>();

        // add() -> O(log n)[cite: 23]
        pq.add(new Student("A", 4));
        pq.add(new Student("B", 5));
        pq.add(new Student("C", 2));
        pq.add(new Student("D", 12));
        pq.add(new Student("E", 1));

        // peek() -> O(1), remove() -> O(log n)[cite: 23]
        System.out.println("Students extracted according to rank priority:");
        while (!pq.isEmpty()) { //[cite: 23]
            Student curr = pq.peek(); //[cite: 23]
            System.out.println(curr.name + " -> Rank: " + curr.rank);
            pq.remove(); //[cite: 23]
        }
        /*
         Output:
         E -> Rank: 1
         C -> Rank: 2
         A -> Rank: 4
         B -> Rank: 5
         D -> Rank: 12
        */
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 37 — Priority Queue for Objects (Using Comparable Interface)[cite: 23]

Core Mechanics:

1. Why standard custom objects fail in PriorityQueue:
   - For primitive wrappers like `Integer` or `String`, Java natively knows how to compare them because they implement `Comparable`.
   - For custom objects (like `Student` with `name` and `rank`), Java does not know whether to prioritize by name or rank.
   - If a custom class is passed to a PriorityQueue without implementing `Comparable<T>` or providing a `Comparator<T>`, it throws a runtime `ClassCastException`.

2. The `Comparable<T>` Interface:
   - Resides in `java.lang`.
   - Requires overriding a single method:
       `public int compareTo(T o);`
   - Contract:
     - `this.val - o.val < 0`  => `this` has higher priority (placed earlier).
     - `this.val - o.val == 0` => equivalent priority.
     - `this.val - o.val > 0`  => `this` has lower priority (placed later).

3. Comparable vs Comparator:

---------------------------------------------------------------------------------------------------------
Feature             | Comparable<T>                              | Comparator<T>
---------------------------------------------------------------------------------------------------------
Package             | java.lang                                  | java.util
Method              | compareTo(T o)                             | compare(T o1, T o2)
Sorting Logic       | Single, default natural ordering           | Multiple, customized sorting strategies
Class Modification  | Modifies original class source code        | Implemented in separate helper or lambda
---------------------------------------------------------------------------------------------------------

-------------------------------------------------

Internal Min-Heap Array Reordering Visual

Insertion Sequence:
  1. Add Student("A", 4)
  2. Add Student("B", 5)
  3. Add Student("C", 2)  --> Sifts up above Rank 4
  4. Add Student("D", 12)
  5. Add Student("E", 1)  --> Sifts up to root

Resulting Min-Heap Structure by Rank:

                   E(1)  <-- Highest Priority
                 /      \
               C(2)     A(4)
              /    \
            D(12)  B(5)

Extraction Order:
  E(1) -> C(2) -> A(4) -> B(5) -> D(12)

-------------------------------------------------

Step-by-Step Extraction Trace Table

---------------------------------------------------------------------------------------------------------
Iteration | `pq.peek()` Student | Compared Property | Action Taken        | Remaining Queue Size
---------------------------------------------------------------------------------------------------------
1         | Student("E", 1)     | Rank 1 (Min)      | Extracted via remove| 4
2         | Student("C", 2)     | Rank 2 (Next min) | Extracted via remove| 3
3         | Student("A", 4)     | Rank 4            | Extracted via remove| 2
4         | Student("B", 5)     | Rank 5            | Extracted via remove| 1
5         | Student("D", 12)    | Rank 12 (Max)     | Extracted via remove| 0 (Loop ends)
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- `add()`    : O(log n) — Involves up to $\log n$ comparisons of `compareTo()`.
- `peek()`   : O(1) — Reads array element at root index 0.
- `remove()` : O(log n) — Down-heapifies using `compareTo()` down the tree height.
- Space      : O(n) — Array storage holding references to $n$ custom objects.
=================================================
*/
