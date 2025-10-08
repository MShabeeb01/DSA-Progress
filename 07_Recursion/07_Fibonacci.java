public class RecursionBasics {

    // Method to calculate the nth Fibonacci number using recursion
    public static int Fibonacci(int n) {
        // Base case: if n is 0 or 1, return n itself
        // F(0) = 0, F(1) = 1
        if (n == 0 || n == 1) {
            return n;
        }

        // Recursive call: find (n-1)th Fibonacci number
        int fnm1 = Fibonacci(n - 1);

        // Recursive call: find (n-2)th Fibonacci number
        int fnm2 = Fibonacci(n - 2);

        // Add the previous two Fibonacci numbers to get the nth Fibonacci
        int fn = fnm1 + fnm2;

        // Return the nth Fibonacci number
        return fn;
    }

    public static void main(String args[]) {
        int n = 8; // We want to find the 8th Fibonacci number

        // Call the Fibonacci function and print the result
        System.out.println(Fibonacci(n)); // Output: 21
    }
}
