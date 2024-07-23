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
    private final double HIT_CHANCE = 0.7;


    public QuickAttack(){
        this.name="Ataque Rápido";
        this.spritePath="#implementar";
        this.cost=INITIAL_COST;
        this.damage=INITIAL_DAMAGE;
        this.hitChance=HIT_CHANCE;
        this.skillLevel=1;
        this.description = "Ataca por " + this.damage + " de dano";
    }

    // Getters e setters importantes para a classe
    private void setDamage(int value){
        this.damage = value;
    }
    private int getDamage(){
        return this.damage;
    }

    @Override
    // Não melhora o efeito caso ele estiver no nivel maximo, senão, adiciona 5 ao dano causado
    public boolean upgradeEffect(){
        if(this.isMaxLevel()){
            return false;
        }else {
            int currentLevel = this.getSkillLevel();
            int currentDamage = this.getDamage();
            int currentCost = this.getCost();

            this.setSkillLevel(currentLevel + 1);
            this.setDamage(currentDamage + DAMAGE_LEVEL_UP);
            this.setCost(currentCost + COST_LEVEL_UP);
            return true;
        }
    }

    @Override
    public boolean applyEffect(Character target){
        if(this.didItHit()){
            int currentDamage = this.getDamage();
            int targetShield = target.getShield();
            int targetLife  = target.getLife();

            targetShield = targetShield - currentDamage;
            if(targetShield < 0){
                currentDamage = currentDamage + targetShield;
                targetLife = targetLife - currentDamage;
                targetShield = 0;
            }

            target.setShield(targetShield);
            target.setLife(targetLife);
            return true;
        }else{
            return false;
        }
    }

}
