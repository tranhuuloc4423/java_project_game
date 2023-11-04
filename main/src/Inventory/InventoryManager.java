package Inventory;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;

public class InventoryManager {
    GamePanel gp;
    public int selectedItem;

    public int inventory;
    public final int inventoryOn = 1;
    public final int inventoryOff = 2;
    final int inventorySeparate = 78;
    public BufferedImage compare;
    public ArrayList<Item> items = new ArrayList<>();

    public InventoryManager(GamePanel gp) {
        this.gp = gp;
        selectedItem = 1;
        inventory = inventoryOff;
        setupInventory();
    }

    public void setupInventory() {
        Item item1 = new Item("seed1", "/res/plants/seed_1.png", 4);
        Item item2 = new Item("seed2", "/res/plants/seed_2.png", 4);
        Item item3 = new Item("plant_1_", "/res/plants/plant_1_5.png", 4);
        Item item4 = new Item("plant_2_", "/res/plants/plant_2_5.png", 4);
        Item item5 = new Item("waterbottle", "/res/Object/tile000.png", 0);
        Item item6 = new Item("hoe", "/res/Object/tile002.png", 0);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
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

    public BufferedImage drawInventoryBar(Graphics2D g2) {
        System.out.println("inventory");
        BufferedImage image = setupImage("/res/ui/Menu.png");
        int width = getSizeImage(image)[0];
        int height = getSizeImage(image)[1];
        int x = (gp.screenWidth - width) / 2; // screenWidth = 1152
        int y = gp.screenHeight - 160; // screenHeight = 768
        g2.drawImage(image, x, y, width, height, null);
        return image;
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

    public void drawItem(Graphics2D g2, Item item ,int index) {
        BufferedImage image = item.getItemImage();
        int width = getSizeImage(image)[0];
        int height = getSizeImage(image)[1];
        int initX = width * 5 - 2;
        int x = initX + (inventorySeparate * (index - 1));
        int y = gp.screenHeight - 112;
        g2.drawImage(image, x, y, width, height, null);

        int itemCount = item.quantity; // Số lượng item
        if(itemCount == 0) return;
        String countText = String.valueOf(itemCount);
        Font font = new Font("Arial", Font.BOLD, 20);
        g2.setFont(font);
        g2.setColor(Color.DARK_GRAY);
        int cornerX = x + width - 5; // Vị trí X của góc (10 là khoảng cách từ viền)
        int cornerY = y + height + 5; // Vị trí Y của góc (10 là khoảng cách từ viền)
        g2.drawString(countText, cornerX, cornerY);
    }

    public void draw(Graphics2D g2) {
        if(gp.gameState == gp.playState) {
            if(gp.invetoryM.inventory == gp.invetoryM.inventoryOn) {
                compare = drawInventoryBar(g2);
                drawSelectItem(g2, "/res/ui/SelectMenu.png" , selectedItem);
                for(int i = 0 ; i < items.size(); i++) {
                    Item item = items.get(i);
                    drawItem(g2, item, i + 1);
                }
            }
        }
    }

    public void inventoryPressed() {
        System.out.println("inventory pressed");
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
