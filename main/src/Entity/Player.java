package Entity;

import Tile.Tile;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX, screenY;
    public int landTileX, landTileY;

    public BufferedImage[] hoeUp = new BufferedImage[spritesNum];
    public BufferedImage[] hoeDown = new BufferedImage[spritesNum];
    public BufferedImage[] hoeLeft = new BufferedImage[spritesNum];
    public BufferedImage[] hoeRight = new BufferedImage[spritesNum];

    public BufferedImage[] waterUp = new BufferedImage[spritesNum];
    public BufferedImage[] waterDown = new BufferedImage[spritesNum];
    public BufferedImage[] waterLeft = new BufferedImage[spritesNum];
    public BufferedImage[] waterRight = new BufferedImage[spritesNum];
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize/ 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/ 2);

        solidArea = new Rectangle();
        solidArea.x = 64;
        solidArea.y = 64;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 64;
        solidArea.height = 64;
        setDefaultValues();
        setPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 15;
        worldY = gp.tileSize * 16;
        speed = 4;
        direction = "down";
    }

    public void getTilePositionPlayer() {
        landTileX = (worldX / gp.tileSize) + 1;
        landTileY = (worldY / gp.tileSize) + 1;
    }

    public void pickUpObject(int index) {
        if(index != 999) {

        }
    }

    public void setPlayerImage() {
        System.out.println("player image");
        for (int i = 1; i <= spritesNum; i++) {
            // move
            up[i - 1] = setup("rabit/up_" + i);
            down[i - 1] = setup("rabit/down_" + i);
            left[i - 1] = setup("rabit/left_" + i);
            right[i- 1] = setup("rabit/right_" + i);

            // idle
            idleUp[i - 1] = setup("rabit/idle_up_" + i);
            idleDown[i - 1] = setup("rabit/idle_down_" + i);
            idleLeft[i - 1] = setup("rabit/idle_left_" + i);
            idleRight[i- 1] = setup("rabit/idle_right_" + i);

            // hoe
            hoeUp[i - 1] = setup("action/hoe_up_" + i);
            hoeDown[i - 1] = setup("action/hoe_down_" + i);
            hoeLeft[i - 1] = setup("action/hoe_left_" + i);
            hoeRight[i- 1] = setup("action/hoe_right_" + i);

            // watering
            waterUp[i - 1] = setup("action/water_up_" + i);
            waterDown[i - 1] = setup("action/water_down_" + i);
            waterLeft[i - 1] = setup("action/water_left_" + i);
            waterRight[i- 1] = setup("action/water_right_" + i);
        }
    }

    public BufferedImage setup(String imageName) {
        BufferedImage image = null;
        int size = gp.tileSize * 3;
        try{
            image =  ImageIO.read(getClass().getResourceAsStream("/res/Player/" + imageName +".png"));
            image = UtilityTool.scaleImage(image, size, size);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void drawBorder() {
        gp.aSetter.setObject(landTileX, landTileY);
    }

    @Override
    public void update() {
        getTilePositionPlayer();
        drawBorder();
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if(keyH.upPressed && keyH.rightPressed) {
                direction = "upright";
            }else if(keyH.upPressed && keyH.leftPressed) {
                direction = "upleft";
            }else if(keyH.downPressed && keyH.rightPressed) {
                direction = "downright";
            }else if(keyH.downPressed && keyH.leftPressed) {
                direction = "downleft";
            } else if(keyH.upPressed) {
                direction = "up";
            } else if(keyH.downPressed) {
                direction = "down";
            } else if(keyH.leftPressed) {
                direction = "left";
            } else if(keyH.rightPressed) {
                direction = "right";
            }


            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // IF COLLISON IS FALSE, PLAYER CAN MOVE
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
                    case "upleft":
                        worldY -= speed - 1;
                        worldX -= speed - 1;
                        sprites = left;
                        break;
                    case "upright":
                        worldY -= speed - 1;
                        worldX += speed - 1;
                        sprites = right;
                        break;
                    case "downleft":
                        worldX -= speed -1;
                        worldY += speed - 1;
                        sprites = left;
                        break;
                    case "downright":
                        worldX += speed - 1;
                        worldY += speed - 1;
                        sprites = right;
                        break;
                }
            }
        }

        if(!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && !keyH.btn9Pressed && !keyH.btn8Pressed) {
            switch (direction) {
                case "up":
                    sprites = idleUp;
                    break;
                case "down":
                    sprites = idleDown;
                    break;
                case "left":
                    sprites = idleLeft;
                    break;
                case "right":
                    sprites = idleRight;
                    break;
            }
        }

        if(!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && keyH.btn9Pressed) {
            switch (direction) {
                case "up":
                    sprites = hoeUp;
                    break;
                case "down":
                    sprites = hoeDown;
                    break;
                case "left":
                    sprites = hoeLeft;
                    break;
                case "right":
                    sprites = hoeRight;
                    break;
            }
        }

        if(!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && keyH.btn8Pressed) {
            switch (direction) {
                case "up":
                    sprites = waterUp;
                    break;
                case "down":
                    sprites = waterDown;
                    break;
                case "left":
                    sprites = waterLeft;
                    break;
                case "right":
                    sprites = waterRight;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 30) {
            if(spriteNum == 1) {
                spriteNum = 2;
            } else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void interactNPC(int index) {
        if(index != 999) {
            System.out.println("You are hitting an npc!");
        }
    }

    public void draw(Graphics2D g2) {
        if (sprites != null && spriteNum >= 1 && spriteNum <= sprites.length) {
            BufferedImage image = sprites[spriteNum - 1];
//            int size = gp.tileSize * 3;
            g2.drawImage(image, screenX, screenY, null);
        }
    }
}
