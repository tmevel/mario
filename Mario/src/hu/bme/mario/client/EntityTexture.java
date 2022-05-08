package hu.bme.mario.client;

import java.awt.*;

public class EntityTexture{
    private Texture standing;
    private Texture moving;
    private double width;
    private double height;
    private int spriteOffsetX;
    private int spriteOffsetY;
    public EntityTexture(Texture standing, Texture moving, double width, double height, int spriteOffsetX, int spriteOffsetY){
        this.standing = standing;
        this.moving = moving;
        this.width = width;
        this.height = height;
        this.spriteOffsetX = spriteOffsetX;
        this.spriteOffsetY = spriteOffsetY;
    }

    public Image getMovingTexture() {
        return moving.getTexture();
    }
    public Image getStandingTexture() {
        return standing.getTexture();
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
