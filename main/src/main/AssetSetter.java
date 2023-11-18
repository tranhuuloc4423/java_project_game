package main;
import Entity.*;
import Object.*;
import javax.swing.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }


    public void setObject() {
        gp.obj[0] = new OBJ_Chest(gp);
        gp.obj[0].worldX = gp.tileSize * 38;
        gp.obj[0].worldY = gp.tileSize * 30;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX = gp.tileSize * 34;
        gp.obj[1].worldY = gp.tileSize * 34;
    }

    public void setBorder(int x, int y) {
        gp.border.worldX = x * gp.tileSize;
        gp.border.worldY = y * gp.tileSize;
    }

    public void setBox() {
//        gp.hitboxes[0] = new Hitbox( 4, 48);
//        gp.hitboxes[0].worldX = 34 * gp.tileSize;
//        gp.hitboxes[0].worldY = 34 * gp.tileSize;

//        gp.hitboxes[1] = new Hitbox( 4, 48);
//        gp.hitboxes[1].worldX = 34 * gp.tileSize + 40;
//        gp.hitboxes[1].worldY = 34 * gp.tileSize;

        gp.hitboxes[2] = new Hitbox(16, 288);
        gp.hitboxes[2].worldX = 31 * gp.tileSize + 32;
        gp.hitboxes[2].worldY = 29 * gp.tileSize;

        gp.hitboxes[3] = new Hitbox( 16, 288);
        gp.hitboxes[3].worldX = 37 * gp.tileSize;
        gp.hitboxes[3].worldY = 29 * gp.tileSize;

        gp.hitboxes[4] = new Hitbox( 16, 192);
        gp.hitboxes[4].worldX = 13 * gp.tileSize;
        gp.hitboxes[4].worldY = 17 * gp.tileSize;

        gp.hitboxes[5] = new Hitbox( 16, 144);
        gp.hitboxes[5].worldX = 21 * gp.tileSize + 32;
        gp.hitboxes[5].worldY = 18 * gp.tileSize;

        gp.hitboxes[6] = new Hitbox( 16, 144);
        gp.hitboxes[6].worldX = 17 * gp.tileSize;
        gp.hitboxes[6].worldY = 14 * gp.tileSize;

        gp.hitboxes[7] = new Hitbox( 16, 96);
        gp.hitboxes[7].worldX = 26 * gp.tileSize + 32;
        gp.hitboxes[7].worldY = 14 * gp.tileSize;

        gp.hitboxes[8] = new Hitbox( 192, 16);
        gp.hitboxes[8].worldX = 13 * gp.tileSize;
        gp.hitboxes[8].worldY = 17 * gp.tileSize;

        gp.hitboxes[9] = new Hitbox( 480, 16);
        gp.hitboxes[9].worldX = 17 * gp.tileSize;
        gp.hitboxes[9].worldY = 14 * gp.tileSize;

        gp.hitboxes[10] = new Hitbox( 16, 528);
        gp.hitboxes[10].worldX = 37 * gp.tileSize + 32;
        gp.hitboxes[10].worldY = 14 * gp.tileSize;

        gp.hitboxes[11] = new Hitbox( 16, 336);
        gp.hitboxes[11].worldX = 13 * gp.tileSize ;
        gp.hitboxes[11].worldY = 43 * gp.tileSize;

        gp.hitboxes[12] = new Hitbox( 16, 144);
        gp.hitboxes[12].worldX = 18 * gp.tileSize + 32;
        gp.hitboxes[12].worldY = 43 * gp.tileSize;

        gp.hitboxes[13] = new Hitbox( 16, 192);
        gp.hitboxes[13].worldX = 20 * gp.tileSize + 32;
        gp.hitboxes[13].worldY = 46 * gp.tileSize;

        gp.hitboxes[14] = new Hitbox( 96, 16);
        gp.hitboxes[14].worldX = 19 * gp.tileSize;
        gp.hitboxes[14].worldY = 46 * gp.tileSize;

        gp.hitboxes[15] = new Hitbox( 16, 336);
        gp.hitboxes[15].worldX = 13 * gp.tileSize ;
        gp.hitboxes[15].worldY = 43 * gp.tileSize;

        gp.hitboxes[16] = new Hitbox( 16, 144);
        gp.hitboxes[16].worldX = 51 * gp.tileSize ;
        gp.hitboxes[16].worldY = 49 * gp.tileSize;

        gp.hitboxes[17] = new Hitbox( 16, 96);
        gp.hitboxes[17].worldX = 45 * gp.tileSize ;
        gp.hitboxes[17].worldY = 53 * gp.tileSize;

        gp.hitboxes[18] = new Hitbox( 16, 96);
        gp.hitboxes[18].worldX = 54 * gp.tileSize + 32 ;
        gp.hitboxes[18].worldY = 50 * gp.tileSize;
    }

    public void setNPC() {
        gp.npc[0] = new Animal_Cow(gp);
        gp.npc[0].worldX = gp.tileSize * 28;
        gp.npc[0].worldY = gp.tileSize * 40;

        gp.npc[1] = new Animal_Cow(gp);
        gp.npc[1].worldX = gp.tileSize * 26;
        gp.npc[1].worldY = gp.tileSize * 39;

        gp.npc[2] = new Animal_Chicken(gp);
        gp.npc[2].worldX = gp.tileSize * 28;
        gp.npc[2].worldY = gp.tileSize * 32;

        gp.npc[3] = new Animal_Chicken(gp);
        gp.npc[3].worldX = gp.tileSize * 25;
        gp.npc[3].worldY = gp.tileSize * 32;

        gp.npc[4] = new Animal_Chicken(gp);
        gp.npc[4].worldX = gp.tileSize * 26;
        gp.npc[4].worldY = gp.tileSize * 31;

        gp.npc[5] = new Animal_Chicken(gp);
        gp.npc[5].worldX = gp.tileSize * 27;
        gp.npc[5].worldY = gp.tileSize * 29;
    }
}
