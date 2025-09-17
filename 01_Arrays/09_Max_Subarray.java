import java.util.*;

public class ArraysCC {
    public static void MaxSubarraysum(int numbers[]) {
        int maxsum = Integer.MIN_VALUE; // (-Infinity)

        // Outer loop → choose start index
        for (int i = 0; i < numbers.length; i++) {
            int start = i;

            // Middle loop → choose end index
            for (int j = i; j < numbers.length; j++) {
                int end = j;
                int currsum = 0;   //  reset for each subarray

                System.out.print("Subarray: [ "); // Print subarray elements
                for (int k = start; k <= end; k++) {
                    currsum += numbers[k];
                    System.out.print(numbers[k] + " ");
                }
                System.out.println("]  Sum = " + currsum);

                if (maxsum < currsum) {
                    maxsum = currsum;
                }
            }
            System.out.println(); // gap between groups of subarrays
        }

        System.out.println("Max Subarray Sum = " + maxsum);
    }

    public static void main(String args[]) {
        int numbers[] = {2, 4, 6, 8, 10};
        MaxSubarraysum(numbers);
    }
}
