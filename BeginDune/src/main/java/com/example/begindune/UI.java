package com.example.begindune;

import object.OBJ_Heart;
import object.OBJ_Kris;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;

import static com.example.begindune.LoginController.USERNAME;

public class UI {
    GamePanel gp;
    Font arialFont, arialFont1;
    BufferedImage heartRed, heartBlack;
    BufferedImage keyImage;
    public boolean msgOn = false;
    public String msg = "";
    int msgCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public KeyHandler k=new KeyHandler();

    public UI(GamePanel gp){
//        this.addKeyListener(k);
        this.gp = gp;
        arialFont = new Font("Arial", Font.ITALIC, 25);
        arialFont1 = new Font("Arial", Font.BOLD, 40);

        OBJ_Kris kris = new OBJ_Kris();
        keyImage = kris.image;

        SuperObject heart = new OBJ_Heart();
        heartRed = heart.image;
        heartBlack = heart.img2;
    }

    public void showMsg(String text){
        msg = text;
        msgOn = true;
    }

    public void draw(Graphics2D g2){
        if(gameFinished){
            g2.setFont(arialFont);
            g2.setColor(Color.YELLOW);

            String text = "You've earned the Spice!";
            int textLengh = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.screenWidth/2 - textLengh/2;
            int y = gp.screenHeight/2 - gp.titleSize*2;
            g2.drawString(text, x, y);

            text = "Your time is: " + decimalFormat.format(playTime) + "second!";
//            System.out.println("fin");
            try {
                score_save((1/playTime)*10000);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            textLengh = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLengh/2;
            y = gp.screenHeight/2 + gp.titleSize;
            g2.drawString(text, x, y);

            g2.setFont(arialFont1);
            g2.setColor(Color.YELLOW);

            text = "Congratulations!";
            textLengh = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLengh/2;
            y = gp.screenHeight/2 - gp.titleSize;
            g2.drawString(text, x, y);

//            if(k.enterPressed==true)
//            {
//                System.out.println("ok");
//             MainMenuController m=new MainMenuController();
//                try {
//                    m.BacktoMainMenu(k.eve);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            gp.gameThread = null;



        }
        else{

            g2.setFont(arialFont);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.titleSize/2, gp.titleSize, gp.titleSize, gp.titleSize, null);
            g2.drawString("x " + gp.player.hasSword, 74, 100);
            drawPlayerLife(g2);

            playTime += (double) 1/60;


            g2.drawString("Time: " + decimalFormat.format(playTime), gp.titleSize*12 , gp.titleSize/2);
            if(msgOn){
                g2.drawString(msg, gp.titleSize*6, gp.titleSize);

                msgCounter ++;

                if(msgCounter >60){
                    msgCounter = 0;
                    msgOn = false;
                }
            }
        }


    }
    public void score_save(double playTime) throws SQLException, ClassNotFoundException {
        System.out.println("New"+playTime);
        String dbName = "UserInfo";
        String userName = "root";
        String password = "";
        Double a=0.0;
        String user=USERNAME;
        Connection con=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, "root", "");

        String sql0="select highscore from USERINFO where userName=?";
        PreparedStatement stm0= con.prepareStatement(sql0);
        stm0.setString(1,user);
        ResultSet rs=stm0.executeQuery();
        while (rs.next()){
            a=rs.getDouble(1);
            System.out.println(a);
        }
        if(playTime>a) {


            String sql = "update USERINFO set highscore= ? where  userName=?";


            PreparedStatement stmt = con.prepareStatement(sql);
            System.out.println(playTime + user);

            stmt.setDouble(1, playTime);
            stmt.setString(2, user);
            stmt.executeUpdate();
            System.out.println("Up");
        }

    }

    public void drawPlayerLife(Graphics2D g2){
        int x = gp.titleSize/2;
        int y = gp.titleSize/2;
        int i = 0;

//        base maxlife
        while(i < gp.player.maxLife){
            g2.drawImage(heartBlack, x, y, gp.titleSize/2, gp.titleSize/2, null);
            i++;
            x += gp.titleSize;
        }

//        gp.player.life = 1;
        x = gp.titleSize/2;
        y = gp.titleSize/2;
        i = 0;
        //current life
        while(i < gp.player.life){
            g2.drawImage(heartRed, x, y, gp.titleSize/2, gp.titleSize/2, null);
            i++;
            x += gp.titleSize;
        }
    }
}
