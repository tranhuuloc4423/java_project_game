package Entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;

//    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public int spritesNum = 2;
    public BufferedImage[] sprites;
    public BufferedImage[] up = new BufferedImage[spritesNum];
    public BufferedImage[] down = new BufferedImage[spritesNum];
    public BufferedImage[] left = new BufferedImage[spritesNum];
    public BufferedImage[] right = new BufferedImage[spritesNum];

    public BufferedImage[] idleUp = new BufferedImage[spritesNum];
    public BufferedImage[] idleDown = new BufferedImage[spritesNum];
    public BufferedImage[] idleLeft = new BufferedImage[spritesNum];
    public BufferedImage[] idleRight = new BufferedImage[spritesNum];
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0,0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        BufferedImage[] sprites = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ) {

            switch (direction) {
                case "up":
                    sprites = up;
                    break;
                case "down":
                    sprites = down;
                    break;
                case "left":
                    sprites = left;
                    break;
                case "right":
                    sprites = right;
                    break;
            }

            if (sprites != null && spriteNum >= 1 && spriteNum <= sprites.length) {
                BufferedImage image = sprites[spriteNum - 1];
                int size = gp.tileSize * 2;
                g2.drawImage(image, screenX, screenY, size, size, null);
            }
        }
    }


    public void setAction() {

    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if(!collisionOn) {
            switch (direction){
                case "up":
                    worldY -= speed;
                    sprites = up;
                    break;
                case "down":
                    worldY += speed;
                    sprites = down;
                    break;
                case "left":
                    worldX -= speed;
                    sprites = left;
                    break;
                case "right":
                    worldX += speed;
                    sprites = right;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 20) {
            if(spriteNum == 1) {
                spriteNum = 2;
            } else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public BufferedImage setup(String imagePath) {
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = tool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
