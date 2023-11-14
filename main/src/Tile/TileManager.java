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
    private static final long DIGGING_DURATION = 1500;

    int worldX, worldY;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[1000];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileManager();
//        setupCollisionTiles();
//        setCollisonTile();
//        loadMap("/res/maps/worldV2.txt");
        loadMap("/res/maps/Map.txt");
//        this.mouseH = gp.mouseH;
    }

    public int getTileNumber(int x, int y) {
        return mapTileNum[x][y];
    }

    public void getTileManager() {
        setup(298, "298",false);
        setup(299, "299",true);
        setup(300, "300",false);
        for(int i = 301; i <= 302; i++) {
            setup(i, String.valueOf(i),true);
        }
        for(int i = 303; i <= 304; i++) {
            setup(i, String.valueOf(i),false);
        }
        for(int i = 307; i <= 308; i++) {
            setup(i, String.valueOf(i),false);
        }
        for(int i = 311; i <= 312; i++) {
            setup(i, String.valueOf(i),false);
        }
        for(int i = 315; i <= 323; i++) {
            setup(i, String.valueOf(i),true);
        }
        setup(324, "324",false);
        setup(327, "327",true);
        setup(328, "328",false);

        for(int i = 478; i <= 512; i++) {
            setup(i, String.valueOf(i),false);
        }

        for(int i = 600; i <= 657; i++) {
            if(i >= 613 && i <= 628 || i >= 641 && i <= 643|| i >= 650 && i <= 651) {
                setup(i, String.valueOf(i),true);
            } else {
                setup(i, String.valueOf(i),false);
            }

        }
        setup(657, "657",true);

        setup(659, "659",true);
        for(int i = 994; i <= 996; i++) {
            setup(i, String.valueOf(i),true);
        }
        setup(998, "998",true);
        setup(999, "999",true);
    }
    public void setup(int index, String imageName, boolean collision) {
        try{
            tile[index] = new Tile();
            tile[index].solidArea = new Rectangle(0, 0, 48, 48);
            BufferedImage image =  ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName +".png"));
            tile[index].collision = collision;
            tile[index].image = UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void changeTileImage(int col, int row, int tileIndex) {
        mapTileNum[col][row] = tileIndex;
    }

    public void changTile(int tile,int tileTarget) {
        int col = gp.player.landTileX;
        int row = gp.player.landTileY;
        int[][] map = gp.tileM.mapTileNum;

        int[][] directions = {
                {0, 0},
                {-1, -1},
                {1, 1},
                {1, 0},
                {0, 1},
                {-1, 1},
                {1, -1},
                {-1, 0},
                {0, -1}
        };

        boolean isDirtNearby = false;
        for (int[] dir : directions) {
            int checkCol = col + dir[0];
            int checkRow = row + dir[1];

            if (checkCol >= 0 && checkCol < map.length &&
                    checkRow >= 0 && checkRow < map[0].length &&
                    map[checkCol][checkRow] == tile) {
                isDirtNearby = true;
                break;
            }
        }

        if (isDirtNearby) {
            if (!isDigging) {
                startTime = System.currentTimeMillis();
                isDigging = true;
            } else {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;

                if (elapsedTime >= DIGGING_DURATION) {
                    for (int[] dir : directions) {
                        int tileCol = col + dir[0];
                        int tileRow = row + dir[1];
                        if(map[tileCol][tileRow] == tile) {
                            changeTileImage(tileCol, tileRow, tileTarget);
                        }
                    }
                    isDigging = false;
                    startTime = 0;
                }
            }
        }
    }

    public void interactTile(int col, int row, int tile, int target) {
        int playerX = gp.player.landTileX;
        int playerY = gp.player.landTileY;
        System.out.println("playerX : " + playerX);
        System.out.println("playerY : " + playerY);
        System.out.println("col : " + col);
        System.out.println("row : " + row);
//        playerY == row + 2 || playerY == row - 2 ||
        if(playerX == col && playerY == row - 2 || playerY == row) {
            if(gp.tileM.mapTileNum[col][row] == tile) {
                changeTileImage(col, row, target);
            } else {
                changeTileImage(col, row, tile);
            }
        }

    }

    public void update() {
        if(gp.keyH.hoePressed) {
            changTile(600, 601);
        }
        if(gp.keyH.waterPressed) {
            changTile(601, 602);
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

            worldX = worldCol * gp.tileSize;
            worldY = worldRow * gp.tileSize;
            tile[tileNum].worldX = worldX;
            tile[tileNum].worldY = worldY;
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
