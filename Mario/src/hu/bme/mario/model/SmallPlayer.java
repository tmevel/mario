package hu.bme.mario.model;

public class SmallPlayer extends Player{
    public SmallPlayer(double x, double y, Game game){
        super(x,y,new Hitbox(0.8,1.6), game);

    }

    protected void collideWithEntity(Direction collisionSide, Entity e){
        if(collisionSide == Direction.TOP){
            if (Player.class.isAssignableFrom(e.getClass())) {

            }
        }
    }
}
