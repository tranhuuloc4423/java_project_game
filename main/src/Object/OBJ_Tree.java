package Object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Tree extends SuperObject {
    GamePanel gp;
    public OBJ_Tree(GamePanel gp) {
        name = "Tree";
        try {
            images[0] = ImageIO.read(getClass().getResourceAsStream("/res/plants/tree_1.png"));
            images[1] = ImageIO.read(getClass().getResourceAsStream("/res/tiles/298.png"));
            image = images[0];
            UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
