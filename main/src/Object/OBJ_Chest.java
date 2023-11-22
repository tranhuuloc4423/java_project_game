package Object;

import main.GamePanel;

public class OBJ_Chest extends SuperObject {
    public OBJ_Chest(GamePanel gp) {
        super(gp);
        name = "Chest";
        images[0] = setupImage("res/object/chest.png");
        images[1] = setupImage("res/object/chest_open.png");
        image = images[0];
        collision = true;
    }
}
