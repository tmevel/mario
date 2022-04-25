package hu.bme.mario.client;

import hu.bme.mario.model.BaseBlock;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class BlockTextureCache extends HashMap<Class, Texture> {
<<<<<<< HEAD
=======
    private String os;
    private String path;


>>>>>>> network
    public void loadTextures(){
        os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            path = "Mario/textures/BaseBlock.png";
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            path = "textures/BaseBlock.png";
        }

        try {
<<<<<<< HEAD
            this.put(BaseBlock.class, new UnanimatedTexture(ImageIO.read(new File("textures/BaseBlock.png"))));
=======
            this.put(BaseBlock.class, new UnanimatedTexture(ImageIO.read(new File(path))));
>>>>>>> network
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
