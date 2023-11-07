import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // just some calls
        Scanner scanner = new Scanner(System.in);
        System.out.println("Getting started");
        Game game = new Game(1);
        System.out.println("Current word: " + game.answer);
        System.out.println(game.getProgress());
        game.makeGuess("a");
        System.out.println("Automatic guess a");
        System.out.println(game.getProgress());


        // Rough game play
        Game newgame = new Game("Dr. M.", 0 );
        System.out.println("Make a guess: ");
        System.out.println(newgame.getProgress());
        while (newgame.getGameStatus() == 0) {
            String message = scanner.nextLine();
            System.out.println(newgame.makeGuess(message));
            System.out.println("Score: " + newgame.score);
            System.out.println(newgame.getProgress());
        }
    }
}
