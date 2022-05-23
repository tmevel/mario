package hu.bme.mario.model;

public class ClientGame extends Game{
    private final int playerID;

    public ClientGame(Game game, int playerID){
        super(game.getMap(), game.getEntities(), game.getPlayers());
        this.playerID = playerID;
    }

    public Player getPlayer(){
        return super.getPlayers().get(this.playerID);
    }
}
