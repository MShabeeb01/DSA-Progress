import java.util.*;

public class Operations {
    // Function to clear the ith bit of number n
    public static int clearIthBit(int n, int i) {
        // Step 1: Create bitmask by left shifting 1 by i places
        int bitmask = 1 << i; // Example: i=2 -> 0100

        // Step 2: Negate the bitmask (~0100 = 1011 in 4 bits)
        // Step 3: AND with n to clear the ith bit
        return n & ~bitmask;
    }

    public static void main(String args[]) {
        // Example: n = 10 (binary: 1010)
        // i = 1 (we want to clear 1st bit)
        // bitmask = 1<<1 = 0010
        // ~bitmask = 1101
        // n & ~bitmask = 1010 & 1101 = 1000
        // Decimal = 8
        System.out.println(clearIthBit(10, 1)); // Output = 8
    }
}
