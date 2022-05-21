package hu.bme.mario.model;

public class Goomba extends Enemy {

    public Goomba(double x, double y, Direction initialDir, Game game){

        super(x,y,new Hitbox(0.8, 0.8),initialDir, game);
    }

    protected double getMaxSpeedX(){
        return 0.6;
    }
}