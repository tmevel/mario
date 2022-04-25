package hu.bme.mario.network;

import java.io.Serializable;

public class KeyControl implements Serializable {
    private int id;
    private int code;
    public KeyControl(int id, int code){
        this.id = id;
        this.code = code;
    }
    public int getID(){
        return this.id;
    }
    public int getKeyCode(){
        return this.code;
    }

}
