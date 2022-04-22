package hu.bme.mario.model;

public abstract class Player extends Entity {

    private boolean canJump;
    private int nb_life;
    private boolean in_the_air;

    public Player(double x, double y, Hitbox hitbox){
        super(x,y,0,0,0,0, hitbox, true, false);
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
