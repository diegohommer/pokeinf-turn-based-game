package Jogo.Personagem;

import java.util.ArrayList;

import Jogo.Personagem.Habilidade.Habilidade;

public class Personagem {
    protected String spritePath;
    protected String nome;
    protected int vida;
    protected int escudo;
    protected int pontosDeHabilidade;
    protected ArrayList<Habilidade> habilidadesAtivas;
    
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public int getEscudo() {
        return escudo;
    }
    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }
    

}
