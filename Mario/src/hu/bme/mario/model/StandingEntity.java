package hu.bme.mario.model;

public abstract class StandingEntity extends Entity{

    public StandingEntity(double x, double y, Hitbox hitbox, Game game){
        super(x,y,0,0, 0, 0, hitbox, game);
    }


    protected double getMaxSpeedX() {
        return 0;
    }

    protected double getMaxSpeedY() {
        return 0;
    }

    public void collideWithBlock(Direction collisionSide, int x, int y) {}
}
