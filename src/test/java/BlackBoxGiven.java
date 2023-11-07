import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {
    private Class<Game> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<Game>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {Game0.class},
            {Game1.class},
            {Game2.class},
            {Game3.class}
        };
        return Arrays.asList(classes);
    }

    private Game createGame() throws Exception {
        Constructor<Game> constructor = classUnderTest.getConstructor();
        return constructor.newInstance();
    }

    Game game;

    @org.junit.Before
    public void setUp() throws Exception {
        game = createGame();
    }

    /*
    Simple test to check that beginning score is 10 and name is set correctly
    */
    @Test
    public void startGame() {
        game.init_Game("Aachen", "Dr. M");
        assertEquals(10, game.score);
        assertEquals("Dr. M" , game.getName());
    }

    /*
    Check that if one letter is guessed correctly the score is updated correctly
    */
    @Test
    public void oneHit() {
        game.init_Game("Aachen", "Dr. M");
        boolean guess = game.makeGuess("c");
        assertEquals(11, game.score);
    }

    @Test
    public void testRightLetterInBounds() {
        game.init_Game("pull", "Player");
        assertTrue(game.makeGuess("p"));
        assertEquals(11, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testWrongLetterInBounds() {
        game.init_Game("pull", "Player");
        assertFalse(game.makeGuess("v"));
        assertEquals(9, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testRightLetterAtBounds() {
        game.init_Game("apple", "Player");
        assertTrue(game.makeGuess("a"));
        assertEquals(11, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testRightUpperLetterAtBounds() {
        game.init_Game("Apple", "Player");
        assertTrue(game.makeGuess("A"));
        assertEquals(11, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testWrongLetterAtBounds() {
        game.init_Game("apple", "Player");
        assertFalse(game.makeGuess("z"));
        assertEquals(9, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testWrongUpperLetterAtBounds() {
        game.init_Game("Apple", "Player");
        assertFalse(game.makeGuess("Z"));
        assertEquals(9, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testRightWord() {
        game.init_Game("pancake", "Player");
        assertTrue(game.makeGuess("pancake"));
        assertEquals(24, game.score);
        assertEquals(1, game.getGameStatus());
    }

    @Test
    public void testRightWordAtBound() {
        game.init_Game("oh", "TestPlayer");
        assertTrue(game.makeGuess("oh"));
        assertEquals(14, game.score);
        assertEquals(1, game.getGameStatus());
    }

    @Test
    public void testWrongWord() {
        game.init_Game("ramp", "Player");
        assertFalse(game.makeGuess("bunny"));
        assertEquals(5, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testWrongWordAtBound() {
        game.init_Game("bob", "Player");
        assertFalse(game.makeGuess("of"));
        assertEquals(5, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testEmpty() {
        game.init_Game("bob", "Player");
        assertFalse(game.makeGuess(""));
        assertEquals(10, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testHasSymbols() {
        game.init_Game("bob", "Player");
        assertFalse(game.makeGuess("$#%"));
        assertEquals(10, game.score);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    public void testIsNumbers() {
        game.init_Game("hangman", "Player");
        assertFalse(game.makeGuess("457"));
        assertEquals(10, game.score);
        assertEquals(0, game.getGameStatus());
    }
}