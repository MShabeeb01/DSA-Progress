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
