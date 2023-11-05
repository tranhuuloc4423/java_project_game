package Inventory;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item {
    public String name;
    public String filePath;
    public BufferedImage itemimage;
    public int x,y;
    public int quantity;
    GamePanel gp;
    public Item(String name, String filePath, int quantity, GamePanel gp) {
        this.gp = gp;
        this.name = name;
        this.filePath = filePath;
        this.quantity = quantity;
        drawImage(this.filePath);
    }

    public void drawImage(String filePath) {
        try {
            itemimage = ImageIO.read(getClass().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getItemImage() {
        return this.itemimage;
    }

    public void addQuantity() {
        this.quantity++;
    }

    public void removeQuantity() {
        this.quantity--;
    }

    public void draw(Graphics2D g2) {
        int width = this.itemimage.getWidth() * gp.scale;
        int height = this.itemimage.getHeight() * gp.scale;
        g2.drawImage(this.itemimage, x, y, width, height, null);
    }
}
