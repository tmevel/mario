package hu.bme.mario.model;

public abstract class Entity {
    private double x;
    private double y;
    private double speedX;
    private double speedY;
    private double accX;
    private double accY;
    private Hitbox hitbox;

    public Entity(double x, double y, double speedX, double speedY, double accX, double accY, Hitbox hitbox){
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.accX = accX;
        this.accY = accY;
        this.hitbox = hitbox;
    }

    public double getX(){
        return x;
    }

    //to be removed
    public void incX(){
        this.x+=0.05;
    }
    public void decX(){
        this.x-=0.05;
    }

    public double getY(){
        return y;
    }

    public void setAccX(double accX) {
        this.accX = accX;
    }

    public void setAccY(double accY) {
        this.accY = accY;
    }

    protected abstract double getMaxSpeedX();
    protected abstract double getMaxSpeedY();

    public void update(double dt, Block[][] map){
        //TODO apply mechanical calculation, dt the time between 2 frames
    }
}
