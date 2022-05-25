package hu.bme.mario.model;

public class NormalPlayer extends Player{
    public NormalPlayer(double x, double y, Game game){
        super(x,y,new Hitbox(0.8,1.6), game);

    }

    public void special(){}

    protected void collideWithEntity(Direction collisionSide, Entity e){
        if (Flower.class.equals(e.getClass())) {
            this.newPlayer = new FirePlayer(this.x, this.y, this.game);
            this.newPlayer.speedX = this.speedX;
            this.newPlayer.speedY = this.speedY;
            this.newPlayer.starCount = this.starCount;
            e.hasToBeRemoved = true;
        }
        super.collideWithEntity(collisionSide, e);
    }
}
