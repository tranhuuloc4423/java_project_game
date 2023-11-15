package UI;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
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

    public StartMenu(GamePanel gp) {
        super(gp);
        gp.addMouseListener(this);
    }


    public void drawStartMenu(Graphics2D g2) {
        BufferedImage backgroundImage = null;
        BufferedImage playButtonImage = null;
        BufferedImage settingButtonImage = null;
        BufferedImage aboutButtonImage = null;
        BufferedImage exitButtonImage = null;
        if(isPlayEnabled){
            playButtonImage = setupImage("PlayActive");
        } else {
            playButtonImage = setupImage("Play");
        }
        if(isSettingEnabled){
            settingButtonImage = setupImage("SettingActive");
        } else {
            settingButtonImage = setupImage("Setting");
        }
        if(isAboutEnabled){
            aboutButtonImage = setupImage("AboutActive");
        } else {
            aboutButtonImage = setupImage("About");
        }
        if(isExitEnabled){
            exitButtonImage = setupImage("CancelActive");
        } else {
            exitButtonImage = setupImage("Cancel");
        }
        backgroundImage = setupImage("BackgroundStartMenu");
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
}
