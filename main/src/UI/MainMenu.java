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

public class MainMenu extends Menu {

    private boolean drawMainMenu = true;
    private boolean drawSubMenu = false;
    private boolean drawSubmitMenu = false;

    private boolean isPauseEnabled = false;
    private boolean isOptionEnabled = false;
    private boolean isQuitEnabled = false;

    public boolean isMusicEnabled = true;
    public boolean isSoundEffectEnabled = true;
    private boolean isSubmit = false;
    private boolean isCancel = false;

    private boolean isSubmitExit = false;
    private boolean isCancelExit = false;

    // panel
    BufferedImage panel, imagePause, imageOption, imageQuit;
    BufferedImage[] imagePauses = new BufferedImage[2];
    BufferedImage[] imageOptions = new BufferedImage[2];
    BufferedImage[] imageQuits = new BufferedImage[2];

    // option setting
    BufferedImage optionSetting, imageMusic, imageMusicIcon, imageSoundEffect, imageSoundEffectIcon, imageSubmit, imageCancel;
    BufferedImage[] imageSoundEffects = new BufferedImage[2];
    BufferedImage[] imageMusics = new BufferedImage[2];
    BufferedImage[] imageSubmits = new BufferedImage[2];
    BufferedImage[] imageCancels = new BufferedImage[2];

    // exit
    BufferedImage exitSetting, imageSubmitExit, imageCancelExit;
    BufferedImage[] imageSubmitExits = new BufferedImage[2];
    BufferedImage[] imageCancelExits = new BufferedImage[2];
    public MainMenu(GamePanel gp) {
        super(gp);
        setupPanel();
        gp.addMouseListener(this);
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.playState) {
            drawSubMenu = false;
            drawSubmitMenu = false;
            drawMainMenu = true;
        }
        if (gp.gameState == gp.pauseState) {
            if (gp.keyH.menuPressed) {
                if (drawSubMenu) {
                    drawSubMenuSetting(g2);
                    drawMainMenu = false;
                    drawSubmitMenu = false;
                } else if (drawMainMenu) {
                    drawPanel(g2);
                    drawSubMenu = false;
                    drawSubmitMenu = false;
                } else if (drawSubmitMenu) {
                    drawSubmitMenuSetting(g2);
                    drawMainMenu = false;
                    drawSubMenu = false;
                }
            }
        }
    }

    public void update() {
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

        // option setting
        System.out.println("isMusicEnabled : " + isMusicEnabled);
        if (isMusicEnabled) {
            imageMusic = imageMusics[0];
        } else {
            imageMusic = imageMusics[1];
        }
        if (isSoundEffectEnabled) {
            imageSoundEffect = imageSoundEffects[0];
        }
        else {
            imageSoundEffect = imageSoundEffects[1];
        }
        if (isSubmit) {
            imageSubmit = imageSubmits[0];
        } else {
            imageSubmit = imageSubmits[1];
        }
        if (isCancel) {
            imageCancel = imageCancels[0];
        } else {
            imageCancel = imageCancels[1];
        }

        // exit

        if (isSubmitExit) {
            imageSubmitExit = imageSubmitExits[0];
        } else {
            imageSubmitExit = imageSubmitExits[1];
        }
        if (isCancelExit) {
            imageCancelExit = imageCancelExits[0];
        } else {
            imageCancelExit = imageCancelExits[1];
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


        // option settings
        optionSetting = setupImage("OptionSetting");
        imageMusicIcon = setupImage("Music");
        imageSoundEffectIcon = setupImage("soundeffect");
        imageMusics[0] = setupImage("OnActive");
        imageMusics[1] = setupImage("OffActive");
        imageSoundEffects[0] = setupImage("OnActive");
        imageSoundEffects[1] = setupImage("OffActive");
        imageSubmits[0] = setupImage("SubmitActive");
        imageSubmits[1] = setupImage("Submit");
        imageCancels[0] = setupImage("CancelActive");
        imageCancels[1] = setupImage("Cancel");
        imageMusic = imageMusics[0];
        imageSoundEffect = imageSoundEffects[0];
        imageSubmit = imageSubmits[0];
        imageCancel = imageCancels[0];

        // exit setting
        exitSetting = setupImage("SubmitExitSetting");
        imageSubmitExits[0] = imageSubmits[0];
        imageSubmitExits[1] = imageSubmits[1];
        imageSubmitExit = imageSubmitExits[0];
        imageCancelExits[0] = imageCancels[0];
        imageCancelExits[1] = imageCancels[1];
        imageCancelExit = imageCancelExits[0];
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
    public void drawSubMenuSetting(Graphics2D g2) {
        drawImage(g2, optionSetting, 0, 0 , 1);
        drawImage(g2, imageMusicIcon, -85, -45 , 1);
        drawImage(g2, imageMusic, 80, -45 , 1);
        drawImage(g2, imageSoundEffectIcon, -85, 35 , 1);
        drawImage(g2, imageSoundEffect, 80, 35 , 1);
        drawImage(g2, imageSubmit, -100, 120 , 1);
        drawImage(g2, imageCancel, 95, 120 , 1);
        isSubmit = false;
        isCancel = false;
    }
    public void drawSubmitMenuSetting(Graphics2D g2) {
        drawImage(g2, exitSetting, 0, 0 , 1);
        drawImage(g2, imageSubmitExit, -80, 40 , 1);
        drawImage(g2, imageCancelExit, 80, 40 , 1);
        isSubmitExit = false;
        isCancelExit = false;
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
        int mouseX = e.getX();
        int mouseY = e.getY();
        if (drawSubmitMenu) {
            int[] positionSubmitExit = getBoundPosition(imageSubmitExit,-80,40,1 );
            if (checkMousePosition(positionSubmitExit, mouseX, mouseY) && gp.gameState != gp.startState) {
                isSubmitExit = !isSubmitExit;
                int delay = 150;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gp.gameState = gp.startState;
                        //System.exit(0);
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();
                timer = null;
            }
            int[] positionCancelExit = getBoundPosition(imageCancelExit,80,40,1 );
            if (checkMousePosition(positionCancelExit, mouseX, mouseY) && gp.gameState != gp.startState) {
                isCancelExit = !isCancelExit;
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
        }

        if (drawSubMenu) {
            int[] positionMusic = getBoundPosition(imageMusicIcon,80,-45,1 );
            if (checkMousePosition(positionMusic, mouseX, mouseY) && gp.gameState != gp.startState) {
                isMusicEnabled = !isMusicEnabled;
                System.out.println(isMusicEnabled);
//                if(!isMusicEnabled) {
//                    gp.music[0].stop();
//                } else {
//                    gp.music[0].playMusic();
//                }
            }

            int[] positionSE = getBoundPosition(imageSoundEffectIcon,80,35,1 );
            if (checkMousePosition(positionSE, mouseX, mouseY) && gp.gameState != gp.startState) {
                isSoundEffectEnabled = !isSoundEffectEnabled;
            }

            int[] positionSubmit = getBoundPosition(imageSubmit,-100,120,1 );
            if (checkMousePosition(positionSubmit, mouseX, mouseY) && gp.gameState != gp.startState) {
                isSubmit = !isSubmit;
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

            int[] positionCancel = getBoundPosition(imageCancel,95,120,1 );
            if (checkMousePosition(positionCancel, mouseX, mouseY) && gp.gameState != gp.startState) {
                isCancel = !isCancel;
                int delay = 150;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gp.gameState = gp.playState;
                        isMusicEnabled = false;
                        gp.music[0].stop();
                        isSoundEffectEnabled = false;
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();
                timer = null;

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
                        drawSubMenu = true;
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
                        drawSubmitMenu = true;
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();
                timer = null;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
