package Object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject {
    GamePanel gp;
    public OBJ_Key(GamePanel gp) {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Object/key.png"));
            UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
