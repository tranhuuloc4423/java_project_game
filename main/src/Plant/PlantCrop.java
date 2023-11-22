package Plant;

import Plant.Tree;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlantCrop {
    GamePanel gp;
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
        BufferedImage scaledImage = null;
        try {
            String imagePath = "res/plants/" + fileName + ".png";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(imagePath);
            if (inputStream != null) {
                image = ImageIO.read(inputStream);
                int width = image.getWidth() * gp.scale;
                int height = image.getHeight() * gp.scale;
                scaledImage = UtilityTool.scaleImage(image, width, height);
            } else {
                throw new IOException("Could not find resource: " + imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void plantcropSetup() {
        if(gp.keyH.plantPressed) {
            if(gp.invetoryM.selectedItem == 1) {
                plantCrop("plant_1_", 1000);
            }
            if(gp.invetoryM.selectedItem == 2) {
                plantCrop("plant_2_", 1000);
            }
            if(gp.invetoryM.selectedItem == 3) {
                plantCrop("plant_3_", 1000);
            }
            if(gp.invetoryM.selectedItem == 4) {
                plantCrop("plant_4_", 1000);
            }
            if(gp.invetoryM.selectedItem == 5) {
                plantCrop("plant_5_", 1000);
            }
            if(gp.invetoryM.selectedItem == 6) {
                plantCrop("plant_6_", 1000);
            }
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
                    gp.music[8].playSEOnce();
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
        gp.invetoryM.items.get(index - 1).addQuantity(2);
        gp.storage.items.get(index - 1).addQuantity(2);
    }

    public void plantCrop(String fileName, int time) {
        int col = gp.player.landTileX;
        int row = gp.player.landTileY;
        int index = Integer.parseInt(fileName.split("_")[1]) - 1;
        if(gp.invetoryM.items.get(index).quantity > 0) {
            if (gp.tileM.mapTileNum[col][row] == 602) {
                Point position = new Point(col, row);
                if (!plantMap.containsKey(position)) {
                    gp.music[5].playSEOnce();
                    BufferedImage[] plantImages = new BufferedImage[4];
                    for (int i = 0; i < plantImages.length; i++) {
                        String pathName = fileName + (i + 1);
                        plantImages[i] = setupPlantImage(pathName);
                    }
                    Tree plant = new Tree(gp, plantImages, time, fileName);
                    plant.worldX = gp.tileSize * col;
                    plant.worldY = gp.tileSize * row;
                    plantMap.put(position, plant);
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
