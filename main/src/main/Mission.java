package main;

import Inventory.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Mission {
    Graphics2D g2;

    public ArrayList<Item> listPlant = new ArrayList<Item>();
    GamePanel gp;

    BufferedImage missionPanel;

    public boolean missionOn = false;
    public boolean missionSubmit = false;

    //    mission 1: 1:10 1 hạt giống 2
    //    mission 2: 1:20 2:10 1 hạt giống 3
    //    mission 3: 2:15 3:10 1 hạt giống 4
    //    mission 4: 2:20 3:15 4:5 1 hạt giống 5
    //    mission 5: 1:30 3:20 4:10 5:10 1 hạt giống 6
    //    mission 6: 1:100 2:75 3:50 4:30 5:20 6:15 hoàn thành game
    ArrayList<MissionLevel> missionList = new ArrayList<>();



    public Mission(GamePanel gp) {
        this.gp = gp;
        missionPanel = setupImage("/res/menu/mission_panel.png");
        createMissionList();
        createMissionLevel();
    }

    public void createMissionList() {
        MissionLevel mission1 = new MissionLevel(new ArrayList<Item>(), gp.invetoryM.items.get(1), 4, new int[] {10});
        MissionLevel mission2 = new MissionLevel(new ArrayList<Item>(), gp.invetoryM.items.get(2), 5 , new int[] {20, 10});
        MissionLevel mission3 = new MissionLevel(new ArrayList<Item>(), gp.invetoryM.items.get(3), 6, new int[] {15, 10});
        MissionLevel mission4 = new MissionLevel(new ArrayList<Item>(), gp.invetoryM.items.get(4), 6, new int[] {20, 15, 5});
        MissionLevel mission5 = new MissionLevel(new ArrayList<Item>(), gp.invetoryM.items.get(5), 4, new int[] {30, 20, 10, 10});
        MissionLevel mission6 = new MissionLevel(new ArrayList<Item>(), gp.invetoryM.items.get(5), 5, new int[] {90, 70, 50, 30, 20, 10});
        missionList.add(mission1);
        missionList.add(mission2);
        missionList.add(mission3);
        missionList.add(mission4);
        missionList.add(mission5);
        missionList.add(mission6);
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
    public BufferedImage setupImage(String imagePath, int scale) {
        BufferedImage image = null;
        BufferedImage scaleImage = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            int width = image.getWidth() * scale;
            int height = image.getHeight() * scale;
            scaleImage = UtilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaleImage;
    }

    public void setupPlants() {
        for(int i = 0; i < gp.store.plantItems.size(); i++) {
            listPlant.add(gp.store.plantItems.get(i));
        }
    }

    public void setupMissionLevel() {
        // mission1
        missionList.get(0).addItemToMission(listPlant.get(0));

        // mission2
        missionList.get(1).addItemToMission(listPlant.get(0));
        missionList.get(1).addItemToMission(listPlant.get(1));


        // mission3
        missionList.get(2).addItemToMission(listPlant.get(1));
        missionList.get(2).addItemToMission(listPlant.get(2));

        // mission4
        missionList.get(3).addItemToMission(listPlant.get(1));
        missionList.get(3).addItemToMission(listPlant.get(2));
        missionList.get(3).addItemToMission(listPlant.get(3));

        // mission5
        missionList.get(4).addItemToMission(listPlant.get(0));
        missionList.get(4).addItemToMission(listPlant.get(2));
        missionList.get(4).addItemToMission(listPlant.get(3));
        missionList.get(4).addItemToMission(listPlant.get(4));

        // mission6
        missionList.get(5).addItemToMission(listPlant.get(0));
        missionList.get(5).addItemToMission(listPlant.get(1));
        missionList.get(5).addItemToMission(listPlant.get(2));
        missionList.get(5).addItemToMission(listPlant.get(3));
        missionList.get(5).addItemToMission(listPlant.get(4));
        missionList.get(5).addItemToMission(listPlant.get(5));
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        drawImage(missionPanel, gp.tileSize * (gp.maxScreenCol) - missionPanel.getWidth() - gp.tileSize, gp.tileSize);
        drawSubmitBtn();
        drawMision(missionList.get(0));
    }


    public void drawSubmitBtn() {
        BufferedImage submitBtn = setupImage("/res/menu/btnSubmit.png", 2);
        int x = gp.tileSize * (gp.maxScreenCol) - missionPanel.getWidth() + 22;
        int y = gp.tileSize * 7;
        g2.drawImage(submitBtn, x, y, null);
    }

    public void drawText(Item item, String text) {
        Font font = new Font("Arial", Font.BOLD, 32);
        g2.setFont(font);
        g2.setColor(Color.DARK_GRAY);
        int cornerX = item.x + item.itemimage.getWidth();
        int cornerY = item.y + item.itemimage.getHeight() / 2 + 10;
        g2.drawString(text, cornerX, cornerY);
    }

    public void drawMision(MissionLevel mission) {
        int initY = gp.tileSize * 2 - 20;
        int initX = gp.tileSize * gp.maxScreenCol - 344;
        boolean pass = true;
        String[] names = new String[mission.items.size()];
        for(int i = 0; i < mission.items.size(); i++) {
            Item item = mission.items.get(i);
            int[] targets = mission.targets;
            Item reward = mission.reward;
            names[i] = item.name;

            int index = i + 1;
            if(index <= 3) {
                item.x = initX;
                item.y = initY + (gp.tileSize * index);
            } else {
                int resetIndex = index - 3;
                item.x = initX + 140;
                item.y = initY + (gp.tileSize * resetIndex);
            }

            item.draw(g2);
            String text = String.valueOf(item.quantity) + " / " + String.valueOf(targets[i]);
            drawText(item, text);
            if (item.quantity < targets[i]) {
                pass = false;
            }
        }
        int rewardX = gp.maxScreenCol * gp.tileSize - mission.reward.itemimage.getWidth() - 246;
        int rewardY = gp.tileSize * 6 - 22;
        drawReward(mission.reward, mission.quantityReward, rewardX, rewardY);


        // nếu đủ target và nhấn nút submit
        if (pass && missionSubmit) {
            // lấy số lượng trong kho trừ targets
            for(int i = 0; i < gp.store.plantListSize; i++) {
                for (int j = 0; j < names.length; j++) {
                    if (gp.store.plantItems.get(i).name.equals(names[j])) {
                        gp.store.plantItems.get(i).removeQuantity(mission.targets[i]);
                    }
                }
            }

            // cho hạt giống mới.
            for(int i = 0; i < gp.invetoryM.items.size(); i++) {
                if(gp.invetoryM.items.get(i).name == mission.reward.name) {
                    gp.invetoryM.items.get(i).addQuantity(mission.quantityReward);
                }
            }
        }
    }

    public void drawReward(Item item, int quantity, int x, int y) {
        BufferedImage bar = setupImage("/res/menu/bar.png");
        g2.drawImage(bar, x, y, null);
        int rewardX = x;
        int rewardY = y + 15;
        item.x = rewardX;
        item.y = rewardY;
        g2.drawImage(item.itemimage, rewardX, rewardY, null);
        String text = "x " + String.valueOf(quantity);
        drawText(item, text);
    }

    public void createMissionLevel() {
        if(gp.store != null) {
            setupPlants();
            setupMissionLevel();
            //    mission 1: 1:10 1 hạt giống 2
            //    mission 2: 1:20 2:10 1 hạt giống 3
            //    mission 3: 2:15 3:10 1 hạt giống 4
            //    mission 4: 2:20 3:15 4:5 1 hạt giống 5
            //    mission 5: 1:30 3:20 4:10 5:10 1 hạt giống 6
            //    mission 6: 1:100 2:75 3:50 4:30 5:20 6:15 hoàn thành game
        }
    }
}


class MissionLevel {
    ArrayList<Item> items;
    Item reward;

    int quantityReward;
    int[] targets;

    public MissionLevel(ArrayList<Item> items, Item reward, int quantityReward, int[] targets) {
        this.items = items;
        this.reward = reward;
        this.targets = targets;
        this.quantityReward = quantityReward;
    }

    public void addItemToMission(Item item) {
        this.items.add(item);
    }
}