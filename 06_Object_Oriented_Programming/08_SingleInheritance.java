// If there is one parent class and one derived class that is called single inheritance.
// Parent class
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

// Child class inherits from Animal (single-level inheritance)
class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();   // Create object of child class
        d.eat();             // Call inherited method from Animal
        d.bark();            // Call method of Dog
    }
}
