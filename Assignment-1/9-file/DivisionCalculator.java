public class DivisionCalculator {

    // Method to perform division
    public void divide(int a, int b) {
      System.out.println("Operands: A= "+a+" ,B="+b);
        try {
            int r = a / b; //division 
            System.out.println("Valid division");
            System.out.println("Result: " + r);
        } catch (ArithmeticException e) {  //if it is ERROR
            System.out.println("Error: Cannot divide by zero!");
        } finally {  //default
            System.out.println("Division operation completed (finally block executed).");
        }
    }

    // Testing with the operands 
    public static void main(String[] args) {
        DivisionCalculator calc = new DivisionCalculator(); //constructor

        // Valid division
        
        calc.divide(10, 2);
        System.out.println();

        // Invalid division (b = 0)
        System.out.println("Case 2: Division by zero");
        calc.divide(10, 0);
    }
}

