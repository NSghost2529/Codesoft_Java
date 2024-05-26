import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the marks obtained in each subject (out of 100):");

        int totalMarks = 0;
        int totalSubjects = 0;

        while (true) {
            System.out.print("Subject " + (totalSubjects + 1) + ": ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("q")) {
                break;
            }

            int marks;
            try {
                marks = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks. Please enter marks between 0 and 100.");
                continue;
            }

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Please enter marks between 0 and 100.");
                continue;
            }

            totalMarks += marks;
            totalSubjects++;
        }

        double averagePercentage = calculateAveragePercentage(totalMarks, totalSubjects);
        String grade = calculateGrade(averagePercentage);

        System.out.println("Total marks: " + totalMarks);
        System.out.println("Average percentage: " + String.format("%.2f", averagePercentage * 100) + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    public static double calculateAveragePercentage(int totalMarks, int totalSubjects) {
        return (double) totalMarks / totalSubjects;
    }

    public static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
