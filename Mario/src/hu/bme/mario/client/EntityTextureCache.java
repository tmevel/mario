package hu.bme.mario.client;

import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.SmallPlayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class EntityTextureCache extends HashMap<Class, EntityTexture> {
    public void loadTextures(){
        try {
            BufferedImage[] mario = new BufferedImage[2];
            mario[0] = ImageIO.read(new File("textures/mario1.png"));
            mario[1] = ImageIO.read(new File("textures/mario2.png"));
            this.put(SmallPlayer.class, new EntityTexture(new AnimatedTexture(mario,0.3),1,1.8, 0,0));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
