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

public class Menu implements MouseListener {
    GamePanel gp;
    private boolean drawStartMenu = true;
    public  boolean drawSettingMenu = false;
    public boolean drawExitMenu = false;
    private  boolean drawAboutMenu = false;
    private  boolean drawAboutMenuPage2 = false;

    public static boolean isMusicEnabled = true;
    public static boolean isSoundEffectEnabled = true;
    public boolean isSubmitEnabled = false;
    public boolean isCancelEnabled = false;
    public boolean isSubmitExit = false;
    public boolean isCancelExit = false;
    public boolean isSubmit = false;
    public boolean isCancel = false;

    public BufferedImage settingMenuFrame,settingMenuMusic,settingMenuSoundEffect,  settingMenuMusicIcon, settingMenuSEIcon, settingMenuSubmit, settingMenuCancel;
    public BufferedImage[] settingMenuMusicIcons = new BufferedImage[2];
    public BufferedImage[] settingMenuSEIcons = new BufferedImage[2];
    public BufferedImage[] settingMenuSubmits = new BufferedImage[2];
    public BufferedImage[] settingMenuCancels = new BufferedImage[2];

    // exit
    public BufferedImage exitSetting, imageSubmitExit, imageCancelExit;
    public BufferedImage[] imageSubmitExits = new BufferedImage[2];
    public BufferedImage[] imageCancelExits = new BufferedImage[2];
    public int delay = 150;
    public Menu(GamePanel gp) {
        this.gp = gp;
        setupOptionPanel();
        gp.addMouseListener(this);
    }
    public void update() {
        // option setting
        if(isMusicEnabled) {
            settingMenuMusicIcon = settingMenuMusicIcons[0];
        } else {
            settingMenuMusicIcon = settingMenuMusicIcons[1];
        }
        if(isSoundEffectEnabled){
            settingMenuSEIcon = settingMenuSEIcons[0];
        } else {
            settingMenuSEIcon = settingMenuSEIcons[1];
        }
        if(isSubmitEnabled){
            settingMenuSubmit = settingMenuSubmits[0];
        } else {
            settingMenuSubmit = settingMenuSubmits[1];
        }
        if(isCancelEnabled){
            settingMenuCancel = settingMenuCancels[0];
        } else {
            settingMenuCancel = settingMenuCancels[1];
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

    public void setupOptionPanel() {
        // option panel
        settingMenuFrame = setupImage("OptionSetting");
        settingMenuMusic = setupImage("Music");
        settingMenuSoundEffect = setupImage("soundeffect");
        settingMenuMusicIcons[0] = setupImage("OnActive");
        settingMenuMusicIcons[1] = setupImage("OffActive");
        settingMenuSEIcons[0] = setupImage("OnActive");
        settingMenuSEIcons[1] = setupImage("OffActive");
        settingMenuSubmits[0] = setupImage("SubmitActive");
        settingMenuSubmits[1] = setupImage("Submit");
        settingMenuCancels[0] = setupImage("CancelActive");
        settingMenuCancels[1] = setupImage("Cancel");
        settingMenuMusicIcon = settingMenuMusicIcons[0];
        settingMenuSEIcon = settingMenuSEIcons[0];
        settingMenuSubmit = settingMenuSubmits[0];
        settingMenuCancel = settingMenuCancels[0];

        // exit setting
        exitSetting = setupImage("SubmitExitSetting");
        imageSubmitExits[0] = settingMenuSubmits[0];
        imageSubmitExits[1] = settingMenuSubmits[1];
        imageSubmitExit = imageSubmitExits[0];
        imageCancelExits[0] = settingMenuCancels[0];
        imageCancelExits[1] = settingMenuCancels[1];
        imageCancelExit = imageCancelExits[0];
    }

    public int[] getBoundPosition(BufferedImage image, int xSize , int ySize, int size) {
        int width = image.getWidth();
        int height = image.getHeight();
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

    public void drawOptionPanel(Graphics2D g2){
        drawImage(g2, settingMenuFrame, 0, 0, 1);
        drawImage(g2, settingMenuMusic, -85, -45, 1);
        drawImage(g2, settingMenuSoundEffect, -85, 35, 1);
        drawImage(g2, settingMenuMusicIcon, 80, -45, 1);
        drawImage(g2, settingMenuSEIcon, 80, 35, 1);
        drawImage(g2, settingMenuSubmit, -100, 120, 1);
        drawImage(g2, settingMenuCancel, 95, 120, 1);
        isSubmitEnabled = false;
        isCancelEnabled = false;
    }

    public void drawExitMenu(Graphics2D g2) {
        drawImage(g2, exitSetting, 0, 0 , 1);
        drawImage(g2, imageSubmitExit, -80, 40 , 1);
        drawImage(g2, imageCancelExit, 80, 40 , 1);
        isSubmitExit = false;
        isCancelExit = false;
    }

    public void checkDrawOptionPanel(int mouseX, int mouseY) {
        if(drawSettingMenu) {
            int[] positionMusicIcon = getBoundPosition(settingMenuMusicIcons[1],80,-45,1 );
            if (checkMousePosition(positionMusicIcon, mouseX, mouseY)) {
                isMusicEnabled = !isMusicEnabled;
                if(!isMusicEnabled) {
                    gp.music[0].stop();
                } else {
                    gp.music[0].playMusic();
                }
            }

            int[] positionSEStart = getBoundPosition(settingMenuSoundEffect,80,35,1 );
            if (checkMousePosition(positionSEStart, mouseX, mouseY)) {
                isSoundEffectEnabled = !isSoundEffectEnabled;
                System.out.println(isSoundEffectEnabled);
            }

            int[] positionSubmit = getBoundPosition(settingMenuSEIcons[1],-100,120,1);
            if (checkMousePosition(positionSubmit, mouseX, mouseY)) {
                isSubmitEnabled = !isSubmitEnabled;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        drawSettingMenu = false;
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();
            }

            int[] positionCancel = getBoundPosition(settingMenuCancels[1],95,120,1 );
            if (checkMousePosition(positionCancel, mouseX, mouseY)) {
                isCancelEnabled = !isCancelEnabled;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        isMusicEnabled = false;
                        gp.music[0].stop();
                        isSoundEffectEnabled = false;
                        drawSettingMenu = false;
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();

            }
        }
    }

    public void checkDrawExitMenu(int mouseX, int mouseY, int gameState) {
        if (drawExitMenu) {
            int[] positionSubmitExit = getBoundPosition(imageSubmitExits[1],-80,40,1 );
            if (checkMousePosition(positionSubmitExit, mouseX, mouseY) && gp.gameState != gameState) {
                isSubmitExit = !isSubmitExit;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        System.exit(0);
                    }
                };
                Timer timer = new Timer(delay, emptyAction);
                timer.setRepeats(false);
                timer.start();
                timer = null;
            }
            int[] positionCancelExit = getBoundPosition(imageCancelExits[1],80,40,1 );
            if (checkMousePosition(positionCancelExit, mouseX, mouseY) && gp.gameState != gameState) {
                isCancelExit = !isCancelExit;
                ActionListener emptyAction = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gp.gameState = gameState;
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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
