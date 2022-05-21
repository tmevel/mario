package hu.bme.mario.model;

public abstract class DumbEntity extends Entity {
    private boolean walkRight = false;

    public DumbEntity(double x, double y, Hitbox hitbox, Direction initialDir, Game game){
        super(x,y,0,0,0,0, hitbox, game);
        if(initialDir == Direction.LEFT){
            super.isLookingLeft = true;
            this.walkRight = false;
        }else if(initialDir == Direction.RIGHT){
            this.walkRight = true;
            super.isLookingLeft = false;
        }
    }

    protected double getMaxSpeedY(){ return 60; }

    public void update(double dt){
        if(walkRight){
            super.speedX = getMaxSpeedX();
        }else{
            super.speedX = -getMaxSpeedX();
        }
        super.update(dt);
    }

    public void collideWithBlock(Direction motion, int x, int y){
        if(motion == Direction.LEFT){
            super.isLookingLeft = false;
            this.walkRight = true;
        }else if(motion == Direction.RIGHT){
            super.isLookingLeft = true;
            this.walkRight = false;
        }
    }
}
