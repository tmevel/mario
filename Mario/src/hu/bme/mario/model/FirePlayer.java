package hu.bme.mario.model;

public class FirePlayer extends Player{
    public FirePlayer(double x, double y, Game game){
        super(x,y,new Hitbox(0.8,1.6), game);

    }

    public void special(){
        Direction d = Direction.RIGHT;
        if(isLookingLeft){
            d = Direction.LEFT;
        }
        super.game.addEntity(new FireBall(x, y+1, d, this));
    }

    protected void collideWithEntity(Direction collisionSide, Entity e){
        super.collideWithEntity(collisionSide, e);
    }
}
