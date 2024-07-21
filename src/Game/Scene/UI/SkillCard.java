package Game.Scene.UI;

import Game.Game;
import Game.Character.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.Character.Skill.Skill;

public class SkillCard extends JPanel{
    private final int CARD_HEIGHT = 400;
    private final int CARD_WIDTH = 300;
    private final int CARD_TITLE_SIZE = 25;
    private final int CARD_DESC_SIZE = 12;
    private final int BUTTON_HEIGHT = 40;

    public SkillCard(Skill skill, Player player, Game game) {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT + BUTTON_HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);

        // BELLOW LIES THE FRONT-END CHAOS, PROCEED WITH CAUTION
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setOpaque(false);

        JLabel headerLabel = new JLabel(skill.getName());
        headerLabel.setFont(new Font("Arial", Font.BOLD, CARD_TITLE_SIZE));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea bodyTextArea = new JTextArea(skill.getDescription());
        bodyTextArea.setFont(new Font("Arial", Font.PLAIN, CARD_DESC_SIZE));
        bodyTextArea.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - BUTTON_HEIGHT));
        bodyTextArea.setEditable(false);
        bodyTextArea.setBackground(Color.WHITE);
        bodyTextArea.setLineWrap(true);
        bodyTextArea.setWrapStyleWord(true);

        // Create a separator
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(CARD_WIDTH, 1)); // Adjust height as needed
        separator.setBackground(Color.BLACK);
 
        // Add components to the card panel
        cardPanel.add(headerLabel, BorderLayout.NORTH);
        cardPanel.add(separator, BorderLayout.CENTER); // Add separator between header and body
        cardPanel.add(bodyTextArea, BorderLayout.SOUTH);

        add(cardPanel, BorderLayout.CENTER);
        // FRONT-END SHENANIGANS END

        // Add the button and determine what happens when it's clicked
        JButton actionButton = new JButton("Select Skill");
        actionButton.setPreferredSize(new Dimension(CARD_WIDTH, BUTTON_HEIGHT));
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.addSkill(skill);
                game.setGameState(Game.STATE.BATTLE);
            }
        });

        add(actionButton, BorderLayout.SOUTH);
    }
}
