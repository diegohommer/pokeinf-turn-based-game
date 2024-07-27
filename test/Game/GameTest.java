package test.Game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Game.Character.*;
import Game.Game;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testInitialPlayer() {
        Player player = game.getPlayer();
        assertNotNull(player, "Player should not be null");
        assertEquals("Dennis", player.getName(), "Player name should be 'Dennis'");
    }

    @Test
    public void testSetPlayerSuccess() {
        Player newPlayer = new Player("NewPlayer");
        boolean result = game.setPlayer(newPlayer);

        assertTrue(result, "Setting a new player should be successful");
        assertEquals(newPlayer, game.getPlayer(), "The new player should be set correctly");
    }

    @Test
    public void testInitialState() {
        assertEquals(Game.STATE.BATTLE, game.getGameState(), "Initial game state should be BATTLE");
    }

    @Test
    public void testSetGameStateSuccess() {
        boolean result = game.setGameState(Game.STATE.MENU);

        assertTrue(result, "Setting game state to MENU should be successful");
        assertEquals(Game.STATE.MENU, game.getGameState(), "Game state should be set to MENU");
    }

    @Test
    public void testAdvanceEnemy() {
        int initialEnemyIndex = game.getCurrentEnemyIndex();
        game.advanceEnemy();
        int expectedIndex = (initialEnemyIndex + 1) % game.getEnemies().size();

        assertEquals(expectedIndex, game.getCurrentEnemyIndex(), "Current enemy index should advance by 1");

        // Advance until it loops back to the first enemy
        for (int i = 1; i < game.getEnemies().size(); i++) {
            game.advanceEnemy();
        }

        assertEquals(0, game.getCurrentEnemyIndex(), "Current enemy index should loop back to 0");
    }
    
    @Test
    public void testInitEnemies() {
        assertNotNull(game.getEnemies(), "Enemies list should not be null");
        assertFalse(game.getEnemies().isEmpty(), "Enemies list should not be empty");
        assertEquals("Pimenta", game.getEnemies().get(0).getName(), "The first enemy's name should be 'Pimenta'");
    }
}