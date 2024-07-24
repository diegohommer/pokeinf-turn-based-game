package Game.Scene;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Scene extends JPanel{
    protected static final int WINDOW_WIDTH = 1024;
    protected static final int WINDOW_HEIGHT = 768;
    private Image backgroundSprite;


    // Construtor de Cena que recebe o path para o arquivo de imagem da sprite de background e altura/largura da janela
    public Scene(String spritePath) {
        try {
            backgroundSprite = ImageIO.read(new File(spritePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função que é chamada automaticamente cada vez que a cena precisa ser redesenhada
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundSprite != null) {
            g.drawImage(backgroundSprite, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        }
    }
}
