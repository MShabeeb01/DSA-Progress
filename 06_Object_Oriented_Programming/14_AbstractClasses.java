// Abstract class (cannot create object directly)
abstract class Animal {
    // Abstract method → must be overridden by child class
    abstract void sound();

    // Concrete method → can be used by child class
    void eat() {
        System.out.println("Animal eats food");
    }
}

// Child class overriding abstract method
class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

// Another child class
class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {

        // Abstract reference can hold object of child class
        Animal d = new Dog();
        d.sound();  // Calls overridden method in Dog
        d.eat();    // Calls concrete method from Animal

        Animal c = new Cat();
        c.sound();  // Calls overridden method in Cat
        c.eat();    // Calls concrete method from Animal
    }
}

/* 
Explanation:
// Animal → Abstract class, cannot be instantiated
// sound() → abstract method, overridden in Dog and Cat
// eat() → concrete method, inherited by Dog and Cat
// Demonstrates Abstraction + Runtime Polymorphism
*/
