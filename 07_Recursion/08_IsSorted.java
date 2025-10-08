public class RecursionBasics {

    // Method to check if the array is sorted in ascending order
    // arr[] -> the array to check
    // i -> current index we are checking
    public static boolean IsSorted(int arr[], int i) {
        // Base case: if we reach the last element, the array is sorted
        if (i == arr.length - 1) {
            return true; // Reached end of array, so it is sorted
        }

        // If current element is greater than the next element
        // then array is not sorted
        if (arr[i] > arr[i + 1]) {
            return false;
        }

        // Recursive call: check the rest of the array
        return IsSorted(arr, i + 1);
    }

    public static void main(String args[]) {
        int arr[] = {1, 2, 3, 4, 5}; // Example array

        // Call the IsSorted function starting from index 0
        // Print true if array is sorted, false otherwise
        System.out.println(IsSorted(arr, 0)); // Output: true
    }
}
