package hu.bme.mario.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private ArrayList<Entity> entities;
    private ArrayList<Player> players;
    private ArrayList<Entity> entitiesToBeAdded;
    private Block[][] map;
    private Block[][] mapInitial;
    private static final double resetMapDelay = 30;
    private double resetTime;

    public Game(Block[][] map){
        this(map, new ArrayList<Entity>(), new ArrayList<Player>());
    }

    private void resetMap(){
        map = new Block[this.mapInitial.length][this.mapInitial[0].length];
        for(int i=0;i<this.mapInitial.length;i++){
            for(int j=0;j<this.mapInitial[0].length;j++){
                this.map[i][j] = this.mapInitial[i][j];
            }
        }
        this.resetTime = resetMapDelay;
    }

    public Game(Block[][] map, ArrayList<Entity> entities, ArrayList<Player> players){
        this.mapInitial = map;
        this.resetMap();
        this.entities = entities;
        this.players = players;
        this.entitiesToBeAdded = new ArrayList<Entity>();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getWinner(){
        for(int i=0;i<this.players.size();i++){
            if(this.players.get(i).getStarCount()>=5){
                System.out.println(i);
                return this.players.get(i);
            }
        }
        return null;
    }

    public Block[][] getMap() {
        return map;
    }

    public void update(double dt){

        this.resetTime -= dt;
        if(this.resetTime<=0){
            this.resetMap();
        }

        for(int i=0;i<this.entities.size();i++){
            this.entities.get(i).update(dt);
        }
        for(int i=0;i<this.entities.size();i++){
            if(this.entities.get(i).isToBeRemoved()){
                this.entities.remove(i);
            }
        }
        for(int i=0;i<this.entitiesToBeAdded.size();i++){
            this.entities.add(this.entitiesToBeAdded.get(i));
        }
        this.entitiesToBeAdded = new ArrayList<Entity>();
        for(int i=0;i<this.players.size();i++){
            if(this.players.get(i).getNewPlayer()!=null){
                this.entities.set(this.entities.indexOf(this.players.get(i)), this.players.get(i).getNewPlayer());
                this.players.set(i, this.players.get(i).getNewPlayer());
                this.players.get(i).resetNewPlayer();
            }
        }
    }

    public void addEntity(Entity e) {
        this.entitiesToBeAdded.add(e);
    }
    public void addPlayer(Player p) {
        this.players.add(p);
        this.entities.add(p);
    }
}
