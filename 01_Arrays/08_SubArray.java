//Subarrays
import java.util.*;

public class ArraysCC {
    public static void printSubarrays(int numbers[]) {
        int ts = 0; // total subarrays counter

        // Outer loop → choose start index
        for(int i=0; i<numbers.length; i++) {
            int start = i;

            // Middle loop → choose end index
            for(int j=i; j<numbers.length; j++) {
                int end = j;

                // Inner loop → print from start to end
                for(int k=start; k<=end; k++) {
                    System.out.print(numbers[k] + " ");
                }

                ts++; // increase count
                System.out.println(); // go to next line after one subarray
            }
            System.out.println(); // gap between groups of subarrays
        }

        System.out.println("Total Subarrays = " + ts);
    }

    public static void main(String args[]) {
        int numbers[] = {2,4,6,8,10};
        printSubarrays(numbers);
    }
}
