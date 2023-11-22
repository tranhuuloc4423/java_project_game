package Plant;

import Entity.Entity;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tree {
    GamePanel gp;
    public BufferedImage[] treeImages; // Mảng các hình ảnh tương ứng với các trạng thái cây
    public BufferedImage image;
    public String name;
    public int worldX, worldY;

    private int imageChangeInterval; // Khoảng thời gian thay đổi hình ảnh (tính bằng mili giây)
    private long lastImageChangeTime;
    private int currentImageIndex;


    public Tree(GamePanel gp, BufferedImage[]treeImages, int imageChangeInterval, String name) {
        this.gp = gp;
        this.treeImages = treeImages;
        this.imageChangeInterval = imageChangeInterval;
        this.currentImageIndex = 0;
        this.image = treeImages[currentImageIndex];
        this.lastImageChangeTime = System.currentTimeMillis();
        this.name = name;
    }

    public BufferedImage[] getPlantImages() {
        return treeImages;
    }

    public BufferedImage getCurrentImage() {
        return image;
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastImageChangeTime;

        if(!gp.pod.isSleep) {
            if (elapsedTime >= imageChangeInterval) {
                // Đổi hình ảnh
                if (currentImageIndex < treeImages.length - 1) {
                    currentImageIndex++;
                }
                image = treeImages[currentImageIndex];
                // Cập nhật thời điểm thay đổi hình ảnh cuối cùng
                lastImageChangeTime = currentTime;
            }
        } else {
            image = treeImages[treeImages.length - 1];
        }
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ) {
            if(image.getHeight() == gp.tileSize * 2) {
                g2.drawImage(image, screenX, screenY - gp.tileSize, null);
            } else {
                g2.drawImage(image, screenX, screenY, null);

            }
        }
    }
}
