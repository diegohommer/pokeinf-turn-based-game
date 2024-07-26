package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class MultiAttack extends Skill {
    //efeitos de classe requeridos
    private int damagePerHit;
    private int maxAttacks;

    //constantes de controle
    private final String SPRITE_PATH = "assets//multiAttackSkill.png";
    private final String SKILL_NAME = "Ataque Múltiplo";
    private final int INITIAL_LEVEL = 1;
    private final int INITIAL_COST = 10;
    private final int LEVEL_UP_COST = 5;
    private final int INITIAL_DAMAGE = 20;
    private final int LEVEL_UP_DAMAGE = 5;
    private final double INITIAL_HIT_CHANCE = 0.5;
    private final double LEVEL_UP_HIT_CHANCE = 0.05;
    private final int INITIAL_MAX_ATACKS = 3;
    private final int PERCENTAGE = 100;

    public MultiAttack(){
        super.setName(SKILL_NAME);
        super.setSpritePath(SPRITE_PATH);
        super.setCost(INITIAL_COST);
        super.setHitChance(INITIAL_HIT_CHANCE);
        super.setSkillLevel(INITIAL_LEVEL);
        setDamagePerHit(INITIAL_DAMAGE);
        setMaxAttacks(INITIAL_MAX_ATACKS);
        super.setDescription("Realiza uma sequência de " + this.getMaxAttacks() + 
                             " Ataques de " + this.damagePerHit + " de dano cada, com " + 
                             (this.hitChance * PERCENTAGE) + "% de chance de acertar cada um");
    }

    // getters && setters
    public int getDamagePerHit() {
        return damagePerHit;
    }
    public void setDamagePerHit(int damagePerHit) {
        this.damagePerHit = damagePerHit;
    }

    public int getMaxAttacks() {
        return maxAttacks;
    }

    public void setMaxAttacks(int maxAtacks) {
        this.maxAttacks = maxAtacks;
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
        int hitTimes = findNumAttacks();

        if(hitTimes == 0){
            return false;
        }else{
            for (int i = 0; i < hitTimes; i++) {
                int currentDamage = this.getDamagePerHit();
                int targetShield = targetCharacter.getShield();
                int targetHealth  = targetCharacter.getLife();
    
                if(targetShield <= 0){
                    targetHealth -= currentDamage;
                }else
                    targetShield--;
    
                targetCharacter.setShield(targetShield);
                targetCharacter.setLife(targetHealth);
                System.out.println(targetCharacter.getLife());
                if(targetCharacter.isDead()) break;
            }
            return true;
        }

    }

    @Override
    public boolean upgradeEffect() {
        if(super.isMaxLevel()){
            return false;
        }else {
            int currentLevel = super.getSkillLevel();
            int currentDamage = this.getDamagePerHit();
            int currentCost = super.getCost();
            double currentHitChance = super.getHitChance();

            super.setSkillLevel(currentLevel++);
            this.setDamagePerHit(currentDamage + LEVEL_UP_DAMAGE);
            super.setCost(currentCost + LEVEL_UP_COST);
            super.setHitChance(currentHitChance + LEVEL_UP_HIT_CHANCE);
            return true;
        }
    }
    
}
