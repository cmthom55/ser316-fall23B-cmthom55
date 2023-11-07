import java.nio.charset.Charset;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Specify the character encoding explicitly (e.g., UTF-8)
        Charset charset = Charset.forName("UTF-8");
        
        Scanner scanner = new Scanner(System.in);
        
        // just some calls
        System.out.println("Getting started");
        Game game = new Game(1);
        System.out.println("Current word: " + game.getAnswer());
        System.out.println(new String(game.getProgress(), charset));
        game.makeGuess("a");
        System.out.println("Automatic guess a");
        System.out.println(new String(game.getProgress(), charset));

        // Rough game play
        Game newgame = new Game("Dr. M.", 0);
        System.out.println("Make a guess: ");
        System.out.println(new String(newgame.getProgress(), charset));
        while (newgame.getGameStatus() == 0) {
            String message = scanner.nextLine();
            System.out.println(newgame.makeGuess(message));
            System.out.println("Score: " + newgame.getScore());
            System.out.println(new String(newgame.getProgress(), charset));
        }
    }
}
