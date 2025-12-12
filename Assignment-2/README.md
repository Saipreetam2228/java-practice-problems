  ## Problem: University Student Management System (Mini Version)

### **Description**
Build a simple University Student Management System that keeps track of students, their courses, and grades.

---

## **Requirements**

### **1. Class Person**
- Fields: `name`, `age`
- Constructor to initialize fields  
- Method `displayInfo()` to print details

---

### **2. Class Student (extends Person)**
- Additional fields:
  - `rollNumber`
  - `marks[]` (5 subjects)
- Constructor (use `super` for Person fields)
- Method `calculateAverage()` → returns average marks
- Method `getGrade()`:
  - **A** → average ≥ 75  
  - **B** → average ≥ 60  
  - **C** → average ≥ 40  
  - **F** → average < 40  

---

### **3. Interface Evaluatable**
- Method: `boolean hasPassed()`
- Implement this in `Student`  
  - A student **passes** if grade ≠ **F**

---

### **4. Exception Handling**
- Create a custom exception: `InvalidMarksException`
- Throw it if any mark is:
  - `< 0`
  - `> 100`

---

### **5. Main Application – `UniversityApp`**
- Create an array of **3 Student objects**
- Take user input for:
  - Name  
  - Age  
  - Roll Number  
  - 5 Marks  
- Use try–catch to handle invalid marks  
- Display:
  - Student details  
  - Average  
  - Grade  
  - Pass/Fail status  

---

## **Sample Input & Output**

### **Input**
