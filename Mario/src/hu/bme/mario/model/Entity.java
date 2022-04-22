package hu.bme.mario.model;


public abstract class Entity {
    private double x;
    private double y;
    private double speedX;
    private double speedY;
    private double accX;
    private double accY;
    private Hitbox hitbox;
    private boolean living;
    private boolean left;

    public Entity(double x, double y, double speedX, double speedY, double accX, double accY, Hitbox hitbox, boolean living, boolean left){
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.accX = accX;
        this.accY = accY;
        this.hitbox = hitbox;
        this.living = living;
        this.left = left;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void incX(){this.x+=0.1;}

    public void decX(){this.x-=0.1;}

    public void setAccX(double accX) {this.accX = accX;}

    public void setAccY(double accY) {this.accY = accY;}

    protected abstract double getMaxSpeedX();
    protected abstract double getMaxSpeedY();

    public void update(double dt, Block[][] map){
        //TODO apply mechanical calculation, dt the time between 2 frames
    }


}
