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

    public ArrayList<Item> listPlant = new ArrayList<Item>();
    GamePanel gp;

    BufferedImage missionPanel;

    ArrayList<Item> mission1 = new ArrayList<>(2);
    ArrayList<Item> mission2 = new ArrayList<>(2);
    ArrayList<Item> mission3 = new ArrayList<>(3);
    ArrayList<Item> mission4 = new ArrayList<>(4);

    public boolean missionOn = false;

    public Mission(GamePanel gp) {
        this.gp = gp;
        missionPanel = setupImage("/res/menu/mission_panel.png");
        setupPlants();
        createMissionLevel();
    }

    public void drawItemMission() {
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
        drawItemMission();
        int[] targets = {4, 6};
        drawMision(mission1,targets);
    }

    public void drawMision(ArrayList<Item> mission, int[] target) {
        int initY = gp.tileSize * 3 - 30;
        int initX = gp.tileSize * gp.maxScreenCol - gp.tileSize - 250;
        boolean pass = true;
        String[] names = new String[mission.size()];
        for(int i = 0; i < mission.size(); i++) {
            names[i] = mission.get(i).name;
            int index = i + 1;
            mission.get(i).x = initX;
            mission.get(i).y = initY + (index * gp.tileSize);
            mission.get(i).draw(g2);
            String countText = ":   " + String.valueOf(mission.get(i).quantity) + " / " + String.valueOf(target[i]);
            Font font = new Font("Arial", Font.BOLD, 32);
            g2.setFont(font);
            g2.setColor(Color.DARK_GRAY);
            int cornerX = mission.get(i).x + mission.get(i).itemimage.getWidth() + 30;
            int cornerY = mission.get(i).y + mission.get(i).itemimage.getHeight() - 12;
            g2.drawString(countText, cornerX, cornerY);
            if (mission.get(i).quantity < target[i]) {
                pass = false;
            }
        }

        if (pass) {
            for(int i = 0; i < gp.store.plantListSize; i++) {
                for (int j = 0; j < names.length; j++) {
                    if (gp.store.plantItems.get(i).name.equals(names[j])) {
                        gp.store.plantItems.get(i).removeQuantity(target[j]);
                    }
                }
            }

            // cho hạt giống mới.
        }
    }

    public void createMissionLevel() {
        if(gp.store != null) {
            for(int i = 0; i < gp.store.plantItems.size(); i++) {
                listPlant.add(gp.store.plantItems.get(i));
            }


            System.out.println(listPlant.size());
            // mission1
            mission1.add(listPlant.get(0));
            mission1.add(listPlant.get(1));

            // mission2
            mission2.add(listPlant.get(2));
            mission2.add(listPlant.get(3));

            // mission3
            mission3.add(listPlant.get(1));
            mission3.add(listPlant.get(2));
            mission3.add(listPlant.get(3));

            // mission1
            mission4.add(listPlant.get(0));
            mission4.add(listPlant.get(1));
            mission4.add(listPlant.get(2));
            mission4.add(listPlant.get(3));
        }
    }
}
