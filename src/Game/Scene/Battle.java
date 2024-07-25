package Game.Scene;

import javax.imageio.ImageIO;
import javax.swing.*;

import Game.Game;
import Game.Character.Enemy;
import Game.Character.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Battle extends Scene{
    private String[] buttonLabels;

    JLabel battleText;
    ArrayList<JButton> skillButtons = new ArrayList<JButton>();

    private Game game;
    private Player player;
    private Enemy enemy;

    public Battle(Game game, String spritePath, Player player, Enemy enemy){
        super(spritePath);
        this.game = game;
        this.player = player;
        this.enemy = enemy;

        createUI();
        //battleText.setText("Escolha seu próximo movimento");
    }

    private void createUI()
    {
        setLayout(null);
        addBattleText();

        int charactersSpriteWidth = 180;
        int charactersSpriteHeight = 250;

        try {
            String path = System.getProperty("user.dir");
            path = path.substring(0, path.length() - 3) + player.getSpritePath();
            BufferedImage originalImage = ImageIO.read(new File(path));
            Image scaledImage = originalImage.getScaledInstance(charactersSpriteWidth, charactersSpriteHeight, Image.SCALE_SMOOTH);

            ImageIcon playerImage = new ImageIcon(scaledImage);
            JLabel playerSprite = new JLabel(playerImage);
            playerSprite.setBounds(100, 280, charactersSpriteWidth, charactersSpriteHeight);

            add(playerSprite);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String path = System.getProperty("user.dir");
            path = path.substring(0, path.length() - 3) + enemy.getSpritePath();
            BufferedImage originalImage = ImageIO.read(new File(path));
            Image scaledImage = originalImage.getScaledInstance(charactersSpriteWidth, charactersSpriteHeight, Image.SCALE_SMOOTH);

            ImageIcon playerImage = new ImageIcon(scaledImage);
            JLabel playerSprite = new JLabel(playerImage);
            playerSprite.setBounds(700, 30, charactersSpriteWidth, charactersSpriteHeight);

            add(playerSprite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addBattleText() {
        battleText = new JLabel("A batalha começa!");
        battleText.setBounds(50, 550, WINDOW_WIDTH, 100);
        battleText.setForeground(Color.BLACK); // Set text color
        battleText.setFont(new Font("Courier New", Font.BOLD, 24));
        battleText.setHorizontalAlignment(JLabel.LEFT);
        battleText.setVerticalAlignment(JLabel.TOP);
        add(battleText);
    }

    private void addButtons() {
        for(int i = 0; i < 4; i++)
        {
            JButton skillButton = new JButton("Skill " + Integer.toString(i));
            skillButtons.add(skillButton);
            skillButton.setBounds(50 + BUTTON_X_OFFSET * i, 600, BUTTON_WIDTH, BUTTON_HEIGHT);

            // Determine what the button does when it's clicked
            skillButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    return;
                }
            });
        }
        // Use absolute positioning
        setLayout(null); 
    }

    private void addButton() {

    }

    private void handleButtonClick(String buttonLabel) {
        switch (buttonLabel) {
            case "New Game":
                game.setGameState(Game.STATE.CHOOSE_SKILL);
                break;
            case "Load Game":
                System.out.println("Load Game clicked");
                break;
            case "Settings":
                game.setGameState(Game.STATE.SETTINGS);
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException("Unknown button label: " + buttonLabel);
        }
    }
}
