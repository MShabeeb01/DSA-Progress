import java.util.*;

public class ArraysCC{
    public static int getLargest(int numbers[]){
        int largest = Integer.MIN_VALUE; // -Infinity

        for(int i=0; i<numbers.length; i++){
            if(largest < numbers[i]){
                largest = numbers[i];
            }
        }
        return largest;
    }

    public static void main(String args[]){
        int numbers[] ={1,2,3,6,7,8,9,5};
        System.out.println("The largest value is:"+ getLargest(numbers)) ;
    }
}

//If the current element numbers[i] is greater than the current largest, update largest.This way,largest always stores the biggest value seen so far.
//The concept here is finding the maximum (largest) element in an array by scanning it linearly (one by one).
