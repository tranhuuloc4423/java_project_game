package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

import Object.*;

import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_30, arial_50;
//    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_30 = new Font("Arial", Font.PLAIN, 30);
        arial_50 = new Font("Arial", Font.BOLD, 50);

//        OBJ_Key key = new OBJ_Key(gp);
//        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_30);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState) {
            // do playstate stuff late
            if(gp.inventory == gp.inventoryOn) {
                drawInventory("/res/ui/Menu.png", g2);
                drawSelectItem("/res/ui/SelectMenu.png", g2, gp.selectItem);
            }
        }
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXForCenterText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public int getXForCenterText(String text) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - textLength / 2;
        return x;
    }

    public void drawInventory(String filePath, Graphics2D g2) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = 747; // tileSize = 48
        int height = gp.tileSize * 3;
        int x = (gp.screenWidth - width) / 2; // screenWidth = 1152
        int y = gp.screenHeight - 160; // screenHeight = 768
        g2.drawImage(image, x, y, width, height, null);
    }

    public void drawSelectItem(String filePath, Graphics2D g2, int index) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = gp.tileSize * 2; // tileSize = 48
        int height = gp.tileSize * 2;
        int initX = width * 2 + 22;
        int x = initX + (78 * (index - 1)); // screenWidth = 1152
        int y = gp.screenHeight - 140; // screenHeight = 768
        g2.drawImage(image, x, y, width, height, null);
    }
}
