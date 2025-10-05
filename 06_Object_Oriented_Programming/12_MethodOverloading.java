// Method Overloading Example
// → Same method name 'sum' but different parameters
// → This is Compile-Time Polymorphism (decided during compilation)

public class Main {
    public static void main(String args[]) {

        // Create an object of Calculator class
        Calculator calc = new Calculator();

        // Calls sum(int, int)
        System.out.println(calc.sum(1, 2)); // Output: 3

        // Calls sum(float, float)
        System.out.println(calc.sum((float)1.5, (float)1.5)); // Output: 3.0

        // Calls sum(int, int, int)
        System.out.println(calc.sum(1, 2, 3)); // Output: 6
    }
}

// Class containing overloaded methods
class Calculator {

    // 1. Method with 2 int parameters
    int sum(int a, int b) {
        return a + b;
    }

    // 2. Method with 2 float parameters (different data type)
    float sum(float a, float b) {
        return a + b;
    }

    // 3. Method with 3 int parameters (different number of arguments)
    int sum(int a, int b, int c) {
        return a + b + c;
    }
}

// All three methods have the same name 'sum' but different parameter types or counts
// The compiler decides which method to call based on the arguments
// → This is called Compile-Time Polymorphism (Method Overloading)
