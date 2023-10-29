package main;
import Entity.*;
import Object.*;
import Plant.Plant_1;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(int x, int y) {
        gp.obj[0] = null;
        if(gp.obj[0] == null) {
            gp.obj[0] = new OBJ_Border(gp);
            gp.obj[0].worldX = x * gp.tileSize;
            gp.obj[0].worldY = y * gp.tileSize;
        }

    }

    public void setNPC() {
        gp.npc[0] = new Animal_Cow(gp);
        gp.npc[0].worldX = gp.tileSize * 15;
        gp.npc[0].worldY = gp.tileSize * 15;
    }
}
