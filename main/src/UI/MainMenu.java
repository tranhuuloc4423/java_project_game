package UI;

import main.GamePanel;
import main.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

// Lớp MainMenu kế thừa từ Menu và quản lý menu chính của trò chơi
public class MainMenu extends Menu  {

    private boolean drawMainMenu = true; // Xác định liệu có vẽ menu chính hay không
    private boolean isPauseEnabled = false; // Trạng thái nút pause được kích hoạt hay không
    private boolean isOptionEnabled = false; // Trạng thái nút option được kích hoạt hay không
    private boolean isQuitEnabled = false; // Trạng thái nút quit được kích hoạt hay không

    // panel
    BufferedImage panel, imagePause, imageOption, imageQuit; // Hình ảnh của panel và các nút trong menu
    BufferedImage[] imagePauses = new BufferedImage[2];
    BufferedImage[] imageOptions = new BufferedImage[2];
    BufferedImage[] imageQuits = new BufferedImage[2];
    Timer timer; // Timer để xử lý các hành động trễ

    // exit

    // Khởi tạo đối tượng MainMenu với GamePanel được chuyển vào
    public MainMenu(GamePanel gp) {
        super(gp);
        setupPanel(); // Khởi tạo các thành phần của menu chính
    }

    // Vẽ menu chính lên màn hình dựa trên trạng thái của game
    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.playState) {
            drawSettingMenu = false;
            drawExitMenu = false;
            drawMainMenu = true;
        }
        if (gp.gameState == gp.pauseState) {
            if (gp.keyH.menuPressed) {
                if (drawSettingMenu) {
                    drawOptionPanel(g2);
                    drawMainMenu = false;
                    drawExitMenu = false;
                } else if (drawMainMenu) {
                    drawPanel(g2);
                    drawSettingMenu = false;
                    drawExitMenu = false;
                } else if (drawExitMenu) {
                    drawExitMenu(g2);
                    drawMainMenu = false;
                    drawSettingMenu = false;
                }
            }
        }
    }
    // Cập nhật trạng thái các thành phần trong menu chính
    public void update() {
        super.update();
        // panel
        if(isPauseEnabled) {
            imagePause = imagePauses[0];
        } else {
            imagePause = imagePauses[1];
        }
        if(isOptionEnabled) {
            imageOption = imageOptions[0];
        } else {
            imageOption = imageOptions[1];
        }
        if(isQuitEnabled) {
            imageQuit = imageQuits[0];
        } else {
            imageQuit = imageQuits[1];
        }
    }
    // Khởi tạo hình ảnh và biến cho menu chính
    public void setupPanel() {
        // panel
        panel = setupImage("BasicSetting");
        imagePauses[0] = setupImage("ResumeActive");
        imagePauses[1] = setupImage("Resume");
        imageOptions[0] = setupImage("OptionActive");
        imageOptions[1] = setupImage("Option");
        imageQuits[0] = setupImage("QuitActive");
        imageQuits[1] = setupImage("Quit");
        imagePause = imagePauses[0];
        imageOption = imageOptions[0];
        imageQuit = imageQuits[0];
    }
    // Vẽ menu chính lên màn hình
    public void drawPanel(Graphics2D g2) {
        drawImage(g2, panel, 0, 0);
        drawImage(g2, imagePause, 0, -90);
        drawImage(g2, imageOption, 0, 0);
        drawImage(g2, imageQuit, 0, 90);
        isPauseEnabled = false;
        isOptionEnabled = false;
        isQuitEnabled = false;
    }

    @Override
    // Xử lý sự kiện khi chuột được nhả
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
    // Xử lý sự kiện khi chuột được nhấn
    public void mousePressed(MouseEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Runnable action = null;
                int mouseX = e.getX();
                int mouseY = e.getY();
                if (drawExitMenu) {
                    int[] positionSubmitExit = getBoundPosition(imageSubmitExits[1],-80,40,1 );
                    if (checkMousePosition(positionSubmitExit, mouseX, mouseY) && gp.gameState != gp.playState) {
                        isSubmitExit = !isSubmitExit;
                        action = () -> System.exit(0);
                    }
                    int[] positionCancelExit = getBoundPosition(imageCancelExits[1],80,40,1 );
                    if (checkMousePosition(positionCancelExit, mouseX, mouseY) && gp.gameState != gp.playState) {
                        isCancelExit = !isCancelExit;
                        action = () -> gp.gameState = gp.playState;
                    }
                }

                if(drawSettingMenu) {
                    int[] positionMusicIcon = getBoundPosition(settingMenuMusicIcons[1],80,-70,1 );
                    if (checkMousePosition(positionMusicIcon, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isMusicEnabled = !isMusicEnabled;
                        if(isMusicEnabled) {
                            gp.music[0].playMusic();
                        } else {
                            gp.music[0].stop();
                        }
                    }

                    int[] positionSE = getBoundPosition(settingMenuSEIcons[1],80,-10,1 );
                    if (checkMousePosition(positionSE, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isSoundEffectEnabled = !isSoundEffectEnabled;
                    }

                    int[] positionSubmit = getBoundPosition(settingMenuSubmits[1],-110,140,1);
                    if (checkMousePosition(positionSubmit, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isSubmitEnabled = !isSubmitEnabled;
                        action = () -> gp.gameState = gp.playState;
                    }

                    int[] positionCancel = getBoundPosition(settingMenuCancels[1],110,140,1 );
                    if (checkMousePosition(positionCancel, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isCancelEnabled = !isCancelEnabled;
                        action = () -> {
                            gp.gameState = gp.playState;
                            isMusicEnabled = false;
                            isSoundEffectEnabled = false;
                            gp.music[0].stop();
                            drawSettingMenu = false;
                        };
                    }

                    int[] positionAdd = getBoundPosition(settingAdds[1],100,65,1 );
                    if (checkMousePosition(positionAdd, mouseX, mouseY)) {
                        isAddClicked = !isAddClicked;
                        if(initVolume <= 9) {
                            initVolume++;
                        }
                        float volume = (float) initVolume / 10;
                        System.out.println(volume);
                        for(int i = 0; i < gp.music.length; i++) {
                            if(gp.music[i] != null) {
                                Sound.setVolume(gp.music[i].clip, volume);
                            }
                        }
                        action = () -> {
                            isAddClicked = false;
                        };
                    }

                    int[] positionMinus = getBoundPosition(settingMinuses[1],-20,65,1 );
                    if (checkMousePosition(positionMinus, mouseX, mouseY)) {
                        isMinusClicked = !isMinusClicked;
                        if(initVolume != 0) {
                            initVolume--;
                        }
                        float volume = (float) initVolume / 10;
                        System.out.println(volume);
                        for(int i = 0; i < gp.music.length; i++) {
                            if(gp.music[i] != null) {
                                Sound.setVolume(gp.music[i].clip, volume);
                            }
                        }
                        action = () -> {
                            isMinusClicked = false;
                        };
                    }
                }
                if (drawMainMenu) {
                    int[] positionPause = getBoundPosition(imagePause,0,-90,1 );
                    if (checkMousePosition(positionPause, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isPauseEnabled = !isPauseEnabled;
                        action = () -> gp.gameState = gp.playState;
                    }

                    int[] positionOption = getBoundPosition(imageOption,0,0,1 );
                    if (checkMousePosition(positionOption, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isOptionEnabled = !isOptionEnabled;
                        action = () -> drawSettingMenu = true;
                    }

                    int[] positionQuit = getBoundPosition(imageQuit,0,90,1 );
                    if (checkMousePosition(positionQuit, mouseX, mouseY) && gp.gameState != gp.startState) {
                        isQuitEnabled = !isQuitEnabled;
                        action = () -> {
                            drawMainMenu = !drawMainMenu;
                            drawExitMenu = true;
                        };
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
