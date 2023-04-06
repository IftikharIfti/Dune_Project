package object;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Spice extends SuperObject{
    public OBJ_Spice(){
        name = "Spice";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/sun01.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
