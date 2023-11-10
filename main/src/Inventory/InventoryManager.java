package Inventory;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;
public class InventoryManager {
    GamePanel gp;
    Graphics2D g2;
    public int selectedItem;

    public boolean inventoryOn = false;
    final int inventorySeparate = 78;
    private boolean isDragging; // Biến kiểm tra xem có đang kéo item hay không
    private int dragItemIndex; // Chỉ số của item đang được kéo
    private int dragOffsetX; // Khoảng cách từ vị trí chuột đến vị trí góc trái trên của item khi bắt đầu kéo
    private int dragOffsetY; // Khoảng cách từ vị trí chuột đến vị trí góc trái trên của item khi bắt đầu kéo
    private boolean itemPositionChanged = false;
//    public Item dragItem;

    private int mouseX;
    private int mouseY;
//    public BufferedImage compare;
    public ArrayList<Item> items = new ArrayList<>();

    public InventoryManager(GamePanel gp) {
        this.gp = gp;
        selectedItem = 1;
        setupInventory();
    }

    public void setItemPositionChanged(boolean changed) {
        itemPositionChanged = changed;
    }

    public void setupInventory() {
        Item item1 = new Item("seed1", "/res/plants/seed_1.png", 4, gp);
        Item item2 = new Item("seed2", "/res/plants/seed_2.png", 4, gp);
        Item item3 = new Item("plant_1_", "/res/plants/plant_1_5.png", 4, gp);
        Item item4 = new Item("plant_2_", "/res/plants/plant_2_5.png", 4, gp);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
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
        BufferedImage image = setupImage("/res/ui/Menu.png");
        int width = getSizeImage(image)[0];
        int height = getSizeImage(image)[1];
        BufferedImage scaleImage = UtilityTool.scaleImage(image, width, height);
        int x = (gp.screenWidth - width) / 2; // screenWidth = 1152
        int y = gp.screenHeight - 160; // screenHeight = 768

        g2.drawImage(scaleImage, x, y, null);
        return image;
    }

    public void drawSelectItem(Graphics2D g2, String filePath,int index) {
        BufferedImage image = setupImage(filePath);
        int width = getSizeImage(image)[0];
        int height = getSizeImage(image)[1];
        BufferedImage scaleImage = UtilityTool.scaleImage(image, width, height);
        int initX = width * 2 + 22;
        int x = initX + (inventorySeparate * (index - 1));
        int y = gp.screenHeight - 140;
        g2.drawImage(scaleImage, x, y, null);
    }

    public void drawItem(Graphics2D g2, Item item ,int index) {
        BufferedImage image = item.getItemImage();
//        BufferedImage scaleImage = UtilityTool.scaleImage(image, width, height);

        int initX = image.getWidth() * 5 - 2;
//        int x = initX + (inventorySeparate * (index - 1));
//        int y = gp.screenHeight - 112;
        item.setX(initX + (inventorySeparate * (index - 1)));
        item.setY(gp.screenHeight - 112);
        item.draw(g2);
//        g2.drawImage(image, x, y, null);

        int itemCount = item.quantity; // Số lượng item
        if(itemCount == 0) return;
        String countText = String.valueOf(itemCount);
        Font font = new Font("Arial", Font.BOLD, 20);
        g2.setFont(font);
        g2.setColor(Color.DARK_GRAY);
        int cornerX = item.x + image.getWidth() - 5; // Vị trí X của góc (10 là khoảng cách từ viền)
        int cornerY = item.y + image.getHeight() + 5; // Vị trí Y của góc (10 là khoảng cách từ viền)
        g2.drawString(countText, cornerX, cornerY);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        drawInventoryBar(g2);
        drawSelectItem(g2, "/res/ui/SelectMenu.png", selectedItem);
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            drawItem(g2, item, i + 1);
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
