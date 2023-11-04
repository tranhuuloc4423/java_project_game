package Entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Animal_Cow extends Entity {

    public Animal_Cow(GamePanel gp) {
        super(gp);
        direction = "idleright";
        speed = 1;
        setImage();
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = 48;
        solidArea.height = 48;
    }

    public void setImage() {
        for (int i = 1; i <= spritesNum; i++) {
            // move
            left[i - 1] = setup("cow_left_" + i);
            right[i - 1] = setup("cow_right_" + i);
            up[i - 1] = setup("cow_left_" + i);
            down[i - 1] = setup("cow_right_" + i);
            idleLeft[i - 1] = setup("cow_idle_left_" + i);
            idleRight[i - 1] = setup("cow_idle_right_" + i);
        }
    }

    public BufferedImage setup(String imageName) {
        BufferedImage image = null;
        int size = gp.tileSize * 2;
        try{
            image =  ImageIO.read(getClass().getResourceAsStream("/res/animals/Cow/" + imageName +".png"));
            image = UtilityTool.scaleImage(image, size, size);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void update() {
        setAction();
        collisionOn = false;
//        gp.cChecker.checkTile(this);

        if(!collisionOn) {
            switch (direction){
                case "up":
                    worldY -= speed;
                    sprites = left;
                    break;
                case "down":
                    worldY += speed;
                    sprites = right;
                    break;
                case "left":
                    worldX -= speed;
                    sprites = left;
                    break;
                case "right":
                    worldX += speed;
                    sprites = right;
                    break;
                case "idleright":
                    sprites = idleRight;
                    break;
                case "idleleft":
                    sprites = idleLeft;
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

    public void setAction() {
        actionLockCounter++;
        if(actionLockCounter == gp.FPS * 2) {
            Random random = new Random();
            int i = random.nextInt(150) + 1;

            if(i >= 0 && i <= 25) {
                direction = "up";
            }

            if(i > 25 && i <= 50) {
                direction = "down";
            }

            if(i > 50 && i <= 75) {
                direction = "left";
            }

            if(i > 75 && i <= 100) {
                direction = "right";
            }

            if(i > 100 && i <= 125) {
                direction = "idleright";
            }
            if(i > 125 && i <= 150) {
                direction = "idleleft";
            }
            actionLockCounter = 0;
        }
    }

    @Override
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
                case "idleright":
                    sprites = idleRight;
                    break;
                case "idleleft":
                    sprites = idleLeft;
                    break;
            }

            if (sprites != null && spriteNum >= 1 && spriteNum <= sprites.length) {
                BufferedImage image = sprites[spriteNum - 1];
                g2.drawImage(image, screenX, screenY, null);
            }
        }
    }
}
