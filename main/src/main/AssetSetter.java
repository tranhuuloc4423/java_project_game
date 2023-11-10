package main;
import Entity.*;
import Object.*;
import javax.swing.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }


    public void setObject(int x, int y) {
//        if(gp.obj[0] != null) {
//            gp.obj[0] = null;
//        }
//        gp.obj[0] = new OBJ_Border(gp);
//        gp.obj[0].worldX = x * gp.tileSize;
//        gp.obj[0].worldY = y * gp.tileSize;


        gp.border.worldX = x * gp.tileSize;
        gp.border.worldY = y * gp.tileSize;
    }

    public void setBox() {
        gp.hitboxes[0] = new Hitbox(0 , 0 , 48, 48);
        gp.hitboxes[0].worldX = 34 * gp.tileSize;
        gp.hitboxes[0].worldY = 34 * gp.tileSize;

        gp.hitboxes[1] = new Hitbox(0, 0, 16, 192);
        gp.hitboxes[1].worldX = 32 * gp.tileSize;
        gp.hitboxes[1].worldY = 30 * gp.tileSize;
    }

    public void setNPC() {
        gp.npc[0] = new Animal_Cow(gp);
        gp.npc[0].worldX = gp.tileSize * 28;
        gp.npc[0].worldY = gp.tileSize * 40;

        gp.npc[1] = new Animal_Cow(gp);
        gp.npc[1].worldX = gp.tileSize * 26;
        gp.npc[1].worldY = gp.tileSize * 39;
    }
}
