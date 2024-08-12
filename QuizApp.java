import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    private static final int TIME_LIMIT = 15;
    private static int currentQuestionIndex = 0;
    private static int score = 0;
    private static Timer timer;
    private static int timeRemaining;
    private static boolean timeUp = false;

    private static Question[] questions = {
        new Question("What is the capital of France?", new String[]{"Paris", "London", "Rome", "Berlin"}, "Paris"),
        new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Jupiter", "Saturn", "Mars"}, "Jupiter"),
        new Question("Who wrote 'To Kill a Mockingbird'?", new String[]{"Harper Lee", "Mark Twain", "Ernest Hemingway", "F. Scott Fitzgerald"}, "Harper Lee")
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (currentQuestionIndex < questions.length) {
            loadQuestion(currentQuestionIndex, scanner);
        }
        showResults();
        scanner.close();
    }

    private static void loadQuestion(int index, Scanner scanner) {
        Question question = questions[index];
        System.out.println("Question " + (index + 1) + ": " + question.getQuestion());

        for (int i = 0; i < question.getOptions().length; i++) {
            System.out.println((i + 1) + ". " + question.getOptions()[i]);
        }

        startTimer();

        String answer = null;
        while (answer == null && !timeUp) {
            System.out.print("Enter your answer (1-4): ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 4) {
                    answer = question.getOptions()[choice - 1];
                } else {
                    System.out.println("Invalid choice. Please select a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear invalid input
            }
        }

        submitAnswer(answer);
    }

    private static void startTimer() {
        timeRemaining = TIME_LIMIT;
        timeUp = false;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp = true;
                System.out.println("\nTime's up!");
                timer.cancel();
            }
        }, TIME_LIMIT * 1000);
    }

    private static void submitAnswer(String selectedOption) {
        timer.cancel();
        Question question = questions[currentQuestionIndex];
        if (selectedOption != null && selectedOption.equals(question.getCorrectAnswer())) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect! The correct answer was: " + question.getCorrectAnswer());
        }
        currentQuestionIndex++;
        System.out.println();
    }

    private static void showResults() {
        System.out.println("Quiz Completed. Your score: " + score + "/" + questions.length);
    }

    private static class Question {
        private String question;
        private String[] options;
        private String correctAnswer;

        public Question(String question, String[] options, String correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
