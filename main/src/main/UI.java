package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    public boolean isMission = false;
    public boolean isMissionDrawn = false;
    double playTime;

    BufferedImage mission;


    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_30 = new Font("Arial", Font.PLAIN, 30);
        arial_50 = new Font("Arial", Font.BOLD, 50);

//        OBJ_Key key = new OBJ_Key(gp);
//        keyImage = key.image;
        setupImages();
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if(gp.gameState == gp.playState) {

        }

        if(isMission) {
            drawImage(mission, gp.tileSize * (gp.maxScreenCol - 3) - mission.getWidth(), gp.tileSize);
        }
    }

    public void drawImage(BufferedImage image, int x, int y) {
        int width = image.getWidth() * gp.scale;
        int height = image.getHeight() * gp.scale;
        g2.drawImage(image, x, y, width, height, null);
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

    void setupImages() {
        try {
            mission = ImageIO.read(getClass().getResourceAsStream("/res/menu/setting1.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}
