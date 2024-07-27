package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class Heal extends Skill {

    //atributos de efeito
    private int healPoints;

    //constantes
    private final int INITIAL_COST = 5;
    private final int COST_LEVEL_UP = 3;
    private final int INITIAL_HEAL = 25;
    private final int CURE_LEVEL_UP = 5;
    private final double HIT_CHANCE = 0.8;
    private final String SKILL_NAME = "Heal";
    private final String SPRITE_PATH = "assets//heal.jpg";
    private final int INITIAL_LEVEL = 1;


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
    
    public int getHealPoints() {
        return healPoints;
    }

    public void setHealPoints(int healPoints) {
        this.healPoints = healPoints;
    }

    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        int casterSP = casterCharacter.getSkillPoints();
        int skillCost = super.getCost();

        if(casterSP >= skillCost){
            casterCharacter.setSkillPoints(casterSP - skillCost);
            
            int currentHeal = this.getHealPoints();
            int targetLife = casterCharacter.getLife();
            int maxLife = casterCharacter.getMaxLife();

            targetLife = targetLife + currentHeal;
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
        if(Skill.isMaxLevel(this)){
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
