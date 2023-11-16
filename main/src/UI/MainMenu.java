package UI;

import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu extends Menu  {

    private boolean drawMainMenu = true;

    private boolean isPauseEnabled = false;
    private boolean isOptionEnabled = false;
    private boolean isQuitEnabled = false;
    private boolean isSubmit = false;
    private boolean isCancel = false;

    // panel
    BufferedImage panel, imagePause, imageOption, imageQuit;
    BufferedImage[] imagePauses = new BufferedImage[2];
    BufferedImage[] imageOptions = new BufferedImage[2];
    BufferedImage[] imageQuits = new BufferedImage[2];

    // exit

    public MainMenu(GamePanel gp) {
        super(gp);
        setupPanel();
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.playState) {
            drawSettingMenu = false;
            drawExitMenu = false;
            drawMainMenu = true;
        }
        if (gp.gameState == gp.pauseState) {
            if (gp.keyH.menuPressed) {
                if (drawSettingMenu) {
                    drawOptionPanel(g2);
                    drawMainMenu = false;
                    drawExitMenu = false;
                } else if (drawMainMenu) {
                    drawPanel(g2);
                    drawSettingMenu = false;
                    drawExitMenu = false;
                } else if (drawExitMenu) {
                    drawExitMenu(g2);
                    drawMainMenu = false;
                    drawSettingMenu = false;
                }
            }
        }
    }

    public void update() {
        super.update();
        // panel
        if(isPauseEnabled) {
            imagePause = imagePauses[0];
        } else {
            imagePause = imagePauses[1];
        }
        if(isOptionEnabled) {
            imageOption = imageOptions[0];
        } else {
            imageOption = imageOptions[1];
        }
        if(isQuitEnabled) {
            imageQuit = imageQuits[0];
        } else {
            imageQuit = imageQuits[1];
        }
    }

    public void setupPanel() {
        // panel
        panel = setupImage("BasicSetting");
        imagePauses[0] = setupImage("ResumeActive");
        imagePauses[1] = setupImage("Resume");
        imageOptions[0] = setupImage("OptionActive");
        imageOptions[1] = setupImage("Option");
        imageQuits[0] = setupImage("QuitActive");
        imageQuits[1] = setupImage("Quit");
        imagePause = imagePauses[0];
        imageOption = imageOptions[0];
        imageQuit = imageQuits[0];
    }
    public void drawPanel(Graphics2D g2) {
        drawImage(g2, panel, 0, 0 , 1);
        drawImage(g2, imagePause, 0, -90 , 1);
        drawImage(g2, imageOption, 0, 0 , 1);
        drawImage(g2, imageQuit, 0, 90 , 1);
        isPauseEnabled = false;
        isOptionEnabled = false;
        isQuitEnabled = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPauseEnabled = false;
        isOptionEnabled = false;
        isQuitEnabled = false;

        if(isSubmit) {
            isSubmit = false;
        }
        if(isCancel) {
            isMusicEnabled = !isMusicEnabled;
            isSoundEffectEnabled = !isSoundEffectEnabled;
            isCancel = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        checkDrawExitMenu(mouseX, mouseY, gp.playState);

        if(drawSettingMenu) {
            int[] positionMusicIcon = getBoundPosition(settingMenuMusicIcons[1],80,-45,1 );
            if (checkMousePosition(positionMusicIcon, mouseX, mouseY) && gp.gameState != gp.startState) {
                isMusicEnabled = !isMusicEnabled;
                if(isMusicEnabled) {
                    gp.music[0].playMusic();
                } else {
                    gp.music[0].stop();
                }
            }

            int[] positionSEStart = getBoundPosition(settingMenuSoundEffect,80,35,1 );
            if (checkMousePosition(positionSEStart, mouseX, mouseY) && gp.gameState != gp.startState) {
                isSoundEffectEnabled = !isSoundEffectEnabled;
            }

            int[] positionSubmit = getBoundPosition(settingMenuSEIcons[1],-100,120,1);
            if (checkMousePosition(positionSubmit, mouseX, mouseY) && gp.gameState != gp.startState) {
                isSubmitEnabled = !isSubmitEnabled;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gp.gameState = gp.playState;
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();
            }

            int[] positionCancel = getBoundPosition(settingMenuCancels[1],95,120,1 );
            if (checkMousePosition(positionCancel, mouseX, mouseY) && gp.gameState != gp.startState) {
                isCancelEnabled = !isCancelEnabled;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gp.gameState = gp.playState;
                        isMusicEnabled = false;
                        isSoundEffectEnabled = false;
                        gp.music[0].stop();
                        drawSettingMenu = false;
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();
            }
        }
        if (drawMainMenu) {
            int[] positionPause = getBoundPosition(imagePause,0,-90,1 );
            if (checkMousePosition(positionPause, mouseX, mouseY) && gp.gameState != gp.startState) {
                isPauseEnabled = !isPauseEnabled;
                int delay = 150;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gp.gameState = gp.playState;
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();
                timer = null;

            }

            int[] positionOption = getBoundPosition(imageOption,0,0,1 );
            if (checkMousePosition(positionOption, mouseX, mouseY) && gp.gameState != gp.startState) {
                isOptionEnabled = !isOptionEnabled;
                int delay = 150;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        drawSettingMenu = true;
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();
            }

            int[] positionQuit = getBoundPosition(imageQuit,0,90,1 );
            if (checkMousePosition(positionQuit, mouseX, mouseY) && gp.gameState != gp.startState) {
                isQuitEnabled = !isQuitEnabled;
                int delay = 150;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        drawMainMenu = !drawMainMenu;
                        drawExitMenu = true;
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();
                timer = null;
            }
        }
    }
}
