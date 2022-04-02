package hu.bme.mario.client;

import hu.bme.mario.model.BaseBlock;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class BlockTextureCache extends HashMap<Class, Image> {
    public void loadTextures(){
        try {
            this.put(BaseBlock.class, ImageIO.read(new File("textures/BaseBlock.png")));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
