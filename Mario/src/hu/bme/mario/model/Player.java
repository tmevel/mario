package hu.bme.mario.model;

public abstract class Player extends Entity {
    private static final double jumpSpeed = 21;
    private static final double walkAcc = 15;
    private static final double stopAcc = 50;

    private boolean walkRight = false;
    private boolean walkLeft = false;

    public Player(double x, double y, Hitbox hitbox){
        super(x,y,0,0,0,0, hitbox);
    }

    public void jump(){
        if(this.onTheGround){
            super.speedY+=jumpSpeed;
        }
    }
    public void walkRight(){
        this.walkLeft = false;
        this.walkRight = true;
        super.isLookingLeft = false;
    }
    public void walkLeft(){
        this.walkLeft = true;
        this.walkRight = false;
        super.isLookingLeft = true;
    }
    public void stop(){
        this.walkLeft = false;
        this.walkRight = false;
    }

    public void update(double dt, Block[][] map){
        if(this.walkRight || (this.speedX<0 && !this.walkLeft)){
            super.accX = this.walkAcc;
        }else if(this.walkLeft || this.speedX>0){
            super.accX = -this.walkAcc;
        }
        if(this.speedX<0 && !this.walkLeft){
            super.accX = this.stopAcc;
            if(Math.abs(this.speedX)<(this.walkAcc*dt)){ // avoid speed oscillation
                super.speedX = 0;
                super.accX = 0;
            }
        }
        if(this.speedX>0 && !this.walkRight){
            super.accX = -this.stopAcc;
            if(Math.abs(this.speedX)<(this.walkAcc*dt)){
                super.speedX = 0;
                super.accX = 0;
            }
        }
        System.out.println("acc: "+super.accX);
        System.out.println("v "+super.accX);

        super.update(dt, map);
    }



    protected double getMaxSpeedX(){
        return 12;
    }
    protected double getMaxSpeedY(){ return 60; }
}
