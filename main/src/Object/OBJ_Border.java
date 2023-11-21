package Object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Border extends SuperObject {
    public OBJ_Border(GamePanel gp) {
        super(gp);
        name = "Border";
        image = setupImage("/res/ui/border.png", 1.5);
    }
}
