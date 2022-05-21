package hu.bme.mario.model;

public abstract class Enemy extends DumbEntity{
    public Enemy(double x, double y, Hitbox hitbox, Direction initialDir, Game game) {
        super(x, y, hitbox, initialDir, game);
    }

    protected void collideWithEntity(Direction collisionSide, Entity e){
        if(collisionSide == Direction.TOP){
            if (Player.class.isAssignableFrom(e.getClass())) {

            }
        }
    }
}
