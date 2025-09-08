//There are 4 operations in arrays 
//1-Create , 2- Input , 3-Output , 4-Update.

//1- Creating an Array:
import java.util.*;
public class ArrayCC {
    public static void main(String args[]){

    // This is how you create an Array. 

        int marks[] = new int [100];  

    // This is to Input the Array.

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Physics Mark:");
        marks[0] = sc.nextInt();
        System.out.println("Enter your Chemistry Mark:");
        marks[1] = sc.nextInt();
        System.out.println("Enter your Maths Mark:");
        marks[2] = sc.nextInt();
        System.out.println();

    // This is how you Output the Arrays .

        System.out.println("Physics Mark:"+ marks[0]);
        System.out.println("Chemistry Mark:"+ marks[1]);
        System.out.println("Maths Mark:"+ marks[2]);

    // This is how you update a specific Array.

    marks[2] = 97;
    System.out.println("Maths marks:"+ marks[2]);

    // To find the length of an Array .

    System.out.println("Length of Array ="+ marks.length);

    }
}
