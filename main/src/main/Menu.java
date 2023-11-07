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
    private boolean drawSubMenu = false;
    private boolean drawMainMenu = true;
    private boolean drawSubmitMenu = false;
    private boolean isPlayEnabled = false;
    private boolean isAboutEnabled = false;
    private boolean isSettingEnabled = false;
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
            if(drawSettingMenu) {

            } else if (drawAboutMenu) {

            } else if (drawExitMenu) {

            }
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
    }

    public void draw(Graphics2D g2) {
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


    private class MouseClickListener extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            isOptionEnabled = false;
            isSubmit = false;
            isCancel = false;
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
                int[] positionPlay = getBoundPosition(imagePlay,350,-300,1 );
                if (mouseX >= positionPlay[2] && mouseX <= positionPlay[2] + positionPlay[0] && mouseY >= positionPlay[3] && mouseY <= positionPlay[3] + positionPlay[1]) {
                    isPlayEnabled = !isPlayEnabled;

                    int delay = 200;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            gp.gameState = gp.playState;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                int[] positionSetting = getBoundPosition(imageSetting,245,-220,1 );
                if (mouseX >= positionSetting[2] && mouseX <= positionSetting[2] + positionSetting[0] && mouseY >= positionSetting[3] && mouseY <= positionSetting[3] + positionSetting[1]) {
                    isSettingEnabled = !isSettingEnabled;

                    int delay = 200;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawSettingMenu = true;
                            drawAboutMenu = false;
                            drawExitMenu = false;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                int[] positionAbout = getBoundPosition(imageAbout,350,-220,1 );
                if (mouseX >= positionAbout[2] && mouseX <= positionAbout[2] + positionAbout[0] && mouseY >= positionAbout[3] && mouseY <= positionAbout[3] + positionAbout[1]) {
                    isAboutEnabled = !isAboutEnabled;

                    int delay = 200;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawSettingMenu = false;
                            drawAboutMenu = true;
                            drawExitMenu = false;;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                int[] positionExit = getBoundPosition(imageExit,455,-220,1 );
                if (mouseX >= positionExit[2] && mouseX <= positionExit[2] + positionExit[0] && mouseY >= positionExit[3] && mouseY <= positionExit[3] + positionExit[1]) {
                    isExitEnabled = !isExitEnabled;

                    int delay = 200;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawSettingMenu = false;
                            drawAboutMenu = false;
                            drawExitMenu = true;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
            }

            if (drawSubmitMenu) {
                int[] positionSubmitExit = getBoundPosition(imageSubmitExit,-80,50,1 );
                if (mouseX >= positionSubmitExit[2] && mouseX <= positionSubmitExit[2] + positionSubmitExit[0] && mouseY >= positionSubmitExit[3] && mouseY <= positionSubmitExit[3] + positionSubmitExit[1]) {
                    isSubmitExit = !isSubmitExit;

                    int delay = 200;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            System.exit(0);
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                int[] positionCancelExit = getBoundPosition(imageCancelExit,80,50,1 );
                if (mouseX >= positionCancelExit[2] && mouseX <= positionCancelExit[2] + positionCancelExit[0] && mouseY >= positionCancelExit[3] && mouseY <= positionCancelExit[3] + positionCancelExit[1]) {
                    isCancelExit = !isCancelExit;
                    int delay = 200;
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
                if (mouseX >= positionMusic[2] && mouseX <= positionMusic[2] + positionMusic[0] && mouseY >= positionMusic[3] && mouseY <= positionMusic[3] + positionMusic[1]) {
                    isMusicEnabled = !isMusicEnabled;
                }

                int[] positionSE = getBoundPosition(imageSoundEffectIcon,80,35,1 );
                if (mouseX >= positionSE[2] && mouseX <= positionSE[2] + positionSE[0] && mouseY >= positionSE[3] && mouseY <= positionSE[3] + positionSE[1]) {
                    isSoundEffectEnabled = !isSoundEffectEnabled;
                }

                int[] positionSubmit = getBoundPosition(imageSubmit,-100,120,1 );
                if (mouseX >= positionSubmit[2] && mouseX <= positionSubmit[2] + positionSubmit[0] && mouseY >= positionSubmit[3] && mouseY <= positionSubmit[3] + positionSubmit[1]) {
                    isSubmit = !isSubmit;
                    int delay = 200;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            gp.gameState = gp.playState;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                int widthCancel = imageCancel.getWidth() * gp.scale;
                int heightCancel = imageCancel.getHeight() * gp.scale;
                int xCancel = (gp.screenWidth - widthCancel) / 2 + 95;
                int yCancel = (gp.screenHeight - heightCancel) / 2 + 120;
                if (mouseX >= xCancel && mouseX <= xCancel + widthCancel && mouseY >= yCancel && mouseY <= yCancel + heightCancel) {
                    isCancel = !isCancel;
                    int delay = 200;
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
            if (drawMainMenu) {
                int widthPause = imagePause.getWidth() * gp.scale;
                int heightPause = imagePause.getHeight() * gp.scale;
                int xPause = (gp.screenWidth - widthPause) / 2;
                int yPause = (gp.screenHeight - heightPause) / 2 - 90;

                if (mouseX >= xPause && mouseX <= xPause + widthPause && mouseY >= yPause && mouseY <= yPause + heightPause) {
                    isPauseEnabled = true;
                    int delay = 200;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            gp.gameState = gp.playState;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                int widthOption = imageOption.getWidth() * gp.scale ;
                int heightOption = imageOption.getHeight() * gp.scale;
                int xOption = (gp.screenWidth - widthOption) / 2;
                int yOption = (gp.screenHeight - heightOption) / 2;

                if (mouseX >= xOption && mouseX <= xOption + widthOption && mouseY >= yOption && mouseY <= yOption + heightOption) {
                    isOptionEnabled = true;
                    int delay = 200;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawSubMenu = true;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                int widthQuit = imageQuit.getWidth() * gp.scale;
                int heightQuit = imageQuit.getHeight() * gp.scale;
                int xQuit = (gp.screenWidth - widthQuit) / 2;
                int yQuit = (gp.screenHeight - heightQuit) / 2 + 90;

                if (mouseX >= xQuit && mouseX <= xQuit + widthQuit && mouseY >= yQuit && mouseY <= yQuit + heightQuit) {
                    isQuitEnabled = true;
                    int delay = 200;
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

    public void drawImage(Graphics2D g2,BufferedImage image, int xSize , int ySize, int size) {
        int w = image.getWidth() * gp.scale * size;
        int h = image.getHeight() * gp.scale * size;
        int x = (gp.screenWidth - w) / 2 + xSize;
        int y = (gp.screenHeight - h) / 2 + ySize;
        g2.drawImage(image, x, y, w, h, null);
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            imageSetting = ImageIO.read(getClass().getResourceAsStream("/res/menu/OptionSetting.png"));
            imageMusic = ImageIO.read(getClass().getResourceAsStream("/res/menu/Music.png"));
            imageSoundEffect = ImageIO.read(getClass().getResourceAsStream("/res/menu/soundeffect.png"));
        } catch (IOException e) {
            e.printStackTrace();
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
