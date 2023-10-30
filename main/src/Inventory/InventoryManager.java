package Inventory;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class InventoryManager {
    GamePanel gp;
    public int selectedItem;

    public int inventory;
    public final int inventoryOn = 1;
    public final int inventoryOff = 2;
    final int inventorySeparate = 78;
    public InventoryManager(GamePanel gp) {
        this.gp = gp;
        selectedItem = 1;
        inventory = inventoryOff;
    }

    public void setSelectedItem(int index) {
        selectedItem = index;
    }

    public BufferedImage setupImage(String filePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public int[] getSizeImage(BufferedImage image) {
        int width = image.getWidth() * gp.scale;
        int height = image.getHeight() * gp.scale;
        int[] size = new int[2];
        size[0] = width;
        size[1] = height;
        return size;
    }

    public void drawInventoryBar(Graphics2D g2) {
        BufferedImage image = setupImage("/res/ui/Menu.png");
        int width = getSizeImage(image)[0];
        int height = getSizeImage(image)[1];
        int x = (gp.screenWidth - width) / 2; // screenWidth = 1152
        int y = gp.screenHeight - 160; // screenHeight = 768
        g2.drawImage(image, x, y, width, height, null);
    }

    public void drawSelectItem(Graphics2D g2, String filePath,int index) {
        BufferedImage image = setupImage(filePath);
        int width = getSizeImage(image)[0];
        int height = getSizeImage(image)[1];
        int initX = width * 2 + 22;
        int x = initX + (inventorySeparate * (index - 1));
        int y = gp.screenHeight - 140;
        g2.drawImage(image, x, y, width, height, null);
    }

    public void drawItem(Graphics2D g2, String filePath,int index) {
        BufferedImage image = setupImage(filePath);
        int width = getSizeImage(image)[0];
        int height = getSizeImage(image)[1];
        int initX = width * 5 - 2;
        int x = initX + (inventorySeparate * (index - 1));
        int y = gp.screenHeight - 112;
        g2.drawImage(image, x, y, width, height, null);
    }

    public void draw(Graphics2D g2) {
        if(gp.gameState == gp.playState) {
            if(gp.invetoryM.inventory == gp.invetoryM.inventoryOn) {
                drawInventoryBar(g2);
                drawSelectItem(g2, "/res/ui/SelectMenu.png" , selectedItem);
                drawItem(g2, "/res/plants/seed_1.png", 1);
                drawItem(g2, "/res/plants/seed_2.png", 2);
                drawItem(g2, "/res/Object/tile000.png", 8);
                drawItem( g2,"/res/Object/tile002.png", 9);
            }
        }
    }

    public void inventoryPressed() {
        if(gp.keyH.inventoryPressed) {
            if(inventory == inventoryOn) {
                inventory = inventoryOff;
            } else  if(inventory == inventoryOff) {
                inventory = inventoryOn;
            }
        }
    }

    public void update() {
        if(gp.keyH.btn1Pressed) {
            setSelectedItem(1);
        }
        if(gp.keyH.btn2Pressed) {
            setSelectedItem(2);
        }
        if(gp.keyH.btn3Pressed) {
            setSelectedItem(3);

        }
        if(gp.keyH.btn4Pressed) {
            setSelectedItem(4);

        }
        if(gp.keyH.btn5Pressed) {
            setSelectedItem(5);

        }
        if(gp.keyH.btn6Pressed) {
            setSelectedItem(6);

        }
        if(gp.keyH.btn7Pressed) {
            setSelectedItem(7);

        }
        if(gp.keyH.btn8Pressed) {
            setSelectedItem(8);

        }
        if(gp.keyH.btn9Pressed) {
            setSelectedItem(9);

        }
    }
}
