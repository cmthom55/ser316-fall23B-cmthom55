import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;



public class GivenWhiteBox {
    Game game;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void startGame() {
        Game game = new Game("Aachen", "Alex");
        assertEquals(game.score, 10);
    }

}
