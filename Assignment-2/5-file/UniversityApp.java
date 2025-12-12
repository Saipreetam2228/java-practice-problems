import java.util.Scanner;

class Person {
    String name;
    int age;

    Person(String n, int a) {
        this.name = n;
        this.age = a;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
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
    int rollNumber;
    int[] marks;

    Student(String n, int a, int r, int[] m) throws InvalidMarksException {
        super(n, a);
        this.rollNumber = r;
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
        return sum / (double) marks.length;
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
        System.out.println("Student: " + name + " (" + rollNumber + ")");
        System.out.println("Average: " + calculateAverage());
        System.out.println("Grade: " + getGrade());
        System.out.println("Status: " + (hasPassed() ? "Passed" : "Failed"));
        System.out.println("--------------------------------");
    }
}

public class UniversityApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] students = new Student[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter details for Student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = sc.nextInt();
            System.out.print("Roll No: ");
            int rollNo = sc.nextInt();

            int[] marks = new int[5];
            System.out.print("Enter 5 marks: ");
            for (int j = 0; j < 5; j++) {
                while (true) {
                    try {
                        int mark = sc.nextInt();
                        if (mark < 0 || mark > 100) {
                            throw new InvalidMarksException("Marks must be between 0 and 100.");
                        }
                        marks[j] = mark;
                        break;
                    } catch (InvalidMarksException e) {
                        System.out.println("Invalid input: " + e.getMessage() + " Please re-enter:");
                    }
                }
            }
            sc.nextLine(); 

            try {
                students[i] = new Student(name, age, rollNo, marks);
            } catch (InvalidMarksException e) {
                System.out.println("Error creating student: " + e.getMessage());
                i--;
            }
            System.out.println();
        }

        System.out.println("===== Student Results =====");
        for (Student s : students) {
            s.displayStudentInfo();
        }

        sc.close();
    }
}

