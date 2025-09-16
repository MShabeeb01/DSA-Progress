//Reverse an array
import java.util.*;

public class ArraysCC {
    public static void reverse(int numbers[]){
        int first = 0, last = numbers.length-1;

        //first is an index starting at the beginning of the array (0).
        //last is an index pointing to the last element (length-1).


        while (first < last){ //Run until all the numbers are swapped.
            //swap
            int temp = numbers[last];
            numbers[last] = numbers[first];
            numbers[first] = temp;

            first++; //First index will go forward 
            last--; // Last Index will go back 
        }
    }

    public static void main(String args[]){
        int numbers[] = {2,4,6,8,10};

        reverse(numbers);
        for(int i=0; i<numbers.length;i++){
            System.out.print( numbers[i]+" ");
        }
        System.out.println();
    }
}
