package hu.bme.mario.client;

import java.awt.*;

public class EntityTexture implements Texture{
    private Texture texture;
    private double width;
    private double height;
    private int spriteOffsetX;
    private int spriteOffsetY;
    public EntityTexture(Texture t, double width, double height, int spriteOffsetX, int spriteOffsetY){
        this.texture = t;
        this.width = width;
        this.height = height;
        this.spriteOffsetX = spriteOffsetX;
        this.spriteOffsetY = spriteOffsetY;
    }

    public Image getTexture() {
        return texture.getTexture();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getSpriteOffsetX() {
        return spriteOffsetX;
    }

    public int getSpriteOffsetY() {
        return spriteOffsetY;
    }
}
