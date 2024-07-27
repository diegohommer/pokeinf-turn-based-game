package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class Rest extends Skill{
    // class specific atributes
    private int skillPointsGen;

    // class constants
    private static final String SPRITE_PATH = "assets//shieldUpSkill.png";
    private static final String SKILL_NAME = "Rest";
    private static final int INITIAL_LEVEL = 1;
    private static final int INITIAL_COST = 0;
    private static final double INITIAL_HIT_CHANCE = 1.0;
    private static final int INITIAL_SP_GEN = 10;
    private static final int LEVEL_UP_SP_GEN = 5;

    public Rest(){
        super.setName(SKILL_NAME);
        super.setSpritePath(SPRITE_PATH);
        super.setCost(INITIAL_COST);
        super.setHitChance(INITIAL_HIT_CHANCE);
        super.setSkillLevel(INITIAL_LEVEL);
        this.setSPGen(INITIAL_SP_GEN);
        this.setType(Skill.Type.REST);
        this.description = "Receive " + getSPGen() + " SP";
    }

    // getters && setters
    private void setSPGen(int skillPoints){
        int clampedSPGen = Math.max(1, skillPoints);
        this.skillPointsGen = clampedSPGen;
    }
    private int getSPGen(){
        return this.skillPointsGen;
    }

    // skill methods
    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        int casterSP = casterCharacter.getSkillPoints();
        int skillCost = super.getCost();

        if(casterSP >= skillCost){
            casterCharacter.setSkillPoints(casterSP - skillCost);

            if(this.didItHit()){
                int currentSPGen = this.getSPGen();
    
                casterCharacter.setSkillPoints(casterSP + currentSPGen);
                return true;
            }else{
                return false; // Missed Rest
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
            int currentSPGen = this.getSPGen();

            this.setSkillLevel(currentLevel + 1);
            this.setSPGen(currentSPGen + LEVEL_UP_SP_GEN);
            return true;
        }
    }
}
