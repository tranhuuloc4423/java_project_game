package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    GamePanel gp;
    public int tileX = 0, tileY = 0, tileNum = 0;
    public boolean isMouseClicked = false;
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
        tileX = worldX / tileSize; // Xác định cột của ô đất
        tileY = worldY / tileSize; // Xác định hàng của ô đất

        // Kiểm tra xem người chơi đã nhấp chuột vào ô đất nào và thực hiện hành động tương ứng
        tileNum = gp.tileM.getTileNumber(tileX, tileY);
        System.out.println("Clicked tile position: " + tileX + ", " + tileY);
        System.out.println("Tile number: " + tileNum);
        isMouseClicked = true;
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
