package Entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        setImage();
    }

    public void setImage() {
        try {
            for (int i = 1; i <= spritesNum; i++) {
                // move
                up[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_up_" + i + ".png"));
                down[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_down_" + i + ".png"));
                left[i - 1] = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_left_" + i + ".png"));
                right[i- 1] = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_right_" + i + ".png"));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;
        if(actionLockCounter == gp.FPS * 2) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if(i <= 25) {
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
            actionLockCounter = 0;
        }
    }
}
