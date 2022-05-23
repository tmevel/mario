package hu.bme.mario.client;

import hu.bme.mario.model.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.HashMap;

public class BlockTextureCache extends HashMap<Class, Texture> {

    public void loadTextures(){
        String os = System.getProperty("os.name").toLowerCase();
        String path1 = null;
        String path2 = null;
        String emptyPath = null;
        String breakablePath = null;
        String voidPath = null;
        if (os.contains("win")) {
            path1 = "Mario/textures/BaseBlock.png";
            path2 = "Mario/textures/QuestionBlock.png";
            emptyPath = "Mario/textures/EmptyBlock.png";
            breakablePath = "Mario/textures/BreakableBlock.png";
            voidPath = "Mario/textures/VoidBlock.png";
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            path1 = "textures/BaseBlock.png";
            path2 = "textures/QuestionBlock.png";
            emptyPath = "textures/EmptyBlock.png";
            breakablePath = "textures/BreakableBlock.png";
            voidPath = "textures/VoidBlock.png";
        }

        try {
            this.put(BaseBlock.class, new UnanimatedTexture(ImageIO.read(new File(path1))));
            this.put(QuestionBlock.class, new UnanimatedTexture(ImageIO.read(new File(path2))));
            this.put(EmptyBlock.class, new UnanimatedTexture(ImageIO.read(new File(emptyPath))));
            this.put(VoidBlock.class, new UnanimatedTexture(ImageIO.read(new File(voidPath))));
            this.put(BreakableBlock.class, new UnanimatedTexture(ImageIO.read(new File(breakablePath))));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
