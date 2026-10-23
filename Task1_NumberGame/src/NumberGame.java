
import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        Random random = new Random();

        try (Scanner scanner = new Scanner(System.in)) {
            int totalRounds = 0;
            int roundsWon = 0;
            boolean playAgain = true;

            System.out.println("================================");
            System.out.println("       CODSOFT NUMBER GAME");
            System.out.println("================================");

            while (playAgain) {
                int secretNumber =
                        random.nextInt(MAX_NUMBER - MIN_NUMBER + 1)
                        + MIN_NUMBER;

                int attempts = 0;
                boolean guessedCorrectly = false;
                totalRounds++;

                System.out.println("\nRound " + totalRounds);
                System.out.println("Guess a number from "
                        + MIN_NUMBER + " to " + MAX_NUMBER);
                System.out.println("You have " + MAX_ATTEMPTS
                        + " attempts.");

                while (attempts < MAX_ATTEMPTS) {
                    System.out.print("Enter your guess: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println(
                                "Invalid input. Enter a whole number.");
                        scanner.next();
                        continue;
                    }

                    int guess = scanner.nextInt();

                    if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                        System.out.println(
                                "Enter a number between 1 and 100.");
                        continue;
                    }

                    attempts++;

                    if (guess < secretNumber) {
                        System.out.println("Too low!");
                    } else if (guess > secretNumber) {
                        System.out.println("Too high!");
                    } else {
                        guessedCorrectly = true;
                        roundsWon++;
                        System.out.println(
                                "Correct! You guessed the number.");
                        System.out.println("Attempts used: " + attempts);
                        break;
                    }

                    System.out.println("Attempts remaining: "
                            + (MAX_ATTEMPTS - attempts));
                }

                if (!guessedCorrectly) {
                    System.out.println("Round over! The number was "
                            + secretNumber);
                }

                System.out.println("Rounds won: " + roundsWon
                        + "/" + totalRounds);

                System.out.print("Play another round? (yes/no): ");
                playAgain = scanner.next().equalsIgnoreCase("yes");
            }

            System.out.println("\n========== FINAL SCORE ==========");
            System.out.println("Rounds played: " + totalRounds);
            System.out.println("Rounds won: " + roundsWon);
            System.out.println("Thanks for playing!");
        }
    }
}
