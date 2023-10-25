package Entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Animal_Cow extends  Entity {
    public Animal_Cow(GamePanel gp) {
        super(gp);
        direction = "right";
        speed = 1;
        setImage();
    }

    public void setImage() {
        try {
            for (int i = 1; i <= spritesNum; i++) {
                // move
                left[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/animals/Cow/cow_left_" + i + ".png"));
                right[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/animals/Cow/cow_right_" + i + ".png"));

                // idle
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;
        if(actionLockCounter == gp.FPS * 2) {
            Random random = new Random();
            int i = random.nextInt(50) + 1;

            if(i >= 0 && i <= 25) {
                direction = "left";
            }

            if(i > 25 && i <= 50) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
