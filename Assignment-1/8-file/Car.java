
class Vehicle {  //Base class
    protected int speed;  
    protected int capacity;
// we used access specifiers for these fields

    Vehicle(int speed, int capacity) {
        this.speed = speed;
        this.capacity = capacity;
    }

    public void displayDetails() {
        System.out.println("Speed: " + speed + " km/h");
        System.out.println("Capacity: " + capacity + " persons");
    }
}
//==================Inheritance===================
class Car extends Vehicle {
    private String fuelType;

    Car(int speed, int capacity, String fuelType) {
        super(speed, capacity); //extract from the parent class
        this.fuelType = fuelType; //initializing the variable to the actual variable(pointer)
    }

    @Override  // re writing the method
    public void displayDetails() {
        super.displayDetails(); 
        System.out.println("Fuel Type: " + fuelType);
    }


  public static void main(String[] args) {
      Vehicle v1 = new Vehicle(80, 4);
      System.out.println("=== Vehicle Details ===");
      v1.displayDetails();

      Car c1 = new Car(120, 5, "Petrol");
      System.out.println("\n=== Car Details ===");
      c1.displayDetails();
  }
}
