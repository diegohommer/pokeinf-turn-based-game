package Jogo;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import Jogo.Cena.Cena;
import Jogo.Personagem.Personagem;

public class Jogo extends JFrame {
    public final String GAME_TITLE = "PokeINF";
    public final int WINDOW_HEIGHT = 768;
    public final int WINDOW_WIDTH = 1024;

    public Jogo() {
        setTitle("PokeINF");
        setSize( WINDOW_WIDTH , WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        Cena luigi = new Cena("assets//test_background.jpg", WINDOW_WIDTH, WINDOW_HEIGHT);
        add(luigi);
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.setVisible(true);
    }
}
