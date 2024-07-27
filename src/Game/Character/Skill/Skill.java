package Game.Character.Skill;

import java.util.ArrayList;

import Game.Character.Character;

public abstract class Skill {
    protected String spritePath;
    protected String name;
    protected String description;
    protected int cost;
    protected int skillLevel;
    protected double hitChance;

    private final int MAX_LEVEL = 5;

    // class variable
    public static ArrayList<Skill> createdSkills = new ArrayList<>();

    // abstract methods
    public abstract boolean applyEffect(Character casterCharacter, Character targetCharacter);
    public abstract boolean upgradeEffect();

    // getters && setters
    public String getSpritePath(){
        return this.spritePath;
    }
    public boolean setSpritePath(String spritePath){
        if (spritePath != null && !spritePath.isEmpty()) {
            this.spritePath = spritePath;
            return true;
        }
        return false;
    }

    public String getName() {
        return this.name;
    }
    public boolean setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
            return true;
        }
        return false;
    }

    public String getDescription(){
        return this.description;
    }
    public boolean setDescription(String description){
        if (description != null && !description.isEmpty()) {
            this.description = description;
            return true;
        }
        return false;
    }

    public int getSkillLevel(){
        return this.skillLevel;
    }
    public void setSkillLevel(int level){
        int clampedSkillLevel = Math.max(1, Math.min(MAX_LEVEL, level));
        this.skillLevel = clampedSkillLevel;
    }

    public int getCost(){
        return this.cost;
    }
    public void setCost(int newCost){
        int clampedCost = Math.max(0, newCost);
        this.cost = clampedCost;
    }

    public double getHitChance() {
        return hitChance;
    }
    public void setHitChance(double hitChance) {
        double clampedHitChance = Math.max(0.0, Math.min(1.0, hitChance));
        this.hitChance = clampedHitChance;
    }


    public static ArrayList<Skill> getCreatedSkills(){
        return createdSkills;
    }
    public static void addCreatedSkill(Skill newSkill){
        createdSkills.add(newSkill);
    }


    public boolean isMaxLevel(){
        return this.getSkillLevel() == MAX_LEVEL;
    }
    
    public boolean didItHit(){
        double randNum = Math.random(); // Random double between 0.0 and 1.0
        return randNum <= this.getHitChance();
    }

}
