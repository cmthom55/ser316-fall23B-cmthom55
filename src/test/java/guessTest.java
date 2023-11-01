import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class guessTest{

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testMakeGuessOneHit() {
        game.init_Game("Aachen", "Dr. M");
        boolean guess = game.makeGuess("c");
        assertEquals(11, game.score);
    }

    @Test
    public void testMakeGuessRightLetterInBounds() {
        game.init_Game("pull", "Player");
        assertTrue(game.makeGuess("p"));
        assertEquals(11, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessWrongLetterInBounds() {
        game.init_Game("pull", "Player");
        assertFalse(game.makeGuess("v"));
        assertEquals(9, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessRightLetterAtBounds() {
        game.init_Game("apple", "Player");
        assertTrue(game.makeGuess("a"));
        assertEquals(11, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessRightUpperLetterAtBounds() {
        game.init_Game("Apple", "Player");
        assertTrue(game.makeGuess("A"));
        assertEquals(11, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessWrongLetterAtBounds() {
        game.init_Game("apple", "Player");
        assertFalse(game.makeGuess("z"));
        assertEquals(9, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessWrongUpperLetterAtBounds() {
        game.init_Game("Apple", "Player");
        assertFalse(game.makeGuess("Z"));
        assertEquals(9, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessRightWord() {
        game.init_Game("pancake", "Player");
        assertTrue(game.makeGuess("pancake"));
        assertEquals(24, game.score);
        assertEquals(1, game.getGameStatus());
    }

    @Test
    public void testMakeGuessRightWordAtBound() {
        game.init_Game("oh", "TestPlayer");
        assertTrue(game.makeGuess("oh"));
        assertEquals(14, game.score);
        assertEquals(1, game.getGameStatus());
    }

    @Test
    public void testMakeGuessWrongWord() {
        game.init_Game("ramp", "Player");
        assertFalse(game.makeGuess("bunny"));
        assertEquals(5, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessWrongWordAtBound() {
        game.init_Game("bob", "Player");
        assertFalse(game.makeGuess("fl"));
        assertEquals(5, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessEmpty() {
        game.init_Game("bob", "Player");
        assertFalse(game.makeGuess(""));
        assertEquals(10, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessHasSymbols() {
        game.init_Game("bob", "Player");
        assertFalse(game.makeGuess("$#%"));
        assertEquals(10, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testMakeGuessIsNumbers() {
        game.init_Game("hangman", "Player");
        assertFalse(game.makeGuess("457"));
        assertEquals(10, game.score);
        assertEquals(0, game.getGameStatus());
    }
}
