package Jogo.Personagem.Habilidade;

import java.util.ArrayList;

import Jogo.Personagem.Personagem;

public abstract class Habilidade {

    protected String spritePath;
    protected String nome;
    protected int custo;
    protected int nivelHabilidade;
    protected double chanceDeAcerto;

    public static ArrayList<Habilidade> habilidadesCriadas;

    //metodos que devem ser criados em todas as habilidades separadamente
    protected abstract boolean aplicaEfeito(Personagem targetPersonagem);
    protected abstract boolean melhoraEfeito();

    //getters e setters importantes
    public int getNivelHabilidade(){
        return this.nivelHabilidade;
    }
    public void setNivelHabilidade(int nivel){
        this.nivelHabilidade = nivel;
    }
    public int getCusto(){
        return this.custo;
    }
    public void setCusto(int newCusto){
        this.custo = newCusto;
    }

    //metodos de classe
    public static ArrayList<Habilidade> consultaHabilidadesCriadas(){
        return habilidadesCriadas;
    }
    public static void adicionaHabilidadeCriada(Habilidade newHabilidade){
        habilidadesCriadas.add(newHabilidade);
    }

    //função de verificação de nivel maximo
    public boolean isMaxLevel(){
        if(this.getNivelHabilidade() == 5){
            return true;
        }else{
            return false;
        }
    }
    
    //função que verifica se a habilidade acertou o alvo
    public boolean didItHit(){
        double rolamento = Math.random();

        if(rolamento > (1.0 - this.chanceDeAcerto)){
            return true;
        }else{
            return false;
        }
    }

}
