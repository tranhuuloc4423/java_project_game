package UI;

import Inventory.Item;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu implements MouseListener {
    GamePanel gp;
    public  boolean drawSettingMenu = false;
    public boolean drawExitMenu = false;

    public static boolean isMusicEnabled = true;
    public static boolean isSoundEffectEnabled = true;
    public boolean isSubmitEnabled = false;
    public boolean isCancelEnabled = false;
    public boolean isSubmitExit = false;
    public boolean isCancelExit = false;
    public boolean isSubmit = false;
    public boolean isCancel = false;

    public boolean isAddClicked = false;
    public boolean isMinusClicked = false;

    public static int initVolume = 8;

    // setting panel
    public BufferedImage settingMenuFrame,settingMenuMusic, settingMenuSoundEffect,  settingMenuMusicIcon, settingMenuSEIcon, settingMenuSubmit, settingMenuCancel, settingAdd, settingMinus;
    public BufferedImage[] settingMenuMusicIcons = new BufferedImage[2];
    public BufferedImage[] settingMenuSEIcons = new BufferedImage[2];
    public BufferedImage[] settingMenuSubmits = new BufferedImage[2];
    public BufferedImage[] settingMenuCancels = new BufferedImage[2];
    public BufferedImage[] settingAdds = new BufferedImage[2];
    public BufferedImage[] settingMinuses = new BufferedImage[2];

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
        if(isAddClicked) {
            settingAdd = settingAdds[0];
        } else {
            settingAdd = settingAdds[1];
        }
        if(isMinusClicked) {
            settingMinus = settingMinuses[0];
        } else {
            settingMinus = settingMinuses[1];
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
        settingMenuSubmits[0] = setupImage("SubmitActive", 2);
        settingMenuSubmits[1] = setupImage("Submit", 2);
        settingMenuCancels[0] = setupImage("CancelActive", 2);
        settingMenuCancels[1] = setupImage("Cancel", 2);
        settingMenuMusicIcon = settingMenuMusicIcons[0];
        settingMenuSEIcon = settingMenuSEIcons[0];
        settingMenuSubmit = settingMenuSubmits[0];
        settingMenuCancel = settingMenuCancels[0];
        settingAdds[0] = setupImage("AddActive", 2);
        settingAdds[1] = setupImage("Add", 2);
        settingMinuses[0] = setupImage("MinusActive", 2);
        settingMinuses[1] = setupImage("Minus", 2);
        settingAdd = settingAdds[1];
        settingMinus = settingMinuses[1];


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

    public void drawImage(Graphics2D g2,BufferedImage image, int xSize , int ySize) {
        int x = (gp.screenWidth - image.getWidth()) / 2 + xSize;
        int y = (gp.screenHeight - image.getHeight()) / 2 + ySize;
        g2.drawImage(image, x, y, null);
    }

    public BufferedImage setupImage(String pathName) {
        BufferedImage image;
        BufferedImage scaledIamge = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/ui/"+ pathName +".png"));
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
        drawImage(g2, settingMenuFrame, 0, 0);
        drawImage(g2, settingMenuMusic, -85, -65);
        drawImage(g2, settingMenuSoundEffect, -85, 0);
        drawImage(g2, settingMenuMusicIcon, 80, -65);
        drawImage(g2, settingMenuSEIcon, 80, 0);
        drawImage(g2, settingAdd, 100,65);
        drawImage(g2, settingMinus, -20,65);
        drawImage(g2, settingMenuSubmit, -110, 140);
        drawImage(g2, settingMenuCancel, 110, 140);
        drawText(g2, initVolume, 25, 80);

        isSubmitEnabled = false;
        isCancelEnabled = false;
    }

    public void drawText(Graphics2D g2, int value, int x, int y) {
        Font font = new Font("Arial", Font.BOLD, 40);
        g2.setFont(font);
        g2.setColor(Color.DARK_GRAY);
        int newX = (gp.screenWidth / 2) + x;
        int newY = (gp.screenHeight / 2) + y;
        String text = value < 10 ? "0" + String.valueOf(value) : String.valueOf(value);
        g2.drawString(text, newX, newY);
    }

    public void drawExitMenu(Graphics2D g2) {
        drawImage(g2, exitSetting, 0, 0);
        drawImage(g2, imageSubmitExit, -80, 40);
        drawImage(g2, imageCancelExit, 80, 40);
        isSubmitExit = false;
        isCancelExit = false;
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
