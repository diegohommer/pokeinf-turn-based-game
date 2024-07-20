package Game.Character;

import java.util.ArrayList;

import Game.Character.Skill.Skill;

public class Character {
    protected String spritePath;
    protected String name;
    protected int life;
    protected int shield;
    protected int pontosDeHabilidade;
    protected ArrayList<Skill> habilidadesAtivas;
    
    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life;
    }
    public int getShield() {
        return shield;
    }
    public void setShield(int shield) {
        this.shield = shield;
    }
    

}
