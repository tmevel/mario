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
    private double counterJump;

    public Entity(double x, double y, double speedX, double speedY, double accX, double accY, Hitbox hitbox, boolean living, double counterJump){
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.accX = accX;
        this.accY = accY;
        this.hitbox = hitbox;
        this.living = living;
        this.counterJump = counterJump;
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

        if(map[(int)x][(int)y-1]==null && map[(int)x+1][(int)y-1]==null){             //gravity
            accY=-1;                    //si les deux cases en dessous de marie sont vide, il accelere vers le bas
        }
        else{
            speedY = 0;                 //sinon son Y reste le meme
            accY = 0;
        }

        if(map[(int)(x+ hitbox.getWidth())][(int)y]==null && map[(int)(x+ hitbox.getWidth())][(int)(y+ hitbox.getHeight())]==null){
            accX=+1;                    //si il n'y a pas de case devant lui, a droit, il avance a droit
        }
        else{
            speedX = 0;                 //sinon son X reste le meme
            accX = 0;
        }

        if(map[(int)(x - 1)][(int)y]==null && map[(int)(x-1)][(int)(y+ hitbox.getHeight())]==null){
            accX=-1;                    //si il n'y a pas de case devant lui, a gauche, il avance a droit
        }
        else{
            speedX = 0;                 //sinon son X reste le meme
            accX = 0;
        }

        if(map[(int)x][(int)(y+ hitbox.getHeight())]==null && map[(int)x+1][(int)(y+ hitbox.getHeight())]==null){
            counterJump++;                  //augmente pour que mario ralentisse en l'air
            if (counterJump < 11) {
                accY = 10 - counterJump;
            }                                       //si les deux cases en dessus de marie sont vide, il peut faire son saut
        }
        else{
            speedY = 0;                 //sinon son Y reste le meme
            accY = 0;
        }

        if(y<0){                    //fall into the void
            living=false;                   //si il descend en dessous de la map, il meurt
        }

        speedY +=accY;
        speedX +=accX;

        y+=speedY;
        x+=speedX;
    }


    public void walk(){
        if (speedX<getMaxSpeedX()){
            speedX=+accX;
        }else {
            speedX=getMaxSpeedX();
        }
        x=+speedX;
    }

    public void jump() {
            counterJump++;
            if (counterJump < 11) {
                accY = 10 - counterJump;
            }
            speedY = +accY;
            y = +speedY;
    }


}
