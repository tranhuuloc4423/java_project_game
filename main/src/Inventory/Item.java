package Inventory;

import java.awt.image.BufferedImage;

public class Item {
    public String name;
    public BufferedImage image;

    public Item(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }
}
