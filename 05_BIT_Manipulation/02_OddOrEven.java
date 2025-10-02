import java.util.*;
public class ArraysCC{
    public static void OddOrEven(int n){
        int bitmask = 1;
        if((n & bitmask) == 0 ){
            //Even Number
            System.out.println("Even Number");
           } else {
                System.out.println("Odd Number");
            }
        
    }
    public static void main(String args[]){
        System.out.println("Enter Number: ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        OddOrEven(num);
    }
}
