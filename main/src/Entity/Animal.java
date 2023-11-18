package Entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Animal extends Entity {

    public Animal(GamePanel gp) {
        super(gp);
        direction = "idleright";
        speed = 1;
        setImage();
    }

    public void setImage() {
    }

    @Override
    public void update() {
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ) {
            if(gp.gameState != gp.pauseState) {
                setAction();
                collisionOn = false;
                gp.cChecker.checkTile(this);
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
        }
    }

    public void setAction() {

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
