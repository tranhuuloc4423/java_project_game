package main;

import Inventory.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Mission {
    Graphics2D g2;

    public ArrayList<BufferedImage> listPlantImage = new ArrayList<BufferedImage>();
    GamePanel gp;

    BufferedImage missionPanel;

    public boolean missionOn = false;

    public Mission(GamePanel gp) {
        this.gp = gp;
        missionPanel = setupImage("/res/menu/mission_panel.png");
        setupPlants();
    }

    public void drawItemMission(Graphics2D g2) {
//        for(int i = 0; i < listPlantImage.size(); i++) {
////            g2.drawImage(listPlantImage.get(i), 0 , 0, null);
//            drawItem(listPlantImage.get(i), i);
//        }
    }

    public void drawItem(BufferedImage image,int index) {
        int initY = gp.tileSize * 3;
        int x = gp.tileSize * gp.maxScreenCol - 300;
        int y = initY + index * gp.tileSize;
        g2.drawImage(image, x, y, null);
    }

    public void drawImage(BufferedImage image, int x, int y) {
        g2.drawImage(image, x, y, null);
    }

    public BufferedImage setupImage(String imagePath) {
        BufferedImage image = null;
        BufferedImage scaleImage = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            int width = image.getWidth() * gp.scale;
            int height = image.getHeight() * gp.scale;
            scaleImage = UtilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaleImage;
    }

    public void setupPlants() {
//        for(int i = 0; i < listItem.size(); i++) {
//            listPlantImage.add(listItem.get(i).itemimage);
//        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        drawImage(missionPanel, gp.tileSize * (gp.maxScreenCol) - missionPanel.getWidth() - gp.tileSize, gp.tileSize);
        drawItemMission(g2);
    }

    public void createMission() {

    }

    public void createMissionLevel(BufferedImage[] images, int[] quantities) {

    }
}
