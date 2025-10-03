import java.util.*;

public class Operations {
    // Function to count the number of set bits (1s) in binary representation of n
    public static int CountSetBits(int n) {
        int count = 0; // stores number of set bits
        while (n > 0) {
            // Check if the last bit is 1 using bitwise AND
            if ((n & 1) != 0) {
                count++; // increment count if last bit is 1
            }
            n = n >> 1; // right shift n by 1 to check the next bit
        }
        return count; // return the total count of set bits
    }

    public static void main(String args[]) {
        // Example: binary of 10 = 1010, so it has 2 set bits
        System.out.println(CountSetBits(10));
    }
}
