import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create an InputStreamReader with UTF-8 character encoding to read input from System.in
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);

        // Wrap the InputStreamReader in a BufferedReader for efficient reading
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
                if (message != null) {
                    System.out.println(newgame.makeGuess(message));
                    System.out.println("Score: " + newgame.getScore());
                    System.out.println(newgame.getProgress());
                } else {
                    // Handle end of input or an error here
                    System.out.println("End of input or an error occurred.");
                    break; // Exit the loop
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
