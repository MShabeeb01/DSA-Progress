import java.util.Arrays;

public class ArraysCC {

    // Function to perform Counting Sort
    public static void CountingSort(int arr[]) {
        // Step 1: Find the largest element in the array
        int largest = Integer.MIN_VALUE;   // Initialize with smallest possible value
        for (int i = 0; i < arr.length; i++) {
            largest = Math.max(largest, arr[i]); // Update largest value
        }

        // Step 2: Create a count array of size (largest + 1)
        // This will store the frequency of each element
        int count[] = new int[largest + 1];

        // Step 3: Fill the count array
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;  // Increase the count of the element
        }

        // Step 4: Place elements back into original array in sorted order
        int j = 0;  // Index for arr[]
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {   // While the number occurs more than 0 times
                arr[j] = i;          // Place the element into the array
                j++;                 // Move to next position
                count[i]--;          // Decrease frequency
            }
        }
    }

    // Function to print the array. 
    public static void printarr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Main function
    public static void main(String args[]) {
        int arr[] = {5, 4, 1, 3, 4, 8, 1, 2};  // Input array
        CountingSort(arr);   // Call CountingSort function
        printarr(arr);       // Print sorted array
    }
}
