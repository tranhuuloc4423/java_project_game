package main;

import Entity.Entity;
import Entity.Player;
import Inventory.InventoryManager;
import Inventory.Storage;
import Object.SuperObject;
import Tile.PlantCrop;
import Tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Object.*;
import UI.*;

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

    // time
    private static final int GAME_MINUTES_PER_HOUR = 60; // Số phút trong game tương đương với một giờ thời gian thực
    private static final int GAME_HOURS_PER_DAY = 24; // Số giờ trong game tương đương với một ngày

    public int gameMinute = 0;
    public int gameHour = 0; // Giờ trong game
    public int gameDay = 0; // Ngày trong game

    // FPS
    public int FPS = 60;

    // SYSTEM
    public KeyHandler keyH = new KeyHandler(this);
    Cursor customCursor;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    Thread gameThread;
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[50];
    public SuperObject border = new OBJ_Border(this);
    public boolean drawBorder = true;
//    public Tree plants[] = new Tree[10];
    public Entity npc[] = new Entity[10];

    // Box - to set hit box in tile
    public Hitbox hitboxes[] = new Hitbox[100];

    // tiles
    public TileManager tileM = new TileManager(this);
    public PlantCrop plantcrop = new PlantCrop(this);
//    public Menu menu = new Menu(this);
    public StartMenu startMenu = new StartMenu(this);
    public MainMenu mainMenu = new MainMenu(this);
    public PeriodOfDay pod = new PeriodOfDay(this);
    public Sound[] music = new Sound[20];
    // inventory
    public InventoryManager invetoryM = new InventoryManager(this);
    public Storage storage = new Storage(this);
    public Mission mission = new Mission(this);

    public InteractHandler interactH = new InteractHandler(this);
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
        customCursor();
        gameHour = 0;
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
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setBox();
        music[0] = new Sound("background_music", this);
        music[1] = new Sound("walk", this);
        music[2] = new Sound("hoe", this);
        music[3] = new Sound("water", this);
        music[4] = new Sound("closedoor", this);
        music[5] = new Sound("seed", this);
        music[6] = new Sound("cow", this);
        music[7] = new Sound("chicken", this);
        music[8] = new Sound("harvest", this);
        music[9] = new Sound("chest_open", this);
        music[10] = new Sound("chest_close", this);
        music[11] = new Sound("sleep", this);
        music[12] = new Sound("cut_tree", this);

        music[0].playMusic();
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
                updateTime();
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }



    void updateTime() {
        if(gameState == playState) {
            gameMinute += 5;
            if(gameMinute >= GAME_MINUTES_PER_HOUR) {
                gameMinute %= GAME_MINUTES_PER_HOUR;
                gameHour++;
                if (gameHour >= GAME_HOURS_PER_DAY) {
                    gameHour %= GAME_HOURS_PER_DAY;
                    gameDay++;
                }
            }
        }
    }

    public void update() {
        if(gameState == startState) {
            startMenu.update();
        }

        if(gameState == playState) {
//            updateGameTime();
            player.update();
            // tiles
            tileM.update();
            // inventory
            invetoryM.update();
            // plantcrop
            plantcrop.update();

            // menu
            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
            for(int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    obj[i].update();
                }
            }

            interactH.update();
        }
        mainMenu.update();
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
            startMenu.draw(g2);
        } else if (gameState == playState || gameState == pauseState) {
            long drawStart = 0;

            if (keyH.checkDrawTime) {
                drawStart = System.nanoTime();
            }

            // tiles
            tileM.draw(g2);
            plantcrop.draw(g2);





            // npc
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }
            obj[0].draw(g2, this);
            // player
            player.draw(g2);

            // objects
            for (int i = 1; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            // border
            if(drawBorder) {
                border.draw(g2, this);
            }

            // inventory
            if(invetoryM.inventoryOn) {
                invetoryM.draw(g2);
            }

            // plantcrop

//            ui.draw(g2);
            if(mission.missionOn) {
                mission.draw(g2);
            }

            if(storage.storeOn) {
                storage.draw(g2);
            }

//            for(int i = 0; i < hitboxes.length; i++) {
//                if(hitboxes[i] != null) {
//                    hitboxes[i].draw(g2, this);
//                }
//            }

            // debug
            if (keyH.checkDrawTime) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.WHITE);
                g2.drawString("DrawTime : " + passed, 10, 400);
                System.out.println("Draw time : " + passed);
            }
            mainMenu.draw(g2);

            pod.draw(g2);
        }

        // Sao chép bộ đệm ẩn lên màn hình
//        g2.drawImage(g2, 0, 0, null);

//        g2.dispose();
        g2.dispose();
    }
}
