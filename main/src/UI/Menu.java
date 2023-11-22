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
import java.io.InputStream;
import java.util.Objects;

// Đối tượng quản lý menu và giao diện người dùng
public class Menu implements MouseListener {
    GamePanel gp; // Tham chiếu đến đối tượng GamePanel
    public  boolean drawSettingMenu = false; // Xác định liệu có vẽ menu cài đặt hay không
    public boolean drawExitMenu = false; // Xác định liệu có vẽ menu thoát hay không

    public static boolean isMusicEnabled = true; // Trạng thái âm thanh được kích hoạt hay không
    public static boolean isSoundEffectEnabled = true; // Trạng thái hiệu ứng âm thanh được kích hoạt hay không
    public boolean isSubmitEnabled = false; // Trạng thái nút submit được kích hoạt hay không
    public boolean isCancelEnabled = false; // Trạng thái nút cancel được kích hoạt hay không
    public boolean isSubmitExit = false; // Trạng thái nút submit trong menu thoát được kích hoạt hay không
    public boolean isCancelExit = false; // Trạng thái nút cancel trong menu thoát được kích hoạt hay không
    public boolean isSubmit = false; // Trạng thái nút submit được kích hoạt hay không
    public boolean isCancel = false; // Trạng thái nút cancel được kích hoạt hay không

    public boolean isAddClicked = false; // Trạng thái nút add được nhấn hay không
    public boolean isMinusClicked = false; // Trạng thái nút minus được nhấn hay không

    public static int initVolume = 8;   // Giá trị khởi tạo của âm lượng

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
    // Khởi tạo đối tượng Menu với GamePanel được chuyển vào
    public Menu(GamePanel gp) {
        this.gp = gp;
        setupOptionPanel(); // Khởi tạo các thành phần của menu
        gp.addMouseListener(this); // Đăng ký lắng nghe sự kiện chuột
    }

    // Cập nhật trạng thái của các thành phần trong menu
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

    // Khởi tạo các hình ảnh và biến cho menu cài đặt
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

    // Tính toán vị trí và kích thước của một đối tượng dựa trên vị trí mong muốn và kích thước của màn hình
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
    // Kiểm tra xem chuột có ở trong vùng xác định của một đối tượng hay không
    public boolean checkMousePosition(int[] position, int mouseX, int mouseY) {
        return mouseX >= position[2] && mouseX <= position[2] + position[0] && mouseY >= position[3] && mouseY <= position[3] + position[1];
    }

    // Vẽ một đối tượng BufferedImage lên màn hình tại vị trí và kích thước xác định
    public void drawImage(Graphics2D g2, BufferedImage image, int xSize , int ySize) {
        int x = (gp.screenWidth - image.getWidth()) / 2 + xSize;
        int y = (gp.screenHeight - image.getHeight()) / 2 + ySize;
        g2.drawImage(image, x, y, null);
    }

    // Tải hình ảnh từ tệp nguồn và thay đổi kích thước theo tỉ lệ đã xác định
    public BufferedImage setupImage(String pathName) {
        BufferedImage image;
        BufferedImage scaledImage = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("res/ui/" + pathName + ".png");
            if (inputStream != null) {
                image = ImageIO.read(inputStream);
                int width = image.getWidth() * gp.scale;
                int height = image.getHeight() * gp.scale;
                scaledImage = UtilityTool.scaleImage(image, width, height);
            } else {
                throw new IOException("Could not find resource: " + pathName);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }


    public BufferedImage setupImage(String pathName, int scale) {
        BufferedImage image;
        BufferedImage scaledImage = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("res/ui/" + pathName + ".png");
            if (inputStream != null) {
                image = ImageIO.read(inputStream);
                int width = image.getWidth() * scale;
                int height = image.getHeight() * scale;
                scaledImage = UtilityTool.scaleImage(image, width, height);
            } else {
                throw new IOException("Could not find resource: " + pathName);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

    // Vẽ các thành phần của menu cài đặt lên màn hình
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

    // Vẽ một chuỗi văn bản lên màn hình tại một vị trí xác định
    public void drawText(Graphics2D g2, int value, int x, int y) {
        Font font = new Font("Arial", Font.BOLD, 40);
        g2.setFont(font);
        g2.setColor(Color.DARK_GRAY);
        int newX = (gp.screenWidth / 2) + x;
        int newY = (gp.screenHeight / 2) + y;
        String text = value < 10 ? "0" + String.valueOf(value) : String.valueOf(value);
        g2.drawString(text, newX, newY);
    }

    // Vẽ các thành phần của menu thoát lên màn hình
    public void drawExitMenu(Graphics2D g2) {
        drawImage(g2, exitSetting, 0, 0);
        drawImage(g2, imageSubmitExit, -80, 40);
        drawImage(g2, imageCancelExit, 80, 40);
        isSubmitExit = false;
        isCancelExit = false;
    }

    @Override
    // Phương thức không được triển khai cho sự kiện nhấp chuột
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    // Phương thức không được triển khai cho sự kiện nhấn chuột
    public void mousePressed(MouseEvent e) {

    }

    @Override
    // Phương thức không được triển khai cho sự kiện nhả chuột
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    // Phương thức không được triển khai cho sự kiện chuột nhập vào
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    // Phương thức không được triển khai cho sự kiện chuột rời khỏi
    public void mouseExited(MouseEvent e) {

    }
}
