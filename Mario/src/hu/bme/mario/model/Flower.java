package hu.bme.mario.model;

public class Flower extends StandingEntity{

    public Flower(double x, double y, Game game){

        super(x,y,new Hitbox(1, 1), game);
    }

    protected void collideWithEntity(Direction collisionSide, Entity e){
    }
}
