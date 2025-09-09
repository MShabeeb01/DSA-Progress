import java.util.*;

public class ArraysCC {

// Creating a update function and creating an unchangable variable.

    public static void update(int marks[], int nonChangable) {
        nonChangable = 10;
        for (int i=0; i<marks.length; i++) {
            marks[i] = marks[i] + 1;
        }
    }    

// Creating the main function.

    public static void main(String args[]) {
        int marks[] = {97,91,80};
        int nonChangable = 5;
        update(marks, nonChangable);
        System.out.println(nonChangable);

//Print out the marks

for(int i=0; i<marks.length; i++){
    System.out.print(marks[i] + " ");
}
System.out.println();
    }
}

// Using this code we can see that when we update a certain array it changes its value in the main function as arrays are (By reference) changes are also made to the main function.

// As we can see that the unChangable value will still be 5 and not updated to 10.
