package hu.bme.mario.model;

public abstract class Enemy extends DumbEntity{
    public Enemy(double x, double y, Hitbox hitbox, Direction initialDir, Game game) {
        super(x, y, hitbox, initialDir, game);
    }

    protected void collideWithEntity(Direction collisionSide, Entity e){
        if (Player.class.isAssignableFrom(e.getClass())) {
            Player p = (Player) e;
            if(!p.isProtected()){
                if(collisionSide != Direction.BOTTOM){
                    if(collisionSide == Direction.RIGHT){
                        p.speedX=-hitSpeed;
                    }else if(collisionSide == Direction.LEFT){
                        p.speedX=hitSpeed;
                    }

                    if(p.getStarCount()>0) {
                        p.decStarCount();
                        this.game.addEntity(new Star(this.x, this.y, (this.x<e.x)?Direction.RIGHT:Direction.LEFT, this.game));
                    }

                    if(SmallPlayer.class.equals(p.getClass())){
                        p.kill();
                    }else if(NormalPlayer.class.equals(p.getClass())){
                        p.newPlayer = new SmallPlayer(this.x, this.y, this.game);
                        p.newPlayer.speedX = this.speedX;
                        p.newPlayer.speedY = this.speedY;
                        p.newPlayer.protect();
                        p.newPlayer.setStarCount(p.getStarCount());
                    }else if(FirePlayer.class.equals(p.getClass())){
                        p.newPlayer = new NormalPlayer(this.x, this.y, this.game);
                        p.newPlayer.speedX = this.speedX;
                        p.newPlayer.speedY = this.speedY;
                        p.newPlayer.protect();
                        p.newPlayer.setStarCount(p.getStarCount());
                    }

                    p.protect();
                }else{
                    p.speedY=rebounceSpeed;
                    super.hasToBeRemoved = true;
                }
            }
        }
        if(FireBall.class.equals(e.getClass())){
            super.hasToBeRemoved = true;
            e.hasToBeRemoved = true;
        }
    }
}
