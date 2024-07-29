package Game.Character.Skill;

import Game.Character.Character;

public abstract class Skill {
    protected String spritePath;
    protected String name;
    protected String description;
    protected int cost;
    protected int skillLevel;
    protected double hitChance;

    // Atributo de classe
    private static final int MAX_LEVEL = 5;

    public enum Type
    {
        DAMAGE,
        HEALING,
        REST,
        SHIELD,
        NONE
    }
    protected Type type = Type.NONE;

    // abstract methods
    public abstract boolean applyEffect(Character casterCharacter, Character targetCharacter);
    public abstract boolean upgradeEffect();
    public abstract void resetLevel();

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

    public Type getType()
    {
        return type;
    }
    protected void setType(Type t)
    {
        type = t;
    }

    // Método de classe
    public static boolean isMaxLevel(Skill skill){
        return skill.getSkillLevel() == MAX_LEVEL;
    }

    public boolean didItHit(){
        double randNum = Math.random(); // Random double between 0.0 and 1.0
        return randNum <= this.getHitChance();
    }
}
