import java.util.Scanner;

public class TemperatureConverter {
    // Celsius to Fahrenheit conversion
    float CelsiusToFahrenheit(float c) {
        return (c * (9f / 5f)) + 32; //f refers to the actual float numbers
    } // so that the result will be in the perfect float 

    // Fahrenheit to Celsius conversion
    float FahrenheitToCelsius(float f) {
        return (f - 32) * (5f / 9f);
    }
    
//===============================================

//main function
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in); //we have named scanner as "sc"
      TemperatureConverter temp = new TemperatureConverter();  // temp = temperatureconverter by allocating the location
        
        // Celsius to Fahrenheit
        System.out.print("Celsius Temp: ");
        float c1 = sc.nextFloat(); //input method
        float f1 = temp.CelsiusToFahrenheit(c1);
        System.out.println("In Fahrenheit: " + f1);
        
        // Fahrenheit to Celsius
        System.out.print("Fahrenheit Temp: ");
        float f2 = sc.nextFloat();
        float c2 = temp.FahrenheitToCelsius(f2);
        System.out.println("In Celsius: " + c2);
        
        sc.close();
    }
}

/*
 Celsius to Fahrenheit:  F = (C × 9/5) + 32
 Fahrenheit to Celsius:  C = (F − 32) × 5/9
*/


/*output
celsius Temp: 53  (input)scanner 
In Fahrenheit: 127.399994  (result)
Fahrenheit Temp: 20  (input)scanner 
In Celsius: -6.666667  (result)
