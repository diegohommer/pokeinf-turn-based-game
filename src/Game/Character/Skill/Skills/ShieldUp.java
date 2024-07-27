package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class ShieldUp extends Skill{
    // class specific atributes
    private int shieldPoints;

    // class constants
    private static final String SPRITE_PATH = "assets//shieldUpSkill.png";
    private static final String SKILL_NAME = "Shield-Up";
    private static final int INITIAL_LEVEL = 1;
    private static final int INITIAL_COST = 10;
    private static final int LEVEL_UP_COST = 5;
    private static final double INITIAL_HIT_CHANCE = 1.0;
    private static final int INITIAL_SHIELD_GEN = 1;

    public ShieldUp(){
        super.setName(SKILL_NAME);
        super.setSpritePath(SPRITE_PATH);
        super.setCost(INITIAL_COST);
        super.setHitChance(INITIAL_HIT_CHANCE);
        super.setSkillLevel(INITIAL_LEVEL);
        super.setType(Skill.Type.SHIELD);
        this.setShieldGen(INITIAL_SHIELD_GEN);
        this.description = "Receive " + getShieldGen() + " shield(s)";
    }

    // getters && setters
    private void setShieldGen(int shieldPoints){
        int clampedShieldPoints = Math.max(1, shieldPoints);
        this.shieldPoints = clampedShieldPoints;
    }
    private int getShieldGen(){
        return this.shieldPoints;
    }

    // skill methods
    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        int casterSP = casterCharacter.getSkillPoints();
        int skillCost = super.getCost();

        if(casterSP >= skillCost){
            casterCharacter.setSkillPoints(casterSP - skillCost);

            if(this.didItHit()){
                int currentShieldGen = this.getShieldGen();
                int targetShield  = casterCharacter.getShield();
    
                casterCharacter.setShield(targetShield + currentShieldGen);
                return true;
            }else{
                return false; // Missed ShieldUp
            }
        }else{
            return false; // Insufficient SP
        }
    }

    @Override
    public boolean upgradeEffect() {
        if(Skill.isMaxLevel(this)){
            return false;
        }else {
            int currentLevel = this.getSkillLevel();
            int currentShieldGen = this.getShieldGen();
            int currentCost = this.getCost();

            this.setSkillLevel(currentLevel + 1);
            this.setShieldGen(currentShieldGen + 1);
            this.setCost(currentCost + LEVEL_UP_COST);
            return true;
        }
    }
}
