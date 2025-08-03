import java.util.Scanner;

public class StudentGrade {
    public static void main(String[] args) {
        // Create a Scanner object to take user input
        Scanner scanner = new Scanner(System.in);

        // Declare variables for marks
        double mark1, mark2, mark3, mark4, average;

        // Prompt the user for marks of four subjects
        System.out.print("Enter the mark for Subject 1: ");
        mark1 = scanner.nextDouble();

        System.out.print("Enter the mark for Subject 2: ");
        mark2 = scanner.nextDouble();

        System.out.print("Enter the mark for Subject 3: ");
        mark3 = scanner.nextDouble();

        System.out.print("Enter the mark for Subject 4: ");
        mark4 = scanner.nextDouble();

        // Calculate the average of the marks
        average = (mark1 + mark2 + mark3 + mark4) / 4;

        // Display the average mark
        System.out.println("\nAverage Mark: " + average);

        // Determine and display the grade based on average mark
        char grade;
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Output the grade
        System.out.println("Grade: " + grade);
        
        // Close the scanner
        scanner.close();
    }
}
