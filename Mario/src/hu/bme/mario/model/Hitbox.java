package hu.bme.mario.model;

public class Hitbox {
    private double width;
    private double height;


    public Hitbox(double width, double height){
        this.width = width;
        this.height = height;

    }

    public boolean hitboxCollision(Entity a, Entity b){
        //TODO check collision between 2 entities
        if (a.getX()==b.getX()+width&&a.getY()==b.getY()||a.getX()==b.getX()&&a.getY()+height==b.getY()||a.getX()+width==b.getX()&&a.getY()==b.getY()||a.getX()==b.getX()&&a.getY()==b.getY()+height){
            return true;
        }else{
            return false;
        }
    }

    public boolean CollisionUp(Entity a, Entity b){
        if (a.getX()==b.getX()&&a.getY()+height==b.getY()){
            return true;
        }else{
            return false;
        }
    }

    public boolean CollisionSide(Entity a, Entity b){
        if (a.getX()==b.getX()+width&&a.getY()==b.getY()||a.getX()+width==b.getX()&&a.getY()==b.getY()){
            return true;
        }else{
            return false;
        }
    }

    public boolean CollisionDown(Entity a, Entity b){
        if (a.getX()==b.getX()&&a.getY()-height==b.getY()){
            return true;
        }else{
            return false;
        }
    }
}
