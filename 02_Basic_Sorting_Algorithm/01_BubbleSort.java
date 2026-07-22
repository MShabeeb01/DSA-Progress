import java.util.*;

public class ArraysCC{
    public static void Bubblesort(int arr[]){
        for(int turn=0; turn<arr.length-1; turn++){
            for(int j=0; j<arr.length-1-turn; j++){
                if(arr[j]>arr[j+1]){
                    //Swap
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] =temp;
                }
            }
        }
    }

    public static void main(String args[]){
        int arr[] = {5,4,1,3,2};
        Bubblesort(arr);
        printarr(arr);
    }

    public static void printarr(int arr[]){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}

/*
================ CODE EXPLANATION ================

public static void Bubblesort(int arr[])
- This method takes an array and sorts it using Bubble Sort.

for(int turn=0; turn<arr.length-1; turn++)
- 'turn' represents one complete pass through the array.
- One pass cannot sort the whole array.
- After every pass, the largest unsorted element reaches its correct position.
- Therefore, we repeat the process (n-1) times.

for(int j=0; j<arr.length-1-turn; j++)
- Compares two neighbouring elements.
- We use (arr.length-1-turn) because the last 'turn' elements
  are already sorted after each pass, so we don't compare them again.

if(arr[j] > arr[j+1])
- Checks whether the left element is greater than the right element.
- If yes, they are in the wrong order and need to be swapped.

int temp = arr[j];
arr[j] = arr[j+1];
arr[j+1] = temp;
- Swaps the two elements using a temporary variable.

main()
- Creates the unsorted array.
- Calls BubbleSort() to sort the array.
- Calls printarr() to display the sorted array.

printarr(int arr[])
- Loops through the array.
- Prints each element one by one.

==================================================
*/
