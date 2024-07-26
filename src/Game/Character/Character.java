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
    
    Character(String spritePath, String name, int life, int maxShield, int skillPoints, ArrayList<Skill> activeSkills)
    {
        setName(name);
        setLife(life);
        setMaxLife(life);
        setShield(0);
        setMaxShield(maxShield);
        setSkillPoints(skillPoints);
        setMaxSkillPoints(skillPoints);
        setActiveSkills(activeSkills);
    }

    public String getName(){
        return name;
    }
    public boolean setName(String name){
        try{
            if(name.length() > 0){
                this.name = name;
                return true;
            }else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String getSpritePath(){
        return spritePath;
    }
    public boolean setSpritePath(String spritePath){
        try{
            this.spritePath = spritePath;
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public int getLife() {
        return life;
    }
    public boolean setLife(int life) {
        try{
            if(life > 0){
                this.life = life;
                return true;
            }else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int getShield() {
        return shield;
    }
    public boolean setShield(int shield) {
        try{
            if(shield >= 0){
                this.shield = shield;
                return true;
            }else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Skill> getActiveSkills() {
        return activeSkills;
    }
    public boolean setActiveSkills(ArrayList<Skill> activeSkills) {
        try{
            this.activeSkills = activeSkills;
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public int getMaxLife() {
        return maxLife;
    }
    public boolean setMaxLife(int maxLife) {
        try{
            if(maxLife > 0){
                this.maxLife = maxLife;
                return true;
            }else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int getMaxShield() {
        return maxShield;
    }
    public boolean setMaxShield(int maxShield) {
        try{
            if(maxShield >= 0){
                this.maxShield = maxShield;
                return true;
            }else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int getSkillPoints() {
        return skillPoints;
    }
    public boolean setSkillPoints(int skillPoints) {
        try{
            if(skillPoints >= 0){
                this.skillPoints = skillPoints;
                return true;
            }else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int getMaxSkillPoints() {
        return maxSkillPoints;
    }
    public boolean setMaxSkillPoints(int maxSkillPoints) {
        try{
            if(maxSkillPoints >= 0){
                this.maxSkillPoints = maxSkillPoints;
                return true;
            }else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Skill selectSkill(int selectedSkill){
        if(selectedSkill < 0 || selectedSkill >= activeSkills.size()){
            return new ErrorSkill();
        }else{
            return activeSkills.get(selectedSkill);
        }
    }
    public boolean deleteSkill(int selectedSkill){
        try{
            if(selectedSkill < 0 || selectedSkill >= activeSkills.size()){
                return false;
            }else{
                this.getActiveSkills().remove(selectedSkill);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
