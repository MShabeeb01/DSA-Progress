import java.util.*;

public class ArraysCC{
    public static String substring(String str,int si, int ei){
        String substr ="";
        for(int i=si; i<ei; i++){
            substr += str.charAt(i);       // This is the manual method .
        }
        return substr;
    } 

    public static void main(String args[]){
        //Substring
        String str = "HelloWorld";
        System.out.println(str.substring(0,5));// This is the inbuilt function.
        System.out.println(substring(str,0,5));//(0 --> n-1) OP= Hello.
       }
}
