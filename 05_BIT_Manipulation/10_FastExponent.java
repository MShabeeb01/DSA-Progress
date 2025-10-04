import java.util.*;

public class Operations {

    // Function to calculate a^n using fast exponentiation. 
    public static int FastExpo(int a, int n) {
        int ans = 1; // Initialize answer to 1
        while (n > 0) {
            if ((n & 1) != 0) { // If the current bit of n is 1
                ans = ans * a; // Multiply ans by a
            }
            a = a * a; // Square a for the next bit
            n = n >> 1; // Shift n to right by 1 (move to next bit)
        }
        return ans; // Return the final answer
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create Scanner object

        System.out.print("Enter the base (a): ");
        int a = sc.nextInt(); // Input base

        System.out.print("Enter the exponent (n): ");
        int n = sc.nextInt(); // Input exponent. 

        int result = FastExpo(a, n); // Call FastExpo function
        System.out.println(a + "^" + n + " = " + result); // Print the result

        sc.close(); // Close the scanner
    }
}
