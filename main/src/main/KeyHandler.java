package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean btn1Pressed,btn2Pressed, btn3Pressed, btn4Pressed, btn5Pressed, btn6Pressed, btn7Pressed, btn8Pressed, btn9Pressed;
    public boolean escapePressed;
    public boolean isHoe;

    public boolean inventoryPressed;
    // debug
    boolean checkDrawTime = false;
    public boolean isPlant = false;

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

        if(code == KeyEvent.VK_R) {
            int col = gp.player.landTileX;
            int row = gp.player.landTileY;
            int dirtTileNum = 29;
            if(gp.tileM.mapTileNum[col][row] == dirtTileNum) {
                isHoe = true;
            }
        }

        if(code == KeyEvent.VK_ESCAPE) {
            escapePressed = true;
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else  if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }

        // game state
        if(code == KeyEvent.VK_P) {
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else  if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }

        // inventory
        if(code == KeyEvent.VK_I) {
            inventoryPressed = true;
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
            if(!checkDrawTime) {
                checkDrawTime = true;
            } else {
                checkDrawTime = false;
            }
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

        if(code == KeyEvent.VK_R) {
            isHoe = false;
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
