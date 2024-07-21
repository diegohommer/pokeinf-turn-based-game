package Game.Character;

import java.util.ArrayList;

import Game.Character.Skill.Skill;

public abstract class Character {
    protected String spritePath;
    protected String name;
    protected int life;
    protected int maxLife;
    protected int shield;
    protected int maxShield;
    protected int skillPoints;
    protected int maxSkillPoints;
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
    
    public int getMaxLife() {
        return maxLife;
    }
    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
    public int getMaxShield() {
        return maxShield;
    }
    public void setMaxShield(int maxShield) {
        this.maxShield = maxShield;
    }
    public int getSkillPoints() {
        return skillPoints;
    }
    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }
    public int getMaxSkillPoints() {
        return maxSkillPoints;
    }
    public void setMaxSkillPoints(int maxSkillPoints) {
        this.maxSkillPoints = maxSkillPoints;
    }
    public abstract Skill selectSkill(int selectedSkill);

}
