package com.example.begindune;

import object.OBJ_Door;
import object.OBJ_Kris;
import object.OBJ_Spice;
import object.OBJ_Water;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJ_Kris();
        gp.obj[0].worldX = 23 * gp.titleSize;
        gp.obj[0].worldY = 7 * gp.titleSize;

        gp.obj[1] = new OBJ_Kris();
        gp.obj[1].worldX = 23 * gp.titleSize;
        gp.obj[1].worldY = 40 * gp.titleSize;

        gp.obj[2] = new OBJ_Kris();
        gp.obj[2].worldX = 38 * gp.titleSize;
        gp.obj[2].worldY = 8 * gp.titleSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 10 * gp.titleSize;
        gp.obj[3].worldY = 11 * gp.titleSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 12 * gp.titleSize;
        gp.obj[4].worldY = 22 * gp.titleSize;

        gp.obj[5] = new OBJ_Spice();
        gp.obj[5].worldX = 10 * gp.titleSize;
        gp.obj[5].worldY = 7 * gp.titleSize;

        gp.obj[6] = new OBJ_Water();
        gp.obj[6].worldX = 37 * gp.titleSize;
        gp.obj[6].worldY = 42 * gp.titleSize;

    }
}
