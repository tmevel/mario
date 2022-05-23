package hu.bme.mario.model;

public abstract class Player extends Entity {
    private static final double jumpSpeed = 21;
    private static final double walkAcc = 15;
    private static final double stopAcc = 50;
    private static final double walkAccInAir = 5;
    private static final double stopAccInAir = 15;

    private static final double protectionDelay = 1.5;
    private double protection = 0;

    private boolean dead = false;

    private boolean walkRight = false;
    private boolean walkLeft = false;

    private int starCount = 0;

    protected Player newPlayer = null; //if flower/mushroom/damage take

    public Player(double x, double y, Hitbox hitbox, Game game){
        super(x,y,0,0,0,0, hitbox, game);
    }

    public void jump(){
        if(this.onTheGround){
            super.speedY=jumpSpeed;
        }
    }
    public abstract void special();
    public boolean isDead(){
        return this.dead;
    }
    public void kill(){
        this.dead = true;
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

    public boolean isProtected(){
        return this.protection>0;
    }

    public void protect(){
        this.protection = Player.protectionDelay;
    }

    public Player getNewPlayer() {
        return this.newPlayer;
    }

    public void resetNewPlayer() {
        this.newPlayer = null;
    }

    public void update(double dt){
        if(this.protection>0){
            this.protection = Math.max(0, this.protection-dt);
        }

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

        if(this.getY()+this.getHitbox().getHeight() < 0){
            this.kill();
        }

        super.update(dt);
    }



    protected double getMaxSpeedX(){
        return 12;
    }
    protected double getMaxSpeedY(){ return 60; }
    public void collideWithBlock(Direction motion, int x, int y){
        if(motion == Direction.TOP){
            if (QuestionBlock.class.equals(super.game.getMap()[x][y].getClass())) {
                super.game.getMap()[x][y] = new EmptyBlock();
                if(SmallPlayer.class.equals(this.getClass())){
                    super.game.addEntity(new Mushroom(x, y+1, Direction.RIGHT, this.game));
                }else{
                    super.game.addEntity(new Flower(x, y+1, this.game));
                }
            }else if (BreakableBlock.class.equals(super.game.getMap()[x][y].getClass())) {
                super.game.getMap()[x][y] = null;
            }
        }
    }

    protected void collideWithEntity(Direction collisionSide, Entity e){
        if(!this.isProtected()){
            if (Star.class.equals(e.getClass())) {
                this.starCount ++;
                e.hasToBeRemoved = true;
            }else if(Player.class.isAssignableFrom(e.getClass())){
                Player p = (Player)e;
                if(!p.isProtected()){
                    if(collisionSide != Direction.TOP){
                        if(this.starCount>0) {
                            this.starCount--;
                            this.game.addEntity(new Star(this.x, this.y, (this.x>e.x)?Direction.RIGHT:Direction.LEFT, this.game));
                        }
                        this.protect();
                    }
                    if(collisionSide == Direction.TOP){
                        this.speedY = rebounceSpeed;
                    }else if(collisionSide == Direction.RIGHT){
                        this.speedX=hitSpeed;
                    }else if(collisionSide == Direction.LEFT){
                        this.speedX=-hitSpeed;
                    }
                }

            }
        }
    }
}
