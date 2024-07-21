package Game;

import javax.swing.*;

import Game.Character.*;
import Game.Scene.*;
import java.awt.*;

public class Game extends JFrame {
    public final String WINDOW_TITLE = "PokeINF";
    public final int WINDOW_HEIGHT = 768;
    public final int WINDOW_WIDTH = 1024;
    private final String PLAYER_NAME = "Dennis";

    private final String MENU_BACKGROUND = "assets//test_background.jpg";
    private Player player;

    public enum STATE {
        MENU,
        SETTINGS,
        BATTLE,
        CHOOSE_SKILL,
        CHOOSE_BATTLE
    }
    private STATE gameState;

    public Game() {
        setTitle(WINDOW_TITLE);
        setSize(WINDOW_WIDTH , WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setPlayer(new Player(PLAYER_NAME));
        setGameState(STATE.MENU);
    }

    public Player getPlayer(){
        return this.player;
    }
    public boolean setPlayer(Player player){
        try {
            this.player = player;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public STATE getGameState(){
        return gameState;
    }
    public boolean setGameState(STATE newState){
        try {
            this.gameState = newState;
            updateDisplay();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    // Handle state-based display updates
    private void updateDisplay() {
        this.getContentPane().removeAll();

        switch (getGameState()) {
            case MENU:
                this.getContentPane().add(new MainMenu(this, MENU_BACKGROUND));
                break;
            case SETTINGS:
                // Add CONFIGS panel
                break;
            case BATTLE:
                // Add BATTLE panel
                break;
            case CHOOSE_SKILL:
                this.getContentPane().add(new ChooseSkill(this, getPlayer(), MENU_BACKGROUND));
                break;
            case CHOOSE_BATTLE:
                // Add CHOOSE_BATTLE panel
                break;
        }
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
}
