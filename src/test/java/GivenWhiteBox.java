import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class GivenWhiteBox {
    Game game;

    @Before
    public void setUp() throws Exception {
        // Initialize the game object here
        game = new Game("Aachen", "Alex");
    }

    @Test
    public void startGame() {
        // This test is for starting a game and checking the initial score.
        assertEquals(10, game.getScore());
    }

    /* Addresses Node Coverage Sequence for fillProgress */
    @Test
    public void testFillProgressSequence() {
        char[] progress = new char[game.getAnswer().length()];
        char f = '_';
        for (int i = 0; i < progress.length; i++) {
            progress[i] = f;
        }
        game.progress = progress;

        int hit = game.fillProgress();

        int expectedHit = 6;
        assertEquals(expectedHit, hit);
    }

    /* Addresses Fill Progress Edge Coverage Sequence 1 */
    @Test
    public void testFillProgressProgressContainsUnderscores() {
        char[] progress = game.getAnswer().toCharArray();
        progress[0] = '_';
        progress[2] = '_';
        game.progress = progress;

        int hit = game.fillProgress();

        assertFalse(String.valueOf(game.getProgress()).contains("_"));

        int expectedHit = 2;
        assertEquals(expectedHit, hit);
    }

    /* Addresses Fill Progress Edge Coverage Sequence 2 */
    @Test
    public void testFillProgressProgressContainsNoUnderscores() {
        char[] progress = game.getAnswer().toCharArray();
        game.progress = progress;

        int hit = game.fillProgress();

        assertArrayEquals(game.getAnswer().toCharArray(), game.getProgress());

        assertEquals(0, hit);
    }

    /* Addresses Set Progress Node Sequence */
    @Test
    public void testSetProgressSequence() {
        game.answer = "Aachen";

        game.progress = new char[game.getAnswer().length()];
        char f = '_';
        for (int i = 0; i < game.progress.length; i++) {
            game.progress[i] = f;
        }

        int count = game.setProgress('a');
        assertEquals(2, count);
    }

    /* Addresses Set Progress Edge Coverage Sequence 1 */
    @Test
    public void testSetProgressEdgeCoverageTrue() {
        game.answer = "Aachen";

        game.progress = new char[game.getAnswer().length()];
        char f = '_';
        for (int i = 0; i < game.progress.length; i++) {
            game.progress[i] = f;
        }

        int count = game.setProgress('a');
        assertEquals(2, count);
    }

    /* Addresses Set Progress Edge Coverage Sequence 2 */
    @Test
    public void testSetProgressEdgeCoverageFalse() {
        game.answer = "Aachen";

        game.progress = new char[game.getAnswer().length()];
        char f = '_';
        for (int i = 0; i < game.progress.length; i++) {
            game.progress[i] = f;
        }

        int count = game.setProgress('b');

        char[] expectedProgress = "______".toCharArray();
        assertArrayEquals(expectedProgress, game.getProgress());

        assertEquals(0, count);
    }     

    @Test
    public void testMakeGuess() {
        game.answer = "Aachpn";
        boolean result = game.makeGuess("a");
        assertEquals(12, game.getScore());
        assertTrue(result);

        // Additional test cases:

        // 1. Test for an incorrect letter guess;

        boolean result1 = game.makeGuess("b");
        assertEquals(11, game.getScore());
        assertFalse(result1);

        // 2. Test for an incorrect word guess

        boolean result2 = game.makeGuess("Berli");
        assertEquals(6, game.getScore());
        assertFalse(result2);

        // 3. Test for a correct letter guess

        boolean result3 = game.makeGuess("a");
        assertEquals(4, game.getScore());
        assertFalse(result3);

        // 4. Test for a correct word guess

        boolean result4 = game.makeGuess("Aachpn");
        assertEquals(12, game.getScore());
        assertTrue(result4);
    }

    @Test
    public void testGetRandomWord() {
        game.getRandomWord("city");
        assertNotNull(game.getAnswer());
    }

    @Test
    public void testGameConstructorWithStringAndInt() {
        Game newGame = new Game("Munich", 0);
        assertNotNull(newGame);
    }

}
