# Java OOP Practice Problems

A clean and organized list of 10 Java beginner-to-intermediate level practice programs covering
**Classes, Objects, Methods, Inheritance, Interfaces, Exception Handling, Arrays**, and **Packages**.

---

## 1. Simple Calculator

- Create a class `Calculator` with methods:
  - `add()`, `subtract()`, `multiply()`, `divide()`
- Take user input using `Scanner`
- Validate division by zero

---

## 2. Temperature Converter

- Class `TemperatureConverter`:
  - Methods:
    - `celsiusToFahrenheit()`
    - `fahrenheitToCelsius()`
- Use input through Scanner
- Validate input ranges with if-else

---

## 3. Employee Salary Slip

- Class `Employee`:
  - Fields: `name`, `id`, `basicSalary`
- Methods:
  - Calculate:
    - HRA = 20%
    - DA = 10%
    - Gross Salary = basic + HRA + DA
- Print formatted salary slip

---

## 4. Number Pattern Printer

- Class `PatternPrinter`:
  - Method to print a right-angled number triangle using nested loops
- Practice loops and method calling

---

## 5. Bank Account Simulation

- Class `BankAccount` with:
  - Fields: `accountNumber`, `balance`
  - Methods:
    - `deposit()`
    - `withdraw()`
    - `displayBalance()`
- Add insufficient balance validation with if-else

---

## 6. Student Grade Calculator

- Class `Student`:
  - Fields: `name`, `marks[]`
  - Method to calculate:
    - Average
    - Grade (A, B, C, F)
- Use arrays & constructors

---

## 7. Rectangle Area & Perimeter

- Class `Rectangle`:
  - Private fields: `length`, `width`
  - Constructor to initialize values
  - Methods:
    - `getArea()`
    - `getPerimeter()`
- Demonstrates **encapsulation**

---

## 8. Inheritance Demo – Vehicle

- Base class `Vehicle`:
  - Fields: `speed`, `capacity`
- Subclass `Car`:
  - Additional field: `fuelType`
- Method to display details of both classes

---

## 9. Exception Handling – Division

- Class `DivisionCalculator`:
  - Method: `divide(int a, int b)`
  - Throw & catch `ArithmeticException` for division by zero
- Use try-catch-finally

---

## 10. Package & Interface – Shapes

- Create package: `shapes`
- Inside package:
  - `Shape` interface → `area()`, `perimeter()`
  - Class `Circle` implements `Shape`
- In another file:
  - Import the package
  - Create Circle object and test methods

---

