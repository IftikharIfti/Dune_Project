package object;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Water extends SuperObject{
    public OBJ_Water(){
        name = "Water";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/water.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
       // collision = true;

    }
}
