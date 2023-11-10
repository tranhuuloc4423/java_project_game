package main;

import Entity.Entity;
import Entity.Player;
import Inventory.InventoryManager;
import Inventory.Store;
import Object.SuperObject;
import Plant.Tree;
import Tile.PlantCrop;
import Tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import Object.*;

public class GamePanel extends JPanel implements  Runnable {

    // screen settings
    final int originalTileSize = 16;
    public final int scale = 3;

    public final int tileSize = originalTileSize * scale; // tile = 16 * 3
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;


    // world setting
    public final int maxWorldCol = 70;
    public final int maxWorldRow = 70;



    // FPS
    public int FPS = 60;

    // SYSTEM
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseH = new MouseHandler(this);
    Cursor customCursor;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Sound music = new Sound();
    public Sound se = new Sound();
    public UI ui = new UI(this);
    Thread gameThread;
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public SuperObject border = new OBJ_Border(this);
    public boolean drawBorder = true;
//    public Tree plants[] = new Tree[10];
    public Entity npc[] = new Entity[10];

    // Box - to set hit box in tile
    public Hitbox hitboxes[] = new Hitbox[100];

    // tiles
    public TileManager tileM = new TileManager(this);
    public PlantCrop plantcrop = new PlantCrop(this);
    public Menu menu = new Menu(this);
    // inventory
    public InventoryManager invetoryM = new InventoryManager(this);

    public Mission mission = new Mission(this, invetoryM.items);
    public Store store = new Store(this);
    // game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int startState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        addMouseListener(mouseH);
        customCursor();
    }
    public void customCursor() {
        try {
            BufferedImage cursorImage = ImageIO.read(getClass().getResourceAsStream("/res/mouse/custom_mouse_1.png"));
            customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
            setCursor(customCursor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setupGame() {
//        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setBox();
        playMusic(0); // 0 is main song
        stopMusic();
        gameState = startState;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    // game loop
//    @Override
//    public void run() {
//        while(gameThread != null) {
//            double drawInterval = 1000000000 / FPS;
//            double nextDrawTime = System.nanoTime() + drawInterval;
//            // 1 update : update information
//            update();
//            // 2 draw : draw the screen with updated information
//            repaint();
//
//
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    // game loop
    public void run() {
        double drawInterval =  1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
//                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if(gameState == playState) {
            player.update();
            // tiles
            tileM.update();
            // inventory
            if(invetoryM.inventoryOn) {
                invetoryM.update();
            }
            // plantcrop
            plantcrop.update();
            // menu
            menu.update();
            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState) {
            // nothing
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Tạo một BufferedImage là bộ đệm ẩn
//        BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2Buffer = buffer.createGraphics();

        // debug
        if (gameState == startState) {
            menu.drawStartMenu(g2);
        } else if (gameState == playState || gameState == pauseState) {
            long drawStart = 0;

            if (keyH.checkDrawTime) {
                drawStart = System.nanoTime();
            }

            // tiles
            tileM.draw(g2);
            plantcrop.draw(g2);

            // objects
//            for (int i = 0; i < obj.length; i++) {
//                if (obj[i] != null) {
//                    obj[i].draw(g2, this);
//                }
//            }

            if(drawBorder) {
                border.draw(g2, this);
            }

            // npc
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }



            // player
            player.draw(g2);

            // inventory
            if(invetoryM.inventoryOn) {
                invetoryM.draw(g2);
            }

            // plantcrop


//            ui.draw(g2);
            if(mission.missionOn) {
                mission.draw(g2);
            }

            if(store.storeOn) {
                store.draw(g2);
            }

            // debug
            if (keyH.checkDrawTime) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.WHITE);
                g2.drawString("DrawTime : " + passed, 10, 400);
                System.out.println("Draw time : " + passed);
            }
            menu.draw(g2);
        }

        // Sao chép bộ đệm ẩn lên màn hình
//        g2.drawImage(g2, 0, 0, null);

//        g2.dispose();
        g2.dispose();
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
        se.loop();
    }

    public void stopSE() {
        if (se != null) {
            se.stop();
        }
    }
}
