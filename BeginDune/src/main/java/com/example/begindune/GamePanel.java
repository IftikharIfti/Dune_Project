package com.example.begindune;
import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //screen
    final int originalTitleSize = 12; //12x12 title  //16x16 tile
    final int scale = 5;//3
    public final int titleSize = originalTitleSize * scale;  //120x120 title   //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 9; //12
    public final int screenWidth = titleSize * maxScreenCol;  //1920   //768
    public final int screenHeight = titleSize * maxScreenRow; //1080     //576

    //map settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = titleSize * maxWorldCol;
    public final int worldHeight = titleSize * maxWorldRow;


    int FPS = 60;

    //system
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    public CollisionController collisionController = new CollisionController(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;


    //player and entity
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity monster[] = new Entity[10];
    public GamePanel(){
        this.setPreferredSize((new Dimension(screenWidth, screenHeight)));
        this.setBackground(new Color(241, 219, 58));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame(){
        assetSetter.setObject();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;  //  1e9 ns / fps = 0.0166 s
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            update();
            repaint();

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void update(){

        player.update();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //tile
        tileM.draw(g2);
        //obj
        for(int i=0; i<obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //player
        player.draw(g2);
        //ui
        ui.draw(g2);

        g2.dispose();

    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
        //sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }

}
