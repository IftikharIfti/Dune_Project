package object;

import com.example.begindune.GamePanel;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Heart extends SuperObject{
    GamePanel gp;
    public OBJ_Heart(){
        this.gp = gp;
        name = "Heart";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/heart.png"));
            img2 = ImageIO.read(new FileInputStream("res/objects/blackHeart.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
