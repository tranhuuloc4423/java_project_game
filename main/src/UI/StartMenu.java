package UI;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartMenu extends Menu implements MouseListener {
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


    // start panel
    BufferedImage backgroundImage, playButtonImage, settingButtonImage, aboutButtonImage, exitButtonImage;
    BufferedImage[] playButtonImages, settingButtonImages, aboutButtonImages, exitButtonImages = new BufferedImage[2];

    // option setting panel
    BufferedImage settingMenuFrame,settingMenuMusic,settingMenuSoundEffect,  settingMenuMusicIcon, settingMenuSEIcon, settingMenuSubmit, settingMenuCancel;
    BufferedImage[] settingMenuMusicIcons, settingMenuSEIcons, settingMenuSubmits, settingMenuCancels = new BufferedImage[2];

    // about
    BufferedImage aboutMenuFrame, aboutMenuClose, aboutMenuMinus, aboutMenuAdd;
    BufferedImage[] aboutMenuFrames, aboutMenuCloses, aboutMenuMinuses, aboutMenuAdds = new BufferedImage[2];

    // exit panel
    BufferedImage exitMenuFrame, exitMenuSubmit, exitMenuCancel;
    BufferedImage[] exitMenuSubmits, exitMenuCancels = new BufferedImage[2];

    public StartMenu(GamePanel gp) {
        super(gp);
        gp.addMouseListener(this);
        setupPanel();
    }

    public void update() {

        // setting option panel

        if(isMusicStartEnabled) {
            settingMenuMusicIcon = settingMenuMusicIcons[0];
        } else {
            settingMenuMusicIcon = settingMenuMusicIcons[1];
        }
        if(isSoundEffectStartEnabled){
            settingMenuSEIcon = settingMenuSEIcons[0];
        } else {
            settingMenuSEIcon = settingMenuSEIcons[1];
        }
        if(isSubmitStartEnabled){
            settingMenuSubmit = settingMenuSubmits[0];
        } else {
            settingMenuSubmit = settingMenuSubmits[1];
        }
        if(isCancelStartEnabled){
            settingMenuCancel = settingMenuCancels[0];
        } else {
            settingMenuCancel = settingMenuCancels[1];
        }

        // start menu
        if(isPlayEnabled){
            playButtonImage = playButtonImages[0];
        } else {
            playButtonImage = playButtonImages[1];
        }
        if(isSettingEnabled){
            settingButtonImage = settingButtonImages[0];
        } else {
            settingButtonImage = settingButtonImages[1];
        }
        if(isAboutEnabled){
            aboutButtonImage = aboutButtonImages[0];
        } else {
            aboutButtonImage = aboutButtonImages[1];
        }
        if(isExitEnabled){
            exitButtonImage = exitButtonImages[0];
        } else {
            exitButtonImage = exitButtonImages[1];
        }



        // about page 2
        if(isMinusEnabled) {
            aboutMenuMinus = aboutMenuMinuses[0];
        } else {
            aboutMenuMinus = aboutMenuMinuses[1];
        }
        if(isCloseEnabled) {
            aboutMenuClose = aboutMenuCloses[0];
        } else {
            aboutMenuClose = aboutMenuCloses[1];
        }
        if(isAddEnabled) {
            aboutMenuAdd = aboutMenuAdds[0];
        } else {
            aboutMenuAdd = aboutMenuAdds[1];
        }

        // exit panel
        if(isSubmitExitStart) {
            exitMenuSubmit = exitMenuSubmits[0];
        } else {
            exitMenuSubmit = exitMenuSubmits[1];
        }
        if(isCancelExitStart) {
            exitMenuCancel = exitMenuCancels[0];
        } else {
            exitMenuCancel = exitMenuCancels[1];
        }
    }

    public void drawStartMenu(Graphics2D g2) {
        int widthBackground = gp.screenWidth;
        int heightBackground = gp.screenHeight;
        g2.drawImage(backgroundImage, 0, 0, widthBackground, heightBackground, null);
        drawImage(g2, playButtonImage, 350, -300 , 1);
        drawImage(g2, settingButtonImage, 248, -215 , 1);
        drawImage(g2, aboutButtonImage, 350, -215 , 1);
        drawImage(g2, exitButtonImage, 452, -215 , 1);
        isPlayEnabled = false;
        isSettingEnabled = false;
        isAboutEnabled = false;
        isExitEnabled = false;
    }

    public void setupPanel() {
        // start menu
        backgroundImage = setupImage("BackgroundStartMenu");
        playButtonImages[0] = setupImage("PlayActive");
        playButtonImages[1] = setupImage("Play");
        settingButtonImages[0] = setupImage("SettingActive");
        settingButtonImages[1] = setupImage("Setting");
        aboutButtonImages[0] = setupImage("AboutActive");
        aboutButtonImages[1] = setupImage("About");
        exitButtonImages[0] = setupImage("CancelActive");
        exitButtonImages[1] = setupImage("Cancel");
        playButtonImage = playButtonImages[0];
        settingButtonImage = settingButtonImages[0];
        aboutButtonImage = aboutButtonImages[0];
        exitButtonImage = exitButtonImages[0];

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

        // about page 2
        aboutMenuFrames[0] = setupImage("AboutSetting");
        aboutMenuFrames[1] = setupImage("AboutSetting_2");
        aboutMenuFrame = aboutMenuFrames[0];
        aboutMenuMinuses[0] = setupImage("MinusActive", 2);
        aboutMenuMinuses[1] = setupImage("Minus", 2);
        aboutMenuCloses[0] = setupImage("CloseActive", 2);
        aboutMenuCloses[1] = setupImage("Close", 2);
        aboutMenuAdds[0] = setupImage("AddActive", 2);
        aboutMenuAdds[1] = setupImage("Add", 2);
        aboutMenuMinus = aboutMenuMinuses[0];
        aboutMenuClose = aboutMenuCloses[0];
        aboutMenuAdd = aboutMenuAdds[0];

        // exit panel
        exitMenuFrame = setupImage("SubmitExitSetting");
        exitMenuSubmits[0] = setupImage("SubmitActive");
        exitMenuSubmits[1] = setupImage("Submit");
        exitMenuCancels[0] = setupImage("CancelActive");
        exitMenuCancels[1] = setupImage("Cancel");
        exitMenuSubmit = exitMenuSubmits[0];
        exitMenuCancel = exitMenuCancels[0];
    }

    public void drawOptionPanel(Graphics2D g2){
        drawImage(g2, settingMenuFrame, 0, 0, 1);
        drawImage(g2, settingMenuMusic, -85, -45, 1);
        drawImage(g2, settingMenuSoundEffect, -85, 35, 1);
        drawImage(g2, settingMenuMusicIcon, 80, -45, 1);
        drawImage(g2, settingMenuSEIcon, 80, 35, 1);
        drawImage(g2, settingMenuSubmit, -100, 120, 1);
        drawImage(g2, settingMenuCancel, 95, 120, 1);
//        isMusicEnabled = false;
//        isSoundEffectEnabled = false;
    }

    public void drawAboutMenuStartPage2(Graphics2D g2){
        aboutMenuFrame = aboutMenuFrames[0];
        drawImage(g2, aboutMenuFrame, 0, 0, 1);
        drawImage(g2, aboutMenuMinus, -130, 155, 1);
        drawImage(g2, aboutMenuClose, 130, 155, 1);
        isMinusEnabled = false;
        isCloseEnabled = false;
    }

    public void drawAboutMenuStart(Graphics2D g2){
        aboutMenuFrame = aboutMenuFrames[1];
        drawImage(g2, aboutMenuFrame, 0, 0, 1);
        drawImage(g2, aboutMenuAdd, 130, 155, 1);
        drawImage(g2, aboutMenuClose, -130, 155, 1);
        isAddEnabled = false;
        isCloseEnabled = false;
    }

    public void drawExitMenuStart(Graphics2D g2){
        drawImage(g2, exitMenuFrame, 0, 0, 1);
        drawImage(g2, exitMenuSubmit, -80, 40, 1);
        drawImage(g2, exitMenuCancel, 80, 40, 1);

        isSubmitExitStart = false;
        isCancelExitStart = false;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if(drawStartMenu) {
            int[] positionSetting = getBoundPosition(settingButtonImages[1], 248, -215, 1);
            int[] positionAbout = getBoundPosition(aboutButtonImages[1], 350, -215, 1);
            int[] positionExit = getBoundPosition(exitButtonImages[1], 452, -215, 1);
            int[] positionPlay = getBoundPosition(playButtonImages[1], 350, -300, 1);
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
                timer = null;
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
                timer = null;
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
                timer = null;

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
                timer = null;

            }
        }
    }
}
