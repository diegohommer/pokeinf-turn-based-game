package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class Cura extends Skill {

    //atributos de efeito
    private int healPoints;

    //constantes de controle
    private final int INITIAL_COST = 5;
    private final int COST_LEVEL_UP = 3;
    private final int INITIAL_CURE = 25;
    private final int CURE_LEVEL_UP = 5;
    private final double HIT_CHANCE = 0.8;

    public Cura(){
        this.name="Cura";
        this.spritePath="assets//cureSkill.png";
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
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        if(this.didItHit()){
            int curaAtual = this.getHealPoints();
            int vidaAlvo  = casterCharacter.getLife();
            int vidaMax = casterCharacter.getMaxLife();

            vidaAlvo = vidaAlvo + curaAtual;
            if(vidaAlvo > vidaMax){
                vidaAlvo = vidaMax;
            }

            casterCharacter.setLife(vidaAlvo);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean upgradeEffect() {
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
