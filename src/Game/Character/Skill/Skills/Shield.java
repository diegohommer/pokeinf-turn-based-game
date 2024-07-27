package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class Shield extends Skill {

    //atributos de efeito
    private int shieldPoints;

    //constantes de controle
    private final int INITIAL_COST = 5;
    private final int COST_LEVEL_UP = 3;
    private final int INITIAL_SHIELD = 1;
    private final int SHIELD_LEVEL_UP = 1;
    private final double HIT_CHANCE = 0.8;

    public Shield(){
        this.name="Escudo";
        this.spritePath="assets//shieldSkill.png";
        this.cost=INITIAL_COST;
        this.shieldPoints=INITIAL_SHIELD;
        this.hitChance=HIT_CHANCE;
        this.skillLevel=1;
        this.description = "Aumenta escudo por " + this.shieldPoints;
    }
    
    public int getShieldPoints() {
        return shieldPoints;
    }

    public void setShieldPoints(int shieldPoints) {
        this.shieldPoints = shieldPoints;
    }

    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        if(this.didItHit()){
            int currentShield = this.getShieldPoints();
            int targetLife = casterCharacter.getShield();
            int maxLife = casterCharacter.getMaxShield();

            targetLife = targetLife + currentShield;
            if(targetLife > maxLife){
                targetLife = maxLife;
            }

            casterCharacter.setLife(targetLife);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean upgradeEffect() {
        if(this.isMaxLevel()){
            return false;
        }else {
            int currentLevel = this.getSkillLevel();
            int currentShield = this.getShieldPoints();
            int currentCost = this.getCost();

            this.setSkillLevel(currentLevel + 1);
            this.setShieldPoints(currentShield + SHIELD_LEVEL_UP);
            this.setCost(currentCost + COST_LEVEL_UP);
            return true;
        }
    }
}
