import java.util.*;

public class Operations {

    // Function to clear the ith bit of a number
    public static int clearIthBit(int n, int i) {
        int bitmask = ~(1 << i);  // Create mask with 0 at ith position and 1s elsewhere
        return n & bitmask;       // AND with n clears the ith bit
    }

    // Function to update the ith bit to a new value (0 or 1)
    public static int UpdateIthBit(int n, int i, int newBit) {
        // Step 1: Clear the ith bit
        n = clearIthBit(n, i);

        // Step 2: Create a bitmask with newBit at ith position
        int bitmask = newBit << i;

        // Step 3: OR operation sets the newBit at ith position
        return n | bitmask;
    }

    // Main function to test
    public static void main(String args[]) {
        System.out.println(UpdateIthBit(10, 1, 1)); // 10 -> 1010, set 1st bit -> 1011 = 11
        System.out.println(UpdateIthBit(10, 2, 0)); // 10 -> 1010, set 2nd bit to 0 -> 0010 = 2
    }
}
