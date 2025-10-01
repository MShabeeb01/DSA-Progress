import java.util.*;

public class ArraysCC{
    public static void main(String args[]){
    String s1 = "Sony";
    String s2 = "Sony";
    String s3 = new String("Sony");
    // Now we will compare s1,s2,s3.
    if(s1==s2){
        System.out.println("Strings are equal.");
    }else{ 
        System.out.println("Strings are not equal."); // Here the output will be equal.
    }

    if(s1==s3){
        System.out.println("Strings are equal.");
    } else {
        System.out.println("Strings are not equal."); //Here the output will be not equal.
    }
    // This happens because when we create a string using new it creates a new string and java checks in object level if both are equal . Now to compare only the values we use equals().

    if(s1.equals(s3)){
        System.out.println("Strings are equal.");//Now the output will be equal.
    } 
    }
}
