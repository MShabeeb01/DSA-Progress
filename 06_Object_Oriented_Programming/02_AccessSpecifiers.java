class Person {

    // public → can be used from anywhere
    public String name;

    // private → can be used only inside this class
    private int age;

    // protected → can be used in same package and in subclasses
    protected String city;

    // default (no modifier) → can be used only in the same package
    String country;

    // public method → can be used anywhere
    // used to set private 'age' value
    public void setAge(int a) {
        age = a;
    }

    // public method → used to get private 'age' value
    public int getAge() {
        return age;
    }

    // public method → shows all details
    public void showDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        System.out.println("Country: " + country);
    }
}

// What is a SUBCLASS?
// A subclass is a class that extends another class and can use its features.
// Example: Student is a subclass of Person because every student is a person.
class Student extends Person {
    // Subclass can use protected variables from parent class
    public void showCity() {
        System.out.println("Student city (from protected): " + city);
    }
}

// What is a PACKAGE?
// A package is like a folder that stores related Java classes together.
// Example: 
// package school;  <-- (you can write this on top to group all school-related classes)
// For now, all classes are in the "default package" (same file, same folder)

// Main class → this is where the program starts
public class Main {
    public static void main(String[] args) {

        // What is an OBJECT?
        // An object is a real-world example created from a class.
        // Example: If class = Person, then object = Alice, Bob, etc.

        Person p1 = new Person(); // creating object p1 of class Person

        // public variable → can access directly
        p1.name = "Alice";

        // private variable → cannot be accessed directly
        // p1.age = 20; ❌  (Not allowed)

        // use setter and getter to access private variable
        p1.setAge(20);
        System.out.println("Age using getter: " + p1.getAge());

        // protected and default → can be accessed in same package (same file here)
        p1.city = "Delhi";
        p1.country = "India";

        // Display all info
        p1.showDetails();

        System.out.println("----------------------");

        // Creating an object of subclass Student
        Student s1 = new Student();
        s1.name = "Bob";
        s1.setAge(18);
        s1.city = "Mumbai"; // protected → accessible here because Student is subclass
        s1.country = "India";

        // Calling methods from Person (parent) class
        s1.showDetails();
        s1.showCity(); // method inside Student (subclass)
    }
}
