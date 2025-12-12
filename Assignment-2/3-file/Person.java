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

// Interface
interface Evaluatable {
  boolean hasPassed();
}

class Student extends Person implements Evaluatable {
  int Rno;
  int[] marks;
  
  Student(String n, int a, int r, int[] m) {
    super(n, a);
    this.Rno = r;
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
    int[] marks1 = {80, 76, 90, 85, 70};
    Student s1 = new Student("Ravi", 20, 101, marks1);
    s1.displayStudentInfo();
  }
}

