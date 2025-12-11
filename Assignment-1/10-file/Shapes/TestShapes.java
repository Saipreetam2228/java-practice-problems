package Shapes; //package

// ===== Interface =====
interface Shape {
    double area();
    double perimeter();
}

// ===== Circle Class implementing Shape =====
class Circle implements Shape {
    private double radius; //Secure

    // Constructor
    public Circle(double radius) {
        this.radius = radius;
    }

    // Implement area
    public double area() {
        return Math.PI * radius * radius;
    }

    // Implement perimeter
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

// ===== Test Class (main method) =====
public class TestShapes {
    public static void main(String[] args) {
        Shape c1 = new Circle(5.0);

        System.out.println("Circle with radius 5.0");
        System.out.println("Area: " + c1.area());
        System.out.println("Perimeter: " + c1.perimeter());
    }
}
//output:) Circle with radius 5.0
//Area: 78.53981633974483
//Perimeter: 31.41592653589793
// File structure:-> projects/Shapes/TestShapes.java

/*
Execution process:
javac Shapes/TestShapes.java
java Shapes.TestShapes

*/
