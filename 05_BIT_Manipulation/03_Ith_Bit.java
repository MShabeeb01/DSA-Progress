import java.util.*;

public class Operations {
    // Function to get the ith bit of a number n
    public static int getIthBit(int n, int i) {
        // Create a bitmask by left shifting 1 by i places
        // Example: if i = 2 -> 1 << 2 = 100 (binary) = 4
        int bitmask = 1 << i;

        // Perform bitwise AND between n and bitmask
        // If the result is 0, that means ith bit is 0
        // Otherwise, ith bit is 1
        if ((n & bitmask) == 0) {
            return 0; // ith bit is 0
        } else {
            return 1; // ith bit is 1
        }
    }

    public static void main(String args[]) {
        // Example: n = 10 (binary: 1010)
        // i = 2 (counting from right, starting at 0)
        // Bitmask = 1<<2 = 4 (binary: 0100)
        // n & bitmask = 1010 & 0100 = 0000 → 0
        // So, output = 0
        System.out.println(getIthBit(10, 2));
    }
}
