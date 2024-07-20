package Game.Scene;

import Game.Game;
import Game.Character.*;
import Game.Character.Skill.*;
import java.util.ArrayList;

public class ChooseSkill extends Scene {
    private ArrayList<Skill> skills;
    private Player player;
    private Game game;

    // Constructor receives the game, the player character of the game and the background sprite path
    public ChooseSkill(Game game, Player player, String spritePath){
        super(spritePath);
        setPlayer(player);
        setGame(game);
        addSkillCards();
    }

    public boolean setPlayer(Player player){
        try{
            this.player = player;
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public boolean setGame(Game game){
        try{
            this.game = game;
            return true;
        } catch (Exception e){
            return false;
        }
    }


    private void addSkillCards() {
    }
}
