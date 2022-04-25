package hu.bme.mario.client;

import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.SmallPlayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class EntityTextureCache extends HashMap<Class, EntityTexture> {
    private String os;
    private String path1;
    private String path2;

    public void loadTextures(){

        os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            path1 = "Mario/textures/mario1.png";
            path2 = "Mario/textures/mario2.png";
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            path1 = "textures/mario1.png";
            path2 = "textures/mario2.png";
        }

        try {
            BufferedImage[] mario = new BufferedImage[2];
            mario[0] = ImageIO.read(new File(path1));
            mario[1] = ImageIO.read(new File(path2));
            this.put(SmallPlayer.class, new EntityTexture(new AnimatedTexture(mario,0.3),1,1.8, 0,0));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
