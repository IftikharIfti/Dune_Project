//package monster;
//
//import com.example.begindune.GamePanel;
//import entity.Entity;
//
//import javax.imageio.ImageIO;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Random;
//
//public class MON_Worm extends Entity {
//    public MON_Worm(GamePanel gp){
//        //super(gp);
//        name = "Worm";
//        maxLife = 4;
//        life = maxLife;
//
//        solidArea.x = 3;
//        solidArea.y = 18;
//        solidArea.width = 42;
//        solidArea.height = 30;
//        solidAreaDefaultX = solidArea.x;
//        solidAreaDefaultY = solidArea.y;
//
//        getImage();
//    }
//
//    public void getImage(){
//        try{
//            up1 = ImageIO.read(new FileInputStream("res/monster/worm.png"));
//            up2 = ImageIO.read(new FileInputStream("res/monster/worm.png"));
//            down1 = ImageIO.read(new FileInputStream("res/monster/worm.png"));
//            down2 = ImageIO.read(new FileInputStream("res/monster/worm.png"));
//            left1 = ImageIO.read(new FileInputStream("res/monster/worm.png"));
//            left2 = ImageIO.read(new FileInputStream("res/monster/worm.png"));
//            right1 = ImageIO.read(new FileInputStream("res/monster/worm.png"));
//            right2 = ImageIO.read(new FileInputStream("res/monster/worm.png"));
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void setAction(){
//        actionCounter++;
//
//        if(actionLockCounter == 120){
//
//            Random random = new Random();
//            int i = random.nextInt(100)+1;
//
//            if(i <= 25){
//                direction = "up";
//
//            }
//            if(i > 25 && i <= 50){
//                direction = "down";
//            }
//            if(i > 50 && i <= 75){
//                direction = "down";
//            }
//            if(i > 75 && i <= 100){
//                direction = "down";
//            }
//
//            actionLockCounter = 0;
//        }
//    }
//}
