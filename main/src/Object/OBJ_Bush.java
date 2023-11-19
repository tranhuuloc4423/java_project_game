package Object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TimerTask;

public class OBJ_Bush extends SuperObject {
    public Timer timer;
    private static final int CHANGE_DURATION = 5000;
    public OBJ_Bush(GamePanel gp) {
        name = "Bush";
        try {
            images[0] = ImageIO.read(getClass().getResourceAsStream("/res/plants/bush_1.png"));
            images[1] = ImageIO.read(getClass().getResourceAsStream("/res/plants/bush_2.png"));
            image = images[1];
            UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
    @Override
    public void update() {
        if(image == images[0]) {
            timer = new Timer(CHANGE_DURATION, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    image = images[1];
                    timer.stop();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
}
