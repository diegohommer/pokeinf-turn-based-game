package Jogo.Personagem.Habilidade;

import java.util.ArrayList;

import Jogo.Personagem.Personagem;

public abstract class Habilidade {

    protected String spritePath;
    protected String name;
    protected int cost;
    protected int skillLevel;
    protected double hitChance;

    public static ArrayList<Habilidade> createdSkills;

    // Métodos que devem ser criados em todas as habilidades separadamente
    protected abstract boolean applyEffect(Personagem targetPersonagem);
    protected abstract boolean upgradeEffect();

    // Getters e setters importantes
    public int getSkillLevel(){
        return this.skillLevel;
    }
    public void setSkillLevel(int level){
        this.skillLevel = level;
    }
    public int getCost(){
        return this.cost;
    }
    public void setCost(int newCost){
        this.cost = newCost;
    }

    // Métodos de classe
    public static ArrayList<Habilidade> getCreatedSkills(){
        return createdSkills;
    }
    public static void addCreatedSkill(Habilidade newSkill){
        createdSkills.add(newSkill);
    }


    // Função de verificação de level maximo
    public boolean isMaxLevel(){
        if(this.getSkillLevel() == 5){
            return true;
        }else{
            return false;
        }
    }
    
    // Função que verifica se a habilidade acertou o alvo
    public boolean didItHit(){
        double randNum = Math.random();

        if(randNum > (1.0 - this.hitChance)){
            return true;
        }else{
            return false;
        }
    }

}
