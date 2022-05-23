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

                    if(SmallPlayer.class.equals(p.getClass())){
                        p.kill();
                    }else if(NormalPlayer.class.equals(p.getClass())){
                        p.newPlayer = new SmallPlayer(this.x, this.y, this.game);
                        p.newPlayer.speedX = this.speedX;
                        p.newPlayer.speedY = this.speedY;
                        p.newPlayer.protect();
                        System.out.println(p.newPlayer==null);
                    }else if(FirePlayer.class.equals(p.getClass())){
                        p.newPlayer = new NormalPlayer(this.x, this.y, this.game);
                        p.newPlayer.speedX = this.speedX;
                        p.newPlayer.speedY = this.speedY;
                        p.newPlayer.protect();
                    }

                    p.protect();
                }else{
                    p.speedY=rebounceSpeed;
                    super.hasToBeRemoved = true;
                }
            }
        }
    }
}
