public class Main {
    public static void main(String[] args) { // Corrected String args[] to String[] args
        Pen p1 = new Pen(); // Created a pen object

        p1.setColor("Blue"); // Setting color to Blue
        System.out.println(p1.getColor()); // Getting and printing color

        p1.setTip(5); // Setting tip size to 5
        System.out.println(p1.getTip()); // Getting and printing tip size

        p1.setColor("Yellow"); // Changing color to Yellow
        System.out.println(p1.getColor()); // Printing updated color
    }
}

class Pen {
    private String color; // Private variable for color
    private int tip;      // Private variable for tip size

    // Getter for color
    public String getColor() {
        return this.color;
    }

    // Setter for color
    public void setColor(String color) {
        this.color = color;
    }

    // Getter for tip
    public int getTip() {
        return this.tip;
    }

    // Setter for tip
    public void setTip(int tip) {
        this.tip = tip;
    }
}
