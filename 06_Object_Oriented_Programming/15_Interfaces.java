// Interface representing a Car
interface Car {
    // Abstract method → must be implemented by class
    void start();

    void stop();

    // Default method → optional for implementing class
    default void fuel() {
        System.out.println("Refuel the car");
    }
}

// Class implementing the Car interface
class Tesla implements Car {
    @Override
    public void start() {        // Implement start method
        System.out.println("Tesla starts silently");
    }

    @Override
    public void stop() {         // Implement stop method
        System.out.println("Tesla stops smoothly");
    }

    // Optionally, we can override the default method
    @Override
    public void fuel() {
        System.out.println("Tesla charges battery");
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        Car myCar = new Tesla();   // Interface reference holding Tesla object

        myCar.start();   // Calls Tesla's start method
        myCar.stop();    // Calls Tesla's stop method
        myCar.fuel();    // Calls overridden fuel method in Tesla
    }
}

/*
Explanation:
// Car → Interface, defines methods that all cars must have
// Tesla → Class implementing Car, provides actual behavior for methods
// myCar → Object reference of interface type pointing to Tesla object
// Demonstrates Interface usage + Runtime Polymorphism
*/
