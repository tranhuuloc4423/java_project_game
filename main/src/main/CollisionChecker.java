package main;

import Entity.Entity;

import java.sql.SQLOutput;
import java.util.Arrays;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;

        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1 = 0, tileNum2 = 0, tileNum3 = 0, tileNum4 = 0;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                break;
            case "upleft":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                break;
            case "upright":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                break;
            case "downleft":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                break;
            case "downright":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                break;
        }

        if (
                (tileNum1 != 0 && gp.tileM.tile[tileNum1].collision) ||
                        (tileNum2 != 0 && gp.tileM.tile[tileNum2].collision) ||
                (tileNum3 != 0 && gp.tileM.tile[tileNum3].collision) ||
                (tileNum4 != 0 && gp.tileM.tile[tileNum4].collision)
        ) {
            entity.collisionOn = true;
        }

//        if (tileNum1 != 0 && entity.solidArea.intersects(gp.tileM.tile[tileNum1].solidArea) ||
//                (tileNum2 != 0 && entity.solidArea.intersects(gp.tileM.tile[tileNum2].solidArea)) ||
//                (tileNum3 != 0 && entity.solidArea.intersects(gp.tileM.tile[tileNum3].solidArea)) ||
//                (tileNum4 != 0 && entity.solidArea.intersects(gp.tileM.tile[tileNum4].solidArea))) {
//            System.out.println("collision intersects");
//            entity.collisionOn = true;
//        }

//        if(gp.tileM.tile[tileNum1].solidArea.intersects(entity.solidArea)) {
//            gp.tileM.tile[tileNum1].collision = true;
//        } else if(gp.tileM.tile[tileNum2].solidArea.intersects((entity.solidArea))) {
//            gp.tileM.tile[tileNum2].collision = true;
//
//        } else if(gp.tileM.tile[tileNum3].solidArea.intersects((entity.solidArea))) {
//            gp.tileM.tile[tileNum3].collision = true;
//        } else if(gp.tileM.tile[tileNum4].solidArea.intersects((entity.solidArea))) {
//            gp.tileM.tile[tileNum4].collision = true;
//        }
//        entity.collisionOn = true;
    }


    public void checkHitbox(Entity entity) {
//        System.out.println("run checkhitbox");
        for(int i = 0; i < Arrays.stream(gp.tileM.tile).count(); i++) {
            if(gp.tileM.tile[i] != null) {
                // entity
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // tile
                gp.tileM.tile[i].solidArea.x = gp.tileM.tile[i].worldX + gp.tileM.tile[i].solidArea.x;
                gp.tileM.tile[i].solidArea.y = gp.tileM.tile[i].worldY + gp.tileM.tile[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;

                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(gp.tileM.tile[i].solidArea)) {
//                    System.out.println("intersects");
                    if(gp.tileM.tile[i].collision) {
                        entity.collisionOn = true;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.tileM.tile[i].solidArea.x = gp.tileM.tile[i].solidAreaDefaultX;
                gp.tileM.tile[i].solidArea.y = gp.tileM.tile[i].solidAreaDefaultY;
            }
        }
    }

    public void checkHitbox2(Entity entity) {
//        System.out.println("run checkhitbox");
        for(int i = 0; i < gp.hitboxes.length; i++) {
            if(gp.hitboxes[i] != null) {
                // entity
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // tile
                gp.hitboxes[i].solidArea.x = gp.hitboxes[i].worldX + gp.hitboxes[i].solidArea.x;
                gp.hitboxes[i].solidArea.y = gp.hitboxes[i].worldY + gp.hitboxes[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;

                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(gp.hitboxes[i].solidArea)) {
//                    System.out.println("intersects");
                    entity.collisionOn = true;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.hitboxes[i].solidArea.x = gp.hitboxes[i].solidAreaDefaultX;
                gp.hitboxes[i].solidArea.y = gp.hitboxes[i].solidAreaDefaultY;
            }
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                // get entity's  solid
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;

                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                    if(gp.obj[i].collision) {
                        entity.collisionOn = true;
                    }
                    if(player) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++) {
            if(target[i] != null) {
                // get entity's  solid
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get the object's solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;

                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;

                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;

                        break;
                }
                if(entity.solidArea.intersects(target[i].solidArea)) {
                        entity.collisionOn = true;
                        index = i;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
