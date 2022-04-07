package hu.bme.mario.client;

import hu.bme.mario.model.BaseBlock;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class BlockTextureCache extends HashMap<Class, Texture> {
    public void loadTextures(){
        try {
            this.put(BaseBlock.class, new UnanimatedTexture(ImageIO.read(new File("textures/BaseBlock.png"))));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
