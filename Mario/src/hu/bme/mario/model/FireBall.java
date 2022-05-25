package hu.bme.mario.model;

public class FireBall extends Entity {
    private boolean walkRight = false;
    private Player owner;

    public FireBall(double x, double y, Direction initialDir, Player owner){
        super(x,y,0,0,0,0, new Hitbox(1.2, 1.2), owner.game);
        this.owner = owner;
        if(initialDir == Direction.LEFT){
            this.walkRight = false;
        }else if(initialDir == Direction.RIGHT){
            this.walkRight = true;
        }
    }

    protected double getMaxSpeedX() {
        return 12;
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
        if(motion == Direction.LEFT || motion == Direction.RIGHT){
            this.hasToBeRemoved = true;
        }
    }

    protected void collideWithEntity(Direction collisionSide, Entity e) {
        if (Player.class.isAssignableFrom(e.getClass())) {
            Player p = (Player) e;
            if (!p.isProtected() && this.owner != p) {

                if (collisionSide == Direction.RIGHT) {
                    p.speedX = -hitSpeed;
                } else if (collisionSide == Direction.LEFT) {
                    p.speedX = hitSpeed;
                }

                if (p.getStarCount() > 0) {
                    p.decStarCount();
                    System.out.println("del fireball");
                    this.game.addEntity(new Star(this.x, this.y, (this.x < e.x) ? Direction.RIGHT : Direction.LEFT, this.game));
                }
                p.protect();
            }
        }
    }
}
