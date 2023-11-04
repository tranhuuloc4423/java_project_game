package Tile;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
//    public MouseHandler mouseH;
    private long startTime; // Thời điểm bắt đầu nhấn giữ phím đào đất
    private boolean isDigging; // Biến đánh dấu việc đang đào đất
    private static final long DIGGING_DURATION = 3000;


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[1000];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileManager();
        setupCollisionTiles();
//        loadMap("/res/maps/worldV2.txt");
        loadMap("/res/maps/Map.txt");

//        this.mouseH = gp.mouseH;

    }

    public int getTileNumber(int x, int y) {
        return mapTileNum[x][y];
    }

    public void getTileManager() {
        for(int i = 298; i <= 328; i++) {
            if(i >= 301 && i <= 316) {
                setup(i, String.valueOf(i),true);
            } else {
                setup(i, String.valueOf(i),false);
            }
        }
        for(int i = 478; i <= 512; i++) {
            setup(i, String.valueOf(i),false);
        }
        for(int i = 600; i <= 657; i++) {
            if(i >= 613 && i <= 628) {
                setup(i, String.valueOf(i),true);
            } else {
                setup(i, String.valueOf(i),false);
            }

        }
        setup(658, "659",false);

        setup(659, "659",false);
        for(int i = 994; i <= 996; i++) {
            setup(i, String.valueOf(i),false);
        }
        setup(998, "998",true);
        setup(999, "999",true);
    }

    public void setupCollisionTiles() {
        tile[303].solidArea.x = 32;
        tile[303].solidArea.y = 0;
        tile[303].solidArea.width = 16;
        tile[303].solidArea.height = 48;
    }
    public void setup(int index, String imageName, boolean collision) {
        try{
            Rectangle solidArea = new Rectangle(0 ,0, 48, 48);
            tile[index] = new Tile(solidArea, collision);
            BufferedImage image =  ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName +".png"));
//            tile[index].collision = collision;
//            tile[index].solidArea.x = 0;
//            tile[index].solidArea.y = 0;
//            tile[index].solidArea.width = 48;
//            tile[index].solidArea.height = 48;
            tile[index].image = UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void changeTileImage(int col, int row, int tileIndex) {
        mapTileNum[col][row] = tileIndex;
    }

    public void checkHoe() {
        int col = gp.player.landTileX;
        int row = gp.player.landTileY;
        int dirtTileNum = 600;
        if(gp.tileM.mapTileNum[col][row] == dirtTileNum) {
            if (!isDigging) {
                startTime = System.currentTimeMillis();
                isDigging = true;
            } else {

                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;

                if (elapsedTime >= DIGGING_DURATION) {
                    changeTileImage(col, row, 601);
                    isDigging = false;
                    startTime = 0;
                }
            }
        }
    }

    public void checkWater() {
        int col = gp.player.landTileX;
        int row = gp.player.landTileY;
        int dirtHoed = 601;
        if(gp.tileM.mapTileNum[col][row] == dirtHoed) {
            if (!isDigging) {
                startTime = System.currentTimeMillis();
                isDigging = true;
            } else {

                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;

                if (elapsedTime >= DIGGING_DURATION) {
                    changeTileImage(col, row, 602);
                    isDigging = false;
                    startTime = 0;
                }
            }
        }
    }

    public void update() {
        if(gp.keyH.btn9Pressed) {
            checkHoe();
        }
        if(gp.keyH.btn8Pressed) {
            checkWater();
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

    public void draw(Graphics2D g2) {
        int worldCol = 0, worldRow = 0;
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = gp.tileM.mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
            ) {
                g2.drawImage(gp.tileM.tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
