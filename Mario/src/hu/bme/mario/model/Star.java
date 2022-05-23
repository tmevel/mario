package hu.bme.mario.model;

public class Star extends Entity {
    private boolean walkRight = false;

    public Star(double x, double y, Direction initialDir, Game game){
        super(x,y,0,0,0,0, new Hitbox(1.2, 1.2), game);
        if(initialDir == Direction.LEFT){
            this.walkRight = false;
        }else if(initialDir == Direction.RIGHT){
            this.walkRight = true;
        }
    }

    protected double getMaxSpeedX() {
        return 8;
    }

    protected double getMaxSpeedY(){ return 12; }

    public void update(double dt){
        if(walkRight){
            super.speedX = getMaxSpeedX();
        }else{
            super.speedX = -getMaxSpeedX();
        }
        if(this.onTheGround){
            super.speedY = getMaxSpeedY();
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

    protected void collideWithEntity(Direction collisionSide, Entity e) {
    }
}
