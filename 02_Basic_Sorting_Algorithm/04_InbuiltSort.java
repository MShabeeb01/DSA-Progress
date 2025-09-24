import java.util.Arrays;
import java.util.Collections;

public class ArraysCC {

    public static void printarr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = {5, 4, 1, 3, 2};
        Arrays.sort(arr);//To sort in Ascending Order
        printarr(arr);
    }
}
// To get the output in decending order we have to first import 
// java.util.Collections
// int[]. This is a primitive int array. Collections.reverseOrder() only works with objects, not with primitive types. So we should use Integer[].
// To get the output Arrays.sort(arr,Collections.reverseorder()).
