package Game.Scene;

import javax.swing.*;

import Game.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Scene {
    private final String MAIN_MENU_TITLE = "PokeINF";
    private final int TITLE_HEIGHT = 250;
    private final int BUTTON_TOTAL = 4;
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;
    private final int BUTTON_X_POS = (WINDOW_WIDTH - BUTTON_WIDTH) / 2;
    private final int BUTTON_Y_POS = (WINDOW_HEIGHT - BUTTON_HEIGHT) / 2;
    private final int BUTTON_Y_OFFSET = 70;

    private Game game;
    private final String[] buttonLabels = {"New Game", "Load Game", "Settings", "Exit"};

    public MainMenu(Game game, String spritePath){
        super(spritePath);
        this.game = game;
        addTitle();
        addButtons();
    }

    private void addTitle() {
        JLabel titleLabel = new JLabel(MAIN_MENU_TITLE);
        titleLabel.setBounds(0, 0, WINDOW_WIDTH, TITLE_HEIGHT);
        titleLabel.setForeground(Color.WHITE); // Set text color
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 100));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        add(titleLabel);
    }

    
    private void addButtons() {
        JButton newButton;

        // Initialize buttons
        for(int i = 0; i < BUTTON_TOTAL; i++){
            final String buttonLabel = buttonLabels[i];
            newButton = new JButton(buttonLabel); 

            // Position and dimension the button
            int yPosition = BUTTON_Y_POS + (i * BUTTON_Y_OFFSET);
            newButton.setBounds(BUTTON_X_POS, yPosition, BUTTON_WIDTH, BUTTON_HEIGHT);

            // Determine what the button does when it's clicked
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(buttonLabel);
                }
            });

            add(newButton);
        
        }
        // Use absolute positioning
        setLayout(null); 
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
