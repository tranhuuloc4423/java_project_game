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

public class StartMenu extends Menu {
    private boolean drawStartMenu = true;
    private  boolean drawAboutMenu = false;
    private  boolean drawAboutMenuPage2 = false;
    private  boolean drawExitMenu = false;
    private  boolean isAddEnabled = false;
    private  boolean isMinusEnabled = false;
    private  boolean isCloseEnabled = false;

    private boolean isPlayEnabled = false;
    private boolean isSettingEnabled = false;
    private boolean isAboutEnabled = false;
    private boolean isExitEnabled = false;



    // start panel
    BufferedImage backgroundImage, playButtonImage, settingButtonImage, aboutButtonImage, exitButtonImage;
    BufferedImage[] playButtonImages = new BufferedImage[2];
    BufferedImage[] settingButtonImages = new BufferedImage[2];
    BufferedImage[] aboutButtonImages = new BufferedImage[2];
    BufferedImage[] exitButtonImages = new BufferedImage[2];

    // about
    BufferedImage aboutMenuFrame, aboutMenuClose, aboutMenuMinus, aboutMenuAdd;
    BufferedImage[] aboutMenuFrames = new BufferedImage[2];
    BufferedImage[] aboutMenuCloses = new BufferedImage[2];
    BufferedImage[] aboutMenuMinuses = new BufferedImage[2];
    BufferedImage[] aboutMenuAdds = new BufferedImage[2];

    Timer timer;

    public StartMenu(GamePanel gp) {
        super(gp);
        setupPanel();
//        gp.addMouseListener(this);
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.startState) {
            drawStartMenu(g2);
            drawStartMenu = true;
            if (drawSettingMenu) {
                drawOptionPanel(g2);
            } else if(drawAboutMenu) {
                drawAboutMenu(g2);
            } else if(drawAboutMenuPage2){
                drawAboutMenuPage2(g2);
            } else if(drawExitMenu) {
                drawExitMenu(g2);
            }
        }
    }

    public void update() {
        super.update();

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
    }

    public void drawAboutMenuPage2(Graphics2D g2){
        aboutMenuFrame = aboutMenuFrames[0];
        drawImage(g2, aboutMenuFrame, 0, 0, 1);
        drawImage(g2, aboutMenuMinus, -130, 155, 1);
        drawImage(g2, aboutMenuClose, 130, 155, 1);
        isMinusEnabled = false;
        isCloseEnabled = false;
    }

    public void drawAboutMenu(Graphics2D g2){
        aboutMenuFrame = aboutMenuFrames[1];
        drawImage(g2, aboutMenuFrame, 0, 0, 1);
        drawImage(g2, aboutMenuAdd, 130, 155, 1);
        drawImage(g2, aboutMenuClose, -130, 155, 1);
        isAddEnabled = false;
        isCloseEnabled = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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
                if (drawStartMenu) {
                    int[] positionSetting = getBoundPosition(settingButtonImages[1], 248, -215, 1);
                    if (checkMousePosition(positionSetting, mouseX, mouseY) && !drawAboutMenu && !drawExitMenu && !drawAboutMenuPage2) {
                        isSettingEnabled = !isSettingEnabled;
                        action = () -> drawSettingMenu = true;
                    }
                    int[] positionAbout = getBoundPosition(aboutButtonImages[1], 350, -215, 1);
                    if (checkMousePosition(positionAbout, mouseX, mouseY) && !drawSettingMenu && !drawExitMenu && !drawAboutMenu && !drawAboutMenuPage2) {
                        isAboutEnabled = !isAboutEnabled;
                        action = () -> drawAboutMenu = true;
                    }
                    int[] positionExit = getBoundPosition(exitButtonImages[1], 452, -215, 1);
                    if (checkMousePosition(positionExit, mouseX, mouseY) && !drawAboutMenu && !drawSettingMenu && !drawAboutMenuPage2) {
                        isExitEnabled = !isExitEnabled;
                        action = () -> drawExitMenu = true;
                    }
                    int[] positionPlay = getBoundPosition(playButtonImages[1], 350, -300, 1);
                    if (checkMousePosition(positionPlay, mouseX, mouseY) && !drawSettingMenu && !drawAboutMenu && !drawExitMenu && !drawAboutMenuPage2) {
                        isPlayEnabled = !isPlayEnabled;
                        action = () -> gp.gameState = gp.playState;
                    }
                }
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
                    }

                    int[] positionSubmit = getBoundPosition(settingMenuSEIcons[1],-100,120,1);
                    if (checkMousePosition(positionSubmit, mouseX, mouseY)) {
                        isSubmitEnabled = !isSubmitEnabled;
                        action = () -> drawSettingMenu = false;
                    }

                    int[] positionCancel = getBoundPosition(settingMenuCancels[1],95,120,1 );
                    if (checkMousePosition(positionCancel, mouseX, mouseY)) {
                        isCancelEnabled = !isCancelEnabled;
                        action = () -> {
                            isMusicEnabled = false;
                            gp.music[0].stop();
                            isSoundEffectEnabled = false;
                            drawSettingMenu = false;
                        };
                    }
                }
                if (drawAboutMenuPage2) {
                    int[] positionAboutMinus = getBoundPosition(aboutMenuMinuses[1], -130, 155, 1);
                    if (checkMousePosition(positionAboutMinus, mouseX, mouseY)) {
                        isMinusEnabled = !isMinusEnabled;
                        action = () -> {
                            drawAboutMenu = true;
                            drawAboutMenuPage2 = false;
                        };
                    }

                    int[] positionAboutClose = getBoundPosition(aboutMenuCloses[1], -130, 155, 1);
                    if (checkMousePosition(positionAboutClose, mouseX, mouseY)) {
                        isCloseEnabled = !isCloseEnabled;
                        action = () -> drawAboutMenuPage2 = false;
                    }
                }

                if (drawAboutMenu) {
                    int[] positionAboutAdd = getBoundPosition(aboutMenuAdds[1], 130, 155, 1);
                    if (checkMousePosition(positionAboutAdd, mouseX, mouseY)) {
                        isAddEnabled = !isAddEnabled;
                        action = () -> {
                            drawAboutMenuPage2 = true;
                            drawAboutMenu = false;
                        };
                    }

                    int[] positionAboutExit = getBoundPosition(aboutMenuCloses[1], -130, 155, 1);
                    if (checkMousePosition(positionAboutExit, mouseX, mouseY)) {
                        isCloseEnabled = !isCloseEnabled;
                        action = () -> drawAboutMenu = false;
                    }
                }
                if (drawExitMenu) {
                    int[] positionSubmitExit = getBoundPosition(imageSubmitExits[1],-80,40,1 );
                    if (checkMousePosition(positionSubmitExit, mouseX, mouseY)) {
                        isSubmitExit = !isSubmitExit;
                        action = () -> System.exit(0);
                    }
                    int[] positionCancelExit = getBoundPosition(imageCancelExits[1],80,40,1 );
                    if (checkMousePosition(positionCancelExit, mouseX, mouseY)) {
                        isCancelExit = !isCancelExit;
                        action = () -> drawExitMenu = false;
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
