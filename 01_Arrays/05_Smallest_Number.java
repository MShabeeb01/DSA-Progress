import java.util.*;

public class ArraysCC{
    public static int getSmallest(int numbers[]){
        int smallest = Integer.MAX_VALUE; // +Infinity

        for(int i=0; i<numbers.length; i++){
            if(smallest > numbers[i]){
                smallest = numbers[i];
            }
        }
        return smallest;
    }

    public static void main(String args[]){
        int numbers[] ={1,2,3,6,7,8,9,5};
        System.out.println("The Smallest value is:"+ getSmallest(numbers)) ;
    }
}

//If the current element numbers[i] is smaller than the current smaller, update Smallest.This way, Smallest always stores the lowest value seen so far.
//The concept here is finding the smallest element in an array by scanning it linearly (one by one).
// It always compare the if the number is smaller than +infinity.
