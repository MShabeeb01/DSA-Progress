import java.util.*;

public class ArraysCC {
    public static void MaxSubarraysum(int numbers[]) {
        int n = numbers.length;
        int prefix[] = new int[n];

        // Build prefix sum array
        prefix[0] = numbers[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + numbers[i];
        }

        int maxsum = Integer.MIN_VALUE;

        // Outer loop → choose start index
        for (int i = 0; i < n; i++) {
            // Middle loop → choose end index
            for (int j = i; j < n; j++) {
                // Subarray sum using prefix array
                int currsum = (i == 0) ? prefix[j] : prefix[j] - prefix[i - 1];

                // Print subarray
                System.out.print("Subarray: [ ");
                for (int k = i; k <= j; k++) {
                    System.out.print(numbers[k] + " ");
                }
                System.out.println("]  Sum = " + currsum);

                if (maxsum < currsum) {
                    maxsum = currsum;
                }
            }
            System.out.println();
        }

        System.out.println("Max Subarray Sum = " + maxsum);
    }

    public static void main(String args[]) {
        int numbers[] = {2, -4, 6, -5, 7};
        MaxSubarraysum(numbers);
    }
}
