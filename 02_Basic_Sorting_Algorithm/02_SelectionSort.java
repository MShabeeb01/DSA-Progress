import java.util.*;  

// First we define a class
public class ArraysCC {

    // Function to perform Selection Sort
    public static void SelectionSort(int arr[]) {
        // Outer loop: Runs from start to end of array
        for (int i = 0; i < arr.length; i++) {
            int minpos = i; // Assume current index has the smallest value

            // Inner loop: Find the actual minimum element in the unsorted part
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minpos] > arr[j]) {  
                    minpos = j;  // Update minpos when smaller element is found
                }
            }

            // Swap: Place the smallest element at the correct position
            int temp = arr[minpos];
            arr[minpos] = arr[i];
            arr[i] = temp;
        }
    }

    // Function to print the array
    public static void Printarr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " "); // Print each element
        }
        System.out.println(); // Print new line at the end
    }

    // Main function (entry point of program)
    public static void main(String args[]) {
        int arr[] = {5, 4, 1, 3, 2}; // Declare and initialize array

        SelectionSort(arr); // Call SelectionSort function to sort array

        Printarr(arr); // Call Printarr function to print sorted array
    }
}


// Example Iteration
// Array = {5, 3, 8, 1, 4}

// Pass 1 (i = 0)
// minpos = 0 (5)
// Compare 5 with 3 → minpos = 1
// Compare 3 with 8 → no change
// Compare 3 with 1 → minpos = 3
// Compare 1 with 4 → no change
// Swap 5 and 1
// Array = {1, 3, 8, 5, 4}

// Pass 2 (i = 1)
// minpos = 1 (3)
// Compare 3 with 8 → no change
// Compare 3 with 5 → no change
// Compare 3 with 4 → no change
// No swap needed
// Array = {1, 3, 8, 5, 4}

// Pass 3 (i = 2)
// minpos = 2 (8)
// Compare 8 with 5 → minpos = 3
// Compare 5 with 4 → minpos = 4
// Swap 8 and 4
// Array = {1, 3, 4, 5, 8}

// Pass 4 (i = 3)
// minpos = 3 (5)
// Compare 5 with 8 → no change
// No swap needed
// Array = {1, 3, 4, 5, 8}

// Final Sorted Array = {1, 3, 4, 5, 8}
