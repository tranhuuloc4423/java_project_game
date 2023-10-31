package Inventory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item {
    public String name;
    public String filePath;
    public BufferedImage itemimage;
    public int quantity;
    public Item(String name, String filePath, int quantity) {
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

    public BufferedImage getItemImage() {
        return this.itemimage;
    }

    public void addQuantity() {
        this.quantity++;
    }

    public void removeQuantity() {
        this.quantity--;
    }
}
