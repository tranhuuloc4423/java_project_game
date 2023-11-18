package Entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Animal_Chicken extends Animal {
    public Animal_Chicken(GamePanel gp) {
        super(gp);
        speed = 1;
        direction = "idleright";
        setImage();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;

    }

    @Override
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
                gp.music[7].playSEOnce();
            }
            if(i > 125 && i <= 150) {
                direction = "idleleft";
                gp.music[7].playSEOnce();

            }
            actionLockCounter = 0;
        }
    }

    public void setImage() {
        for (int i = 1; i <= spritesNum; i++) {
            left[i - 1] = setup("chicken_left_" + i);
            right[i - 1] = setup("chicken_right_" + i);
            up[i - 1] = setup("chicken_left_" + i);
            down[i - 1] = setup("chicken_right_" + i);
            idleLeft[i - 1] = setup("chicken_idle_left_" + i);
            idleRight[i - 1] = setup("chicken_idle_right_" + i);
        }
    }

    @Override
    public BufferedImage setup(String imageName) {
        BufferedImage image = null;
        int size = gp.tileSize;
        try{
            image =  ImageIO.read(getClass().getResourceAsStream("/res/animals/Chicken/" + imageName +".png"));
            image = UtilityTool.scaleImage(image, size, size);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
