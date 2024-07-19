package Jogo.Personagem.Habilidade.Habilidades;

import Jogo.Personagem.Personagem;
import Jogo.Personagem.Habilidade.Habilidade;

public class AtaqueRapido extends Habilidade {
    //efeito da classe ataquerapido
    private int dano;

    //constantes de controle
    private final int DANO_INICIAL = 20;
    private final int DANO_LEVEL_UP = 5;
    private final int CUSTO_INICIAL = 5;
    private final int CUSTO_LEVEL_UP = 2;
    private final double CHANCE_DE_ACERTO = 0.7;


    public AtaqueRapido(){
        this.nome="Ataque Rápido";
        this.spritePath="#implementar";
        this.custo=CUSTO_INICIAL;
        this.dano=DANO_INICIAL;
        this.chanceDeAcerto=CHANCE_DE_ACERTO;
        this.nivelHabilidade=1;
    }

    //getters e setters importantes para a classe
    private void setDano(int valor){
        this.dano = valor;
    }
    private int getDano(){
        return this.dano;
    }

    @Override
    //não melhora o efeito caso ele estiver no nivel maximo, senão, adiciona 5 ao dano causado
    public boolean melhoraEfeito(){
        if(this.isMaxLevel()){
            return false;
        }else{
            int nivelAtual = this.getNivelHabilidade();
            int danoAtual = this.getDano();
            int custoAtual = this.getCusto();

            this.setNivelHabilidade( nivelAtual + 1);
            this.setDano(danoAtual + DANO_LEVEL_UP);
            this.setCusto(custoAtual + CUSTO_LEVEL_UP);
            return true;
        }

    }

    @Override
    public boolean aplicaEfeito(Personagem target){
        if(this.didItHit()){
            int danoAtual = this.getDano();
            int escudoAlvo = target.getEscudo();
            int vidaAlvo  = target.getVida();

            escudoAlvo = escudoAlvo - danoAtual;
            if(escudoAlvo < 0){
                danoAtual = danoAtual + escudoAlvo;
                vidaAlvo = vidaAlvo - danoAtual;
                escudoAlvo = 0;
            }

            target.setEscudo(escudoAlvo);
            target.setVida(vidaAlvo);
            return true;
        }else{
            return false;
        }
    }

}
