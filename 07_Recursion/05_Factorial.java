public class RecursionBasics {

    public static int fact(int n) {
        if (n == 1) {
            return 1; // base case
        }

        int fnm1 = fact(n - 1);   // factorial of (n-1)
        int fn = n * fnm1;        // multiply n * (n-1)!
        return fn;                // return factorial
    }

    public static void main(String args[]) {
        int n = 5;
        System.out.println(fact(n)); // prints 120
    }
}
