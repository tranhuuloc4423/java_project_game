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
    private boolean drawSubMenu = false;
    private boolean drawSubmitMenu = false;
    private boolean drawMainMenu = true;
    private boolean isOptionEnabled = false;
    private boolean isPlayEnabled = false;
    private boolean isQuitEnabled = false;
    private boolean isMusicEnabled = false;
    private boolean isSoundEffectEnabled = false;
    private boolean isSubmit = false;
    private boolean isCancel = false;

    public Menu(GamePanel gp) {
        this.gp = gp;
        MouseClickListener mouseClickListener = new MouseClickListener();
        gp.addMouseListener(mouseClickListener);
    }

    public void update() {

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
        @Override
        public void mousePressed(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            BufferedImage imagePlay = null;
            BufferedImage imageOption = null;
            BufferedImage imageQuit = null;
            BufferedImage imageMusicIcon = null;
            BufferedImage imageSoundEffectIcon = null;
            BufferedImage imageSubmit = null;
            BufferedImage imageCancel = null;
            BufferedImage imageSubmitExit = null;
            BufferedImage imageCancelExit = null;

            try {
                imagePlay = ImageIO.read(getClass().getResourceAsStream("/res/menu/play1.png"));
                imageOption = ImageIO.read(getClass().getResourceAsStream("/res/menu/option1.png"));
                imageQuit = ImageIO.read(getClass().getResourceAsStream("/res/menu/quit1.png"));
                imageMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/off1.png"));
                imageSoundEffectIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/off1.png"));
                imageSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/submit1.png"));
                imageCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/cancel1.png"));
                imageSubmitExit = ImageIO.read(getClass().getResourceAsStream("/res/menu/submit1.png"));
                imageCancelExit = ImageIO.read(getClass().getResourceAsStream("/res/menu/cancel1.png"));
            } catch (IOException e2) {
                e2.printStackTrace();
            }

            if (drawSubMenu) {
                int widthMusic = imageMusicIcon.getWidth() * gp.scale;
                int heightMusic = imageMusicIcon.getHeight() * gp.scale;
                int xMusic = (gp.screenWidth - widthMusic) / 2 + 80;
                int yMusic = (gp.screenHeight - heightMusic) / 2 - 45;

                if (mouseX >= xMusic && mouseX <= xMusic + widthMusic && mouseY >= yMusic && mouseY <= yMusic + heightMusic) {
                    isMusicEnabled = !isMusicEnabled;
                }

                int widthSoundEffect = imageSoundEffectIcon.getWidth() * gp.scale;
                int heightSoundEffect = imageSoundEffectIcon.getHeight() * gp.scale;
                int xSoundEffect = (gp.screenWidth - widthSoundEffect) / 2 + 80;
                int ySoundEffect = (gp.screenHeight - heightSoundEffect) / 2 + 35;

                if (mouseX >= xSoundEffect && mouseX <= xSoundEffect + widthSoundEffect && mouseY >= ySoundEffect && mouseY <= ySoundEffect + heightSoundEffect) {
                    isSoundEffectEnabled = !isSoundEffectEnabled;
                }

                int widthSubmit = imageSubmit.getWidth() * gp.scale;
                int heightSubmit = imageSubmit.getHeight() * gp.scale;
                int xSubmit = (gp.screenWidth - widthSubmit) / 2 - 100;
                int ySubmit = (gp.screenHeight - heightSubmit) / 2 + 120;
                if (mouseX >= xSubmit && mouseX <= xSubmit + widthSubmit && mouseY >= ySubmit && mouseY <= ySubmit + heightSubmit) {
                    isSubmit = !isSubmit;
                    gp.gameState = gp.playState;
                }

                int widthCancel = imageCancel.getWidth() * gp.scale;
                int heightCancel = imageCancel.getHeight() * gp.scale;
                int xCancel = (gp.screenWidth - widthCancel) / 2 + 95;
                int yCancel = (gp.screenHeight - heightCancel) / 2 + 120;
                if (mouseX >= xCancel && mouseX <= xCancel + widthCancel && mouseY >= yCancel && mouseY <= yCancel + heightCancel) {
                    isCancel = !isCancel;
                    gp.gameState = gp.playState;
                }
            }
            if (drawMainMenu) {
                int widthPlay = imagePlay.getWidth() * gp.scale * 8/10;
                int heightPlay = imagePlay.getHeight() * gp.scale * 8/10;
                int xPlay = (gp.screenWidth - widthPlay) / 2;
                int yPlay = (gp.screenHeight - heightPlay) / 2 - 90;

                if (mouseX >= xPlay && mouseX <= xPlay + widthPlay && mouseY >= yPlay && mouseY <= yPlay + heightPlay) {
                    isPlayEnabled = true;
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

                int widthOption = imageOption.getWidth() * gp.scale * 8/10;
                int heightOption = imageOption.getHeight() * gp.scale * 8/10;
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

                int widthQuit = imageQuit.getWidth() * gp.scale * 8/10;
                int heightQuit = imageQuit.getHeight() * gp.scale * 8/10;
                int xQuit = (gp.screenWidth - widthQuit) / 2;
                int yQuit = (gp.screenHeight - heightQuit) / 2 + 90;

                if (mouseX >= xQuit && mouseX <= xQuit + widthQuit && mouseY >= yQuit && mouseY <= yQuit + heightQuit) {
                    drawSubmitMenu = true;
                    drawMainMenu = !drawMainMenu;
                }
            }
            if (drawSubmitMenu) {
                int widthSubmitExit = imageSubmitExit.getWidth() * gp.scale;
                int heightSubmitExit = imageSubmitExit.getHeight() * gp.scale;
                int xSubmitExit = (gp.screenWidth - widthSubmitExit) / 2 - 80;
                int ySubmitExit = (gp.screenHeight - heightSubmitExit) / 2 + 50;
                if (mouseX >= xSubmitExit && mouseX <= xSubmitExit + widthSubmitExit && mouseY >= ySubmitExit && mouseY <= ySubmitExit + heightSubmitExit) {
                    System.exit(0);
                }

                int widthCancel = imageCancelExit.getWidth() * gp.scale;
                int heightCancel = imageCancelExit.getHeight() * gp.scale;
                int xCancel = (gp.screenWidth - widthCancel) / 2 + 80;
                int yCancel = (gp.screenHeight - heightCancel) / 2 + 50;
                if (mouseX >= xCancel && mouseX <= xCancel + widthCancel && mouseY >= yCancel && mouseY <= yCancel + heightCancel) {
                    isCancel = !isCancel;
                    gp.gameState = gp.playState;
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
        BufferedImage imagePlay = null;
        BufferedImage imageOption = null;
        BufferedImage imageQuit = null;

        try {
            if(isPlayEnabled) {
                imagePlay = ImageIO.read(getClass().getResourceAsStream("/res/menu/play2.png"));
            } else {
                imagePlay = ImageIO.read(getClass().getResourceAsStream("/res/menu/play1.png"));
            }

            if(isOptionEnabled) {
                imageOption = ImageIO.read(getClass().getResourceAsStream("/res/menu/option2.png"));
            } else {
                imageOption = ImageIO.read(getClass().getResourceAsStream("/res/menu/option1.png"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            imageSetting = ImageIO.read(getClass().getResourceAsStream("/res/menu/setting2.png"));
            imageQuit = ImageIO.read(getClass().getResourceAsStream("/res/menu/quit1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int widthSetting = imageSetting.getWidth() * gp.scale;
        int heightSetting = imageSetting.getHeight() * gp.scale;
        int xSetting = (gp.screenWidth - widthSetting) / 2;
        int ySetting = (gp.screenHeight - heightSetting) / 2;
        g2.drawImage(imageSetting, xSetting, ySetting, widthSetting, heightSetting, null);


        int widthPlay = imagePlay.getWidth() * gp.scale * 8/10;
        int heightPlay = imagePlay.getHeight() * gp.scale * 8/10;
        int xPlay = (gp.screenWidth - widthPlay) / 2;
        int yPlay = (gp.screenHeight - heightPlay) / 2 - 90;
        g2.drawImage(imagePlay, xPlay, yPlay, widthPlay, heightPlay, null);

        int widthOption = imageOption.getWidth() * gp.scale * 8/10;
        int heightOption = imageOption.getHeight() * gp.scale * 8/10;
        int xOption = (gp.screenWidth - widthOption) / 2;
        int yOption = (gp.screenHeight - heightOption) / 2;
        g2.drawImage(imageOption, xOption, yOption, widthOption, heightOption, null);

        int widthQuit = imageQuit.getWidth() * gp.scale * 8/10;
        int heightQuit = imageQuit.getHeight() * gp.scale * 8/10;
        int xQuit = (gp.screenWidth - widthQuit) / 2;
        int yQuit = (gp.screenHeight - heightQuit) / 2 + 90;
        g2.drawImage(imageQuit, xQuit, yQuit, widthQuit, heightQuit, null);

        isOptionEnabled = false;
        isPlayEnabled = false;
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
                imageMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/on2.png"));
            } else {
                imageMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/off1.png"));
            }

            if (isSoundEffectEnabled) {
                imageSoundEffectIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/on2.png"));
            }
            else {
                imageSoundEffectIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/off1.png"));
            }

            if (isSubmit) {
                if (isMusicEnabled) {
                    imageMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/on2.png"));
                } else {
                    imageMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/off1.png"));
                }

                if (isSoundEffectEnabled) {
                    imageSoundEffectIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/on2.png"));
                }
                else {
                    imageSoundEffectIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/off1.png"));
                }
            }

            if (isCancel) {

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            imageSetting = ImageIO.read(getClass().getResourceAsStream("/res/menu/setting1.png"));
            imageMusic = ImageIO.read(getClass().getResourceAsStream("/res/menu/music.png"));
            imageSoundEffect = ImageIO.read(getClass().getResourceAsStream("/res/menu/se.png"));
            imageSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/submit1.png"));
            imageCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/cancel1.png"));
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
    }
    public void drawSubmitMenuSetting(Graphics2D g2) {
        BufferedImage imageSetting = null;
        BufferedImage imageSubmit = null;
        BufferedImage imageCancel = null;
        try {
            imageSetting = ImageIO.read(getClass().getResourceAsStream("/res/menu/setting3.png"));
            imageSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/submit1.png"));
            imageCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/cancel1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawImage(g2, imageSetting, 0, 0 , 1);
        drawImage(g2, imageSubmit, -80, 50 , 1);
        drawImage(g2, imageCancel, 80, 50 , 1);
    }
}
