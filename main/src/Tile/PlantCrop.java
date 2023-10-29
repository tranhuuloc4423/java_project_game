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
    public PlantCrop(GamePanel gp) {
        this.gp = gp;
    }
    public void drawPlants(Graphics2D g2) {
        for (Tree plant : plantList) {
            plant.draw(g2, gp);
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
