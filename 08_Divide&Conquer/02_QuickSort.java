public class DividenConquer {

    // Utility function to print the array
    public static void printarr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // QuickSort algorithm
    public static void quicksort(int arr[], int si, int ei) {
        // Base case: If the segment to sort has 0 or 1 elements, do nothing
        if (si >= ei) {
            return;
        }
        // Partition the array, get pivot index
        int pidx = partition(arr, si, ei);
        // Recursively apply QuickSort to left part
        quicksort(arr, si, pidx - 1);
        // Recursively apply QuickSort to right part
        quicksort(arr, pidx + 1, ei);
    }

    // Partition method organizes all items < pivot to its left, > pivot to its right
    public static int partition(int arr[], int si, int ei) {
        int pivot = arr[ei];  // Choose the last element as the pivot
        int i = si - 1;       // Index of the last smaller element
        for (int j = si; j < ei; j++) {
            // If current element is <= pivot, swap it toward the front
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        // Place the pivot after the last smaller element
        i++;
        int temp = arr[ei];
        arr[ei] = arr[i];
        arr[i] = temp;
        return i; // Return the pivot index
    }

    public static void main(String args[]) {
        int arr[] = {6, 3, 9, 8, 2, 5}; // Example array
        quicksort(arr, 0, arr.length - 1); // Sort the array
        printarr(arr); // Output should be: 2 3 5 6 8 9
    }
}
