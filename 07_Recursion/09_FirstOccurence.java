public class RecursionBasics {

    // Method to find the first occurrence of 'key' in the array
    // arr[] -> array to search in
    // key -> value to search for
    // i -> current index.
    public static int FirstOccurance(int arr[], int key, int i) {
        // Base case: if we reach the end of the array, return -1 (not found)
        if (i == arr.length) {
            return -1;
        }

        // If the current element matches the key, return its index
        if (arr[i] == key) {
            return i;
        }

        // Recursive call: check the next element in the array
        return FirstOccurance(arr, key, i + 1);
    }

    public static void main(String args[]) {
        int arr[] = {1, 2, 3, 4, 5, 6, 5, 7, 5, 9}; // Example array

        // Call FirstOccurance to find first index of 5 starting from index 0
        System.out.println(FirstOccurance(arr, 5, 0)); // Output: 4
    }
}
