package hu.bme.mario.model;

public class Hitbox {
    private double width;
    private double height;

    public Hitbox(double width, double height){
        this.width = width;
        this.height = height;
    }

    public static boolean hitboxCollision(Entity a, Entity b){
        //TODO check collision between 2 entities
        return false;
    }
}
