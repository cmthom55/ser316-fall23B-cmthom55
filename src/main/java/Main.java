import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);

        BufferedReader bufferedReader = new BufferedReader(reader);

        // Rest of your code remains the same
        System.out.println("Getting started");
        Game game = new Game(1);
        System.out.println("Current word: " + game.getAnswer());
        System.out.println(game.getProgress());
        game.makeGuess("a");
        System.out.println("Automatic guess a");
        System.out.println(game.getProgress());

        Game newgame = new Game("Dr. M.", 0);
        System.out.println("Make a guess: ");
        System.out.println(newgame.getProgress());

        while (newgame.getGameStatus() == 0) {
            try {
                String message = bufferedReader.readLine();
                System.out.println(newgame.makeGuess(message));
                System.out.println("Score: " + newgame.getScore());
                System.out.println(newgame.getProgress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
