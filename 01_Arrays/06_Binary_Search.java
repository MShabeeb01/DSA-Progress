import java.util.*;

public class ArraysCC {
    public static int binarySearch(int numbers[], int key) {
        int start = 0, end = numbers.length - 1;

        while(start <= end) {
            int mid = (start + end) / 2;

            // Check if key is at mid
            if(numbers[mid] == key) {
                return mid;
            }

            // If key is larger, search right half
            if(numbers[mid] < key) {
                start = mid + 1;
            } else { 
                // If key is smaller, search left half
                end = mid - 1;
            }
        }

        // Key not found
        return -1;
    }

    public static void main(String args[]) {
        int numbers[] = {2, 4, 6, 8, 10, 12, 14};
        int key = 10;

        int index = binarySearch(numbers, key);

        if(index == -1) {// It means Key does not exist.
            System.out.println("Key not found.");
        } else {
            System.out.println("Key found at index: " + index);
        }
    }
}

// This Binary search is only applicable for sorted array.

//First it will find the mid of the array using (start+end/2) and compare if the key is equal to or less than or greater than mid and perform certain action.

// If its equal it will return the mid value as key.

//If the mid is greater than key then the key must be on the left side of mid then we split the array again and start will be the same but end will be (mid-1).

//If the mid is smaller than key it means the key must be on the right side then we split the array where end will be same but start=(mid+1).

//This continues until mid == key

// Time complexity is O(log n) # Faster than Linear Search O(n). 
