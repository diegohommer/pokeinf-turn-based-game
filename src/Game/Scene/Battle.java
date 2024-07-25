package Game.Scene;

import javax.imageio.ImageIO;
import javax.swing.*;

import Game.Game;
import Game.Character.Skill.*;
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
    private static final int BUTTONS_TOTAL = 4;
    private static final int BUTTON_YPOS = 600;
    private static final int MARGIN_LEFT = 50;
    
    private static final int BTL_TXT_HEIGHT = 100;
    private static final int BTL_TXT_YPOS = 550;
    private static final int BTL_TXT_FONT_SIZE = 24;
    private static final String BTL_TEXT_FONT = "Courier New";
    private static final String BTL_TXT_INIT = "A batalha começa!";

    private static final int CHARACTER_HEIGHT = 250;
    private static final int CHARACTER_WIDTH = 180;
    private static final int PLAYER_XPOS = 100;
    private static final int PLAYER_YPOS = 280;
    private static final int ENEMY_XPOS = 700;
    private static final int ENEMY_YPOS = 30;

    
    private JLabel battleText;
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
        addButtons();
        addCharacterSprites();
    }

    private void addBattleText() {
        battleText = new JLabel(BTL_TXT_INIT);
        battleText.setBounds(MARGIN_LEFT,BTL_TXT_YPOS, WINDOW_WIDTH, BTL_TXT_HEIGHT);
        battleText.setForeground(Color.WHITE); // Set text color
        battleText.setFont(new Font(BTL_TEXT_FONT, Font.BOLD, BTL_TXT_FONT_SIZE));
        battleText.setHorizontalAlignment(JLabel.LEFT);
        battleText.setVerticalAlignment(JLabel.TOP);
        add(battleText);
    }

    private void addButtons() {
        for(int i = 0; i < BUTTONS_TOTAL; i++)
        {
            addButton(i);
        }
    }

    private void addButton(int offsetIndex) {
        final Skill skill = this.player.selectSkill(offsetIndex);

        JButton skillButton = new JButton(skill.getName());

        int xPos = MARGIN_LEFT + (BUTTON_X_OFFSET * offsetIndex);
        skillButton.setBounds(xPos, BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT);

        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skill.applyEffect(player, enemy); // Determine what the button does when it's clicked
            }
        });

        this.add(skillButton);
    }

    private void addCharacterSprites() {
        try {
            Image playerImage = ImageIO.read(new File(player.getSpritePath()));
            Image scaledImage = playerImage.getScaledInstance(CHARACTER_WIDTH, CHARACTER_HEIGHT, Image.SCALE_SMOOTH);
            ImageIcon playerIcon = new ImageIcon(scaledImage);
            JLabel playerSprite = new JLabel(playerIcon);
            playerSprite.setBounds(PLAYER_XPOS, PLAYER_YPOS, CHARACTER_WIDTH, CHARACTER_HEIGHT);

            add(playerSprite);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image enemyImage = ImageIO.read(new File(enemy.getSpritePath()));
            Image scaledImage = enemyImage.getScaledInstance(CHARACTER_WIDTH, CHARACTER_HEIGHT, Image.SCALE_SMOOTH);
            ImageIcon enemyIcon = new ImageIcon(scaledImage);
            JLabel enemySprite = new JLabel(enemyIcon);
            enemySprite.setBounds(ENEMY_XPOS, ENEMY_YPOS, CHARACTER_WIDTH, CHARACTER_HEIGHT);

            add(enemySprite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
