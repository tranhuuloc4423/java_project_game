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
    Graphics2D g2;

    public BufferedImage[] hoeUp = new BufferedImage[spritesNum];
    public BufferedImage[] hoeDown = new BufferedImage[spritesNum];
    public BufferedImage[] hoeLeft = new BufferedImage[spritesNum];
    public BufferedImage[] hoeRight = new BufferedImage[spritesNum];
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
//        worldX = gp.player.worldX - screenX;
//        worldY = gp.player.worldY - screenY;
        landTileX = (worldX / gp.tileSize) + 1;
        landTileY = (worldY / gp.tileSize) + 1;
//        int tileSize = gp.tileSize;
//        int x = landTileX * tileSize;
//        int y = landTileY * tileSize;
//        int width = tileSize;
//        int height = tileSize;
//
//        g2.setColor(Color.BLACK);
//        g2.setStroke(new BasicStroke(2));
//        g2.drawRect(x, y, width, height);
    }

    public void pickUpObject(int index) {
        if(index != 999) {

        }
    }

    public void setPlayerImage() {
//        try {
//
//        } catch(IOException e) {
//            e.printStackTrace();
//        }

        for (int i = 1; i <= spritesNum; i++) {
            // move
//                up[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabit/up_" + i + ".png"));
//                down[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabit/down_" + i + ".png"));
//                left[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabit/left_" + i + ".png"));
//                right[i- 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabit/right_" + i + ".png"));
//
//                // idle
//                idleUp[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabit/idle_up_" + i + ".png"));
//                idleDown[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabit/idle_down_" + i + ".png"));
//                idleLeft[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabit/idle_left_" + i + ".png"));
//                idleRight[i- 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/rabit/idle_right_" + i + ".png"));
//
//                // hoe
//                hoeUp[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/action/t" + i + ".png"));
//                hoeDown[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/action/b" + i + ".png"));
//                hoeLeft[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/action/l" + i + ".png"));
//                hoeRight[i- 1] = ImageIO.read(getClass().getResourceAsStream("/res/Player/action/r" + i + ".png"));

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
            hoeUp[i - 1] = setup("action/t" + i);
            hoeDown[i - 1] = setup("action/b" + i);
            hoeLeft[i - 1] = setup("action/l" + i);
            hoeRight[i- 1] = setup("action/r" + i);
        }
    }

    public BufferedImage setup(String imageName) {
        BufferedImage image = null;
//        int size = gp.tileSize * 3;
        try{
            image =  ImageIO.read(getClass().getResourceAsStream("/res/Player/" + imageName +".png"));
//            image = UtilityTool.scaleImage(image, size, size);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public void drawBorder() {
        gp.aSetter.setObject(landTileX, landTileY);
    }


    public void update() {
        getTilePositionPlayer();
        drawBorder();
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

        if(!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && !keyH.isHoe) {
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

        if(!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && keyH.isHoe) {
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

        spriteCounter++;
        if(spriteCounter > 15) {
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
            int size = gp.tileSize * 3;
            g2.drawImage(image, screenX, screenY, size, size, null);
        }
    }
}
