package Jogo.Personagem.Habilidade.Habilidades;

import Jogo.Personagem.Personagem;
import Jogo.Personagem.Habilidade.Habilidade;

public class AtaqueRapido extends Habilidade {
    //efeito da classe ataquerapido
    private int damage;

    //constantes de controle
    private final int INITIAL_DAMAGE = 20;
    private final int DAMAGE_LEVEL_UP = 5;
    private final int INITIAL_COST = 5;
    private final int COST_LEVEL_UP = 2;
    private final double HIT_CHANCE = 0.7;


    public AtaqueRapido(){
        this.name="Ataque Rápido";
        this.spritePath="#implementar";
        this.cost=INITIAL_COST;
        this.damage=INITIAL_DAMAGE;
        this.hitChance=HIT_CHANCE;
        this.skillLevel=1;
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
    public boolean applyEffect(Personagem target){
        if(this.didItHit()){
            int danoAtual = this.getDamage();
            int escudoAlvo = target.getShield();
            int vidaAlvo  = target.getLife();

            escudoAlvo = escudoAlvo - danoAtual;
            if(escudoAlvo < 0){
                danoAtual = danoAtual + escudoAlvo;
                vidaAlvo = vidaAlvo - danoAtual;
                escudoAlvo = 0;
            }

            target.setShield(escudoAlvo);
            target.setLife(vidaAlvo);
            return true;
        }else{
            return false;
        }
    }

}
