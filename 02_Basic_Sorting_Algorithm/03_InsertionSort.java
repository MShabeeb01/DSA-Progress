import java.util.*;

public class ArraysCC {
    public static void InsertionSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];   // Store the current value
            int prev = i - 1;       // Previous index

            // Shift elements greater than current to one position ahead
            while (prev >= 0 && arr[prev] > current) {
                arr[prev + 1] = arr[prev];
                prev--;
            }

            // Insert current value at the correct position
            arr[prev + 1] = current;
        }
    }

    public static void printarr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = {5, 4, 1, 3, 2};
        InsertionSort(arr);
        printarr(arr);
    }
}
