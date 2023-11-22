package Tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    int worldX, worldY;
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[1000];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileManager();
        loadMap("res/maps/Map.txt");
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
            setup(i, String.valueOf(i), i >= 481 && i <= 489);

        }

        for(int i = 600; i <= 659; i++) {
            setup(i, String.valueOf(i), i >= 613 && i <= 628 || i >= 641 && i <= 643 || i >= 650 && i <= 651 || i >= 657);

        }
        for(int i = 994; i <= 996; i++) {
            setup(i, String.valueOf(i),true);
        }
        setup(998, "998",true);
        setup(999, "999",true);
    }
    public void setup(int index, String imageName, boolean collision) {
        try {
            tile[index] = new Tile();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("res/tiles/" + imageName + ".png");
            if (inputStream != null) {
                BufferedImage image = ImageIO.read(inputStream);
                tile[index].collision = collision;
                tile[index].image = UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
            } else {
                throw new IOException("Could not find resource: " + imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void changeTileImage(int col, int row, int tileIndex) {
        mapTileNum[col][row] = tileIndex;
    }

    public void update() {

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = reader.readLine();
                while(col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

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
