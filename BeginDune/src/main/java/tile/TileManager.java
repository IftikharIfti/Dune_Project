package tile;

import com.example.begindune.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];


    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new FileInputStream("res/titles/road00.png")); // grass
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new FileInputStream("res/titles/wall.png")); // wall
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new FileInputStream("res/titles/rock-6.png")); //water
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new FileInputStream("res/titles/earth.png"));  //earth
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new FileInputStream("res/titles/unga.png"));  //tree
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new FileInputStream("res/titles/desert_16x16.png")); //sand

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String fp){
        try{
            InputStream is = getClass().getResourceAsStream(fp);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            int tileNum = mapTileNum[col][row];

            int worldX = col * gp.titleSize;
            int worldY = row * gp.titleSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX > gp.player.worldX - gp.player.screenX - gp.titleSize &&
               worldX < gp.player.worldX + gp.player.screenX + gp.titleSize &&
               worldY > gp.player.worldY - gp.player.screenY - gp.titleSize &&
               worldY < gp.player.worldY + gp.player.screenY + gp.titleSize)
            {
                g2.drawImage(tile[0].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
            }

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

    }
}
