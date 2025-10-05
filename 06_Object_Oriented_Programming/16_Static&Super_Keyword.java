class Animal {
    String type = "Animal"; // Parent class variable

    void eat() {            // Parent class method
        System.out.println("Animal eats food");
    }
}

class Dog extends Animal {
    String type = "Dog";    // Child class variable

    void eat() {            // Child class method
        System.out.println("Dog eats bones");
    }

    void show() {
        System.out.println(type);       // Access child variable → Dog
        System.out.println(super.type); // Access parent variable → Animal
        eat();                          // Calls child method → Dog eats bones
        super.eat();                    // Calls parent method → Animal eats food
    }

    // Static method → belongs to class, not object
    static void info() {
        System.out.println("All dogs are animals");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();

        d.show();      // Shows use of super keyword
        Dog.info();    // Calls static method without creating another object
    }
}

/*
Output:
Dog
Animal
Dog eats bones
Animal eats food
All dogs are animals

Notes:
// super → access parent class variables and methods
// static → belongs to class, accessed using ClassName.methodName()
*/
