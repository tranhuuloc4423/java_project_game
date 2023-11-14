package Entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    public BufferedImage setup(String imageName) {
        BufferedImage image = null;
        int size = gp.tileSize * 2;
        try{
            image =  ImageIO.read(getClass().getResourceAsStream("/res/animals/Cow/" + imageName +".png"));
            image = UtilityTool.scaleImage(image, size, size);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
