package Tile;

import Plant.Tree;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlantCrop {
    GamePanel gp;
    public ArrayList<Tree> plantList = new ArrayList<Tree>();
    public int plantcrop[][];
    public Map<Point, Tree> plantMap = new HashMap<>();

    public PlantCrop(GamePanel gp) {

        this.gp = gp;
        this.plantcrop = new int[gp.maxWorldCol][gp.maxWorldRow];
        setupPlantcrop();
    }
    public void drawPlants(Graphics2D g2) {
        for (Map.Entry<Point, Tree> entry : plantMap.entrySet()) {
            Tree plant = entry.getValue();
            plant.draw(g2, gp);
        }
    }

    public void setupPlantcrop() {
        for(int i = 0; i < gp.maxWorldCol; i++) {
            for(int j = 0;j < gp.maxWorldRow; j++) {
                plantcrop[i][j] = 0;
            }
        }
    }

    public BufferedImage setupPlantImage(String fileName) {
        BufferedImage image = null;
        BufferedImage scaleImage = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/plants/" + fileName + ".png"));
            int width = image.getWidth() * gp.scale;
            int height = image.getHeight() * gp.scale;
            scaleImage = UtilityTool.scaleImage(image, width, height);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return scaleImage;
    }

    public void plantcropSetup() {
        if(gp.keyH.btn1Pressed) {
            plantCrop("plant_1_", 1000);
        }
        if(gp.keyH.btn2Pressed) {
            plantCrop("plant_2_", 2000);
        }
        if(gp.keyH.btn3Pressed) {
            plantCrop("plant_3_", 2000);
        }
        if(gp.keyH.btn4Pressed) {
            plantCrop("plant_4_", 1000);
        }
        if(gp.keyH.btn5Pressed) {
            plantCrop("plant_5_", 2000);
        }
        if(gp.keyH.btn6Pressed) {
            plantCrop("plant_6_", 2000);
        }
    }

    public void harvestCrop() {
        int col = gp.player.landTileX;
        int row = gp.player.landTileY;
        if (gp.tileM.mapTileNum[col][row] == 602) {
            Point position = new Point(col, row);
            Tree plant = plantMap.get(position);
            if (plant != null) {
                String name = plant.name;
                BufferedImage[] plantImages = plant.getPlantImages();
                BufferedImage lastImage = plantImages[plantImages.length - 1];
                if (plant.getCurrentImage() == lastImage) {
                    // Thu hoạch cây trồng
                    plant = null;
                    plantMap.remove(position);
                    gp.tileM.mapTileNum[col][row] = 600;
                    handleQuantity(name);
                }
            }
        }
    }

    public void handleQuantity(String name) {
        int index = Integer.parseInt(name.split("_")[1]);
        gp.invetoryM.items.get(index - 1).addQuantity(1);
        gp.store.plantItems.get(index - 1).addQuantity(2);
    }

    public void plantCrop(String fileName, int time) {
        int col = gp.player.landTileX;
        int row = gp.player.landTileY;
        int index = Integer.parseInt(fileName.split("_")[1]) - 1;
        if(gp.invetoryM.items.get(index).quantity > 0) {
            if (gp.tileM.mapTileNum[col][row] == 602) {
                Point position = new Point(col, row);
                if (!plantMap.containsKey(position)) {
                    BufferedImage[] plantImages = new BufferedImage[4];
                    for (int i = 0; i < plantImages.length; i++) {
                        String pathName = fileName + (i + 1);
                        plantImages[i] = setupPlantImage(pathName);
                    }
                    Tree plant = new Tree(plantImages, time, fileName);
                    plant.worldX = gp.tileSize * col;
                    plant.worldY = gp.tileSize * row;
                    plantMap.put(position, plant);

//                    System.out.println(index);
                    gp.invetoryM.items.get(index).removeQuantity(1);
                }
            }
        }
    }

    public void update() {
        for (Map.Entry<Point, Tree> entry : plantMap.entrySet()) {
            Tree plant = entry.getValue();
            plant.update();
        }
        plantcropSetup();
        harvestCrop();
    }

    public void draw(Graphics2D g2) {
        drawPlants(g2);
    }
}
