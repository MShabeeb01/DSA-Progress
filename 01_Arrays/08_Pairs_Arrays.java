//Pairs in arrays
import java.util.*;

public class ArraysCC {
    public static void PrintPairs(int numbers[]){
        int tp = 0;  // tp = total pairs counter

        // Outer loop → picks one number at a time
        for(int i=0; i<numbers.length; i++) {
            int curr = numbers[i]; // the current element (example: 2, then 4, etc.)

            // Inner loop → pairs curr with every element that comes AFTER it
            for(int j=i+1; j<numbers.length; j++){
                System.out.print("(" + curr + "," + numbers[j] + ") ");
                tp++; // increase total pair count
            }
            System.out.println(); // move to new line after finishing one row of pairs
        }

        // After all pairs are printed
        System.out.println("Total Pairs = " + tp);
    }

    public static void main(String args[]){
        int numbers[] = {2,4,6,10};
        PrintPairs(numbers);
    }
}
