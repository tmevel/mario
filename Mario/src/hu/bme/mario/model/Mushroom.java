package hu.bme.mario.model;

public class Mushroom extends DumbEntity{

    public Mushroom(double x, double y, Direction initialDir, Game game){

        super(x,y,new Hitbox(0.8, 0.8),initialDir, game);
    }

    protected double getMaxSpeedX(){
        return 1;
    }

    protected void collideWithEntity(Direction collisionSide, Entity e){
    }
}
