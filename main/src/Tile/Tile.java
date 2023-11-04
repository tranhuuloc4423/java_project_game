package Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0,0, 48, 48);
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;

    public Crop crop;

    public Tile(Rectangle solidArea, boolean collision) {
        this.solidArea = solidArea;
        this.collision = collision;
    }
}
