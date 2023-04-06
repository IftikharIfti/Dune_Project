package entity;

import com.example.begindune.GamePanel;
import com.example.begindune.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasSword = 0;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        name = "Player";
        screenX = gp.screenWidth / 2 - (gp.titleSize / 2);
        screenY = gp.screenHeight / 2 - (gp.titleSize / 2);

        solidArea = new Rectangle(gp.titleSize/3, (gp.titleSize/3),  (gp.titleSize/3), 2 * (gp.titleSize/3));
        //solidArea.x = ;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.titleSize * 23;
        worldY = gp.titleSize * 21;
        speed  = 4;
        direction = "down";

        maxLife = 3;
        life = maxLife;
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(new FileInputStream("res/player/1up-1.png"));
            up2 = ImageIO.read(new FileInputStream("res/player/1up-2.png"));
            down1 = ImageIO.read(new FileInputStream("res/player/1down-1.png"));
            down2 = ImageIO.read(new FileInputStream("res/player/1down-2.png"));
            left1 = ImageIO.read(new FileInputStream("res/player/1left-1.png"));
            left2 = ImageIO.read(new FileInputStream("res/player/1left-2.png"));
            right1 = ImageIO.read(new FileInputStream("res/player/1right-1.png"));
            right2 = ImageIO.read(new FileInputStream("res/player/1right-2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed){

            if(keyH.upPressed){
                direction = "up";

            } else if(keyH.downPressed){
                direction = "down";

            } else if (keyH.leftPressed) {
                direction = "left";

            } else if (keyH.rightPressed) {
                direction = "right";

            }
            collision = false;
            gp.collisionController.checkTile(this);

            int objIndex = gp.collisionController.checkObject(this, true);
            pickUpObject(objIndex);
            if(!collision){
                switch (direction){
                    case "up": worldY -= speed;

                        break;
                    case "down": worldY += speed;

                        break;
                    case "left": worldX -= speed;

                        break;
                    case "right": worldX += speed;

                        break;

                }
            }
            spriteCounter++;
            if(spriteCounter > 8){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            String objName = gp.obj[i].name;

            switch (objName){
                case "Kris":
                    gp.playSE(1);
                    hasSword++;
                    gp.obj[i] = null;
                    gp.ui.showMsg("Earned a Krisknife!");
                    System.out.println("total key: " + hasSword);
                    break;
                case "Door":
                    if(hasSword > 0){
                        gp.playSE(2);
                        gp.obj[i] = null;
                        gp.ui.showMsg("Door unlocked!");
                        hasSword--;
                    }
                    else{
                        gp.ui.showMsg("Need a Krisknife to break it");
                    }
                    System.out.println("total key: " + hasSword);
                    break;
                case "Spice":
                    if(hasSword > 0){
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasSword--;
                        gp.ui.gameFinished = true;

                    }
                    else{
                        gp.ui.showMsg("Need a Krisknife to earn it");
                    }
                    System.out.println("total key: " + hasSword);
                    break;
                case "Water":
                    gp.playSE(0);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMsg("Ahh!! Thanks for the water!");
                    break;

            }

        }
    }
    public void draw( Graphics2D g2){
//        g2.setColor(Color.black);
//        g2.fillRect(x, y, gp.titleSize, gp.titleSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;

        }

        g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
    }
}
