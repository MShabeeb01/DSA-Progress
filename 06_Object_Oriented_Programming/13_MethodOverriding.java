// Parent class
class Animals {
    void eat() {  // Method in parent class
        System.out.println("Eats Anything");
    }
}

// Child class overrides the eat() method
class Deer extends Animals {
    @Override   // Optional but recommended
    void eat() {
        System.out.println("Eats Grass");
    }
}

// Main class
public class Main {
    public static void main(String args[]) {

        Deer d = new Deer();  // Create object of child class
        d.eat();              // Calls overridden method in Deer
    }
}

/* 
Explanation:
// Animals → Parent class with method eat()
// Deer → Child class overriding eat() method
// d.eat() calls the method of the child class (Deer) → Runtime Polymorphism
*/
