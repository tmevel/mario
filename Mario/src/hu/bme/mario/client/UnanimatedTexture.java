package hu.bme.mario.client;

import java.awt.*;

public class UnanimatedTexture implements Texture{
    private Image texture;
    private double cycleTime;

    public UnanimatedTexture(Image texture){
        this.texture = texture;
    }
    public Image getTexture(){
        return texture;
    }
}
