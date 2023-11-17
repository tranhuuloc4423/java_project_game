package Object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject {
    GamePanel gp;
    public OBJ_Chest(GamePanel gp) {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/ui/chest.png"));
            UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
            this.collision = true;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
