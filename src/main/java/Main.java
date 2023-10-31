import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // just some calls
        System.out.println("Getting started");
        Game game = new Game(1);
        System.out.println("Current word: " + game.getAnswer());
        System.out.println(game.getProgress());
        game.makeGuess("a");
        System.out.println("Automatic guess a");
        System.out.println(game.getProgress());

        // Rough game play
        Game newgame = new Game("Dr. M.", 0);
        System.out.println("Make a guess: ");
        System.out.println(newgame.getProgress());
        while (newgame.getGameStatus() == 0) {
            String message = scanner.nextLine();

            if (validateMessage(message)) {
                System.out.println(newgame.makeGuess(message));
                System.out.println("Score: " + newgame.getScore());
                System.out.println(newgame.getProgress());
            } else {
                System.out.println("Input is not valid. Please enter only alphabetic characters.");
            }
        }
    }

    public static boolean validateMessage(String message) {
        return !message.isEmpty() && message.matches("^[a-zA-Z]*$");
    }
}
