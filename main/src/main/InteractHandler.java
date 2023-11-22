package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractHandler {
    GamePanel gp;

    Timer timer;

    private long startTime = 0; // Thời điểm bắt đầu nhấn giữ phím đào đất
    private boolean isAction = false; // Biến đánh dấu việc đang đào đất
    private static final long ACTION_DURATION = 2000;

    int x, y;

    public InteractHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void bedHandle(int col, int row) {
        if(x == col && y == row) {
            if(!gp.pod.isSleep) {
                if(gp.gameHour < 4 || gp.gameHour >= 20) {
                    gp.pod.isSleep = !gp.pod.isSleep;
                    gp.music[11].playSEOnce();
                }
            }
        }
    }

    public void chestHandle(int col, int row) {
        int radius = 1; // Bán kính 1 ô
        if (Math.abs(x - col) <= radius && Math.abs(y - row) <= radius) {
            if(gp.obj[1].image == gp.obj[1].images[0]) {
                gp.music[9].playSEOnce();
                gp.obj[1].image = gp.obj[1].images[1];
                gp.storage.storeOn = true;
            } else if(gp.obj[1].image == gp.obj[1].images[1]) {
                gp.music[10].playSEOnce();
                gp.obj[1].image = gp.obj[1].images[0];
                gp.storage.storeOn = false;
            }
        }

    }
    public void doorHandle(int col, int row) {
        int radius = 1; // Bán kính 1 ô
        if (Math.abs(x - col) <= radius && Math.abs(y - row) <= radius || x == col && y == row - 2) {
            gp.music[4].playSEOnce();
            if(gp.obj[0].image == gp.obj[0].images[0]) {
                gp.obj[0].image = gp.obj[0].images[1];
                gp.obj[0].collision = false;
            } else if(gp.obj[0].image == gp.obj[0].images[1]) {
                gp.obj[0].image = gp.obj[0].images[0];
                gp.obj[0].collision = true;
            }
        }
    }

    public void changTile(int tile,int tileTarget) {
        int[][] map = gp.tileM.mapTileNum;

        int[][] directions = {
                {0, 0},
                {-1, -1},
                {1, 1},
                {1, 0},
                {0, 1},
                {-1, 1},
                {1, -1},
                {-1, 0},
                {0, -1}
        };

        boolean isDirtNearby = false;
        for (int[] dir : directions) {
            int checkCol = x + dir[0];
            int checkRow = y + dir[1];

            if (checkCol >= 0 && checkCol < map.length &&
                    checkRow >= 0 && checkRow < map[0].length &&
                    map[checkCol][checkRow] == tile) {
                isDirtNearby = true;
                break;
            }
        }
        if(isDirtNearby) {
            for (int[] dir : directions) {
                int tileCol = x + dir[0];
                int tileRow = y + dir[1];
                if(map[tileCol][tileRow] == tile) {
                    gp.tileM.changeTileImage(tileCol, tileRow, tileTarget);
                }
            }
        }
        isAction = false;
        startTime = 0;
    }

    public void update() {
        x = gp.player.landTileX;
        y = gp.player.landTileY;

        if (gp.keyH.hoePressed || gp.keyH.waterPressed) {
            if (!isAction) {
                startTime = System.currentTimeMillis();
                isAction = true;
            } else {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;

                if (elapsedTime >= ACTION_DURATION) {
                    if (gp.keyH.hoePressed) {
                        changTile(600, 601);
                    } else if (gp.keyH.waterPressed) {
                        changTile(601, 602);
                    }
                }
            }
        } else {
            isAction = false;
            startTime = 0;
        }
    }
}
