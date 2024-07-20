package Jogo;

import javax.swing.*;

import Jogo.Cena.*;
import Jogo.Personagem.Personagem;

public class Game extends JFrame {
    public final String WINDOW_TITLE = "PokeINF";
    public final int WINDOW_HEIGHT = 768;
    public final int WINDOW_WIDTH = 1024;

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
        setGameState(STATE.MENU);
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

    private void updateDisplay() {
        this.getContentPane().removeAll();

        // Handle state-based display updates
        switch (getGameState()) {
            case MENU:
                this.getContentPane().add(new MainMenu(this, "assets//test_background.jpg"));
                break;
            case SETTINGS:
                // Add CONFIGS panel
                break;
            case BATTLE:
                // Add BATTLE panel
                break;
            case CHOOSE_SKILL:
                // Add CHOOSE_SKILL panel
                break;
            case CHOOSE_BATTLE:
                // Add CHOOSE_BATTLE panel
                break;
        }
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
}
