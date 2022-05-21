package hu.bme.mario.model;

import java.io.Serializable;

public class Hitbox implements Serializable {
    private double width;
    private double height;


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

    /*
    public static boolean hitboxCollision(Entity a, Entity b){
        //TODO check collision between 2 entities
        if (a.getX()==b.getX()+width&&a.getY()==b.getY()||a.getX()==b.getX()&&a.getY()+height==b.getY()||a.getX()+width==b.getX()&&a.getY()==b.getY()||a.getX()==b.getX()&&a.getY()==b.getY()+height){
            return true;
        }else{
            return false;
        }
    }

    public boolean collisionUp(Entity a, Entity b){
        if (a.getX()==b.getX()&&a.getY()+height==b.getY()){
            return true;
        }else{
            return false;
        }
    }

    public boolean collisionSide(Entity a, Entity b){
        if (a.getX()==b.getX()+width&&a.getY()==b.getY()||a.getX()+width==b.getX()&&a.getY()==b.getY()){
            return true;
        }else{
            return false;
        }
    }

    public boolean collisionDown(Entity a, Entity b){
        if (a.getX()==b.getX()&&a.getY()-height==b.getY()){
            return true;
        }else{
            return false;
        }
    }

    */

    public static boolean collisionMap(Block[][] map, Entity e, Direction motionDirection){
        int xStart = Math.max(0,(int)e.getX());
        int yStart = Math.max(0,(int)e.getY());

        int xEnd = Math.min(map.length-1, (int)Math.ceil(e.getX()+e.getHitbox().getWidth()));
        int yEnd = Math.min(map[0].length-1, (int)Math.ceil(e.getY()+e.getHitbox().getHeight()));

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
