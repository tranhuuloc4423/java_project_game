package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public boolean isHoe;
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

        if(code == KeyEvent.VK_R) {
            isHoe = true;
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
        if(code == KeyEvent.VK_B) {
            if(gp.inventory == gp.inventoryOn) {
                gp.inventory = gp.inventoryOff;
            } else  if(gp.inventory == gp.inventoryOff) {
                gp.inventory = gp.inventoryOn;
            }
            System.out.println(gp.inventory);
        }
        switch (code) {
            case KeyEvent.VK_1:
                gp.selectItem = 1;
                break;
            case KeyEvent.VK_2:
                gp.selectItem = 2;
                break;
            case KeyEvent.VK_3:
                gp.selectItem = 3;
                break;
            case KeyEvent.VK_4:
                gp.selectItem = 4;
                break;
            case KeyEvent.VK_5:
                gp.selectItem = 5;
                break;
            case KeyEvent.VK_6:
                gp.selectItem = 6;
                break;
            case KeyEvent.VK_7:
                gp.selectItem = 7;
                break;
            case KeyEvent.VK_8:
                gp.selectItem = 8;
                break;
            case KeyEvent.VK_9:
                gp.selectItem = 9;
                break;
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
    }
}
