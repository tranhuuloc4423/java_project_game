package main;

import Inventory.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class Mission implements MouseListener {
    Graphics2D g2;

    public ArrayList<Item> listPlant = new ArrayList<>();
    GamePanel gp;

    BufferedImage missionPanel, bar, submitBtn, finishPanel;
    BufferedImage[] submitBtnArr = new BufferedImage[2];
    public boolean missionOn = false;
    public boolean missionSubmit = false;
    public boolean isFinish = false;
    public boolean done = false;



    //    mission 1: 1:10 1 hạt giống 2
    //    mission 2: 1:20 2:10 1 hạt giống 3
    //    mission 3: 2:15 3:10 1 hạt giống 4
    //    mission 4: 2:20 3:15 4:5 1 hạt giống 5
    //    mission 5: 1:30 3:20 4:10 5:10 1 hạt giống 6
    //    mission 6: 1:100 2:75 3:50 4:30 5:20 6:15 hoàn thành game
    ArrayList<MissionLevel> missionList = new ArrayList<>();

    public Mission(GamePanel gp) {
        this.gp = gp;
        setupPanel();
        createMissionList();
        createMissionLevel();
        gp.addMouseListener(this);
    }

    public void createMissionList() {
        MissionLevel mission1 = new MissionLevel(new ArrayList<>(), gp.invetoryM.items.get(1), 4, new int[] {10});
        MissionLevel mission2 = new MissionLevel(new ArrayList<>(), gp.invetoryM.items.get(2), 5 , new int[] {20, 10});
        MissionLevel mission3 = new MissionLevel(new ArrayList<>(), gp.invetoryM.items.get(3), 6, new int[] {15, 10});
        MissionLevel mission4 = new MissionLevel(new ArrayList<>(), gp.invetoryM.items.get(4), 6, new int[] {20, 15, 5});
        MissionLevel mission5 = new MissionLevel(new ArrayList<>(), gp.invetoryM.items.get(5), 4, new int[] {30, 20, 10, 10});
        MissionLevel mission6 = new MissionLevel(new ArrayList<>(), gp.invetoryM.items.get(5), 5, new int[] {90, 70, 50, 30, 20, 10});
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
        BufferedImage image;
        BufferedImage scaledImage = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(imagePath);
            if (inputStream != null) {
                image = ImageIO.read(inputStream);
                int width = image.getWidth() * gp.scale;
                int height = image.getHeight() * gp.scale;
                scaledImage = UtilityTool.scaleImage(image, width, height);
            } else {
                throw new IOException("Could not find resource: " + imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public BufferedImage setupImage(String imagePath, int scale) {
        BufferedImage image;
        BufferedImage scaledImage = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(imagePath);
            if (inputStream != null) {
                image = ImageIO.read(inputStream);
                int width = image.getWidth() * scale;
                int height = image.getHeight() * scale;
                scaledImage = UtilityTool.scaleImage(image, width, height);
            } else {
                throw new IOException("Could not find resource: " + imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void setupPlants() {
        listPlant.addAll(gp.storage.items);
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
        if(!done) {
            if(!isFinish) {
                drawImage(missionPanel, gp.tileSize * (gp.maxScreenCol) - missionPanel.getWidth() - gp.tileSize, gp.tileSize);
                drawImage(submitBtn, gp.tileSize * (gp.maxScreenCol) - missionPanel.getWidth() + 22, gp.tileSize * 7);
                drawMissionLevel(0);
            } else {
                drawCompletedGame(g2);
            }
        }
    }

    public void drawCompletedGame(Graphics2D g2) {
        int screenCenterX = (gp.screenWidth / 2);
        int screenCenterY = (gp.screenHeight / 2);
        int centerX = screenCenterX  - (finishPanel.getWidth() / 2);
        int centerY = screenCenterY - (finishPanel.getHeight() / 2);
        drawImage(finishPanel, centerX, centerY);

        String title = "Congratulations!";
        String text = "You completed";
        String text2 = "the game in";
        String time = gp.gameDay + " d, " + gp.gameHour + " h, " + gp.gameMinute + " m.";
        FontMetrics fontMetrics = g2.getFontMetrics();
        int titleWidth = fontMetrics.stringWidth(title);
        int textWidth = fontMetrics.stringWidth(text);
        int text2Width = fontMetrics.stringWidth(text2);
        int timeWidth = fontMetrics.stringWidth(time);
        int cornerXTitle = screenCenterX - (titleWidth / 2) - 78;
        int cornerXText = screenCenterX - (textWidth / 2) - 50;
        int cornerXText2 = screenCenterX - (text2Width / 2) - 44;
        int cornerXTime = screenCenterX - (timeWidth / 2) - 44;
        int cornerY = screenCenterY - 60;
        drawText(title, cornerXTitle, cornerY);
        drawText(text, cornerXText, cornerY + 50, 26);
        drawText(text2, cornerXText2, cornerY + 100, 26);
        drawText(time, cornerXTime, cornerY + 150, 26);
        int widthBtn = screenCenterX - (submitBtn.getWidth()) + 90;
        int heightBtn = screenCenterY + 110;
        drawImage(submitBtn, widthBtn, heightBtn);
    }

    public void setupPanel() {
        submitBtnArr[0] = setupImage("res/ui/btnSubmit.png", 2);
        submitBtnArr[1] = setupImage("res/ui/btnSubmitActive.png", 2);
        missionPanel = setupImage("res/ui/mission_panel.png");
        finishPanel = setupImage("res/ui/finish.png");
        bar = setupImage("res/ui/bar.png");
        submitBtn = submitBtnArr[0];
    }

    public void drawText(String text, int x, int y) {
        Font font = new Font("Arial", Font.BOLD, 32);
        g2.setFont(font);
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(text, x, y);
    }

    public void drawText(String text, int x, int y, int fontsize) {
        Font font = new Font("Arial", Font.BOLD, fontsize);
        g2.setFont(font);
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(text, x, y);
    }

    public void drawMission(MissionLevel mission) {
        int initX, initY;
        if(mission.items.size() <= 3) {
            initX = gp.tileSize * gp.maxScreenCol - 280;
        } else {
            initX = gp.tileSize * gp.maxScreenCol - 344;
        }
        initY = gp.tileSize * 2 - 20;

        boolean pass = true;
        String[] names = new String[mission.items.size()];
        for(int i = 0; i < mission.items.size(); i++) {
            Item item = mission.items.get(i);
            int[] targets = mission.targets;
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
            int x = item.x + item.itemimage.getWidth();
            int y = item.y + item.itemimage.getHeight() / 2 + 10;
            drawText(text, x, y);
            if (item.quantity < targets[i]) {
                pass = false;
            }
        }
        int rewardX = gp.maxScreenCol * gp.tileSize - mission.reward.itemimage.getWidth() - 246;
        int rewardY = gp.tileSize * 6 - 22;
        drawReward(mission.reward, mission.quantityReward, rewardX, rewardY);

        // nếu đủ target và nhấn nút submit
        if (pass && missionSubmit) { // missionSubmit
            // lấy số lượng trong kho trừ targets
            for(int i = 0; i < gp.storage.size; i++) {
                for (int j = 0; j < names.length; j++) {
                    if (gp.storage.items.get(i).name.equals(names[j])) {
                        gp.storage.items.get(i).removeQuantity(mission.targets[j]);
                    }
                }
            }

            // cho hạt giống mới.
            for(int i = 0; i < gp.invetoryM.items.size(); i++) {
                if(gp.invetoryM.items.get(i).name.equals(mission.reward.name)) {
                    gp.invetoryM.items.get(i).addQuantity(mission.quantityReward);
                }
            }
            mission.isCompleted = true;
        }
    }

    public void drawReward(Item item, int quantity, int x, int y) {
        g2.drawImage(bar, x, y, null);
        item.y = y + 15;
        item.x = x + 34;
        g2.drawImage(item.itemimage, item.x, item.y, null);
        String text = "x " + quantity;
        int cornerX = item.x + item.itemimage.getWidth();
        int cornerY = item.y + item.itemimage.getHeight() / 2 + 10;
        drawText(text, cornerX, cornerY);
    }

    public void drawMissionLevel(int missionIndex) {
        if (missionIndex >= missionList.size()) {
            System.out.println("Mission completed");
            isFinish = true;
            return;
        }

        if (checkCompletedMission(missionIndex)) {
            drawMissionLevel(missionIndex + 1);
        }
    }

    public boolean checkCompletedMission(int index) {
        if(!missionList.get(index).isCompleted) {
            drawMission(missionList.get(index));
        }
        return missionList.get(index).isCompleted;
    }

    public void createMissionLevel() {
        if(gp.storage != null) {
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


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int imageX = gp.tileSize * (gp.maxScreenCol) - missionPanel.getWidth() + 22;
        int imageY = gp.tileSize * 7;
        int width = submitBtn.getWidth() + imageX;
        int height = submitBtn.getHeight() + imageY;
        if (x >= imageX && x <= width && y >= imageY && y <= height && gp.mission.missionOn) {
            submitBtn = submitBtnArr[1];
            missionSubmit = true;
            int delay = 100;
            ActionListener action = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    missionSubmit = false;
                    submitBtn = submitBtnArr[0];
                }
            };
            Timer timer = new Timer(delay, action);
            timer.setRepeats(false);
            timer.start();
            timer.restart();
        }
        int imageX2 = (gp.screenWidth / 2) - 90;
        int imageY2 = (gp.screenHeight / 2) + 110;
        int width2 = submitBtn.getWidth() + imageX2;
        int height2 = submitBtn.getHeight() + imageY2;
        if(x >= imageX2 && x <= width2 && y >= imageY2 && y <= height2 && gp.mission.missionOn) {
            submitBtn = submitBtnArr[1];
            System.out.println("finish: " + isFinish);
            int delay = 100;
            ActionListener action = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    submitBtn = submitBtnArr[0];
                    isFinish = false;
                    done = true;
                }
            };
            Timer timer = new Timer(delay, action);
            timer.setRepeats(false);
            timer.start();
            timer.restart();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


class MissionLevel {
    ArrayList<Item> items;
    Item reward;

    int quantityReward;
    int[] targets;
    boolean isCompleted = false;

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