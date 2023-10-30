package Inventory;

import java.awt.image.BufferedImage;

public class ItemCountable extends Item {

    public int quantity;
    public ItemCountable(String name, BufferedImage image, int quantity) {
         super(name, image);
         this.quantity = quantity;
    }

    public void addQuantity() {
        this.quantity++;
    }

    public void removeQuantity() {
        this.quantity--;
    }
}
