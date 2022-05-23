package hu.bme.mario.client;

import hu.bme.mario.model.*;

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
        String goombaPath1 = null;
        String goombaPath2 = null;
        String koopaPath1 = null;
        String koopaPath2 = null;
        String marioFirePath1 = null;
        String marioFirePath2 = null;
        String marioFirePath3 = null;
        String shellPath = null;
        String mushroomPath = null;
        String flowerPath = null;
        String starPath = null;
        String firePath = null;

        if (os.contains("win")) {
            marioPath1 = "Mario/textures/mario_run1.png";
            marioPath2 = "Mario/textures/mario_run2.png";
            marioPath3 = "Mario/textures/mario_standing.png";
            goombaPath1 = "Mario/textures/goomba1.png";
            goombaPath2 = "Mario/textures/goomba2.png";
            koopaPath1 = "Mario/textures/koopa1.png";
            koopaPath2 = "Mario/textures/koopa2.png";
            marioFirePath1 = "Mario/textures/mario_fire_run1.png";
            marioFirePath2 = "Mario/textures/mario_fire_run2.png";
            marioFirePath3 = "Mario/textures/mario_fire_standing.png";
            shellPath = "Mario/textures/shell.png";
            mushroomPath = "Mario/textures/mushroom.png";
            flowerPath = "Mario/textures/flower.png";
            starPath = "Mario/textures/star.png";
            firePath = "Mario/textures/fireball.png";
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            marioPath1 = "textures/mario_run1.png";
            marioPath2 = "textures/mario_run2.png";
            marioPath3 = "textures/mario_standing.png";
            goombaPath1 = "textures/goomba1.png";
            goombaPath2 = "textures/goomba2.png";
            koopaPath1 = "textures/koopa1.png";
            koopaPath2 = "textures/koopa2.png";
            marioFirePath1 = "textures/mario_fire_run1.png";
            marioFirePath2 = "textures/mario_fire_run2.png";
            marioFirePath3 = "textures/mario_fire_standing.png";
            shellPath = "textures/shell.png";
            mushroomPath = "textures/mushroom.png";
            flowerPath = "textures/flower.png";
            starPath = "textures/star.png";
            firePath = "textures/fireball.png";
        }

        try {
            BufferedImage[] mario = new BufferedImage[2];
            mario[0] = ImageIO.read(new File(marioPath1));
            mario[1] = ImageIO.read(new File(marioPath2));
            this.put(SmallPlayer.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(marioPath3))), new AnimatedTexture(mario,0.3),1,0.9, 0,0));
            this.put(NormalPlayer.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(marioPath3))), new AnimatedTexture(mario,0.3),1,1.8, 0,0));

            BufferedImage[] marioFire = new BufferedImage[2];
            marioFire[0] = ImageIO.read(new File(marioFirePath1));
            marioFire[1] = ImageIO.read(new File(marioFirePath2));
            this.put(FirePlayer.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(marioFirePath3))), new AnimatedTexture(marioFire,0.3),1,1.8, 0,0));


            BufferedImage[] goomba = new BufferedImage[2];
            goomba[0] = ImageIO.read(new File(goombaPath1));
            goomba[1] = ImageIO.read(new File(goombaPath2));
            this.put(Goomba.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(goombaPath1))), new AnimatedTexture(goomba,0.3),1,1, 0,0));
            BufferedImage[] koopa = new BufferedImage[2];
            koopa[0] = ImageIO.read(new File(koopaPath1));
            koopa[1] = ImageIO.read(new File(koopaPath2));
            this.put(Koopa.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(koopaPath1))), new AnimatedTexture(koopa,0.3),1,1, 0,0));

            this.put(Mushroom.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(mushroomPath))), new UnanimatedTexture(ImageIO.read(new File(mushroomPath))), 1,1, 0, 0));
            this.put(FireBall.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(firePath))), new UnanimatedTexture(ImageIO.read(new File(firePath))), 0.4,0.4, 0, 0));
            this.put(Flower.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(flowerPath))), new UnanimatedTexture(ImageIO.read(new File(flowerPath))), 1,1, 0, 0));
            this.put(Star.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(starPath))), new UnanimatedTexture(ImageIO.read(new File(starPath))), 1.2,1.2, 0, 0));
            this.put(KoopaShell.class, new EntityTexture(new UnanimatedTexture(ImageIO.read(new File(shellPath))), new UnanimatedTexture(ImageIO.read(new File(shellPath))), 1,1, 0, 0));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
