package object;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Kris extends SuperObject{
    public OBJ_Kris(){
        name = "Kris";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/sword2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
