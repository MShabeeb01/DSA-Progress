// Base class
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

// Child class inheriting from Animal
class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

// Interface (acts like another parent)
interface Pet {
    void play();  // abstract method (must be implemented)
}

// Grandchild class inherits Dog (class) and implements Pet (interface)
class Puppy extends Dog implements Pet {
    public void play() {
        System.out.println("Puppy is playing");
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        Puppy p = new Puppy();
        p.eat();   // from Animal
        p.bark();  // from Dog
        p.play();  // from Pet interface
    }
}
