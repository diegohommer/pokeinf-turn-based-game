package Game.Scene;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Scene extends JPanel{
    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;
    protected static final int BUTTON_WIDTH = 200;
    protected static final int BUTTON_HEIGHT = 50;
    protected static final int BUTTON_X_OFFSET = 210;
    protected static final int BUTTON_Y_OFFSET = 70;
    private Image backgroundSprite;

    public Scene(String spritePath) {
        try {
            backgroundSprite = ImageIO.read(new File(spritePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    // This function is called everytime the scene needs to be redrawn
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundSprite != null) {
            g.drawImage(backgroundSprite, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        }
    }
}
