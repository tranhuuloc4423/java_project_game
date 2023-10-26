package Object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Border extends SuperObject {

    GamePanel gp;
    public OBJ_Border(GamePanel gp) {
        name = "Border";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/ui/SelectMenu.png"));
            tool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
