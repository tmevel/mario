package hu.bme.mario.localtesting;

import hu.bme.mario.model.Game;
import hu.bme.mario.network.KeyControl;

public class FakeSession{
    private Game g;

    private KeyControl control;

    public FakeSession(Game g){
        this.g = g;
        this.control = new KeyControl();
    }

    public KeyControl getControl(){
        return this.control;
    }
    public Game getGame(){ return this.g;}

    public void updateControl(KeyControl kc){
        this.control = kc;
    }

}