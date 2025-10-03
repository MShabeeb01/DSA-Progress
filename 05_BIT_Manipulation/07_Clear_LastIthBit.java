import java.util.*;

public class Operations {
    // Function to clear last i bits of a number
    public static int clearLastIBits(int n, int i) {
        // Step 1: Create a mask with all 1s (by default)
        // Step 2: Left shift (-1 << i), so last i bits become 0, rest are 1
        int bitmask = (-1 << i);  
        
        // Step 3: AND with n so that last i bits are cleared
        return n & bitmask;  
    }

    public static void main(String args[]) {
        int n = 59; // 111011 in binary
        int i = 3;  // clear last 3 bits
        
        System.out.println(clearLastIBits(n, i)); 
        // Expected output: 56 (111000 in binary)
    }
}
