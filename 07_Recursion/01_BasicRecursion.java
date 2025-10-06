public class RecursionExample {

    // Recursive method to calculate factorial
    static int factorial(int n) {
        // Base case: when n becomes 1, stop recursion
        // because 1! = 1
        if (n == 1) {
            return 1;
        }

        // Recursive case:
        // factorial(n) = n * factorial(n - 1)
        // The method calls itself with a smaller value of n
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int num = 5;

        // Call the factorial method
        int result = factorial(num);

        // Print the result
        System.out.println("Factorial of " + num + " is: " + result);
    }
}
