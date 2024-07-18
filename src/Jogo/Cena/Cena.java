package Jogo.Cena;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cena extends JPanel{
    private Image backgroundSprite;
    private ArrayList<JPanel> UIComponents;
    private int window_height, window_width;

    // Construtor de Cena que recebe o path para o arquivo de imagem da sprite de background e altura/largura da janela
    public Cena(String spritePath, int window_width, int window_height) {
        try {
            backgroundSprite = ImageIO.read(new File(spritePath));
            this.window_height = window_height;
            this.window_width = window_width;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função que é chamada automaticamente cada vez que a cena precisa ser redesenhada
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundSprite != null) {
            g.drawImage(backgroundSprite, 0, 0, window_width, window_height, this);
        }
    }
}
