package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class QuickAttack extends Skill {
    //efeito da classe ataquerapido
    private int damage;

    //constantes de controle
    private final int INITIAL_DAMAGE = 20;
    private final int DAMAGE_LEVEL_UP = 5;
    private final int INITIAL_COST = 5;
    private final int COST_LEVEL_UP = 2;
    private final double INITIAL_HIT_CHANCE = 0.7;
    private final String SKILL_NAME = "Quick Attack";
    private final String SPRITE_PATH = "assets//quick_attack.jpg";
    private final int INITIAL_LEVEL = 1;
    private final int PERCENTAGE = 100;


    public QuickAttack(){
        super.setName(SKILL_NAME);
        super.setSpritePath(SPRITE_PATH);
        super.setCost(INITIAL_COST);
        super.setHitChance(INITIAL_HIT_CHANCE);
        super.setSkillLevel(INITIAL_LEVEL);
        this.setDamage(INITIAL_DAMAGE);
        this.setType(Skill.Type.DAMAGE);
        updateDescription();
    }

    public int getDamage(){
        return this.damage;
    }

    // Getters e setters importantes para a classe
    private void setDamage(int value){
        this.damage = value;
    }

    @Override
    // Não melhora o efeito caso ele estiver no nivel maximo, senão, adiciona 5 ao dano causado
    public boolean upgradeEffect(){
        if(Skill.isMaxLevel(this)){
            return false;
        }else {
            int currentLevel = this.getSkillLevel();
            int currentDamage = this.getDamage();
            int currentCost = this.getCost();

            this.setSkillLevel(currentLevel + 1);
            this.setDamage(currentDamage + DAMAGE_LEVEL_UP);
            this.setCost(currentCost + COST_LEVEL_UP);
            updateDescription();
            return true;
        }
    }

    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        int casterSP = casterCharacter.getSkillPoints();
        int skillCost = super.getCost();

        if (casterSP >= skillCost) {
            casterCharacter.setSkillPoints(casterSP - skillCost);
            
            if(this.didItHit()){            
                int currentDamage = this.getDamage();
                int targetShield = targetCharacter.getShield();
                int targetLife = targetCharacter.getLife();

                targetShield = targetShield - 1;
                if (targetShield < 0) {
                    targetLife = targetLife - currentDamage;
                    targetShield = 0;
                    if (targetLife < 0) {
                        targetLife = 0;
                    }
                }

                targetCharacter.setShield(targetShield);
                targetCharacter.setLife(targetLife);
                return true;
            } else {
                return false; // Missed attack
            }
        } else {
            return false; // Insufficient SP
        }
    }

    @Override
    public void resetLevel()
    {
        skillLevel = INITIAL_LEVEL;
        cost = INITIAL_COST;
        damage = INITIAL_DAMAGE;
        hitChance = INITIAL_HIT_CHANCE;
        updateDescription();
    }

    private void updateDescription()
    {
        this.description = "Attack for " + this.damage + " damage with a " + 
        (int)(this.hitChance * PERCENTAGE) + "% chance to hit the target.";
    }
}
