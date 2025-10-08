public class StackOverflowExample {

    // Recursive function without a base case
    public static void printNumber(int n) {
        System.out.println(n);

        // This function keeps calling itself forever
        // No base case to stop recursion
        printNumber(n + 1); // new call made each time → stack keeps growing
    }

    public static void main(String[] args) {
        printNumber(1); // Start recursion
    }
}
// Step 1: printNumber(1) is called → stack up
// Step 2: printNumber(2) is called → stack up
// Step 3: printNumber(3) is called → stack up
// ...
// This continues infinitely (no base case), so the stack never clears.
// Eventually, Java throws:
