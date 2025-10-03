import java.util.*;

public class Operations {
    // Function to set the ith bit of a number n
    public static int setIthBit(int n, int i) {
        // Create a bitmask by left shifting 1 by i places
        // Example: if i = 2 -> 1 << 2 = 100 (binary) = 4
        int bitmask = 1 << i;

        // Perform bitwise OR between n and bitmask
        // OR rule: 0|1 = 1, 1|0 = 1, 1|1 = 1
        // So this will ensure ith bit becomes 1 (set)
        return n | bitmask;
    }

    public static void main(String args[]) {
        // Example: n = 10 (binary: 1010)
        // i = 2 (counting from right, starting at 0)
        // Bitmask = 1<<2 = 0100 (decimal 4)
        // n | bitmask = 1010 | 0100 = 1110
        // Decimal of 1110 = 14
        System.out.println(setIthBit(10, 2)); // Output = 14
    }
}
