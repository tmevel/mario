package hu.bme.mario.client;

import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.SmallPlayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class EntityTextureCache extends HashMap<Class, EntityTexture> {

    public void loadTextures(){

        String os = System.getProperty("os.name").toLowerCase();
        String marioPath1 = null;
        String marioPath2 = null;
        String marioPath3 = null;
        if (os.contains("win")) {
            marioPath1 = "Mario/textures/mario_run1.png";
            marioPath2 = "Mario/textures/mario_run2.png";
            marioPath3 = "Mario/textures/mario_standing.png";
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            marioPath1 = "textures/mario_run1.png";
            marioPath2 = "textures/mario_run2.png";
            marioPath3 = "textures/mario_standing.png";
        }

        try {
            BufferedImage[] mario = new BufferedImage[2];
            mario[0] = ImageIO.read(new File(marioPath1));
            mario[1] = ImageIO.read(new File(marioPath2));
            this.put(SmallPlayer.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(marioPath3))), new AnimatedTexture(mario,0.3),1,1.8, 0,0));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
