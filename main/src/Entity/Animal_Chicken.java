package Entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
