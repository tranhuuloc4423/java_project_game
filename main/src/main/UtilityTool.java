package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        int type = BufferedImage.TYPE_INT_ARGB; // Sử dụng kiểu dữ liệu màu sắc ARGB

        BufferedImage scaledImage = new BufferedImage(width, height, type);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

    public static BufferedImage scaleImage(BufferedImage original,int x, int y, int width, int height) {
        int type = BufferedImage.TYPE_INT_ARGB; // Sử dụng kiểu dữ liệu màu sắc ARGB

        BufferedImage scaledImage = new BufferedImage(width, height, type);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, x, y, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}
