package Tile;

import Plant.Plant_1;
import Plant.Tree;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class PlantCrop {
    GamePanel gp;
    public ArrayList<Tree> plantList = new ArrayList<Tree>();
    public int plantcrop[][];
    public PlantCrop(GamePanel gp) {

        this.gp = gp;
        this.plantcrop = new int[gp.maxWorldCol][gp.maxWorldRow];
        setupPlantcrop();
    }
    public void drawPlants(Graphics2D g2) {
        for (Tree plant : plantList) {
            plant.draw(g2, gp);
        }
    }

    public void setupPlantcrop() {
        for(int i = 0; i < gp.maxWorldCol; i++) {
            for(int j = 0;j < gp.maxWorldRow; i++) {
                plantcrop[i][j] = 0;
            }
        }
    }

    public BufferedImage setupPlantImage(String fileName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/plants/" + fileName + ".png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void plantcropSetup() {
        if(gp.keyH.btn1Pressed) {
            plantCrop("plant_1_");
        }
        if(gp.keyH.btn2Pressed) {
            plantCrop("plant_2_");
        }
    }
    public void plantCrop(String fileName) {
        int col = gp.player.landTileX;
        int row = gp.player.landTileY;
        if(gp.tileM.mapTileNum[col][row] == 46) {
            if(plantcrop[col][row] == 0) {
                BufferedImage[] plantImages = new BufferedImage[5];
                for(int i = 0; i < plantImages.length; i++) {
                    String pathName = fileName + (i + 1);
                    plantImages[i] = setupPlantImage(pathName);
                }
                Plant_1 plant = new Plant_1(plantImages, 3000);
                plant.worldX = gp.tileSize * col;
                plant.worldY = gp.tileSize * row;
                plantList.add(plant);
            }
            plantcrop[col][row] = 46;
        }
    }

    public void update() {
        plantcropSetup();
        for (Tree plant : plantList) {
            plant.update();
        }
    }

    public void draw(Graphics2D g2) {
        drawPlants(g2);
    }
}
