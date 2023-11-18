package Object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    GamePanel gp;
    public OBJ_Door(GamePanel gp) {
        name = "Door";
        try {
            images[0] = ImageIO.read(getClass().getResourceAsStream("/res/tiles/299.png"));
            images[1] = ImageIO.read(getClass().getResourceAsStream("/res/tiles/298.png"));
            image = images[0];
            UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
