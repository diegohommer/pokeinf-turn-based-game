package Game.Scene;

import Game.Game;
import Game.Character.*;
import Game.Character.Skill.*;
import Game.Character.Skill.Skills.*;
import Game.Scene.UI.SkillCard;

import java.util.ArrayList;
import java.awt.FlowLayout;

public class ChooseSkill extends Scene {    
    private static final int HOR_GAP = 50;
    private static final int VER_GAP = 100;
    
    private ArrayList<Skill> skills;
    private Player player;
    private Game game;

    public ChooseSkill(Game game, Player player, String spritePath){
        super(spritePath);
        this.player = player;
        this.game = game;

        // PLACEHOLDER -> DEVEMOS TER UM MÉTODO PARA SORTEAR NOVAS SKILLS A SEREM ADICIONADAS
        this.skills = new ArrayList<>(); 
        skills.add(new AtaqueRapido());
        skills.add(new ErrorSkill());
        // PLACEHOLDER

        addSkillCards();
    }

    private void addSkillCards() {
        setLayout(new FlowLayout(FlowLayout.CENTER, HOR_GAP, VER_GAP)); // Added horizontal and vertical gaps

        for(Skill skill : skills){
            add(new SkillCard(skill, this.player, this.game));
        }
    }
}
