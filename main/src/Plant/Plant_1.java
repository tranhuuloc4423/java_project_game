package Plant;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Plant_1 extends Tree {
    GamePanel gp;
    public Plant_1(GamePanel gp) {
        name = "Plant_1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/plants/tile003.png"));
            tool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
