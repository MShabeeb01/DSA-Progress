import java.util.*;

public class ArraysCC {

    // Kadane's Algorithm
    public static void kadanes(int numbers[]) {
        int ms = Integer.MIN_VALUE;  // max sum so far
        int cs = 0;                  // current sum

        for (int i = 0; i < numbers.length; i++) {
            cs = cs + numbers[i];

            if (cs < 0) {
                cs = 0; // reset if current sum is negative.
            }

            ms = Math.max(ms, cs);
        }

        System.out.println("Our max subarray sum is : " + ms);
    }

    public static void main(String args[]) {
        int numbers[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        kadanes(numbers);
    }
}
