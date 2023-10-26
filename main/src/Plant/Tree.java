package Plant;

import Entity.Entity;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tree {

    public BufferedImage[] treeImages; // Mảng các hình ảnh tương ứng với các trạng thái cây
    public int growthTime; // Thời gian cây phát triển (tính bằng mili giây)
    public long startTime; // Thời điểm cây bắt đầu mọc
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
//    UtilityTool tool = new UtilityTool();

    private int imageChangeInterval; // Khoảng thời gian thay đổi hình ảnh (tính bằng mili giây)
    private long lastImageChangeTime;
    private int currentImageIndex;

    public boolean isHarvested;


    public Tree(BufferedImage[]treeImages, int imageChangeInterval) {
        this.treeImages = treeImages;
        this.imageChangeInterval = imageChangeInterval;
        this.currentImageIndex = 0;
        this.image = treeImages[currentImageIndex];
        this.lastImageChangeTime = System.currentTimeMillis();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastImageChangeTime;

        if (elapsedTime >= imageChangeInterval) {
            // Đổi hình ảnh
            if (currentImageIndex < treeImages.length - 1) {
                currentImageIndex++;
            }

            image = treeImages[currentImageIndex];

            // Cập nhật thời điểm thay đổi hình ảnh cuối cùng
            lastImageChangeTime = currentTime;
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
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

//    public BufferedImage setupImage(String fileName) {
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(getClass().getResourceAsStream("/res/plants/" + fileName + ".png"));
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//        return image;
//    }

    public void harvest() {
        isHarvested = true;
    }
}
