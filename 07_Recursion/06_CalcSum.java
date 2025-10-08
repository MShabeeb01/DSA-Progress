public class RecursionBasics {

    // Recursive function to calculate sum of first n natural numbers
    public static int CalSum(int n) {
        // Base case: when n is 1, return 1
        // because sum of first 1 natural number = 1
        if (n == 1) {
            return 1;
        }

        // Recursive call: function calls itself with (n-1)
        // This will keep calling until it reaches the base case
        int snm1 = CalSum(n - 1);  // sum till (n-1)

        // Current sum = n + sum of (n-1) numbers
        int sn = n + snm1;

        // Return the calculated sum
        return sn;    
    }

    // main method (program starts from here)
    public static void main(String args[]) {
        int n = 5;  // we want sum of first 5 numbers
        System.out.println(CalSum(n));  // print the result
    }
}
