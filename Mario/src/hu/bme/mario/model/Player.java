package hu.bme.mario.model;

public abstract class Player extends Entity{
    private boolean canJump;

    public Player(double x, double y, Hitbox hitbox){
        super(x,y,0,0,0,0, hitbox);
        this.canJump = false;
    }

    public boolean canJump(){
        return canJump;
    }
    public void setCanJump(boolean canJump){
        this.canJump = canJump;
    }
    protected double getMaxSpeedX(){
        return 1;
    }
    protected double getMaxSpeedY(){
        return 1;
    }
}
