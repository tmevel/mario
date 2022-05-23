package hu.bme.mario.model;

import java.io.Serializable;

public class Hitbox implements Serializable {
    private double width;
    private double height;

    private static final double topBottomHitboxPercent = 30;


    public Hitbox(double width, double height){
        this.width = width;
        this.height = height;

    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public static Direction collisionEntity(Entity a, Entity b){
        if((a.getX()>=b.getX() && a.getX()<=b.getX()+b.getHitbox().getWidth()) || (b.getX()>=a.getX() && b.getX()<=a.getX()+a.getHitbox().getWidth())){
            if(a.getY()>b.getY() && a.getY()>b.getY()+b.getHitbox().getHeight()*(1-Hitbox.topBottomHitboxPercent/100) && a.getY()<b.getY()+b.getHitbox().getHeight()){
                return Direction.TOP; //a collides with the top of b
            }else if(b.getY()>a.getY() && b.getY()>a.getY()+a.getHitbox().getHeight()*(1-Hitbox.topBottomHitboxPercent/100) && b.getY()<a.getY()+a.getHitbox().getHeight()){
                return Direction.BOTTOM;
            }else if((a.getY()>=b.getY() && a.getY()<=b.getY()+b.getHitbox().getHeight()) || (b.getY()>=a.getY() && b.getY()<=a.getY()+a.getHitbox().getHeight())){
                if(a.getX()>b.getX()){
                    return Direction.RIGHT;
                }
                return Direction.LEFT;
            }
        }
        return null;
    }

    public static boolean collisionMap(Block[][] map, Entity e, Direction motionDirection){
        int xStart = Math.max(0,(int)e.getX());
        int yStart = Math.max(0,(int)e.getY());

        int xEnd = Math.min(map.length, (int)Math.ceil(e.getX()+e.getHitbox().getWidth()));
        int yEnd = Math.min(map[0].length, (int)Math.ceil(e.getY()+e.getHitbox().getHeight()));

        boolean collision = false;

        for(int x=xStart;x<xEnd;x++){
            for(int y=yStart;y<yEnd;y++){
                if(map[x][y]!=null){
                    e.collideWithBlock(motionDirection, x,y);
                    collision =  true;
                }
            }
        }
        return collision;
    }
    public static boolean onGround(Block[][] map, Entity e){
        int xStart = Math.max(0,(int)e.getX());
        int yStart = Math.max(0,(int)e.getY());

        int xEnd = Math.min(map.length-1, (int)Math.ceil(e.getX()+e.getHitbox().getWidth()));

        for(int x=xStart;x<xEnd;x++){
            if(map[x][yStart]!=null){return true;}
        }
        return false;
    }
}
