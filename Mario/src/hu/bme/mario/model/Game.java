package hu.bme.mario.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
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

    public void update(double dt){
        for(int i=0;i<this.entities.size();i++){
            this.entities.get(i).update(dt);
        }
    }

    public void addEntity(Entity e) {
        this.entities.add(e);
    }
}
