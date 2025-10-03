import java.util.*;

public class Operations {
    // Function to clear bits from i to j
    public static int clearRangeOfBits(int n, int i, int j) {
        // Step 1: Create left part of mask with 1s before j
        // (-1 << (j+1)) gives 111...0000 (0s from jth bit and below)
        int a = (-1 << (j + 1));

        // Step 2: Create right part of mask with 1s after i
        // (1 << i) gives 1000..., subtracting 1 gives 000...111 (i bits set)
        int b = (1 << i) - 1;

        // Step 3: OR the two parts → mask with 0s from i to j, 1s elsewhere
        int bitmask = a | b;

        // Step 4: AND with n to clear the range
        return n & bitmask;
    }

    public static void main(String args[]) {
        int n = 31; // 11111 in binary
        int i = 1;  
        int j = 3;  // clear bits from position 1 to 3
        
        System.out.println(clearRangeOfBits(n, i, j)); 
        // Expected output: 17 (10001 in binary)
    }
}
