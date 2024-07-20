package Game.Character;

import java.util.ArrayList;

import Game.Character.Skill.Skill;

public abstract class Character {
    protected String spritePath;
    protected String name;
    protected int life;
    protected int shield;
    protected int skillPoints;
    protected ArrayList<Skill> activeSkills;
    

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
    public ArrayList<Skill> getActiveSkills() {
        return activeSkills;
    }
    public void setActiveSkills(ArrayList<Skill> activeSkills) {
        this.activeSkills = activeSkills;
    }
    
    public abstract Skill selectSkill(int selectedSkill);

}
