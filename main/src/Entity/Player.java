package Entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import Object.*;

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

    boolean isHoeSE = false;
    boolean isWalkingSE = false;
    boolean isWateringSE = false;
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize/ 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/ 2);

        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 24;
        solidArea.height = 32;
        setDefaultValues();
        setPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 34;
        worldY = gp.tileSize * 33;
        speed = 4;
        direction = "down";
    }

    public void getTilePositionPlayer() {
        landTileX = (worldX / gp.tileSize);
        landTileY = (worldY / gp.tileSize);
    }

    public void interactObject(int index) {
        if(index != 999) {
            switch (index) {
                case 0:
                    break;
            }
        }
    }

    public void interactObject() {

    }

    public void chestHandle(int col, int row) {
        int playerX = gp.player.landTileX;
        int playerY = gp.player.landTileY;
        int radius = 1; // Bán kính 1 ô
        if (Math.abs(playerX - col) <= radius && Math.abs(playerY - row) <= radius) {
            if(gp.obj[0].image == gp.obj[0].images[0]) {
                gp.music[9].playSEOnce();
                gp.obj[0].image = gp.obj[0].images[1];
                gp.store.storeOn = true;
            } else if(gp.obj[0].image == gp.obj[0].images[1]) {
                gp.music[10].playSEOnce();
                gp.obj[0].image = gp.obj[0].images[0];
                gp.store.storeOn = false;
            }
        }

    }
    public void doorHandle(int col, int row) {
        int playerX = gp.player.landTileX;
        int playerY = gp.player.landTileY;
        int radius = 1; // Bán kính 1 ô
        if (Math.abs(playerX - col) <= radius && Math.abs(playerY - row) <= radius || playerX == col && playerY == row - 2) {
            gp.music[4].playSEOnce();
            if(gp.obj[1].image == gp.obj[1].images[0]) {
                gp.obj[1].image = gp.obj[1].images[1];
                gp.obj[1].collision = false;
            } else if(gp.obj[1].image == gp.obj[1].images[1]) {
                gp.music[4].playSEOnce();
                gp.obj[1].image = gp.obj[1].images[0];
                gp.obj[1].collision = true;
            }
        }
    }

    public void interactNPC(int index) {
        if(index != 999) {
            switch (index) {
            }
        }
    }


    public void setPlayerImage() {
        for (int i = 1; i <= spritesNum; i++) {
            // move
            up[i - 1] = setup("move/up_" + i, 1);
            down[i - 1] = setup("move/down_" + i, 1);
            left[i - 1] = setup("move/left_" + i, 1);
            right[i- 1] = setup("move/right_" + i, 1);

            // idle
            idleUp[i - 1] = setup("move/idle_up_" + i, 1);
            idleDown[i - 1] = setup("move/idle_down_" + i, 1);
            idleLeft[i - 1] = setup("move/idle_left_" + i, 1);
            idleRight[i- 1] = setup("move/idle_right_" + i, 1);

            // hoe
            hoeUp[i - 1] = setup("action/hoe_up_" + i, 3);
            hoeDown[i - 1] = setup("action/hoe_down_" + i, 3);
            hoeLeft[i - 1] = setup("action/hoe_left_" + i, 3);
            hoeRight[i- 1] = setup("action/hoe_right_" + i, 3);

            // watering
            waterUp[i - 1] = setup("action/water_up_" + i, 3);
            waterDown[i - 1] = setup("action/water_down_" + i, 3);
            waterLeft[i - 1] = setup("action/water_left_" + i, 3);
            waterRight[i- 1] = setup("action/water_right_" + i, 3);
        }
    }

    public BufferedImage setup(String imageName, int scale) {
        BufferedImage image = null;
        int size = gp.tileSize * scale;
        try{
            image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/" + imageName + ".png")));
            image = UtilityTool.scaleImage(image, size, size);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void drawBorder() {
        gp.aSetter.setBorder(landTileX, landTileY);
    }

    @Override
    public void update() {
        getTilePositionPlayer();
        walkHandle();
        hoeAnimate();
        wateringAnimate();
        idleAnimate();
        handleSE();

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

    public void handleSE() {
        if(sprites == down || sprites == up || sprites == left || sprites == right) {
            if (!isWalkingSE) {
                gp.music[1].playSE();
                isWalkingSE = true;
            }
        } else if(sprites == hoeDown || sprites == hoeUp || sprites == hoeLeft || sprites == hoeRight) {
            if (!isHoeSE) {
                gp.music[2].playSE();
                isHoeSE = true;
            }
        } else if(sprites == waterDown || sprites == waterUp || sprites == waterLeft || sprites == waterRight) {
            if (!isWateringSE) {
                gp.music[3].playSE();
                isWateringSE = true;
            }
        } else {
            if(isWalkingSE) {
                isWalkingSE = false;
                gp.music[1].stop();
            } else if(isHoeSE) {
                isHoeSE = false;
                gp.music[2].stop();
            } else if(isWateringSE) {
                isWateringSE = false;
                gp.music[3].stop();
            }

        }
    }

    public void walkHandle() {

        if((keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && !keyH.hoePressed && !keyH.waterPressed) {
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
            } else {
                direction = "right";
            }

            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
//            int objIndex = gp.cChecker.checkObject(this, true);
//            interactObject(objIndex);


            gp.cChecker.checkHitbox(this);
            // check npc collision
//            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
//            interactNPC(npcIndex);

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
                        worldX -= speed - 1;
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
    }

    public void hoeAnimate() {
        if(!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && !keyH.waterPressed && keyH.hoePressed) {
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
    }

    public void idleAnimate() {
        boolean movePressed = (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed);
        if((!movePressed && !keyH.hoePressed && !keyH.waterPressed) || (movePressed && keyH.hoePressed) || (movePressed && keyH.waterPressed) || (keyH.hoePressed && keyH.waterPressed)) {
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
                case "upright", "downright":
                    sprites = idleRight;
                    direction = "right";
                    break;
                case "upleft", "downleft":
                    sprites = idleLeft;
                    direction = "left";
                    break;
            }
        }
    }

    public void wateringAnimate() {
        if(!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && !keyH.hoePressed && keyH.waterPressed) {
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

    }

    public void draw(Graphics2D g2) {

        if (sprites != null && spriteNum >= 1 && spriteNum <= sprites.length) {
            BufferedImage image = sprites[spriteNum - 1];
            if((keyH.waterPressed || keyH.hoePressed) && !(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && !(keyH.hoePressed && keyH.waterPressed))  {
                g2.drawImage(image, screenX - gp.tileSize, screenY - gp.tileSize, null);
            } else {
                g2.drawImage(image, screenX, screenY, null);
            }
        }
        if(gp.drawBorder) {
            drawBorder();
        }
    }
}
