package Game.Scene.UI;

import Game.Game;
import Game.Character.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.Character.Skill.Skill;

public class SkillCard extends JPanel{
    public static final int CARD_HEIGHT = 300;
    public static final int CARD_WIDTH = 200;
    public static final int CARD_TITLE_SIZE = 18;
    public static final int CARD_DESC_SIZE = 12;
    public static final int BUTTON_HEIGHT = 40;
    public static final String BUTTON_TITLE = "Upgrade Skill";

    public SkillCard(Skill skill, Player player, Game game) {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT + BUTTON_HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);

        // "Container" for the card display
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setOpaque(false);

        // Create and add the components to the container
        JLabel headerLabel = createHeader(skill.getName());
        JTextArea bodyTextArea = createBody(skill.getDescription());
        JSeparator separator = createSeparator();
        cardPanel.add(headerLabel, BorderLayout.NORTH);
        cardPanel.add(separator, BorderLayout.CENTER); 
        cardPanel.add(bodyTextArea, BorderLayout.SOUTH);

        // Add the container and it's content to the SkillCard panel
        this.add(cardPanel, BorderLayout.CENTER);

        // Create and add the button under the card
        JButton actionButton = createButton(skill, player, game);
        this.add(actionButton, BorderLayout.SOUTH);
    }

    private JLabel createHeader(String skillName){
        JLabel headerLabel = new JLabel(skillName);
        headerLabel.setFont(new Font("Arial", Font.BOLD, CARD_TITLE_SIZE));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return headerLabel;
    }

    private JTextArea createBody(String skillDescription){
        JTextArea bodyTextArea = new JTextArea(skillDescription);
        bodyTextArea.setFont(new Font("Arial", Font.PLAIN, CARD_DESC_SIZE));
        bodyTextArea.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - BUTTON_HEIGHT));
        bodyTextArea.setEditable(false);
        bodyTextArea.setBackground(Color.WHITE);
        bodyTextArea.setLineWrap(true);
        bodyTextArea.setWrapStyleWord(true);
        return bodyTextArea;
    }

    private JSeparator createSeparator(){
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(CARD_WIDTH, 1)); 
        separator.setBackground(Color.BLACK);
        return separator;
    }

    private JButton createButton(Skill skill, Player player, Game game){
        JButton actionButton = new JButton(BUTTON_TITLE);
        actionButton.setPreferredSize(new Dimension(CARD_WIDTH, BUTTON_HEIGHT));
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.addSkill(skill);
                game.setGameState(Game.STATE.BATTLE);
            }
        });
        return actionButton;
    }
}
