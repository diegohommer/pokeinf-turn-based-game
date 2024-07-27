package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class Heal extends Skill {
    // class specific atributes
    private int healPoints;

    // class constants
    private static final String SPRITE_PATH = "assets//cureSkill.png";
    private static final String SKILL_NAME = "Heal";
    private static final int INITIAL_LEVEL = 1;
    private static final int INITIAL_COST = 5;
    private static final int LEVEL_UP_COST = 3;
    private static final double HIT_CHANCE = 1.0;
    private static final int INITIAL_HEAL = 25;
    private static final int LEVEL_UP_HEAL = 5;

    public Heal(){
        super.setName(SKILL_NAME);
        super.setSpritePath(SPRITE_PATH);
        super.setCost(INITIAL_COST);
        super.setHitChance(HIT_CHANCE);
        super.setSkillLevel(INITIAL_LEVEL);
        this.setHealPoints(INITIAL_HEAL);
        this.setType(Skill.Type.HEALING);
        super.setDescription("Heal for " + this.getHealPoints() + " HP");
    }
    
    // getters && setters
    public int getHealPoints() {
        return healPoints;
    }
    public void setHealPoints(int healPoints) {
        int clampedHealPoints = Math.max(1, healPoints);
        this.healPoints = clampedHealPoints;
    }

    // skill methods
    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        int casterSP = casterCharacter.getSkillPoints();
        int skillCost = super.getCost();

        if(casterSP >= skillCost){
            casterCharacter.setSkillPoints(casterSP - skillCost);

            if(this.didItHit()){
                int currentHeal = this.getHealPoints();
                int targetHealth  = casterCharacter.getLife();
    
                casterCharacter.setLife(targetHealth + currentHeal);
                return true;
            }else{
                return false; // Missed heal
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
            int currentHealing = this.getHealPoints();
            int currentCost = this.getCost();

            this.setSkillLevel(currentLevel + 1);
            this.setHealPoints(currentHealing + LEVEL_UP_HEAL);
            this.setCost(currentCost + LEVEL_UP_COST);
            return true;
        }
    }
}
