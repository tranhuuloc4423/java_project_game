package Tile;

import main.GamePanel;
import main.MouseHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public MouseHandler mouseH;
    public TileManager(GamePanel gp, MouseHandler mouseH) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileManager();
//        loadMap("/res/maps/worldV2.txt");
        loadMap("/res/maps/Map_farm.txt");

        this.mouseH = mouseH;
    }

    public int getTileNumber(int x, int y) {
        return mapTileNum[x][y];
    }

    public void getTileManager() {
        // place holder
        setup(0, "grass00", false);
        setup(1, "grass00", false);
        setup(2, "grass00", false);
        setup(3, "grass00", false);
        setup(4, "grass00", false);
        setup(5, "grass00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);


        // placeholder

        setup(10, "W01", true);
        setup(11, "H03", false);
        setup(12, "H12", false);
        setup(13, "H15", false);
        setup(14, "H29", false);
        setup(15, "H30", false);
        setup(16, "H36", false);
        setup(17, "H37", false);
        setup(18, "H38", false);
        setup(19, "H40", false);
        setup(20, "H42", false);
        setup(21, "H43", false);
        setup(22, "H44", false);
        setup(23, "H45", false);
        setup(24, "H47", false);
        setup(25, "H48", false);
        setup(26, "H49", false);
        setup(27, "H50", false);
        setup(28, "H51", false);
        setup(29, "T09", false);
        setup(30, "T65", false);
        setup(31, "T66", false);
        setup(32, "T67", false);
        setup(33, "T68", false);
        setup(34, "T69", false);
        setup(35, "T70", false);
        setup(36, "T71", false);
        setup(37, "T72", false);
        setup(38, "T73", false);
        setup(39, "T74", false);
        setup(40, "H05", false);
        setup(41, "H06", false);
        setup(42, "H39", false);
        setup(43, "H35", false);
        setup(44, "H41", false);
        setup(45, "H46", false);
        setup(46, "T10", false);
    }
    public void setup(int index, String imageName, boolean collision) {
        UtilityTool tool = new UtilityTool();
        try{
            tile[index] = new Tile();
            BufferedImage image =  ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName +".png"));
//            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName +".png"));
//            tile[index].image = tool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            tile[index].image = image;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void changeTileImage(int col, int row, int tileIndex) {
        mapTileNum[col][row] = tileIndex;
    }

    public void update() {
        int col = mouseH.tileX;
        int row = mouseH.tileY;
        if(row >= 8 && row <= 11) {
            if(col >= 15 && col <= 16 ) {
                changeTileImage(col, row, 46);
            }
        }

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = reader.readLine();
                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            reader.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void plantCrop(int col, int row, Crop crop) {
        Tile targetTile = tile[mapTileNum[col][row]];

        if (targetTile.crop != null) {
            System.out.println("This tile is already occupied.");
        } else {
            targetTile.crop = crop;
        }
    }

    public void loadCropImage(Crop crop) {
        BufferedImage cropImage = null;

        // Tải hình ảnh cây trồng từ tệp tin
        try {
            cropImage = ImageIO.read(getClass().getResourceAsStream("/res/tiles/.png")); // Thay đổi đường dẫn tới tệp tin hình ảnh cây trồng
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int worldCol = 0, worldRow = 0;
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
            ) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
