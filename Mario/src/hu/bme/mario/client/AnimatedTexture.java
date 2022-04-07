package hu.bme.mario.client;

import java.awt.*;

public class AnimatedTexture implements Texture{
    private Image[] frames;
    private double cycleTime;

    public AnimatedTexture(Image[] frames, double cycleTime){
        this.frames = frames;
        this.cycleTime = cycleTime;
    }
    public Image getTexture(){
        int frameNumber = (int)((System.currentTimeMillis()%((int)(1000*cycleTime)))* frames.length/(cycleTime*1000));
        return frames[frameNumber];
    }
}
