// Parent class (Level 1)
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

// Child class (Level 2) inherits from Animal
class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

// Grandchild class (Level 3) inherits from Dog
class Puppy extends Dog {
    void sleeping() {
        System.out.println("Puppy is sleeping");
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        Puppy p = new Puppy();  // Object of the last derived class
        p.eat();     // from Animal
        p.bark();    // from Dog
        p.sleeping();  // from Puppy
    }
}
