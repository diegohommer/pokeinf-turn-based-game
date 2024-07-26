package Game.Scene;

import javax.imageio.ImageIO;
import javax.swing.*;

import Game.Game;
import Game.Character.Skill.*;
import Game.Character.Enemy;
import Game.Character.Player;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Battle extends Scene{
    private static final int BUTTON_X_POS = 50;
    private static final int BUTTON_Y_POS = 600;
    private static final int MARGIN_LEFT = 50;

    private static final int BTL_TXT_HEIGHT = 100;
    private static final int BTL_TXT_YPOS = 550;
    private static final int BTL_TXT_FONT_SIZE = 24;
    private static final String BTL_TEXT_FONT = "Courier New";
    private static final String BTL_TXT_INIT = "A batalha começa!";

    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_X_OFFSET = BUTTON_WIDTH + 10;

    private static final int CHARACTER_HEIGHT = 250;
    private static final int CHARACTER_WIDTH = 180;
    private static final int PLAYER_XPOS = 100;
    private static final int PLAYER_YPOS = 280;
    private static final int ENEMY_XPOS = 700;
    private static final int ENEMY_YPOS = 30;

    private Game game;
    private Player player;
    private Enemy enemy;
    private JLabel battleText;
    private ArrayList<JButton> skillButtons = new ArrayList<JButton>();
    JButton continueButton;

    public Battle(Game game, String spritePath, Player player, Enemy enemy){
        super(spritePath);
        this.game = game;
        this.player = player;
        this.enemy = enemy;

        createUI();
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
        battleText.setForeground(Color.BLACK); // Set text color
        battleText.setFont(new Font(BTL_TEXT_FONT, Font.BOLD, BTL_TXT_FONT_SIZE));
        battleText.setHorizontalAlignment(JLabel.LEFT);
        battleText.setVerticalAlignment(JLabel.TOP);
        add(battleText);
    }

    private void addButtons() {
        for(int i = 0; i < player.getActiveSkills().size(); i++)
        {
            JButton skillButton = new JButton("Skill " + Integer.toString(i));
            skillButtons.add(skillButton);
            skillButton.setBounds(BUTTON_X_POS + BUTTON_X_OFFSET * i, BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);

            final Skill skill = this.player.selectSkill(i);
            // Determine what the button does when it's clicked
            skillButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerMakeMove(skill);
                }
            });

            skillButton.setVisible(false);

            add(skillButton);
        }

        continueButton = new JButton("> Continuar");
        continueButton.setBounds(BUTTON_X_POS + BUTTON_X_OFFSET * skillButtons.size(), BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);

        // Determine what the button does when it's clicked
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerChooseSkill();
            }
        });

        add(continueButton);
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

    private void playerChooseSkill()
    {
        battleText.setText("Escolha seu próximo movimento:");

        continueButton.setVisible(false);
        for (JButton skillButton : skillButtons) {
            skillButton.setVisible(true);
        }
    }

    private void playerMakeMove(Skill playerSkill)
    {
        playerSkill.applyEffect(player, enemy);

        battleText.setText(playerSkill.getName() + " utilizada contra " + enemy.getName() + "!");

        continueButton.removeActionListener(continueButton.getActionListeners()[0]);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enemyPrepareMove();
            }
        });
        continueButton.setVisible(true);

        for (JButton skillButton : skillButtons) {
            skillButton.setVisible(false);
        }
    }

    private void enemyPrepareMove()
    {
        battleText.setText("O inimigo se prepara para revidar!");

        continueButton.removeActionListener(continueButton.getActionListeners()[0]);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enemyMakeMove();
            }
        });
    }

    private void enemyMakeMove()
    {
        final Skill enemySkill = enemy.resolveNextSkill();
        enemySkill.applyEffect(enemy, player);

        battleText.setText(enemy.getName() + " revida com a habilidade " + enemySkill.getName() + "!");

        continueButton.removeActionListener(continueButton.getActionListeners()[0]);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerChooseSkill();
            }
        });
    }
}
