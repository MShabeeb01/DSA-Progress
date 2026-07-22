import java.util.*;

public class ArraysCC {

    public static void InsertionSort(int arr[]) {

        // Pick one element at a time (starting from index 1)
        for (int i = 1; i < arr.length; i++) {

            int current = arr[i];   // Number we are holding in our hand
            int prev = i - 1;       // Start checking from the previous element

            // Move all bigger elements one step to the right
            while (prev >= 0 && arr[prev] > current) {
                arr[prev + 1] = arr[prev]; // Shift bigger element right
                prev--;                    // Move left and compare again
            }

            // Put the current element into its correct position
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

/*
-------------------- ITERATION (Using "_" for empty space) --------------------

Initial Array:
5 4 1 3 2

i = 1
current = 4

5 _ 1 3 2      (Picked 4)
_ 5 1 3 2      (Move 5 right)
4 5 1 3 2      (Place 4)

------------------------------------------------------------

i = 2
current = 1

4 5 _ 3 2      (Picked 1)
4 _ 5 3 2      (Move 5 right)
_ 4 5 3 2      (Move 4 right)
1 4 5 3 2      (Place 1)

------------------------------------------------------------

i = 3
current = 3

1 4 5 _ 2      (Picked 3)
1 4 _ 5 2      (Move 5 right)
1 _ 4 5 2      (Move 4 right)
1 3 4 5 2      (Place 3)

------------------------------------------------------------

i = 4
current = 2

1 3 4 5 _      (Picked 2)
1 3 4 _ 5      (Move 5 right)
1 3 _ 4 5      (Move 4 right)
1 _ 3 4 5      (Move 3 right)
1 2 3 4 5      (Place 2)

------------------------------------------------------------

FINAL SORTED ARRAY:
1 2 3 4 5

Memory Trick:
1. Pick one element (current).
2. "_" is the empty space.
3. Move bigger elements into "_".
4. Put current into the empty space.
5. Repeat until the array is sorted.

*/
