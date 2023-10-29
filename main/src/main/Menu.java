package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseAdapter;

public class Menu {
    GamePanel gp;
    private boolean drawSubMenu = false;
    private boolean drawMainMenu = true;

    private boolean isOptionPressing = false;
    private boolean isOptionEnabled = false;
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
            drawMainMenu = true;
        }
        if (gp.gameState == gp.pauseState) {
            if (gp.keyH.escapePressed) {
                if (drawSubMenu) {
                    drawSubMenuSetting(g2);
                    drawMainMenu = false;
                } else {
                    if (drawMainMenu) {
                        drawMenuSetting(g2);
                        drawSubMenu = false;
                    }
                }
            }
        }
    }

    private class MouseClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            BufferedImage imageOption = null;
            BufferedImage imageMusicIcon = null;
            BufferedImage imageSoundEffectIcon = null;
            BufferedImage imageSubmit = null;
            BufferedImage imageCancel = null;
            try {
                imageOption = ImageIO.read(getClass().getResourceAsStream("/res/menu/option1.png"));
                imageMusicIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/off1.png"));
                imageSoundEffectIcon = ImageIO.read(getClass().getResourceAsStream("/res/menu/off1.png"));
                imageSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/submit1.png"));
                imageCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/cancel1.png"));
            } catch (IOException e2) {
                e2.printStackTrace();
            }

            if (drawSubMenu) {
                int widthMusic = imageMusicIcon.getWidth() * gp.scale;
                int heightMusic = imageMusicIcon.getHeight() * gp.scale;
                int xMusic = (gp.screenWidth - widthMusic) / 2 + 80;
                int yMusic = (gp.screenHeight - heightMusic) / 2 - 90;

                if (mouseX >= xMusic && mouseX <= xMusic + widthMusic && mouseY >= yMusic && mouseY <= yMusic + heightMusic) {
                    isMusicEnabled = !isMusicEnabled;
                }

                int widthSoundEffect = imageSoundEffectIcon.getWidth() * gp.scale;
                int heightSoundEffect = imageSoundEffectIcon.getHeight() * gp.scale;
                int xSoundEffect = (gp.screenWidth - widthSoundEffect) / 2 + 80;
                int ySoundEffect = (gp.screenHeight - heightSoundEffect) / 2;

                if (mouseX >= xSoundEffect && mouseX <= xSoundEffect + widthSoundEffect && mouseY >= ySoundEffect && mouseY <= ySoundEffect + heightSoundEffect) {
                    isSoundEffectEnabled = !isSoundEffectEnabled;
                }
            }

            int widthOption = imageOption.getWidth() * gp.scale * 8/10;
            int heightOption = imageOption.getHeight() * gp.scale * 8/10;
            int x = (gp.screenWidth - widthOption) / 2;
            int y = (gp.screenHeight - heightOption) / 2;

            if (mouseX >= x && mouseX <= x + widthOption && mouseY >= y && mouseY <= y + heightOption) {
                drawSubMenu = true;
                drawMainMenu = false;
                isOptionEnabled = true;
            }

            int widthSubmit = imageSubmit.getWidth() * gp.scale;
            int heightSubmit = imageSubmit.getHeight() * gp.scale;
            int xSubmit = (gp.screenWidth - widthSubmit) / 2 - 80;
            int ySubmit = (gp.screenHeight - heightSubmit) / 2 + 100;
            if (mouseX >= xSubmit && mouseX <= xSubmit + widthSubmit && mouseY >= ySubmit && mouseY <= ySubmit + heightSubmit) {
                isSubmit = true;
            }

            int widthCancel = imageCancel.getWidth() * gp.scale;
            int heightCancel = imageCancel.getHeight() * gp.scale;
            int xCancel = (gp.screenWidth - widthCancel) / 2 + 80;
            int yCancel = (gp.screenHeight - heightCancel) / 2 + 100;
            if (mouseX >= xCancel && mouseX <= xCancel + widthCancel && mouseY >= yCancel && mouseY <= yCancel + heightCancel) {
                isCancel = true;
            }
        }
    }

    public void drawMenuSetting(Graphics2D g2) {
        BufferedImage imageSetting = null;
        BufferedImage imagePlay = null;
        BufferedImage imageOption = null;
        BufferedImage imageQuit = null;

        try {
            if(isOptionEnabled)
            {
                imageOption = ImageIO.read(getClass().getResourceAsStream("/res/menu/option2.png"));
            } else {
                imageOption = ImageIO.read(getClass().getResourceAsStream("/res/menu/option1.png"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            imageSetting = ImageIO.read(getClass().getResourceAsStream("/res/menu/setting2.png"));
            imagePlay = ImageIO.read(getClass().getResourceAsStream("/res/menu/play1.png"));
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

        drawSubMenu = false;
        drawMainMenu = true;
        isOptionEnabled = false;
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
                //
            }
            else {
                //
            }

            if (isCancel) {
                //
            }
            else {
                //
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            imageSetting = ImageIO.read(getClass().getResourceAsStream("/res/menu/setting2.png"));
            imageMusic = ImageIO.read(getClass().getResourceAsStream("/res/menu/music.png"));
            imageSoundEffect = ImageIO.read(getClass().getResourceAsStream("/res/menu/se.png"));
            imageSubmit = ImageIO.read(getClass().getResourceAsStream("/res/menu/submit1.png"));
            imageCancel = ImageIO.read(getClass().getResourceAsStream("/res/menu/cancel1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int widthSetting = imageSetting.getWidth() * gp.scale;
        int heightSetting = imageSetting.getHeight() * gp.scale;
        int xSetting = (gp.screenWidth - widthSetting) / 2;
        int ySetting = (gp.screenHeight - heightSetting) / 2;
        g2.drawImage(imageSetting, xSetting, ySetting, widthSetting, heightSetting, null);

        int widthMusic = imageMusic.getWidth() * gp.scale * 5/10;
        int heightMusic = imageMusic.getHeight() * gp.scale * 5/10;
        int xMusic = (gp.screenWidth - widthMusic) / 2 - 85;
        int yMusic = (gp.screenHeight - heightMusic) / 2 - 90;
        g2.drawImage(imageMusic, xMusic, yMusic, widthMusic, heightMusic, null);

        int widthMusicIcon = imageMusicIcon.getWidth() * gp.scale;
        int heightMusicIcon = imageMusicIcon.getHeight() * gp.scale;
        int xMusicIcon = (gp.screenWidth - widthMusicIcon) / 2 + 80;
        int yMusicIcon = (gp.screenHeight - heightMusicIcon) / 2 - 90;
        g2.drawImage(imageMusicIcon, xMusicIcon, yMusicIcon, widthMusicIcon, heightMusicIcon, null);

        int widthSoundEffect = imageSoundEffect.getWidth() * gp.scale;
        int heightSoundEffect = imageSoundEffect.getHeight() * gp.scale;
        int xSoundEffect = (gp.screenWidth - widthSoundEffect) / 2 - 85;
        int ySoundEffect = (gp.screenHeight - heightSoundEffect) / 2 ;
        g2.drawImage(imageSoundEffect, xSoundEffect, ySoundEffect, widthSoundEffect, heightSoundEffect, null);

        int widthSoundEffectIcon = imageSoundEffectIcon.getWidth() * gp.scale;
        int heightSoundEffectIcon = imageSoundEffectIcon.getHeight() * gp.scale;
        int xSoundEffectIcon = (gp.screenWidth - widthSoundEffectIcon) / 2 + 80;
        int ySoundEffectIcon = (gp.screenHeight - heightSoundEffectIcon) / 2;
        g2.drawImage(imageSoundEffectIcon, xSoundEffectIcon, ySoundEffectIcon, widthSoundEffectIcon, heightSoundEffectIcon, null);

        int widthSubmit = imageSubmit.getWidth() * gp.scale;
        int heightSubmit = imageSubmit.getHeight() * gp.scale;
        int xSubmit = (gp.screenWidth - widthSubmit) / 2 - 80;
        int ySubmit = (gp.screenHeight - heightSubmit) / 2 + 100;
        g2.drawImage(imageSubmit, xSubmit, ySubmit, widthSubmit, heightSubmit, null);

        int widthCancel = imageCancel.getWidth() * gp.scale;
        int heightCancel = imageCancel.getHeight() * gp.scale;
        int xCancel = (gp.screenWidth - widthCancel) / 2 + 80;
        int yCancel = (gp.screenHeight - heightCancel) / 2 + 100;
        g2.drawImage(imageCancel, xCancel, yCancel, widthCancel, heightCancel, null);

        drawSubMenu = true;
        drawMainMenu = false;
    }
}
