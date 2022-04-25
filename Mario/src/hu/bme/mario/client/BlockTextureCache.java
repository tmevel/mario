package hu.bme.mario.client;

import hu.bme.mario.model.BaseBlock;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class BlockTextureCache extends HashMap<Class, Texture> {
    private String os;
    private String path;

    public void loadTextures(){
        os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            path = "Mario/textures/BaseBlock.png";
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            path = "textures/BaseBlock.png";
        }

        try {
            this.put(BaseBlock.class, new UnanimatedTexture(ImageIO.read(new File(path))));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
