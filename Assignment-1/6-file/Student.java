import java.util.Scanner;

public class Student {
    String name;
    int[] marks;

    // ===== Constructor =====
    Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    // ===== Average calculation =====
    int Average() {
        int sum = 0;
        int n = marks.length;
        for (int i = 0; i < n; i++) {
            sum += marks[i];
        }
        return sum / n;
    }

    // ===== Grade calculation =====
    void Grade(int avg) {
        if (avg < 0 || avg > 100) {
            System.out.println("Invalid marks! Please enter between 0–100.");
        } else if (avg >= 90) {
            System.out.println("Grade: O+ (Outstanding)");
        } else if (avg >= 75) {
            System.out.println("Grade: O (Distinction)");
        } else if (avg >= 65) {
            System.out.println("Grade: A+ (Very Good)");
        } else if (avg >= 60) {
            System.out.println("Grade: A (Good)");
        } else if (avg >= 50) {
            System.out.println("Grade: B (Fair, Pass)");
        } else {
            System.out.println("Grade: F (Fail)");
        }
    }

    // ===== Main method =====
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("!! Out of 100 !! ");
        System.out.print("Enter No. of Subjects: ");
        int n = sc.nextInt();

        int[] marks1 = new int[n]; // initialize marks array

        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks - " + (i + 1) + ": ");
            marks1[i] = sc.nextInt();
        }

        Student s1 = new Student("Ram", marks1);

        int avg = s1.Average();
        System.out.println("\nStudent: " + s1.name);
        System.out.println("Average: " + avg);
        s1.Grade(avg);
    }
}

