package Game.Scene;

import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Scene {
    public static final String MAIN_MENU_TITLE = "PokeINF";
    public static final int TITLE_FONT_SIZE = 100;
    public static final int TITLE_HEIGHT = 250;
    public static final MenuAction[] BUTTON_ACTIONS = MenuAction.values();

    private enum MenuAction {
        NEW_GAME,
        // LOAD_GAME,
        // SETTINGS,
        EXIT
    }

    private final Game game;

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
        titleLabel.setFont(new Font("Courier New", Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        add(titleLabel);
    }

    
    private void addButtons() {
        for (int i = 0; i < BUTTON_ACTIONS.length; i++) {
            addButton(BUTTON_ACTIONS[i], i);
        }

        setLayout(null); // Use absolute positioning
    }

    private void addButton(MenuAction action, int offset) {
        final String buttonLabel = action.toString().replace("_", " ");
        JButton newButton = new JButton(buttonLabel); 

        int xPos = (Scene.WINDOW_WIDTH - Scene.BUTTON_WIDTH) / 2;
        int yPos = ((Scene.WINDOW_HEIGHT- Scene.BUTTON_HEIGHT) / 2) + (offset * Scene.BUTTON_Y_OFFSET);
        newButton.setBounds(xPos, yPos, Scene.BUTTON_WIDTH, Scene.BUTTON_HEIGHT);

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(action); // Determine what the button does when it's clicked
            }
        });

        add(newButton);
    }

    private void handleButtonClick(MenuAction action) {       
        switch (action) {
            case NEW_GAME:
                game.setGameState(Game.STATE.CHOOSE_SKILL);
                break;
            // case LOAD_GAME:
            //     System.out.println("Load Game clicked");
            //     break;
            // case SETTINGS:
            //     game.setGameState(Game.STATE.SETTINGS);
            //     break;
            case EXIT:
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException("Unknown button label: " + action.toString());
        }
    }

}
