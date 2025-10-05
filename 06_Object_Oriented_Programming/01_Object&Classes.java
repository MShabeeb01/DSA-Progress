// Define a class named 'Car'
class Car {
    // Data members (also called attributes or fields)
    String brand;
    String color;
    int year;

    // Method (function) to display information about the car
    void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Color: " + color);
        System.out.println("Year: " + year);
    }
}

// Main class to execute the program
public class Main {
    public static void main(String[] args) {

        // Creating an object of class 'Car'
        Car car1 = new Car(); // car1 is an object (instance of Car class)

        // Assigning values to object attributes
        car1.brand = "Tesla";
        car1.color = "Red";
        car1.year = 2023;

        // Calling the displayInfo() method using car1 object
        car1.displayInfo();

        System.out.println();

        // Creating another object of class 'Car'
        Car car2 = new Car();

        // Assigning values to car2 attributes
        car2.brand = "BMW";
        car2.color = "Blue";
        car2.year = 2020;

        // Displaying information of car2
        car2.displayInfo();
    }
}

