import java.util.*;

// In Java, everything must be inside a class.

public class ArraysCC {
    public static int linearSearch(int numbers[], int key) { 
        for(int i=0; i<numbers.length; i++)
        //start index from 0 (first element of array).
        //loop continues until last element (array length).
        //increase index by 1 after each iteration
         {
            if(numbers[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        int numbers[] = {2,4,6,8,10,12,14,16};
        int key = 10;
    
        int index = linearSearch(numbers, key);
        if(index == -1) {
            System.out.println("Key not Found.");
        } else {
            System.out.println("The key is at index " + index + ".");
        }
    }
}
