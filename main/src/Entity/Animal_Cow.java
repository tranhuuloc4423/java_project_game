package Entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Animal_Cow extends Animal {

    public Animal_Cow(GamePanel gp) {
        super(gp);
        direction = "idleright";
        speed = 1;
        setImage();
        solidArea.x = 0;
        solidArea.y = 48;
        solidArea.width = 80;
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
                gp.music[6].playSEOnce();
            }
            if(i > 125 && i <= 150) {
                direction = "idleleft";
                gp.music[6].playSEOnce();
            }
            actionLockCounter = 0;
        }
    }

    public BufferedImage setup(String imageName) {
        BufferedImage image = null;
        int size = gp.tileSize * 2;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("res/animals/Cow/" + imageName + ".png");
            if (inputStream != null) {
                image = ImageIO.read(inputStream);
                image = UtilityTool.scaleImage(image, size, size);
            } else {
                throw new IOException("Could not find resource: " + imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
