public class Main {
    public static void main(String args[]) { 
        Student s1 = new Student(); // Calls non-parameterized constructor
        Student s2 = new Student("Shabeeb"); // Calls constructor with String parameter
        System.out.println("Name of s2: " + s2.name); // Print name of s2

        Student s3 = new Student(50); // Calls constructor with int parameter
        System.out.println("Roll of s3: " + s3.roll); // Print roll of s3
    }
}

class Student {
    String name; // Variable to store student name
    int roll;    // Variable to store student roll number

    // Non-parameterized constructor
    Student() {
        System.out.println("Constructor is called..."); // Message to indicate constructor call
    }

    // Parameterized constructor to set name
    Student(String name) {
        this.name = name; // 'this' refers to current object's name
    }

    // Parameterized constructor to set roll
    Student(int roll) {
        this.roll = roll; // 'this' refers to current object's roll
    }
}
