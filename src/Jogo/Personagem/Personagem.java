package Jogo.Personagem;

import java.util.ArrayList;

import Jogo.Personagem.Habilidade.Habilidade;

public class Personagem {
    protected String spritePath;
    protected String name;
    protected int life;
    protected int shield;
    protected int pontosDeHabilidade;
    protected ArrayList<Habilidade> habilidadesAtivas;
    
    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life;
    }
    public int getShield() {
        return shield;
    }
    public void setShield(int shield) {
        this.shield = shield;
    }
    

}
