
import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("==================================");
            System.out.println("     STUDENT GRADE CALCULATOR");
            System.out.println("==================================");

            int numberOfSubjects = readPositiveInteger(
                    scanner, "Enter number of subjects: ");

            int totalMarks = 0;

            for (int i = 1; i <= numberOfSubjects; i++) {
                int marks = readMarks(
                        scanner, "Enter marks for subject " + i + ": ");
                totalMarks += marks;
            }

            double average = (double) totalMarks / numberOfSubjects;
            String grade = calculateGrade(average);

            System.out.println("\n========== RESULTS ==========");
            System.out.println("Total Marks: " + totalMarks
                    + "/" + (numberOfSubjects * 100));
            System.out.printf("Average Percentage: %.2f%%%n", average);
            System.out.println("Grade: " + grade);
            System.out.println("=============================");
        }
    }

    private static int readPositiveInteger(
            Scanner scanner, String message) {

        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();

                if (value > 0) {
                    return value;
                }
            } else {
                scanner.next();
            }

            System.out.println("Please enter a positive whole number.");
        }
    }

    private static int readMarks(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                int marks = scanner.nextInt();

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
            } else {
                scanner.next();
            }

            System.out.println("Invalid marks! Enter a value from 0 to 100.");
        }
    }

    private static String calculateGrade(double average) {
        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
