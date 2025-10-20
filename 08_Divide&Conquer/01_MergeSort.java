public class DividenConquer {

    // Utility function to print the array
    public static void printArr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(); // Move to next line after printing the array
    }

    // Merge Sort function that recursively divides and sorts the array
    public static void mergeSort(int arr[], int si, int ei) {
        // Base case: when only one element is left, return
        if (si >= ei) {
            return;
        }

        // Find the mid-point of the array
        int mid = si + (ei - si) / 2;

        // Recursively divide the left half
        mergeSort(arr, si, mid);

        // Recursively divide the right half
        mergeSort(arr, mid + 1, ei);

        // After dividing both halves, merge them in sorted order
        merge(arr, si, mid, ei);
    }

    // Function to merge two sorted subarrays into a single sorted array
    public static void merge(int arr[], int si, int mid, int ei) {

        // Create a temporary array to store the sorted elements
        int temp[] = new int[ei - si + 1];

        // 'i' starts from beginning of left part
        int i = si;

        // 'j' starts from beginning of right part (mid + 1)
        int j = mid + 1;

        // 'k' is the iterator for temporary array
        int k = 0;

        // Step 1: Compare elements of both parts and pick the smaller one
        while (i <= mid && j <= ei) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i]; // left element is smaller → copy to temp
                i++;
            } else {
                temp[k] = arr[j]; // right element is smaller → copy to temp
                j++;
            }
            k++; // Move to next index in temp
        }

        // Step 2: Copy any remaining elements from the left half
        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }

        // Step 3: Copy any remaining elements from the right half
        while (j <= ei) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        // Step 4: Copy sorted elements back to original array
        for (k = 0, i = si; k < temp.length; k++, i++) {
            arr[i] = temp[k];
        }
    }

    public static void main(String args[]) {
        // Unsorted array
        int arr[] = {6, 3, 9, 5, 2, 8};

        // Step 1: Call mergeSort with start index = 0 and end index = arr.length-1
        mergeSort(arr, 0, arr.length - 1);

        // Step 2: Print the final sorted array
        printArr(arr);
    }
}
