package Game.Character.Skill;

import java.util.ArrayList;

import Game.Character.Character;

public abstract class Skill {

    protected String spritePath;
    protected String name;
    protected int cost;
    protected int skillLevel;
    protected double hitChance;
    protected String description;

    public static ArrayList<Skill> createdSkills = new ArrayList<>();

    // Métodos que devem ser criados em todas as habilidades separadamente
    protected abstract boolean applyEffect(Character targetPersonagem);
    protected abstract boolean upgradeEffect();

    // Getters e setters importantes
    public String getName() {
        return name;
    }
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
    public String getDescription(){
        return this.description;
    }
    public boolean setDescription(String description){
        try{
            this.description = description;
            return true;
        } catch(Exception e){
            return false;
        }
    }

    // Métodos de classe
    public static ArrayList<Skill> getCreatedSkills(){
        return createdSkills;
    }
    public static void addCreatedSkill(Skill newSkill){
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
