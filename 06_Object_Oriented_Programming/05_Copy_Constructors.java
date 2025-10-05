class Student {
    String name;
    int age;

    // Parameterized Constructor
    Student(String n, int a) {
        name = n;
        age = a;
    }

    // Copy Constructor
    Student(Student s) {
        name = s.name;
        age = s.age;
    }

    // Method to display details
    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        // Original student
        Student s1 = new Student("Rahul", 19);

        // Copy of s1
        Student s2 = new Student(s1);

        // Display both
        s1.display();
        s2.display();
    }
}
