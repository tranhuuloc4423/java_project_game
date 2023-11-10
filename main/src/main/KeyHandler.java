package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean hoePressed, waterPressed;
    public boolean btn1Pressed,btn2Pressed, btn3Pressed, btn4Pressed, btn5Pressed, btn6Pressed, btn7Pressed, btn8Pressed, btn9Pressed;
    public boolean escapePressed;
    public boolean isHoe;

    public boolean inventoryPressed;
    // debug
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }

        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if(code == KeyEvent.VK_ESCAPE) {
            escapePressed = true;
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else if(gp.gameState == gp.pauseState) {
//                gp.gameState = gp.playState;
            }
        }

        // inventory
        if(code == KeyEvent.VK_I) {
            gp.invetoryM.inventoryOn = !gp.invetoryM.inventoryOn;
        }

        if(code == KeyEvent.VK_1) {
            btn1Pressed = true;
        }

        if(code == KeyEvent.VK_2) {
            btn2Pressed = true;
        }

        if(code == KeyEvent.VK_3) {
            btn3Pressed = true;
        }

        if(code == KeyEvent.VK_4) {
            btn4Pressed = true;
        }

        if(code == KeyEvent.VK_5) {
            btn5Pressed = true;
        }

        if(code == KeyEvent.VK_6) {
            btn6Pressed = true;
        }

        if(code == KeyEvent.VK_7) {
            btn7Pressed = true;
        }

        if(code == KeyEvent.VK_8) {
            btn8Pressed = true;
        }

        if(code == KeyEvent.VK_9) {
            btn9Pressed = true;
        }

        if(code == KeyEvent.VK_T) {
            checkDrawTime = !checkDrawTime;
        }
        if(code == KeyEvent.VK_M) {
            gp.mission.missionOn = !gp.mission.missionOn;
        }

        if(code == KeyEvent.VK_X) {
            gp.drawBorder = !gp.drawBorder;
        }

        if(code == KeyEvent.VK_E) {
            waterPressed = true;
            gp.tileM.checkWater();
        }
        if(code == KeyEvent.VK_R) {
            hoePressed = true;
            gp.tileM.checkHoe();
        }
        if(code == KeyEvent.VK_B) {
            gp.store.storeOn = !gp.store.storeOn;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if(code == KeyEvent.VK_E) {
            waterPressed = false;
        }
        if(code == KeyEvent.VK_R) {
            hoePressed = false;
        }

        if(code == KeyEvent.VK_1) {
            btn1Pressed = false;
        }

        if(code == KeyEvent.VK_2) {
            btn2Pressed = false;
        }

        if(code == KeyEvent.VK_3) {
            btn3Pressed = false;
        }

        if(code == KeyEvent.VK_4) {
            btn4Pressed = false;
        }

        if(code == KeyEvent.VK_5) {
            btn5Pressed = false;
        }

        if(code == KeyEvent.VK_6) {
            btn6Pressed = false;
        }

        if(code == KeyEvent.VK_7) {
            btn7Pressed = false;
        }

        if(code == KeyEvent.VK_8) {
            btn8Pressed = false;
        }

        if(code == KeyEvent.VK_9) {
            btn9Pressed = false;
        }

        if(code == KeyEvent.VK_I) {
            inventoryPressed = false;
        }
    }
}
