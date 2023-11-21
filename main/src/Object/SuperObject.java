package Object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SuperObject {
    GamePanel gp;
    public BufferedImage image;

    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    int sprites = 2;
    public BufferedImage[] images = new BufferedImage[sprites];

    public SuperObject(GamePanel gp) {
        this.gp = gp;
    }

    public BufferedImage setupImage(String path) {
        BufferedImage image;
        BufferedImage scaledImage = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            int width = image.getWidth() * gp.scale;
            int height = image.getHeight() * gp.scale;
            scaledImage = UtilityTool.scaleImage(image, width, height);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public BufferedImage setupImage(String path, double scale) {
        BufferedImage image;
        BufferedImage scaledImage = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            int width = (int) (image.getWidth() * scale);
            int height = (int) (image.getHeight() * scale);
            scaledImage = UtilityTool.scaleImage(image, width, height);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void update() {

    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ) {
            g2.drawImage(image, screenX, screenY, null);
        }
    }
}
