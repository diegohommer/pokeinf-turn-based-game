package Game.Scene;

import Game.Game;
import Game.Character.*;
import Game.Character.Skill.*;
import Game.Character.Skill.Skills.*;
import Game.Scene.UI.SkillCard;

import java.util.ArrayList;
import java.awt.FlowLayout;

public class ChooseSkill extends Scene {    
    private ArrayList<Skill> skills;
    private Player player;
    private Game game;

    // Constructor receives the game, the player character of the game and the background sprite path
    public ChooseSkill(Game game, Player player, String spritePath){
        super(spritePath);
        setPlayer(player);
        setGame(game);

        // PLACEHOLDER -> DEVEMOS TER UM MÉTODO PARA SORTEAR NOVAS SKILLS A SEREM ADICIONADAS
        this.skills = new ArrayList<>(); 
        skills.add(new AtaqueRapido());
        skills.add(new ErrorSkill());
        // PLACEHOLDER

        addSkillCards();
    }

    public Player getPlayer(){
        return this.player;
    }
    public boolean setPlayer(Player player){
        try{
            this.player = player;
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Game getGame(){
        return this.game;
    }
    public boolean setGame(Game game){
        try{
            this.game = game;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    // Constructs the skills cards that will be displayed to the player to choose
    private void addSkillCards() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100)); // Added horizontal and vertical gaps

        for(Skill skill : skills){
            add(new SkillCard(skill, this.getPlayer(), this.getGame()));
        }
    }
}
