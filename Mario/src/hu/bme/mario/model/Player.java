package hu.bme.mario.model;

public abstract class Player extends Entity {
    private static final double jumpSpeed = 21;
    private static final double walkAcc = 15;
    private static final double stopAcc = 50;
    private static final double walkAccInAir = 5;
    private static final double stopAccInAir = 15;

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
        double wacc = super.onTheGround?this.walkAcc:this.walkAccInAir;
        double sacc = super.onTheGround?this.stopAcc:this.stopAccInAir;


        if(this.walkRight || (this.speedX<0 && !this.walkLeft)){
            super.accX = wacc;
        }else if(this.walkLeft || this.speedX>0){
            super.accX = -wacc;
        }
        if(this.speedX<0 && !this.walkLeft){
            super.accX = sacc;
            if(Math.abs(this.speedX)<=(this.stopAcc*dt)){ // avoid speed oscillation
                super.speedX = 0;
                super.accX = 0;
            }
        }
        if(this.speedX>0 && !this.walkRight){
            super.accX = -sacc;
            if(Math.abs(this.speedX)<=(this.stopAcc*dt)){
                super.speedX = 0;
                super.accX = 0;
            }
        }
        super.update(dt, map);
    }



    protected double getMaxSpeedX(){
        return 12;
    }
    protected double getMaxSpeedY(){ return 60; }
}
