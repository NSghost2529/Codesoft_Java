import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int roundNumber = 1;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (true) {
            System.out.println("\nRound " + roundNumber);
            int number = random.nextInt(100) + 1;
            int attempts = 0;
            final int maxAttempts = 10;

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + " - Guess the number (between 1 and 100): ");
                int guess;

                try {
                    guess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                attempts++;

                if (guess < number) {
                    System.out.println("Too low! Try again.");
                } else if (guess > number) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number " + number + " correctly in " + attempts + " attempts.");
                    score += maxAttempts - attempts + 1;
                    break;
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + number + ".");
            }

            System.out.println("Your score: " + score);
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.nextLine().trim().toLowerCase();

            if (!playAgain.equals("yes")) {
                break;
            }

            roundNumber++;
        }

        System.out.println("Thank you for playing! Your final score is " + score + ".");
        scanner.close();
    }
}