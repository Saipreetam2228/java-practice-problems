import java.util.Scanner;

public class Rectangle {
//  Demonstrating by the encapsulation to make fields as private.
    private double length; //'private' means only this class can access 
    private double width;  //"Double is meant for the floating point numbers "

    Rectangle(double l, double w) {  //constructor to set values ...
        this.length = l;  //pointers pointing to the actual variables
        this.width = w;
    }

    public double getArea() {    //method/function to get the area from these parameters
        return length * width;  // Area=Length*Breadth
    }

    public double getPerimeter() {  //perimeter function P=2(L+B)
        return 2 * (length + width);
    }

    void display(double length, double width){
      System.out.println("Length= "+length+"Width= "+width);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  //system input from the user

        System.out.print("Enter length of rectangle: ");
        double l = sc.nextDouble(); //length stored in var 'l'

        System.out.print("Enter width of rectangle: ");
        double w = sc.nextDouble();  //length stored in var 'w'

        Rectangle rect = new Rectangle(l, w);

        System.out.println("Rectangle Details:");
        rect.display(l,w);
        System.out.println("Area: " + rect.getArea());
        System.out.println("Perimeter: " + rect.getPerimeter());
    }
}

