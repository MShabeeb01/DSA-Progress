public class RecursionBasics {

    // Function to print numbers in increasing order using recursion
    public static void PrintInc(int n) {

        // BASE CASE
        if (n == 1) {
            System.out.print(n + " "); // prints 1 (first number)
            // This is where recursion STOPS going deeper (end of stack up)
            // "1" is printed first because it's printed at the base case
            return; // start of stack down
        }

        // STACK UP
        // The function keeps calling itself with smaller 'n'
        // Each call is put on top of the call stack and waits for the next one to finish
        // Example: PrintInc(5) → PrintInc(4) → PrintInc(3) → PrintInc(2) → PrintInc(1)
        PrintInc(n - 1); 

        // STACK DOWN
        // After reaching the base case and returning,
        // the waiting functions start finishing one by one (Last In, First Out)
        // Now each call prints its own number as the stack unwinds.
        System.out.print(n + " ");
    }

    public static void main(String args[]) {
        int n = 10;
        // Starts the recursion process
        PrintInc(n); // prints: 1 2 3 4 5 6 7 8 9 10
    }
}
