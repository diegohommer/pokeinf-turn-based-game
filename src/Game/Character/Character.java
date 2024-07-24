package Game.Character;

import java.util.ArrayList;
import java.util.Arrays;

import Game.Character.Skill.Skill;
import Game.Character.Skill.Skills.ErrorSkill;

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
    
    Character(String spritePath, String name, int life, 
    int maxShield, int skillPoints, ArrayList<Skill> activeSkills)
    {
        this.spritePath = spritePath;
        this.name = name;
        this.life = life;
        this.maxLife = life;
        this.shield = 0;
        this.maxShield = maxShield;
        this.skillPoints = skillPoints;
        this.maxSkillPoints = skillPoints;
        this.activeSkills = activeSkills;
    }

    public String getName()
    {
        return name;
    }
    public String getSpritePath()
    {
        return spritePath;
    }
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
    public Skill selectSkill(int selectedSkill){
        if(0 > selectedSkill || selectedSkill >= activeSkills.size()){
            return new ErrorSkill();
        }else{
            return activeSkills.get(selectedSkill);
        }
    }


}
