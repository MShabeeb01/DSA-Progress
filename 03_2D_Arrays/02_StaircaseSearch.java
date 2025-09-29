import java.util.*;

public class ArraysCC {

    // Function to perform Staircase Search in a sorted 2D matrix
    public static boolean StaircaseSearch(int matrix[][], int key) {
        // Start from TOP-RIGHT corner
        int row = 0;                     // first row
        int col = matrix[0].length - 1;  // last column

        // Loop until we go out of matrix bounds. 
        while (row < matrix.length && col >= 0) {

            // Case 1: Key is found
            if (matrix[row][col] == key) {
                System.out.println("Key Found at : (" + row + "," + col + ")");
                return true;
            }
            // Case 2: Key is smaller than current element
            // → move LEFT (decrease column)
            else if (key < matrix[row][col]) {
                col--;
            }
            // Case 3: Key is larger than current element
            // → move DOWN (increase row)
            else {
                row++;
            }
        }

        // If we exit the loop, key is not present
        System.out.println("Key not found!");
        return false;
    }

    public static void main(String args[]) {
        // Sorted 2D matrix (each row and column is sorted in ascending order)
        int matrix[][] = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50}
        };

        int key = 33; // element to search

        // Call the staircase search function
        StaircaseSearch(matrix, key);
    }
}
