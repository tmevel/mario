package hu.bme.mario.client;

import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.QuestionBlock;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.HashMap;

public class BlockTextureCache extends HashMap<Class, Texture> {

    public void loadTextures(){
        String os = System.getProperty("os.name").toLowerCase();
        String path1 = null;
        String path2 = null;
        if (os.contains("win")) {
            path1 = "Mario/textures/BaseBlock.png";
            path2 = "Mario/textures/QuestionBlock.png";
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            path1 = "textures/BaseBlock.png";
            path2 = "textures/QuestionBlock.png";
        }

        try {
            this.put(BaseBlock.class, new UnanimatedTexture(ImageIO.read(new File(path1))));
            this.put(QuestionBlock.class, new UnanimatedTexture(ImageIO.read(new File(path2))));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
