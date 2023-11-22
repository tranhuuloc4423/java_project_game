package Inventory;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            if (inputStream != null) {
                BufferedImage image = ImageIO.read(inputStream);
                int width = image.getWidth() * gp.scale;
                int height = image.getHeight() * gp.scale;
                itemimage = UtilityTool.scaleImage(image, width, height);
            } else {
                throw new IOException("Could not find resource: " + filePath);
            }
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

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public void drawQuantity(Graphics2D g2) {
        String countText = "x" + String.valueOf(quantity);
        Font font = new Font("Arial", Font.BOLD, 20);
        g2.setFont(font);
        g2.setColor(Color.DARK_GRAY);
        int cornerX = x + itemimage.getWidth() - 12;
        int cornerY = y + itemimage.getHeight() + 5;
        g2.drawString(countText, cornerX, cornerY);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.itemimage, x, y, null);
    }
}
