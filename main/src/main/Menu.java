package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu {
    GamePanel gp;

    public Menu(GamePanel gp) {
        this.gp = gp;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        if(gp.gameState == gp.playState) {
            //
        }
        if(gp.gameState == gp.pauseState) {
            // draw pause image
            if(gp.keyH.escapePressed) {
                drawMenu(g2);
            }
        }
    }

    public void drawMenu(Graphics2D g2) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/ui/Menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = 747; // tileSize = 48
        int height = gp.tileSize * 3;
        int x = (gp.screenWidth - width) / 2; // screenWidth = 1152
        int y = gp.screenHeight - 160; // screenHeight = 768
        g2.drawImage(image, x, y, width, height, null);
    }
}
