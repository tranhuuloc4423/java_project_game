package Inventory;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Store {
    GamePanel gp;
    Graphics2D g2;
    BufferedImage panel;
    public boolean storeOn = false;
    public final int plantListSize = 6;
    ArrayList<BufferedImage> boardlist = new ArrayList<>();
    public ArrayList<Item> plantItems = new ArrayList<>();
    public Store(GamePanel gp) {
        this.gp = gp;
        panel = setupImage("storage");
        setupBoard();
        setupPlantItems();
    }

    public void setupPlantItems() {
        for(int i = 1; i <= plantListSize; i++) {
            Item item = new Item("plant_" + i, "/res/plants/plant_" + i + "_4.png", 0, gp);
            plantItems.add(item);
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        drawImage(panel, gp.tileSize, gp.tileSize);
        drawBoard();
        drawPlantItems();
    }

    public void drawImage(BufferedImage image, int x, int y) {
        g2.drawImage(image, x, y, null);
    }

    public void drawBoard() {
        int initX = gp.tileSize * 2;
        int initY = gp.tileSize;
        int x,y;
        for(int i = 0; i < boardlist.size(); i++) {
            int index = i + 1;

            if(index <= 3) {
                x = index * initX - 22;
                y = initY * 3 + 20;
            } else {
                x = initX * (index - 3) - 22;
                y = initY * 5 + 20;
            }
            drawImage(boardlist.get(i), x, y);
        }
    }

    public void drawPlantItems() {
        int initX = gp.tileSize * 2;
        int initY = gp.tileSize;
        for(int i = 0; i < plantListSize; i++) {
            Item item = plantItems.get(i);
            int index = i + 1;
            if(index <= 3) {
                item.x = index * initX - 6;
                item.y = initY * 3 + 34;
            } else {
                item.x = initX * (index - 3) - 6;
                item.y = initY * 5 + 34;
            }
            item.draw(g2);
            item.drawQuantity(g2);
        }
    }

    public void setupBoard() {
        int numOfBoard = 6;
        for(int i = 0; i < numOfBoard; i++) {
            boardlist.add(setupImage("button_empty"));
        }
    }

    public BufferedImage setupImage(String namePath) {
        BufferedImage image;
        BufferedImage scaleImage = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/menu/" + namePath + ".png"));
            int width = image.getWidth() * gp.scale;
            int height = image.getHeight() * gp.scale;
            scaleImage = UtilityTool.scaleImage(image, width, height);
            image = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaleImage;
    }
}
