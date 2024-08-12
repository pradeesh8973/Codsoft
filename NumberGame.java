import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            // Select difficulty level
            System.out.println("Choose a difficulty level:");
            System.out.println("1. Easy (1-50, 10 chances)");
            System.out.println("2. Medium (1-100, 7 chances)");
            System.out.println("3. Hard (1-200, 5 chances)");
            int difficulty = sc.nextInt();

            int min = 1;
            int max = 100;
            int maxChances = 7;

            switch (difficulty) {
                case 1:
                    max = 50;
                    maxChances = 10;
                    break;
                case 2:
                    max = 100;
                    maxChances = 7;
                    break;
                case 3:
                    max = 200;
                    maxChances = 5;
                    break;
                default:
                    System.out.println("Invalid choice! Defaulting to Medium.");
            }

            System.out.println("You have " + maxChances + " chances to guess the correct number.");

            int randomNumber = getRandN(min, max);
            boolean guessedCorrectly = false;

            for (int i = 0; i < maxChances; i++) {
                int userGuess = -1;

                while (userGuess < min || userGuess > max) {
                    System.out.println("Chance " + (i + 1) + ": Enter your guess (between " + min + " and " + max + "):");

                    if (sc.hasNextInt()) {
                        userGuess = sc.nextInt();
                        if (userGuess < min || userGuess > max) {
                            System.out.println("Invalid input! Please enter a number between " + min + " and " + max + ".");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a valid integer.");
                        sc.next(); // Clear invalid input
                    }
                }

                if (userGuess == randomNumber) {
                    guessedCorrectly = true;
                    score++;
                    System.out.println("Congratulations! You guessed the number.");
                    break;
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your chances. The correct number was " + randomNumber + ".");
            }

            System.out.println("Do you want to play again? (y/n)");
            String playAgainResponse = sc.next();
            playAgain = playAgainResponse.equalsIgnoreCase("y");
        }

        System.out.println("Thank you for playing! Your final score is: " + score);

        sc.close();
    }

    public static int getRandN(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
