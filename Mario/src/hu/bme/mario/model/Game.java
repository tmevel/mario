package hu.bme.mario.model;

import java.util.arraylist;

public class Game {
    private ArrayList<Entity> entities;
    private Block[][] map;

    public Game(Block[][] map){
        this.map = map;
        this.entities = new ArrayList<Entity>();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Block[][] getMap() {
        return map;
    }

    public void addEntity(Entity e) {
        this.entities.add(e);
    }
}
