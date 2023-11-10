package Inventory;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Store {
    GamePanel gp;
    Graphics2D g2;
    BufferedImage panel;
    public boolean storeOn = false;
    ArrayList<BufferedImage> boardlist = new ArrayList<>();
    public Store(GamePanel gp) {
        this.gp = gp;
        panel = setupImage("MissonMenunew");
        setupBoard();
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        drawImage(panel, 0, 0);
        drawBoard();
    }

    public void drawImage(BufferedImage image, int x, int y) {
        g2.drawImage(image, x, y, null);
    }

    public void drawBoard() {
        int x,y;
        for(int i = 0; i < boardlist.size(); i++) {
            int index = i + 1;

            if(index <= 3) {
                x = gp.tileSize * 2 * index - 40;
                y = gp.tileSize * 3;
            } else {
                x = gp.tileSize * 2 * (index - 3) - 40;
                y = gp.tileSize * 5;
            }
            drawImage(boardlist.get(i), x, y);
        }
    }

    public void setupBoard() {
        int numOfBoard = 6;
        for(int i = 0; i < numOfBoard; i++) {
            boardlist.add(setupImage("button_empty"));
        }
    }

    public BufferedImage setupImage(String namePath) {
        BufferedImage image = null;
        BufferedImage scaleImage = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/menu/" + namePath + ".png"));
            int width = image.getWidth() * gp.scale;
            int height = image.getHeight() * gp.scale;
            scaleImage = UtilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaleImage;
    }
}
