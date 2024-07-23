package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class AtaqueMultiplo extends Skill {
    //efeitos de classe requeridos
    private int damagePerHit;
    private int maxAtacks;

    //constantes de controle
    private final int INITIAL_COST = 10;
    private final int COST_LEVEL_UP = 5;
    private final int INITIAL_DAMAGE = 20;
    private final int DAMAGE_LEVEL_UP = 5;
    private final double INITIAL_HIT_CHANCE = 0.5;
    private final double HIT_CHANCE_LEVEL_UP = 0.05;
    private final int INITIAL_MAX_ATACKS = 3;

    public AtaqueMultiplo(){
        this.name="Ataque Múltiplo";
        this.spritePath="assets//multiAttackSkill.png";
        this.cost=INITIAL_COST;
        this.damagePerHit=INITIAL_DAMAGE;
        this.hitChance=INITIAL_HIT_CHANCE;
        this.maxAtacks = INITIAL_MAX_ATACKS;
        this.skillLevel=1;
        this.description = "Realiza " + this.damagePerHit + " de dano para cada ataque, com " + this.hitChance +" de chance de realizar um ataque";
    }

    //getters && setters
    public int getDamagePerHit() {
        return damagePerHit;
    }

    public void setDamagePerHit(int damagePerHit) {
        this.damagePerHit = damagePerHit;
    }

    public int getMaxAtacks() {
        return maxAtacks;
    }

    public void setMaxAtacks(int maxAtacks) {
        this.maxAtacks = maxAtacks;
    }

    //metodos especiais da classe
    private int findNumAtacks(){
        int hitTimes = 0;
        while(this.didItHit() && hitTimes <this.maxAtacks){
            hitTimes = hitTimes + 1;
        }

        return hitTimes;
    }

    //metodos de habilidade
    @Override
    protected boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        int hitTimes = findNumAtacks();

        if(hitTimes == 0){
            return false;
        }else{
            for (int i = 0; i < hitTimes; i++) {
                int danoAtual = this.getDamagePerHit();
                int escudoAlvo = targetCharacter.getShield();
                int vidaAlvo  = targetCharacter.getLife();
    
                escudoAlvo = escudoAlvo - 1;
                if(escudoAlvo < 0){
                    vidaAlvo = vidaAlvo - danoAtual;
                    escudoAlvo = 0;
                    if(vidaAlvo < 0){
                        vidaAlvo = 0;
                    }
                }
    
                targetCharacter.setShield(escudoAlvo);
                targetCharacter.setLife(vidaAlvo);
            }
            return true;
        }

    }

    @Override
    protected boolean upgradeEffect() {
        if(this.isMaxLevel()){
            return false;
        }else {
            int currentLevel = this.getSkillLevel();
            int currentDamage = this.getDamagePerHit();
            int currentCost = this.getCost();
            double currentHitChance = this.getHitChance();

            this.setSkillLevel(currentLevel + 1);
            this.setDamagePerHit(currentDamage + DAMAGE_LEVEL_UP);
            this.setCost(currentCost + COST_LEVEL_UP);
            this.setHitChance(currentHitChance + HIT_CHANCE_LEVEL_UP);
            return true;
        }
    }
    
}
