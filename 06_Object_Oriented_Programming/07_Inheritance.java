// Inheritance in Java is a mechanism where one class (child/subclass) 
// can acquire the properties and methods of another class (parent/superclass).
// It helps in code reusability and establishing a relationship between classes.

// Base Class  
class Animal {
    String color;

    void eat() {
        System.out.println("Eating");
    }

    void breathe() {
        System.out.println("Breathes");
    }
}

// Derived Class  
class Fish extends Animal {
    int fins;

    void swim() {
        System.out.println("Swims in Water");
    }
}

// Main Class  
public class Main {
    public static void main(String args[]) {
        Fish shark = new Fish();
        shark.eat();
        shark.breathe();
        shark.swim();
    }
}



