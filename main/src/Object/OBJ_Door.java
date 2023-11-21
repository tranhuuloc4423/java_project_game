package Object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(GamePanel gp) {
        super(gp);
        name = "Door";
        images[0] = setupImage("/res/tiles/299.png");
        images[1] = setupImage("/res/tiles/298.png");
        image = images[0];
        collision = true;
    }
}
