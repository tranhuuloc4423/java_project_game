package Entity;

import main.GamePanel;

import java.awt.image.BufferedImage;

public class Tree extends Entity {

    public BufferedImage[] treeImages; // Mảng các hình ảnh tương ứng với các trạng thái cây
    public int growthTime; // Thời gian cây phát triển (tính bằng mili giây)
    public long startTime; // Thời điểm cây bắt đầu mọc
    public Tree(GamePanel gp) {
        super(gp);
        this.treeImages = treeImages;
        this.growthTime = growthTime;
        this.startTime = System.currentTimeMillis();
    }


}
