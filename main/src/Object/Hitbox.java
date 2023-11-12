package Object;
import main.GamePanel;

import java.awt.*;


public class Hitbox {
    public Rectangle solidArea = new Rectangle(0 ,0 ,0 ,0);

    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public int worldX, worldY;

    public Hitbox(int width, int height) {
        this.solidArea.width = width;
        this.solidArea.height = height;
        System.out.println("x: "+ solidArea.x);
        System.out.println("y: "+ solidArea.y);
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ) {
            g2.setColor(Color.BLACK);
            g2.drawRect(screenX, screenY, solidArea.width, solidArea.height);
        }
    }
}
