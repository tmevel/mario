package hu.bme.mario.model;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private static final double GRAVITY = -40;
    protected double x;
    protected double y;
    protected double speedX;
    protected double speedY;
    protected double accX;
    protected double accY;
    protected Hitbox hitbox;
    protected boolean isLookingLeft;
    protected boolean onTheGround;

    public Entity(double x, double y, double speedX, double speedY, double accX, double accY, Hitbox hitbox){
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.accX = accX;
        this.accY = accY;
        this.hitbox = hitbox;
        this.onTheGround = false;
        this.isLookingLeft = false;
    }

    public boolean isLookingLeft() {
        return isLookingLeft;
    }

    public boolean isMoving(){
        return this.speedX!=0;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public Hitbox getHitbox(){
        return this.hitbox;
    }

    protected abstract double getMaxSpeedX();
    protected abstract double getMaxSpeedY();

    public void update(double dt, Block[][] map){

        this.onTheGround = false;

        //saving position in case of collision with block
        double lastX = this.x;
        double lastY = this.y;

        //basic mechanical stuff on Y
        this.speedY = Math.max(Math.min(this.speedY+(this.accY+Entity.GRAVITY)*dt, this.getMaxSpeedY()), -this.getMaxSpeedY());
        this.y+=this.speedY*dt;


        //in case of collision, go back to past position and reset Yspeed
        if(Hitbox.collisionMap(map, this)){
            this.speedY = 0;
            if(Hitbox.onGround(map, this)) {
                this.onTheGround = true;
            }
            this.y = lastY;
        }

        //basic mechanical stuff on X
        this.speedX = Math.max(Math.min(this.speedX+this.accX*dt, this.getMaxSpeedX()), -this.getMaxSpeedX());
        this.x+=this.speedX*dt;


        //in case of collision, go back to past position and reset Xspeed
        if(Hitbox.collisionMap(map, this)){
            this.x = lastX;
            this.speedX = 0;
        }
    }
}
