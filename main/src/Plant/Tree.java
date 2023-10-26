package Plant;

import Entity.Entity;
import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tree {

    public BufferedImage[] treeImages; // Mảng các hình ảnh tương ứng với các trạng thái cây
    public int growthTime; // Thời gian cây phát triển (tính bằng mili giây)
    public long startTime; // Thời điểm cây bắt đầu mọc
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    UtilityTool tool = new UtilityTool();

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
}
