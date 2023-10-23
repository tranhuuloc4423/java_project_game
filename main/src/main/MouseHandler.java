package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    GamePanel gp;
    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // Tính toán vị trí của ô đất
        int tileSize = gp.tileSize; // Kích thước của mỗi ô đất
        int worldX = x + gp.player.worldX - gp.player.screenX;
        int worldY = y + gp.player.worldY - gp.player.screenY;
        int tileX = worldX / tileSize; // Xác định cột của ô đất
        int tileY = worldY / tileSize; // Xác định hàng của ô đất

        // Kiểm tra xem người chơi đã nhấp chuột vào ô đất nào và thực hiện hành động tương ứng
        int tileNum = gp.tileM.getTileNumber(tileX, tileY);
        System.out.println("Clicked tile position: " + tileX + ", " + tileY);
        System.out.println("Tile number: " + tileNum);
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
