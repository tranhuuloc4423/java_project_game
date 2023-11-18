package UI;

import main.GamePanel;
import main.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenu extends Menu  {

    private boolean drawMainMenu = true;
    private boolean isPauseEnabled = false;
    private boolean isOptionEnabled = false;
    private boolean isQuitEnabled = false;

    // panel
    BufferedImage panel, imagePause, imageOption, imageQuit;
    BufferedImage[] imagePauses = new BufferedImage[2];
    BufferedImage[] imageOptions = new BufferedImage[2];
    BufferedImage[] imageQuits = new BufferedImage[2];
    Timer timer;

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
        drawImage(g2, panel, 0, 0);
        drawImage(g2, imagePause, 0, -90);
        drawImage(g2, imageOption, 0, 0);
        drawImage(g2, imageQuit, 0, 90);
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
    public void mousePressed(MouseEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Runnable action = null;
                int mouseX = e.getX();
                int mouseY = e.getY();
                if (drawExitMenu) {
                    int[] positionSubmitExit = getBoundPosition(imageSubmitExits[1],-80,40,1 );
                    if (checkMousePosition(positionSubmitExit, mouseX, mouseY) && gp.gameState != gp.playState) {
                        isSubmitExit = !isSubmitExit;
                        action = () -> System.exit(0);
                    }
                    int[] positionCancelExit = getBoundPosition(imageCancelExits[1],80,40,1 );
                    if (checkMousePosition(positionCancelExit, mouseX, mouseY) && gp.gameState != gp.playState) {
                        isCancelExit = !isCancelExit;
                        action = () -> gp.gameState = gp.playState;
                    }
                }

                if(drawSettingMenu) {
                    int[] positionMusicIcon = getBoundPosition(settingMenuMusicIcons[1],80,-70,1 );
                    if (checkMousePosition(positionMusicIcon, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isMusicEnabled = !isMusicEnabled;
                        if(isMusicEnabled) {
                            gp.music[0].playMusic();
                        } else {
                            gp.music[0].stop();
                        }
                    }

                    int[] positionSE = getBoundPosition(settingMenuSoundEffect,80,-10,1 );
                    if (checkMousePosition(positionSE, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isSoundEffectEnabled = !isSoundEffectEnabled;
                    }

                    int[] positionSubmit = getBoundPosition(settingMenuSEIcons[1],-110,140,1);
                    if (checkMousePosition(positionSubmit, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isSubmitEnabled = !isSubmitEnabled;
                        action = () -> gp.gameState = gp.playState;
                    }

                    int[] positionCancel = getBoundPosition(settingMenuCancels[1],110,140,1 );
                    if (checkMousePosition(positionCancel, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isCancelEnabled = !isCancelEnabled;
                        action = () -> {
                            gp.gameState = gp.playState;
                            isMusicEnabled = false;
                            isSoundEffectEnabled = false;
                            gp.music[0].stop();
                            drawSettingMenu = false;
                        };
                    }

                    int[] positionAdd = getBoundPosition(settingAdds[1],100,65,1 );
                    if (checkMousePosition(positionAdd, mouseX, mouseY)) {
                        isAddClicked = !isAddClicked;
                        if(initVolume <= 9) {
                            initVolume++;
                        }
                        float volume = (float) initVolume / 10;
                        System.out.println(volume);
                        for(int i = 0; i < gp.music.length; i++) {
                            if(gp.music[i] != null) {
                                Sound.setVolume(gp.music[i].clip, volume);
                            }
                        }
                        action = () -> {
                            isAddClicked = false;
                        };
                    }

                    int[] positionMinus = getBoundPosition(settingMinuses[1],-20,65,1 );
                    if (checkMousePosition(positionMinus, mouseX, mouseY)) {
                        isMinusClicked = !isMinusClicked;
                        if(initVolume != 0) {
                            initVolume--;
                        }
                        float volume = (float) initVolume / 10;
                        System.out.println(volume);
                        for(int i = 0; i < gp.music.length; i++) {
                            if(gp.music[i] != null) {
                                Sound.setVolume(gp.music[i].clip, volume);
                            }
                        }
                        action = () -> {
                            isMinusClicked = false;
                        };
                    }
                }
                if (drawMainMenu) {
                    int[] positionPause = getBoundPosition(imagePause,0,-90,1 );
                    if (checkMousePosition(positionPause, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isPauseEnabled = !isPauseEnabled;
                        action = () -> gp.gameState = gp.playState;
                    }

                    int[] positionOption = getBoundPosition(imageOption,0,0,1 );
                    if (checkMousePosition(positionOption, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isOptionEnabled = !isOptionEnabled;
                        action = () -> drawSettingMenu = true;
                    }

                    int[] positionQuit = getBoundPosition(imageQuit,0,90,1 );
                    if (checkMousePosition(positionQuit, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isQuitEnabled = !isQuitEnabled;
                        action = () -> {
                            drawMainMenu = !drawMainMenu;
                            drawExitMenu = true;
                        };
                    }
                }
                if (action != null) {
                    if (timer != null && timer.isRunning()) {
                        timer.stop(); // Dừng Timer hiện tại nếu đang chạy
                    }

                    Runnable finalAction = action;
                    ActionListener delayedAction = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            finalAction.run();
                        }
                    };

                    timer = new Timer(delay, delayedAction);
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        });
    }
}
