package Game;

import javax.swing.*;

import Game.Character.*;
import Game.Character.Skill.Skills.*;
import Game.Scene.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game extends JFrame {
    public final String WINDOW_TITLE = "PokeINF";
    public final int WINDOW_WIDTH = Scene.WINDOW_WIDTH;
    public final int WINDOW_HEIGHT = Scene.WINDOW_HEIGHT;
    private final String PLAYER_NAME = "Dennis";

    private final String MENU_BACKGROUND = "assets//test_background.jpg";
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    private int currentEnemyIndex = 0;

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
        initEnemies();
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

    private void initEnemies()
    {
        enemies.add(new Enemy(
            "assets//pimenta.jpg", 
            "Pimenta", 
            100, 
            60, 
            new ArrayList<>(Arrays.asList(new QuickAttack(), new MultiAttack(), new Heal(), new Rest()))));
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
                this.getContentPane().add(new Battle(this, MENU_BACKGROUND, player, enemies.get(currentEnemyIndex)));
                break;
            case CHOOSE_SKILL:
                this.getContentPane().add(new ChooseSkill(this, getPlayer(), MENU_BACKGROUND));
                break;
            case CHOOSE_BATTLE:
                // Add CHOOSE BATTLE panel
                break;
        }
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void advanceEnemy()
    {
        if(++currentEnemyIndex >= enemies.size())
        {
            currentEnemyIndex = 0;
        }
    }

    public int getCurrentEnemyIndex() {
        return currentEnemyIndex;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
