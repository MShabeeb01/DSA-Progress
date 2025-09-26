import java.util.*;

public class Matrices {

    // Function to search for a key in the matrix
    public static boolean search(int matrix[][], int key) {
        for (int i = 0; i < matrix.length; i++) {          // loop through rows
            for (int j = 0; j < matrix[0].length; j++) {   // loop through columns
                if (matrix[i][j] == key) {                 // check if element matches key
                    System.out.println("Found at Cell (" + i + ", " + j + ")");
                    return true;                           // key found, return true
                }
            }
        }
        System.out.println("Key not found");               // if loop completes, key not found
        return false;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int matrix[][] = new int[3][3];  // fixed 3x3 Matrix
        int n = matrix.length, m = matrix[0].length; // n = rows, m = cols

        // Taking input for the matrix
        System.out.println("Enter elements of the 3x3 matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt(); // user enters numbers
            }
        }

        // Printing the matrix
        System.out.println("Matrix is:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " "); // print each element
            }
            System.out.println(); // new line after each row
        }

        // Asking user which number they want to search
        System.out.print("Enter your number to search: ");
        int key = sc.nextInt();

        // Searching for the entered number
        search(matrix, key);
    }
}
