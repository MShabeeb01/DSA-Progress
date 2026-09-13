import java.util.Stack;

public class StockSpan {
    // Operation: Calculate Stock Span for consecutive previous days (Monotonic Stack)
    public static void calculateSpan(int stocks[], int span[]) {
        // Stack stores indices of previous highs
        Stack<Integer> s = new Stack<>();

        // Day 0: Span is always 1
        span[0] = 1;
        s.push(0);

        // Process remaining days from 1 to n - 1
        for (int i = 1; i < stocks.length; i++) {
            int currPrice = stocks[i];

            // Pop indices of days with price <= current price
            while (!s.isEmpty() && currPrice >= stocks[s.peek()]) {
                s.pop();
            }

            // If stack is empty -> current price is greater than all previous prices
            if (s.isEmpty()) {
                span[i] = i + 1;
            } else {
                // Previous highest index is at s.peek()
                int prevHigh = s.peek();
                span[i] = i - prevHigh;
            }

            // Push current day's index onto stack
            s.push(i);
        }
    }

    public static void main(String args[]) {
        int stocks[] = {100, 80, 60, 70, 60, 85, 100};
        int span[] = new int[stocks.length];

        calculateSpan(stocks, span);

        // Print calculated span array
        for (int i = 0; i < span.length; i++) {
            System.out.print(span[i] + " ");
        }
        // Output: 1 1 1 2 1 5 7
    }
}

/*
==================== SUMMARY ====================

Problem: Stock Span Problem

Objective:
The span of the stock's price today is defined as the maximum number of consecutive days 
(starting from today and going backward) for which the stock price was less than or equal to today's price.

Core Concept: Monotonic Decreasing Stack (Storing Indices)
- Instead of using a brute-force nested loop ($O(n^2)$), use a Stack to track the **indices of previous highs**.
- The stack maintains prices strictly in decreasing order from bottom to top.

Algorithmic Breakdown:
1. Base Day (`i = 0`):
   - Always has a span of 1.
   - Push index `0` onto the stack: `s.push(0)`.
2. For each Day `i` from `1` to `n - 1`:
   - While `!s.isEmpty()` and `stocks[i] >= stocks[s.peek()]`:
     - Pop `s.pop()`. Any smaller price in between is eclipsed by today's price and won't be needed for future days.
   - Span Calculation:
     - If stack becomes empty: Current price is higher than all prior days -> `span[i] = i + 1`.
     - Otherwise: `prevHigh = s.peek()`, therefore `span[i] = i - prevHigh`.
   - Push current index `i` onto the stack.

-------------------------------------------------

Trace Visual: stocks = [100, 80, 60, 70, 60, 85, 100]

 Day 0: Price = 100 -> Stack: [0]               -> span[0] = 1
 Day 1: Price = 80  -> 80 < 100 -> Stack: [0, 1]-> span[1] = 1 - 0 = 1
 Day 2: Price = 60  -> 60 < 80  -> Stack: [0, 1, 2] -> span[2] = 2 - 1 = 1
 Day 3: Price = 70  -> 70 > 60  -> Pop 2
                       70 < 80  -> Stack: [0, 1, 3] -> span[3] = 3 - 1 = 2
 Day 4: Price = 60  -> 60 < 70  -> Stack: [0, 1, 3, 4] -> span[4] = 4 - 3 = 1
 Day 5: Price = 85  -> 85 > 60  -> Pop 4
                       85 > 70  -> Pop 3
                       85 > 80  -> Pop 1
                       85 < 100 -> Stack: [0, 5] -> span[5] = 5 - 0 = 5
 Day 6: Price = 100 -> 100 >= 85 -> Pop 5
                       100 >= 100 -> Pop 0
                       Stack empty -> span[6] = 6 + 1 = 7

-------------------------------------------------

Step-by-Step Trace Table

---------------------------------------------------------------------------------------------------------
Day `i` | Price `stocks[i]` | Elements Popped (`idx`) | Stack After Pops | `prevHigh` / Empty | `span[i]`
---------------------------------------------------------------------------------------------------------
0       | 100               | None (initial)          | [0]              | -                  | 1
1       | 80                | None                    | [0, 1]           | prevHigh = 0       | 1 - 0 = 1
2       | 60                | None                    | [0, 1, 2]        | prevHigh = 1       | 2 - 1 = 1
3       | 70                | 2 (price 60)            | [0, 1, 3]        | prevHigh = 1       | 3 - 1 = 2
4       | 60                | None                    | [0, 1, 3, 4]     | prevHigh = 3       | 4 - 3 = 1
5       | 85                | 4, 3, 1 (prices 60,70,80)| [0, 5]          | prevHigh = 0       | 5 - 0 = 5
6       | 100               | 5, 0 (prices 85, 100)   | [6]              | Empty              | 6 + 1 = 7
---------------------------------------------------------------------------------------------------------
Result Array `span`: [1, 1, 1, 2, 1, 5, 7]

Complexity Analysis:
- Time Complexity : O(n) — Each index is pushed onto the stack exactly once and popped at most once across all iterations.
- Space Complexity: O(n) — Auxiliary Stack holds at most $n$ indices.
=================================================
*/
