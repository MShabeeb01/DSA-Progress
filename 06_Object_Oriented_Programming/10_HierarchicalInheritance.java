// When multiple derieved class is derieved from single parent class.
// Parent class (Base class)
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

// Child class 1 inherits from Animal
class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

// Child class 2 inherits from Animal
class Cat extends Animal {
    void meow() {
        System.out.println("Cat is meowing");
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();   // Object of Dog
        d.eat();             // Inherited from Animal
        d.bark();            // Dog's own method

        Cat c = new Cat();   // Object of Cat
        c.eat();             // Inherited from Animal
        c.meow();            // Cat's own method
    }
}
