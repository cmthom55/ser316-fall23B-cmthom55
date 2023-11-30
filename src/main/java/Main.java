import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        //SER316 TASK 2 SPOTBUGS FIX
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);

        //SER316 TASK 2 SPOTBUGS FIX
        BufferedReader bufferedReader = new BufferedReader(reader);

        Game newgame = new Game("Dr. M.", 0);
        System.out.println("Make a guess: ");
        System.out.println("Score: " + newgame.getScore());
        System.out.println(newgame.getProgress());

        while (newgame.getGameStatus() == 0) {
            try {
                String message = bufferedReader.readLine();
                //SER316 TASK 2 SPOTBUGS FIX
                if (message != null) {
                    newgame.makeGuess(message);
                    System.out.println("Score: " + newgame.getScore());
                    System.out.println(newgame.getProgress());
                } else {
                    System.out.println("Message is null.");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
