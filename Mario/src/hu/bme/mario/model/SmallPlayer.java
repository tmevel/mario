package hu.bme.mario.model;

public class SmallPlayer extends Player{
    public SmallPlayer(double x, double y, Game game){
        super(x,y,new Hitbox(0.8,0.8), game);

    }
    public void special(){}

    protected void collideWithEntity(Direction collisionSide, Entity e){
        if (Mushroom.class.equals(e.getClass())) {
            this.newPlayer = new NormalPlayer(this.x, this.y, this.game);
            this.newPlayer.speedX = this.speedX;
            this.newPlayer.speedY = this.speedY;
            this.newPlayer.starCount = this.starCount;
            e.hasToBeRemoved = true;
        }else if (Flower.class.equals(e.getClass())) {
            this.newPlayer = new FirePlayer(this.x, this.y, this.game);
            this.newPlayer.speedX = this.speedX;
            this.newPlayer.speedY = this.speedY;
            this.newPlayer.starCount = this.starCount;
            e.hasToBeRemoved = true;
        }

        super.collideWithEntity(collisionSide, e);
    }
}
