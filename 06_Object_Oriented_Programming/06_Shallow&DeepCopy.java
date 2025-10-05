// Inner class (Address)
class Address {
    String city;
    String state;

    Address(String city, String state) {
        this.city = city;
        this.state = state;
    }
}

// Outer class (Student)
class Student {
    String name;
    Address address;

    // Constructor
    Student(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow Copy Constructor
    Student(Student s) {
        this.name = s.name;
        this.address = s.address; // ❌ same reference (shallow copy)
    }

    // Deep Copy Constructor
    Student(Student s, boolean deepCopy) {
        this.name = s.name;
        // ✅ create new Address object (deep copy)
        this.address = new Address(s.address.city, s.address.state);
    }
}

public class Main {
    public static void main(String[] args) {
        Address addr = new Address("Delhi", "Delhi");
        Student s1 = new Student("Rahul", addr);

        // Shallow Copy
        Student shallowCopy = new Student(s1);

        // Deep Copy
        Student deepCopy = new Student(s1, true);

        // Change address city in original object
        s1.address.city = "Mumbai";

        System.out.println("Original Student: " + s1.address.city);
        System.out.println("Shallow Copy: " + shallowCopy.address.city);
        System.out.println("Deep Copy: " + deepCopy.address.city);
    }
}
