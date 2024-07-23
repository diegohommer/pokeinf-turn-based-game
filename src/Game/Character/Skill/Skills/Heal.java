package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class Heal extends Skill {

    //atributos de efeito
    private int healPoints;

    //constantes de controle
    private final int INITIAL_COST = 5;
    private final int COST_LEVEL_UP = 3;
    private final int INITIAL_CURE = 25;
    private final int CURE_LEVEL_UP = 5;
    private final double HIT_CHANCE = 0.8;

    public Heal(){
        this.name="Cura";
        this.spritePath="#implementar";
        this.cost=INITIAL_COST;
        this.healPoints=INITIAL_CURE;
        this.hitChance=HIT_CHANCE;
        this.skillLevel=1;
        this.description = "Cura por " + this.healPoints + " de vida";
    }
    
    public int getHealPoints() {
        return healPoints;
    }

    public void setHealPoints(int healPoints) {
        this.healPoints = healPoints;
    }

    @Override
    protected boolean applyEffect(Character targetCharacter) {
        if(this.didItHit()){
            int currentHealing = this.getHealPoints();
            int targetLife  = targetCharacter.getLife();
            int maxLife = targetCharacter.getMaxLife();

            targetLife = targetLife + currentHealing;
            if(targetLife > maxLife){
                targetLife = maxLife;
            }

            targetCharacter.setLife(targetLife);
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected boolean upgradeEffect() {
        if(this.isMaxLevel()){
            return false;
        }else {
            int currentLevel = this.getSkillLevel();
            int currentHealing = this.getHealPoints();
            int currentCost = this.getCost();

            this.setSkillLevel(currentLevel + 1);
            this.setHealPoints(currentHealing + CURE_LEVEL_UP);
            this.setCost(currentCost + COST_LEVEL_UP);
            return true;
        }
    }
    
}
