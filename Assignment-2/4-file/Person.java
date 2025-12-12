public class Person {
  String name;
  int age;
  
  Person(String n, int a) {
    this.name = n; 
    this.age = a;
  }
  
  public void DisplayInfo() {
    System.out.println("Name is: " + name + " Age is: " + age);
  }
  
  public static void main(String[] args) { 
    Person p = new Person("Sai", 18);
    p.DisplayInfo();
  }
}

interface Evaluatable {
  boolean hasPassed();
}

class InvalidMarksException extends Exception {
  public InvalidMarksException(String msg) {
    super(msg);
  }
}

class Student extends Person implements Evaluatable {
  int Rno;
  int[] marks;
  
  Student(String n, int a, int r, int[] m) throws InvalidMarksException {
    super(n, a);
    this.Rno = r;
    for (int mark : m) {
      if (mark < 0 || mark > 100) {
        throw new InvalidMarksException("Marks must be between 0 and 100. Found: " + mark);
      }
    }
    this.marks = m;
  }
  
  public double calculateAverage() {
    int sum = 0;
    for (int mark : marks) {
      sum += mark;
    }
    return sum / (double)marks.length;
  }
  
  public String getGrade() {
    double avg = calculateAverage();
    if (avg >= 75) return "A";
    else if (avg >= 60) return "B";
    else if (avg >= 40) return "C";
    else return "F";
  }

  @Override
  public boolean hasPassed() {
    return !getGrade().equals("F");
  }
  
  public void displayStudentInfo() {
    DisplayInfo(); 
    System.out.println("Roll No: " + Rno);
    System.out.println("Average: " + calculateAverage());
    System.out.println("Grade: " + getGrade());
    System.out.println("Status: " + (hasPassed() ? "Passed" : "Failed"));
  }
  
  public static void main(String[] args) {
    try {
      int[] marks1 = {80, 76, 90, 85, 70};
      Student s1 = new Student("Ravi", 20, 101, marks1);
      s1.displayStudentInfo();
      
      System.out.println("----------------------------");
      
      int[] marks2 = {110, 60, 70, 80, 90};
      Student s2 = new Student("Meena", 19, 102, marks2);
      s2.displayStudentInfo();
    } catch (InvalidMarksException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}

