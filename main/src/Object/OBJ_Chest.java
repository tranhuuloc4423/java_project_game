package Object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_Chest extends SuperObject {
    GamePanel gp;
    public OBJ_Chest(GamePanel gp) {
        name = "Chest";
        try {
            images[0] = ImageIO.read(getClass().getResourceAsStream("/res/ui/chest.png"));
            images[1] = ImageIO.read(getClass().getResourceAsStream("/res/ui/chest_open.png"));
            image = images[0];
            UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
            this.collision = true;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
