package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import javax.swing.Timer;

public class Menu {
    GamePanel gp;
    private boolean drawStartMenu = true;
    private  boolean drawSettingMenu = false;
    private  boolean drawAboutMenu = false;
    private  boolean drawExitMenu = false;

    private boolean isMusicStartEnabled = false;
    private boolean isSoundEffectStartEnabled = false;
    private boolean isSubmitStartEnabled = false;
    private boolean isCancelStartEnabled = false;

    private boolean isSubmitExitStart = false;
    private boolean isCancelExitStart = false;

    private boolean drawMainMenu = true;
    private boolean drawSubMenu = false;
    private boolean drawSubmitMenu = false;

    private boolean isPlayEnabled = false;
    private boolean isSettingEnabled = false;
    private boolean isAboutEnabled = false;
    private boolean isExitEnabled = false;

    private boolean isPauseEnabled = false;
    private boolean isOptionEnabled = false;
    private boolean isQuitEnabled = false;

    private boolean isMusicEnabled = false;
    private boolean isSoundEffectEnabled = false;
    private boolean isSubmit = false;
    private boolean isCancel = false;

    private boolean isSubmitExit = false;
    private boolean isCancelExit = false;
    public Menu(GamePanel gp) {
        this.gp = gp;
        MouseClickListener mouseClickListener = new MouseClickListener();
        gp.addMouseListener(mouseClickListener);
    }
    public void update() {

    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.startState) {
            drawStartMenu(g2);
            drawStartMenu = true;
            if (drawSettingMenu) {
                drawSettingMenuStart(g2);
            } else if(drawAboutMenu) {
                drawAboutMenuStart(g2);
            } else if(drawExitMenu) {
                drawExitMenuStart(g2);
            }
        }
        if (gp.gameState == gp.playState) {
            drawSubMenu = false;
            drawSubmitMenu = false;
            drawMainMenu = true;
        }
        if (gp.gameState == gp.pauseState) {
            if (gp.keyH.escapePressed) {
                if (drawSubMenu) {
                    drawSubMenuSetting(g2);
                    drawMainMenu = false;
                    drawSubmitMenu = false;
                } else if (drawMainMenu) {
                    drawMenuSetting(g2);
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

    public void drawImage(Graphics2D g2,BufferedImage image, int xSize , int ySize, int size) {
        int w = image.getWidth() * gp.scale * size;
        int h = image.getHeight() * gp.scale * size;
        int x = (gp.screenWidth - w) / 2 + xSize;
        int y = (gp.screenHeight - h) / 2 + ySize;
        g2.drawImage(image, x, y, w, h, null);
    }

    public void drawImage2(Graphics2D g2,BufferedImage image, int xSize , int ySize, int size) {
        int w = image.getWidth() * gp.scale * size;
        int h = image.getHeight() * gp.scale * size;
        int x = (gp.screenWidth - w) / 2 + xSize;
        int y = (gp.screenHeight - h) / 2 + ySize;
        BufferedImage scaledImage = UtilityTool.scaleImage(image, w, h);
        g2.drawImage(scaledImage, x, y, null);
    }

    private class MouseClickListener extends MouseAdapter {
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
        public int[] getBoundPosition(BufferedImage image, int xSize , int ySize, int size) {
            int width = image.getWidth() * gp.scale * size;
            int height = image.getHeight() * gp.scale;
            int x = (gp.screenWidth - width) / 2 + xSize;
            int y = (gp.screenHeight - height) / 2 + ySize;

            int[] arr = new int[4];
            arr[0] = width;
            arr[1] = height;
            arr[2] = x;
            arr[3] = y;

            return arr;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            BufferedImage imagePlay = null;
            BufferedImage imageSetting = null;
            BufferedImage imageAbout = null;
            BufferedImage imageExit = null;

            BufferedImage imageMusicIconStart = null;
            BufferedImage imageSoundEffectIconStart = null;
            BufferedImage imageSubmitStart = null;
            BufferedImage imageCancelStart = null;

            BufferedImage imageSubmitExitStart = null;
            BufferedImage imageCancelExitStart = null;

            BufferedImage imagePause = null;
            BufferedImage imageOption = null;
            BufferedImage imageQuit = null;

            BufferedImage imageMusicIcon = null;
            BufferedImage imageSoundEffectIcon = null;
            BufferedImage imageSubmit = null;
            BufferedImage imageCancel = null;

            BufferedImage imageSubmitExit = null;
            BufferedImage imageCancelExit = null;

            try {
                imagePlay = ImageIO.read(getClass().getResourceAsStream("/res/menu/Play.png"));
                imageSetting = ImageIO.read(getClass().getResourceAsStream("/res/menu/Setting.png"));
                imageAbout = ImageIO.read(getClass().getResourceAsStream("/res/menu/About.png"));
                imageExit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Exit.png"));
                imageMusicIconStart = ImageIO.read(getClass().getResourceAsStream("/res/menu/OffActive.png"));
                imageSoundEffectIconStart = ImageIO.read(getClass().getResourceAsStream("/res/menu/OffActive.png"));
                imageSubmitStart = ImageIO.read(getClass().getResourceAsStream("/res/menu/Submit.png"));
                imageCancelStart = ImageIO.read(getClass().getResourceAsStream("/res/menu/Cancel.png"));
                imageSubmitExitStart = ImageIO.read(getClass().getResourceAsStream("/res/menu/Submit.png"));
                imageCancelExitStart = ImageIO.read(getClass().getResourceAsStream("/res/menu/Cancel.png"));
                imagePause = ImageIO.read(getClass().getResourceAsStream("/res/menu/Resume.png"));
                imageOption = ImageIO.read(getClass().getResourceAsStream("/res/menu/Option.png"));
                imageQuit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Quit.png"));
                imageMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/OffActive.png"));
                imageSoundEffectIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/OffActive.png"));
                imageSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Submit.png"));
                imageCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/Cancel.png"));
                imageSubmitExit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Submit.png"));
                imageCancelExit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Cancel.png"));
            } catch (IOException e2) {
                e2.printStackTrace();
            }

            if(drawStartMenu) {
                int[] positionSetting = getBoundPosition(imageSetting,245,-220,1 );
                if (mouseX >= positionSetting[2] && mouseX <= positionSetting[2] + positionSetting[0] && mouseY >= positionSetting[3] && mouseY <= positionSetting[3] + positionSetting[1] && !drawAboutMenu && !drawExitMenu) {
                    isSettingEnabled = !isSettingEnabled;
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
                int[] positionAbout = getBoundPosition(imageAbout,350,-220,1 );
                if (mouseX >= positionAbout[2] && mouseX <= positionAbout[2] + positionAbout[0] && mouseY >= positionAbout[3] && mouseY <= positionAbout[3] + positionAbout[1] && !drawSettingMenu && !drawExitMenu) {
                    isAboutEnabled = !isAboutEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawAboutMenu  = true;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
                int[] positionExit = getBoundPosition(imageExit,455,-220,1 );
                if (mouseX >= positionExit[2] && mouseX <= positionExit[2] + positionExit[0] && mouseY >= positionExit[3] && mouseY <= positionExit[3] + positionExit[1] && !drawAboutMenu && !drawSettingMenu) {
                    isExitEnabled = !isExitEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawExitMenu = true;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
                int[] positionPlay = getBoundPosition(imagePlay,350,-300,1 );
                if (mouseX >= positionPlay[2] && mouseX <= positionPlay[2] + positionPlay[0] && mouseY >= positionPlay[3] && mouseY <= positionPlay[3] + positionPlay[1]) {
                    isPlayEnabled = !isPlayEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            gp.gameState = gp.playState;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
            }

            if(drawSettingMenu) {
                int[] positionMusicStart = getBoundPosition(imageMusicIconStart,80,-45,1 );
                if (mouseX >= positionMusicStart[2] && mouseX <= positionMusicStart[2] + positionMusicStart[0] && mouseY >= positionMusicStart[3] && mouseY <= positionMusicStart[3] + positionMusicStart[1]) {
                    isMusicStartEnabled = !isMusicStartEnabled;
                }

                int[] positionSEStart = getBoundPosition(imageSoundEffectIconStart,80,35,1 );
                if (mouseX >= positionSEStart[2] && mouseX <= positionSEStart[2] + positionSEStart[0] && mouseY >= positionSEStart[3] && mouseY <= positionSEStart[3] + positionSEStart[1]) {
                    isSoundEffectStartEnabled = !isSoundEffectStartEnabled;
                }

                int[] positionSubmit = getBoundPosition(imageSubmitStart,-100,120,1 );
                if (mouseX >= positionSubmit[2] && mouseX <= positionSubmit[2] + positionSubmit[0] && mouseY >= positionSubmit[3] && mouseY <= positionSubmit[3] + positionSubmit[1]) {
                    isSubmitStartEnabled = !isSubmitStartEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawSettingMenu = false;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                int[] positionCancel = getBoundPosition(imageCancelStart,95,120,1 );
                if (mouseX >= positionCancel[2] && mouseX <= positionCancel[2] + positionCancel[0] && mouseY >= positionCancel[3] && mouseY <= positionCancel[3] + positionCancel[1]) {
                    isCancelStartEnabled = !isCancelStartEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            isMusicStartEnabled = false;
                            isSoundEffectStartEnabled = false;
                            drawSettingMenu = false;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
            }

            if(drawExitMenu) {
                int[] positionSubmitExit = getBoundPosition(imageSubmitExitStart,-80,50,1 );
                if (mouseX >= positionSubmitExit[2] && mouseX <= positionSubmitExit[2] + positionSubmitExit[0] && mouseY >= positionSubmitExit[3] && mouseY <= positionSubmitExit[3] + positionSubmitExit[1] && gp.gameState != gp.startState) {
                    isSubmitExitStart = !isSubmitExitStart;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            System.exit(0);
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
                int[] positionCancelExit = getBoundPosition(imageCancelExitStart,80,50,1 );
                if (mouseX >= positionCancelExit[2] && mouseX <= positionCancelExit[2] + positionCancelExit[0] && mouseY >= positionCancelExit[3] && mouseY <= positionCancelExit[3] + positionCancelExit[1] && gp.gameState != gp.startState) {
                    isCancelExitStart = !isCancelExitStart;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            gp.gameState = gp.startState;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
            }

            if (drawSubmitMenu) {
                int[] positionSubmitExit = getBoundPosition(imageSubmitExit,-80,50,1 );
                if (mouseX >= positionSubmitExit[2] && mouseX <= positionSubmitExit[2] + positionSubmitExit[0] && mouseY >= positionSubmitExit[3] && mouseY <= positionSubmitExit[3] + positionSubmitExit[1] && gp.gameState != gp.startState) {
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
                }
                int[] positionCancelExit = getBoundPosition(imageCancelExit,80,50,1 );
                if (mouseX >= positionCancelExit[2] && mouseX <= positionCancelExit[2] + positionCancelExit[0] && mouseY >= positionCancelExit[3] && mouseY <= positionCancelExit[3] + positionCancelExit[1] && gp.gameState != gp.startState) {
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
                }
            }

            if (drawSubMenu) {
                int[] positionMusic = getBoundPosition(imageMusicIcon,80,-45,1 );
                if (mouseX >= positionMusic[2] && mouseX <= positionMusic[2] + positionMusic[0] && mouseY >= positionMusic[3] && mouseY <= positionMusic[3] + positionMusic[1] && gp.gameState != gp.startState) {
                    isMusicEnabled = !isMusicEnabled;
                }

                int[] positionSE = getBoundPosition(imageSoundEffectIcon,80,35,1 );
                if (mouseX >= positionSE[2] && mouseX <= positionSE[2] + positionSE[0] && mouseY >= positionSE[3] && mouseY <= positionSE[3] + positionSE[1] && gp.gameState != gp.startState) {
                    isSoundEffectEnabled = !isSoundEffectEnabled;
                }

                int[] positionSubmit = getBoundPosition(imageSubmit,-100,120,1 );
                if (mouseX >= positionSubmit[2] && mouseX <= positionSubmit[2] + positionSubmit[0] && mouseY >= positionSubmit[3] && mouseY <= positionSubmit[3] + positionSubmit[1] && gp.gameState != gp.startState) {
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
                }

                int[] positionCancel = getBoundPosition(imageCancel,95,120,1 );
                if (mouseX >= positionCancel[2] && mouseX <= positionCancel[2] + positionCancel[0] && mouseY >= positionCancel[3] && mouseY <= positionCancel[3] + positionCancel[1] && gp.gameState != gp.startState) {
                    isCancel = !isCancel;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            gp.gameState = gp.playState;
                            isMusicEnabled = false;
                            isSoundEffectEnabled = false;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
            }
            if (drawMainMenu) {
                int[] positionPause = getBoundPosition(imagePause,0,-90,1 );
                if (mouseX >= positionPause[2] && mouseX <= positionPause[2] + positionPause[0] && mouseY >= positionPause[3] && mouseY <= positionPause[3] + positionPause[1] && gp.gameState != gp.startState) {
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
                }

                int[] positionOption = getBoundPosition(imageOption,0,0,1 );
                if (mouseX >= positionOption[2] && mouseX <= positionOption[2] + positionOption[0] && mouseY >= positionOption[3] && mouseY <= positionOption[3] + positionOption[1] && gp.gameState != gp.startState) {
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
                if (mouseX >= positionQuit[2] && mouseX <= positionQuit[2] + positionQuit[0] && mouseY >= positionQuit[3] && mouseY <= positionQuit[3] + positionQuit[1] && gp.gameState != gp.startState) {
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
                }
            }
        }
    }
    public void drawSettingMenuStart(Graphics2D g2){
        BufferedImage settingMenuFrame = null;
        BufferedImage settingMenuMusic = null;
        BufferedImage settingMenuMusicIcon = null;
        BufferedImage settingMenuSoundEffect = null;
        BufferedImage settingMenuSEIcon = null;
        BufferedImage settingMenuSubmit = null;
        BufferedImage settingMenuCancel = null;

        try {
            settingMenuFrame = ImageIO.read(getClass().getResourceAsStream("/res/menu/OptionSetting.png"));
            settingMenuMusic = ImageIO.read(getClass().getResourceAsStream("/res/menu/Music.png"));
            settingMenuSoundEffect = ImageIO.read(getClass().getResourceAsStream("/res/menu/soundeffect.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawImage(g2, settingMenuFrame, 0, 0, 1);
        drawImage(g2, settingMenuMusic, -85, -45, 1);
        drawImage(g2, settingMenuSoundEffect, -85, 35, 1);

        try {
            if(isMusicStartEnabled) {
                settingMenuMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/OnActive.png"));
            } else {
                settingMenuMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/OffActive.png"));
            }
            if(isSoundEffectStartEnabled){
                settingMenuSEIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/OnActive.png"));
            } else {
                settingMenuSEIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/OffActive.png"));
            }
            if(isSubmitStartEnabled){
                settingMenuSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/SubmitActive.png"));
            } else {
                settingMenuSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Submit.png"));
            }
            if(isCancelStartEnabled){
                settingMenuCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/CancelActive.png"));
            } else {
                settingMenuCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/Cancel.png"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        drawImage(g2, settingMenuMusicIcon, 80, -45, 1);
        drawImage(g2, settingMenuSEIcon, 80, 35, 1);
        drawImage(g2, settingMenuSubmit, -100, 120, 1);
        drawImage(g2, settingMenuCancel, 95, 120, 1);

        isSubmitStartEnabled = false;
        isCancelStartEnabled = false;
    }

    public void drawAboutMenuStart(Graphics2D g2){
        BufferedImage aboutMenuFrame = null;
        BufferedImage aboutMenuClose = null;

        try {
            aboutMenuFrame = ImageIO.read(getClass().getResourceAsStream("/res/menu/AboutSetting.png"));
            aboutMenuClose = ImageIO.read(getClass().getResourceAsStream("/res/menu/Cancel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawImage(g2, aboutMenuFrame, 0, 0, 1);
        drawImage(g2, aboutMenuClose, 120, 145, 1);
    }

    public void drawExitMenuStart(Graphics2D g2){
        BufferedImage exitMenuFrame = null;
        BufferedImage exitMenuSubmit = null;
        BufferedImage exitMenuCancel = null;
        try {
            exitMenuFrame = ImageIO.read(getClass().getResourceAsStream("/res/menu/Are you exit.png"));
            if(isSubmitExitStart) {
                exitMenuSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/SubmitActive.png"));
            } else {
                exitMenuSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Submit.png"));
            }
            if(isCancelExitStart) {
                exitMenuCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/CancelActive.png"));
            } else {
                exitMenuCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/Cancel.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawImage(g2, exitMenuFrame, 0, 0, 1);
        drawImage(g2, exitMenuSubmit, -80, 50, 1);
        drawImage(g2, exitMenuCancel, 80, 50, 1);

        isSubmitExitStart = false;
        isCancelExitStart = false;
    }

    public void drawStartMenu(Graphics2D g2) {
        BufferedImage backgroundImage = null;
        BufferedImage playButtonImage = null;
        BufferedImage settingButtonImage = null;
        BufferedImage aboutButtonImage = null;
        BufferedImage exitButtonImage = null;
        try {
            if(isPlayEnabled){
                playButtonImage = ImageIO.read(getClass().getResourceAsStream("/res/menu/PlayActive.png"));
            } else {
                playButtonImage = ImageIO.read(getClass().getResourceAsStream("/res/menu/Play.png"));
            }
            if(isSettingEnabled){
                settingButtonImage = ImageIO.read(getClass().getResourceAsStream("/res/menu/SettingActive.png"));
            } else {
                settingButtonImage = ImageIO.read(getClass().getResourceAsStream("/res/menu/Setting.png"));
            }
            if(isAboutEnabled){
                aboutButtonImage = ImageIO.read(getClass().getResourceAsStream("/res/menu/AboutActive.png"));
            } else {
                aboutButtonImage = ImageIO.read(getClass().getResourceAsStream("/res/menu/About.png"));
            }
            if(isExitEnabled){
                exitButtonImage = ImageIO.read(getClass().getResourceAsStream("/res/menu/ExitActive.png"));
            } else {
                exitButtonImage = ImageIO.read(getClass().getResourceAsStream("/res/menu/Exit.png"));
            }
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/res/menu/BackgroundStartMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int widthBackground = gp.screenWidth;
        int heightBackground = gp.screenHeight;
        g2.drawImage(backgroundImage, 0, 0, widthBackground, heightBackground, null);
        drawImage(g2, playButtonImage, 350, -300 , 1);
        drawImage(g2, settingButtonImage, 245, -220 , 1);
        drawImage(g2, aboutButtonImage, 350, -220 , 1);
        drawImage(g2, exitButtonImage, 455, -220 , 1);

        isPlayEnabled = false;
        isSettingEnabled = false;
        isAboutEnabled = false;
        isExitEnabled = false;
    }

    public void drawMenuSetting(Graphics2D g2) {
        BufferedImage imageSetting = null;
        BufferedImage imagePause = null;
        BufferedImage imageOption = null;
        BufferedImage imageQuit = null;
        try {
            imageSetting = ImageIO.read(getClass().getResourceAsStream("/res/menu/BasicSetting.png"));
            if(isPauseEnabled) {
                imagePause = ImageIO.read(getClass().getResourceAsStream("/res/menu/ResumeActive.png"));
            } else {
                imagePause = ImageIO.read(getClass().getResourceAsStream("/res/menu/Resume.png"));
            }
            if(isOptionEnabled) {
                imageOption = ImageIO.read(getClass().getResourceAsStream("/res/menu/OptionActive.png"));
            } else {
                imageOption = ImageIO.read(getClass().getResourceAsStream("/res/menu/Option.png"));
            }
            if(isQuitEnabled) {
                imageQuit = ImageIO.read(getClass().getResourceAsStream("/res/menu/QuitActive.png"));
            } else {
                imageQuit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Quit.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawImage(g2, imageSetting, 0, 0 , 1);
        drawImage(g2, imagePause, 0, -90 , 1);
        drawImage(g2, imageOption, 0, 0 , 1);
        drawImage(g2, imageQuit, 0, 90 , 1);
        isPauseEnabled = false;
        isOptionEnabled = false;
        isQuitEnabled = false;
    }

    public void drawSubMenuSetting(Graphics2D g2) {
        BufferedImage imageSetting = null;
        BufferedImage imageMusic = null;
        BufferedImage imageMusicIcon = null;
        BufferedImage imageSoundEffect = null;
        BufferedImage imageSoundEffectIcon = null;
        BufferedImage imageSubmit = null;
        BufferedImage imageCancel = null;

        try {
            if (isMusicEnabled) {
                imageMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/OnActive.png"));
            } else {
                imageMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/OffActive.png"));
            }
            if (isSoundEffectEnabled) {
                imageSoundEffectIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/OnActive.png"));
            }
            else {
                imageSoundEffectIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/OffActive.png"));
            }
            if (isSubmit) {
                imageSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/SubmitActive.png"));
            } else {
                imageSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Submit.png"));
            }
            if (isCancel) {
                imageCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/CancelActive.png"));
            } else {
                imageCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/Cancel.png"));
            }
            imageSetting = ImageIO.read(getClass().getResourceAsStream("/res/menu/OptionSetting.png"));
            imageMusic = ImageIO.read(getClass().getResourceAsStream("/res/menu/Music.png"));
            imageSoundEffect = ImageIO.read(getClass().getResourceAsStream("/res/menu/soundeffect.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        drawImage(g2, imageSetting, 0, 0 , 1);
        drawImage(g2, imageMusic, -85, -45 , 1);
        drawImage(g2, imageMusicIcon, 80, -45 , 1);
        drawImage(g2, imageSoundEffect, -85, 35 , 1);
        drawImage(g2, imageSoundEffectIcon, 80, 35 , 1);
        drawImage(g2, imageSubmit, -100, 120 , 1);
        drawImage(g2, imageCancel, 95, 120 , 1);
        isSubmit = false;
        isCancel = false;
    }
    public void drawSubmitMenuSetting(Graphics2D g2) {
        BufferedImage imageSetting = null;
        BufferedImage imageSubmit = null;
        BufferedImage imageCancel = null;
        try {
            imageSetting = ImageIO.read(getClass().getResourceAsStream("/res/menu/SubmitExitSetting.png"));
            if (isSubmitExit) {
                imageSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/SubmitActive.png"));
            } else {
                imageSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Submit.png"));
            }
            if (isCancelExit) {
                imageCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/CancelActive.png"));
            } else {
                imageCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/Cancel.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawImage(g2, imageSetting, 0, 0 , 1);
        drawImage(g2, imageSubmit, -80, 50 , 1);
        drawImage(g2, imageCancel, 80, 50 , 1);
        isSubmitExit = false;
        isCancelExit = false;
    }
}
