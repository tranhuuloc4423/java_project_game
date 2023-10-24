package Entity;

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
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize/ 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/ 2);

        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 24;
        solidArea.height = 24;
        setDefaultValues();
        setPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 15;
        worldY = gp.tileSize * 16;
        speed = 4;
        direction = "down";
    }


    public void pickUpObject(int index) {
        if(index != 999) {

        }
    }

    public void setPlayerImage() {

        try {
            for (int i = 1; i <= spritesNum; i++) {

                // move
                up[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabbit/up_" + i + ".png"));
                down[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabbit/down_" + i + ".png"));
                left[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabbit/left_" + i + ".png"));
                right[i- 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabbit/right_" + i + ".png"));

                // idle
                idleUp[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabbit/idle_up_" + i + ".png"));
                idleDown[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabbit/idle_down_" + i + ".png"));
                idleLeft[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabbit/idle_left_" + i + ".png"));
                idleRight[i- 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabbit/idle_right_" + i + ".png"));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }



    public void update() {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if(keyH.upPressed) {
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
                }
            }
        }

        if(!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
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

    public void interactNPC(int index) {
        if(index != 999) {
            System.out.println("You are hitting an npc!");
        }
    }



    public void draw(Graphics2D g2) {
//        BufferedImage[] sprites = null;
//
//        switch (direction) {
//            case "up":
//                sprites = up;
//                break;
//            case "down":
//                sprites = down;
//                break;
//            case "left":
//                sprites = left;
//                break;
//            case "right":
//                sprites = right;
//                break;
//        }

        if (sprites != null && spriteNum >= 1 && spriteNum <= sprites.length) {
            BufferedImage image = sprites[spriteNum - 1];
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
//    public void draw(Graphics2D g2) {
//
//        BufferedImage image = null;
//        switch (direction) {
//            case "up":
//                if(spriteNum == 1) {
//                    image = up1;
//                }
//                if (spriteNum == 2) {
//                    image = up2;
//                }
//                break;
//            case "down":
//                if(spriteNum == 1) {
//                    image = down1;
//                }
//                if (spriteNum == 2) {
//                    image = down2;
//                }
//                break;
//            case "left":
//                if(spriteNum == 1) {
//                    image = left1;
//                }
//                if (spriteNum == 2) {
//                    image = left2;
//                }
//                break;
//            case "right":
//                if(spriteNum == 1) {
//                    image = right1;
//                }
//                if (spriteNum == 2) {
//                    image = right2;
//                }
//                break;
//        }
//        g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize, null);
//    }
}
