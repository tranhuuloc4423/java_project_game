package UI;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.swing.Timer;

public class Menu extends MouseAdapter implements MouseListener {
    GamePanel gp;
    private boolean drawStartMenu = true;
    private  boolean drawSettingMenu = false;
    private  boolean drawAboutMenu = false;
    private  boolean drawAboutMenuPage2 = false;
    private  boolean drawExitMenu = false;

    public boolean isMusicStartEnabled = true;
    public boolean isSoundEffectStartEnabled = false;
    private boolean isSubmitStartEnabled = false;
    private boolean isCancelStartEnabled = false;
    private  boolean isAddEnabled = false;
    private  boolean isMinusEnabled = false;
    private  boolean isCloseEnabled = false;
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

    public boolean isMusicEnabled = true;
    public boolean isSoundEffectEnabled = true;
    private boolean isSubmit = false;
    private boolean isCancel = false;

    private boolean isSubmitExit = false;
    private boolean isCancelExit = false;
    public Menu(GamePanel gp) {
        this.gp = gp;
//        MouseClickListener mouseClickListener = new MouseClickListener();
        gp.addMouseListener(this);
    }
    public void update() {
        isMusicEnabled = isMusicStartEnabled;
        isSoundEffectEnabled = isSoundEffectStartEnabled;
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.startState) {
//            drawStartMenu(g2);
            drawStartMenu = true;
            if (drawSettingMenu) {
                drawSettingMenuStart(g2);
            } else if(drawAboutMenu) {
                drawAboutMenuStart(g2);
            } else if(drawAboutMenuPage2){
                drawAboutMenuStartPage2(g2);
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
            if (gp.keyH.menuPressed) {
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
    public boolean checkMousePosition(int[] position, int mouseX, int mouseY) {
        return mouseX >= position[2] && mouseX <= position[2] + position[0] && mouseY >= position[3] && mouseY <= position[3] + position[1];
    }

    public void drawImage(Graphics2D g2,BufferedImage image, int xSize , int ySize, int size) {
        int x = (gp.screenWidth - image.getWidth()) / 2 + xSize;
        int y = (gp.screenHeight - image.getHeight()) / 2 + ySize;
        g2.drawImage(image, x, y, null);
    }

    public BufferedImage setupImage(String pathName) {
        BufferedImage image;
        BufferedImage scaledIamge = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/menu/"+ pathName +".png"));
            int width = image.getWidth() * gp.scale;
            int height = image.getHeight() * gp.scale;
            scaledIamge = UtilityTool.scaleImage(image, width, height);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return scaledIamge;
    }

    public BufferedImage setupImage(String pathName, int scale) {
        BufferedImage image;
        BufferedImage scaledIamge = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/menu/"+ pathName +".png"));
            int width = image.getWidth() * scale;
            int height = image.getHeight() * scale;
            scaledIamge = UtilityTool.scaleImage(image, width, height);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return scaledIamge;
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

            BufferedImage imageAboutExit = null;
            BufferedImage imageAboutAdd = null;

            BufferedImage imageAboutPage2Close = null;
            BufferedImage imageAboutPage2Minus = null;

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
                imagePlay = setupImage("Play");
                imageSetting = setupImage("Setting");
                imageAbout = setupImage("About");
                imageExit = setupImage("Cancel");
                imageMusicIconStart = ImageIO.read(getClass().getResourceAsStream("/res/menu/OffActive.png"));
                imageSoundEffectIconStart = ImageIO.read(getClass().getResourceAsStream("/res/menu/OffActive.png"));
                imageSubmitStart = ImageIO.read(getClass().getResourceAsStream("/res/menu/Submit.png"));
                imageCancelStart = ImageIO.read(getClass().getResourceAsStream("/res/menu/Cancel.png"));
                imageAboutAdd = ImageIO.read(getClass().getResourceAsStream("/res/menu/Add.png"));
                imageAboutExit = ImageIO.read(getClass().getResourceAsStream("/res/menu/Close.png"));
                imageAboutPage2Minus = ImageIO.read(getClass().getResourceAsStream("/res/menu/Minus.png"));
                imageAboutPage2Close = ImageIO.read(getClass().getResourceAsStream("/res/menu/Close.png"));
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
                int[] positionSetting = getBoundPosition(imageSetting, 248, -215, 1);
                int[] positionAbout = getBoundPosition(imageAbout, 350, -215, 1);
                int[] positionExit = getBoundPosition(imageExit, 452, -215, 1);
                int[] positionPlay = getBoundPosition(imagePlay, 350, -300, 1);

                if (checkMousePosition(positionSetting, mouseX, mouseY) && !drawAboutMenu && !drawExitMenu && !drawAboutMenuPage2) {
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

                if (checkMousePosition(positionAbout, mouseX, mouseY) && !drawSettingMenu && !drawExitMenu && !drawAboutMenu && !drawAboutMenuPage2) {
                    isAboutEnabled = !isAboutEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawAboutMenu = true;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                if (checkMousePosition(positionExit, mouseX, mouseY) && !drawAboutMenu && !drawSettingMenu && !drawAboutMenuPage2) {
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

                if (checkMousePosition(positionPlay, mouseX, mouseY) && !drawSettingMenu && !drawAboutMenu && !drawExitMenu && !drawAboutMenuPage2) {
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
                int[] positionSEStart = getBoundPosition(imageSoundEffectIconStart,80,35,1 );
                int[] positionSubmit = getBoundPosition(imageSubmitStart,-100,120,1 );
                int[] positionCancel = getBoundPosition(imageCancelStart,95,120,1 );
                if (checkMousePosition(positionMusicStart, mouseX, mouseY)) {
                    isMusicStartEnabled = !isMusicStartEnabled;
                    if(!isMusicStartEnabled) {
                        gp.music[0].stop();
                    } else {
                        gp.music[0].playMusic();
                    }
                }
                if (checkMousePosition(positionSEStart, mouseX, mouseY)) {
                    isSoundEffectStartEnabled = !isSoundEffectStartEnabled;
                }
                if (checkMousePosition(positionSubmit, mouseX, mouseY)) {
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
                if (checkMousePosition(positionCancel, mouseX, mouseY)) {
                    isCancelStartEnabled = !isCancelStartEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            isMusicStartEnabled = false;
                            gp.music[0].stop();
                            isSoundEffectStartEnabled = false;
                            drawSettingMenu = false;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
            }

            if (drawAboutMenuPage2) {
                int wMinus = imageAboutPage2Minus.getWidth() * 2;
                int hMinus = imageAboutPage2Minus.getHeight() * 2;
                int xMinus = (gp.screenWidth - wMinus) / 2 - 130;
                int yMinus = (gp.screenHeight - hMinus) / 2 + 155;
                if (mouseX >= xMinus && mouseX <= xMinus + wMinus && mouseY >= yMinus && mouseY <= yMinus + hMinus) {
                    isMinusEnabled = !isMinusEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawAboutMenu = true;
                            drawAboutMenuPage2 = false;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                int wClose = imageAboutPage2Close.getWidth() * 2;
                int hClose = imageAboutPage2Close.getHeight() * 2;
                int xClose = (gp.screenWidth - wClose) / 2 - 130;
                int yClose = (gp.screenHeight - hClose) / 2 + 155;
                if (mouseX >= xClose && mouseX <= xClose + wClose && mouseY >= yClose && mouseY <= yClose + hClose) {
                    isCloseEnabled = !isCloseEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawAboutMenuPage2 = false;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
            }

            if(drawAboutMenu) {
                int wAdd = imageAboutAdd.getWidth() * 2;
                int hAdd = imageAboutAdd.getHeight() * 2;
                int xAdd = (gp.screenWidth - wAdd) / 2 + 130;
                int yAdd = (gp.screenHeight - hAdd) / 2 + 155;
                if (mouseX >= xAdd && mouseX <= xAdd + wAdd && mouseY >= yAdd && mouseY <= yAdd + hAdd) {
                    isAddEnabled = !isAddEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawAboutMenuPage2 = true;
                            drawAboutMenu = false;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }

                int wExit = imageAboutExit.getWidth() * 2;
                int hExit = imageAboutExit.getHeight() * 2;
                int xExit = (gp.screenWidth - wExit) / 2 - 130;
                int yExit = (gp.screenHeight - hExit) / 2 + 155;
                if (mouseX >= xExit && mouseX <= xExit + wExit && mouseY >= yExit && mouseY <= yExit + hExit) {
                    isCloseEnabled = !isCloseEnabled;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawAboutMenu = false;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
            }

            if(drawExitMenu) {
                int[] positionSubmitExit = getBoundPosition(imageSubmitExitStart,-80,40,1 );
                if (checkMousePosition(positionSubmitExit, mouseX, mouseY)) {
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
                int[] positionCancelExit = getBoundPosition(imageCancelExitStart,80,40,1 );
                if (checkMousePosition(positionCancelExit, mouseX, mouseY)) {
                    isCancelExitStart = !isCancelExitStart;
                    int delay = 150;
                    ActionListener emptyAction = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            drawExitMenu = false;
                        }
                    };
                    Timer timer = new Timer(delay, emptyAction);
                    timer.setRepeats(false);
                    timer.start();
                }
            }

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
                }
            }

            if (drawSubMenu) {
                int[] positionMusic = getBoundPosition(imageMusicIcon,80,-45,1 );
                if (checkMousePosition(positionMusic, mouseX, mouseY) && gp.gameState != gp.startState) {
                    isMusicEnabled = !isMusicEnabled;
                    if(!isMusicEnabled) {
                        gp.music[0].stop();
                    } else {
                        gp.music[0].playMusic();
                    }
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

        settingMenuFrame = setupImage("OptionSetting");
        settingMenuMusic = setupImage("Music");
        settingMenuSoundEffect = setupImage("soundeffect");


        if(isMusicStartEnabled) {
            settingMenuMusicIcon = setupImage("OnActive");
        } else {
            settingMenuMusicIcon = setupImage("OffActive");
        }
        if(isSoundEffectStartEnabled){
            settingMenuSEIcon = setupImage("OnActive");
        } else {
            settingMenuSEIcon = setupImage("OffActive");
        }
        if(isSubmitStartEnabled){
            settingMenuSubmit = setupImage("SubmitActive");
        } else {
            settingMenuSubmit = setupImage("Submit");
        }
        if(isCancelStartEnabled){
            settingMenuCancel = setupImage("CancelActive");
        } else {
            settingMenuCancel = setupImage("Cancel");
        }

        drawImage(g2, settingMenuFrame, 0, 0, 1);
        drawImage(g2, settingMenuMusic, -85, -45, 1);
        drawImage(g2, settingMenuSoundEffect, -85, 35, 1);
        drawImage(g2, settingMenuMusicIcon, 80, -45, 1);
        drawImage(g2, settingMenuSEIcon, 80, 35, 1);
        drawImage(g2, settingMenuSubmit, -100, 120, 1);
        drawImage(g2, settingMenuCancel, 95, 120, 1);

        isSubmitStartEnabled = false;
        isCancelStartEnabled = false;
    }
    public void drawAboutMenuStartPage2(Graphics2D g2){
        BufferedImage aboutMenuFrame = null;
        BufferedImage aboutMenuClose = null;
        BufferedImage aboutMenuMinus = null;

        try {
            if(isMinusEnabled) {
                aboutMenuMinus = ImageIO.read(getClass().getResourceAsStream("/res/menu/MinusActive.png"));
            } else {
                aboutMenuMinus = ImageIO.read(getClass().getResourceAsStream("/res/menu/Minus.png"));
            }
            if(isCloseEnabled) {
                aboutMenuClose = ImageIO.read(getClass().getResourceAsStream("/res/menu/CloseActive.png"));

            } else {
                aboutMenuClose = ImageIO.read(getClass().getResourceAsStream("/res/menu/Close.png"));

            }
            aboutMenuFrame = ImageIO.read(getClass().getResourceAsStream("/res/menu/AboutSetting_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawImage(g2, aboutMenuFrame, 0, 0, 1);
        int wMinus = aboutMenuMinus.getWidth() * 2;
        int hMinus = aboutMenuMinus.getHeight() * 2;
        int xMinus = (gp.screenWidth - wMinus) / 2 - 130;
        int yMinus = (gp.screenHeight - hMinus) / 2 + 155;
        g2.drawImage(aboutMenuMinus, xMinus, yMinus, wMinus, hMinus, null);

        int wClose = aboutMenuClose.getWidth() * 2;
        int hClose = aboutMenuClose.getHeight() * 2;
        int xClose = (gp.screenWidth - wClose) / 2 + 130;
        int yClose = (gp.screenHeight - hClose) / 2 + 155;
        g2.drawImage(aboutMenuClose, xClose, yClose, wClose, hClose, null);

        isMinusEnabled = false;
        isCloseEnabled = false;
    }
    public void drawAboutMenuStart(Graphics2D g2){
        BufferedImage aboutMenuFrame = null;
        BufferedImage aboutMenuClose = null;
        BufferedImage aboutMenuAdd = null;

        try {
            aboutMenuFrame = ImageIO.read(getClass().getResourceAsStream("/res/menu/AboutSetting.png"));
            if(isCloseEnabled) {
                aboutMenuClose = ImageIO.read(getClass().getResourceAsStream("/res/menu/CloseActive.png"));
            } else {
                aboutMenuClose = ImageIO.read(getClass().getResourceAsStream("/res/menu/Close.png"));
            }
            if(isAddEnabled) {
                aboutMenuAdd = ImageIO.read(getClass().getResourceAsStream("/res/menu/AddActive.png"));
            } else {
                aboutMenuAdd = ImageIO.read(getClass().getResourceAsStream("/res/menu/Add.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawImage(g2, aboutMenuFrame, 0, 0, 1);
        int wAdd = aboutMenuAdd.getWidth() * 2;
        int hAdd = aboutMenuAdd.getHeight() * 2;
        int xAdd = (gp.screenWidth - wAdd) / 2 + 130;
        int yAdd = (gp.screenHeight - hAdd) / 2 + 155;
        g2.drawImage(aboutMenuAdd, xAdd, yAdd, wAdd, hAdd, null);

        int wClose = aboutMenuClose.getWidth() * 2;
        int hClose = aboutMenuClose.getHeight() * 2;
        int xClose = (gp.screenWidth - wClose) / 2 - 130;
        int yClose = (gp.screenHeight - hClose) / 2 + 155;
        g2.drawImage(aboutMenuClose, xClose, yClose, wClose, hClose, null);

        isAddEnabled = false;
        isCloseEnabled = false;
    }

    public void drawExitMenuStart(Graphics2D g2){
        BufferedImage exitMenuFrame = null;
        BufferedImage exitMenuSubmit = null;
        BufferedImage exitMenuCancel = null;
        try {
            exitMenuFrame = ImageIO.read(getClass().getResourceAsStream("/res/menu/SubmitExitSetting.png"));
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
        drawImage(g2, exitMenuSubmit, -80, 40, 1);
        drawImage(g2, exitMenuCancel, 80, 40, 1);

        isSubmitExitStart = false;
        isCancelExitStart = false;
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
        drawImage(g2, imageSubmit, -80, 40 , 1);
        drawImage(g2, imageCancel, 80, 40 , 1);
        isSubmitExit = false;
        isCancelExit = false;
    }
}
