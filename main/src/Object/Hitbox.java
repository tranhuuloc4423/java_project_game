package Object;
import java.awt.*;


public class Hitbox {
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public int worldX, worldY;

    public Hitbox(int x, int y, int width, int height) {
        this.solidArea.x = x;
        this.solidArea.y = y;
        this.solidArea.width = width;
        this.solidArea.height = height;
    }
}
