package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class MultiAttack extends Skill {
    // class specific atributes
    private int damagePerHit;
    private int maxAttacks;

    // class constants
    private static final String SPRITE_PATH = "assets//multiAttackSkill.png";
    private static final String SKILL_NAME = "Multi-Attack";
    private static final int INITIAL_LEVEL = 1;
    private static final int INITIAL_COST = 10;
    private static final int LEVEL_UP_COST = 5;
    private static final double INITIAL_HIT_CHANCE = 0.5;
    private static final double LEVEL_UP_HIT_CHANCE = 0.05;
    private static final int INITIAL_DAMAGE = 15;
    private static final int LEVEL_UP_DAMAGE = 5;
    private static final int INITIAL_MAX_ATACKS = 3;
    private static final int PERCENTAGE = 100;

    public MultiAttack(){
        super.setName(SKILL_NAME);
        super.setSpritePath(SPRITE_PATH);
        super.setCost(INITIAL_COST);
        super.setHitChance(INITIAL_HIT_CHANCE);
        super.setSkillLevel(INITIAL_LEVEL);
        this.setDamagePerHit(INITIAL_DAMAGE);
        this.setMaxAttacks(INITIAL_MAX_ATACKS);
        this.setType(Skill.Type.DAMAGE);
        updateDescription();
    }

    // getters && setters
    public int getDamagePerHit() {
        return damagePerHit;
    }
    public void setDamagePerHit(int damagePerHit) {
        int clampedDamagePerHit = Math.max(1, damagePerHit);
        this.damagePerHit = clampedDamagePerHit;
    }

    public int getMaxAttacks() {
        return maxAttacks;
    }
    public void setMaxAttacks(int maxAttacks) {
        int clampedMaxAttacks = Math.max(1, maxAttacks);
        this.maxAttacks = clampedMaxAttacks;
    }

    // class specific methods
    private int findNumAttacks(){
        int hitTimes = 0;

        for(int i = 0; i < this.getMaxAttacks(); i++){
            if(this.didItHit()) hitTimes++;
        }

        return hitTimes;
    }

    // skill methods
    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        int casterSP = casterCharacter.getSkillPoints();
        int skillCost = super.getCost();

        if(casterSP >= skillCost){
            casterCharacter.setSkillPoints(casterSP - skillCost);
            int hitTimes = findNumAttacks();

            if(hitTimes == 0) {
                return false; // Missed all attacks
            }else{
                for (int i = 0; (i < hitTimes) && (!targetCharacter.isDead()); i++) {
                    int currentDamage = this.getDamagePerHit();
                    int targetShield = targetCharacter.getShield();
                    int targetHealth  = targetCharacter.getLife();
            
                    if(targetShield <= 0){
                        targetHealth -= currentDamage;
                    }else
                        targetShield--;
            
                    targetCharacter.setShield(targetShield);
                    targetCharacter.setLife(targetHealth);
                }
                return true;
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
            int currentLevel = super.getSkillLevel();
            int currentDamage = this.getDamagePerHit();
            int currentCost = super.getCost();
            double currentHitChance = super.getHitChance();

            super.setSkillLevel(currentLevel+ 1);
            this.setDamagePerHit(currentDamage + LEVEL_UP_DAMAGE);
            super.setCost(currentCost + LEVEL_UP_COST);
            super.setHitChance(currentHitChance + LEVEL_UP_HIT_CHANCE);
            updateDescription();
            return true;
        }
    }
    
    @Override
    public void resetLevel()
    {
        skillLevel = INITIAL_LEVEL;
        cost = INITIAL_COST;
        damagePerHit = INITIAL_DAMAGE;
        maxAttacks = INITIAL_MAX_ATACKS;
        hitChance = INITIAL_HIT_CHANCE;
        updateDescription();
    }

    private void updateDescription()
    {
        super.setDescription("Do a sequence of " + this.getMaxAttacks() + 
                             " attacks that hit for " + this.damagePerHit + " damage each, with " + 
                             (int)(this.hitChance * PERCENTAGE) + "% chance to hit each one.");
    }
}
