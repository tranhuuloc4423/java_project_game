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
    public ArrayList<Item> listItem;

    public ArrayList<BufferedImage> listPlantImage = new ArrayList<BufferedImage>();
    GamePanel gp;

    BufferedImage missionPanel;

    public boolean missionOn = false;

    public Mission(GamePanel gp, ArrayList<Item> listItem) {
        this.gp = gp;
        this.listItem = listItem;
        setupPanel();
        setupPlants();
    }

    public void drawItemMission(Graphics2D g2) {
        for(int i = 0; i < listPlantImage.size(); i++) {
//            g2.drawImage(listPlantImage.get(i), 0 , 0, null);
            drawItem(listPlantImage.get(i), i);
        }

//        for(int i = 0; i < listItem.size(); i++) {
//            listItem.get(i).draw(g2);
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

    public BufferedImage getImage(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/plants/" + imagePath + ".png"));
            int width = image.getWidth() * gp.scale;
            int height = image.getHeight() * gp.scale;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public void setupPlants() {
        for(int i = 0; i < listItem.size(); i++) {
            listPlantImage.add(listItem.get(i).itemimage);
        }
    }

    public void setupPanel() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/res/menu/mission_panel.png"));
            int width = image.getWidth() * gp.scale;
            int height = image.getHeight() * gp.scale;
            missionPanel = UtilityTool.scaleImage(image, width, height);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        drawImage(missionPanel, gp.tileSize * (gp.maxScreenCol) - missionPanel.getWidth() - gp.tileSize, gp.tileSize);
        drawItemMission(g2);
    }

    public void createMissionLevel() {

    }
}
