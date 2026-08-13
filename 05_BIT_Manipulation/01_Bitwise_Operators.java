import java.util.*;

public class BitManipulation {
    public static void main(String args[]) {

        System.out.println(5 & 6);   // Binary AND
        System.out.println(5 | 6);   // Binary OR
        System.out.println(5 ^ 6);   // Binary XOR
        System.out.println(~5);      // Binary NOT / One's Complement
        System.out.println(5 << 2);  // Binary Left Shift
        System.out.println(6 >> 1);  // Binary Right Shift
    }
}

/*
==================================================
## Code Summary
==================================================

This program demonstrates the basic Bit Manipulation
operators in Java.

The operators are:

1. &   → AND
2. |   → OR
3. ^   → XOR
4. ~   → NOT / One's Complement
5. <<  → Left Shift
6. >>  → Right Shift

Bit manipulation works directly on the binary
representation of numbers.


==================================================
## Iteration
==================================================

We use:

5 = 101

6 = 110


==================================================
## 1. AND Operator (&)
==================================================

Code:

5 & 6


Binary:

  101
& 110
-----
  100


AND rule:

1 & 1 = 1
Otherwise = 0


Therefore:

100 = 4


Output:

4


==================================================
## 2. OR Operator (|)
==================================================

Code:

5 | 6


Binary:

  101
| 110
-----
  111


OR rule:

If at least one bit is 1 → 1


Therefore:

111 = 7


Output:

7


==================================================
## 3. XOR Operator (^)
==================================================

Code:

5 ^ 6


Binary:

  101
^ 110
-----
  011


XOR rule:

Different bits → 1
Same bits → 0


Therefore:

011 = 3


Output:

3


==================================================
## 4. NOT Operator (~)
==================================================

Code:

~5


5 in binary:

00000101


NOT flips every bit:

11111010


In Java, integers use 32-bit two's complement.

So:

~5 = -6


Important rule:

~n = -(n + 1)


Therefore:

~5 = -(5 + 1)
   = -6


Output:

-6


==================================================
## 5. Left Shift (<<)
==================================================

Code:

5 << 2


Binary:

5 = 101


Shift every bit 2 positions to the LEFT:

101 << 2

= 10100


Binary:

10100 = 20


Output:

20


### Easy Formula

For positive numbers:

n << k

≈ n × 2^k


So:

5 << 2
= 5 × 2²
= 5 × 4
= 20


==================================================
## 6. Right Shift (>>)
==================================================

Code:

6 >> 1


Binary:

6 = 110


Shift every bit 1 position to the RIGHT:

110 >> 1

= 011


Binary:

011 = 3


Output:

3


### Easy Formula

For positive numbers:

n >> k

≈ n / 2^k


So:

6 >> 1
= 6 / 2
= 3


==================================================
## Final Output
==================================================

4
7
3
-6
20
3


==================================================
## Quick Revision
==================================================

5 & 6  → 4
5 | 6  → 7
5 ^ 6  → 3
~5     → -6
5 << 2 → 20
6 >> 1 → 3


==================================================
## Easy Way to Remember
==================================================

&  → Both must be 1
|  → At least one must be 1
^  → Different = 1
~  → Flip every bit
<< → Move bits LEFT  → × 2
>> → Move bits RIGHT → ÷ 2

==================================================
*/
