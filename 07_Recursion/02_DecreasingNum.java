public class RecursionBasics {

    public static void PrintDec(int n) {
        // Base case: when n becomes 1, stop recursion
        if (n == 1) {
            System.out.print(n); // print last number
            return; // function ends here (starts stack down)
        }

        System.out.println(n + " ");

        // STACK UP
        // Each time this line is executed, a new function call is made.
        // The current function pauses and waits for the next one to finish.
        // Example: PrintDec(3) calls PrintDec(2) → stack builds up.
        PrintDec(n - 1);

        // STACK DOWN 
        // After the base case is reached and returns,
        // all previous calls start finishing one by one.
        // Example: PrintDec(1) finishes → returns to PrintDec(2) → then to PrintDec(3), etc.
    }

    public static void main(String args[]) {
        int n = 10;
        // Function call starts recursion
        // Stack up begins from here
        PrintDec(n);
        // After all recursive calls finish, stack down is complete
    }
}
