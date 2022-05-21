package hu.bme.mario.network;

import hu.bme.mario.model.*;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Session extends Thread{
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private final int sessionID;
    private Server server;

    private KeyControl control;

    public Session(Socket s, Server serv) throws IOException {
        this.server = serv;
        this.oos = new ObjectOutputStream(s.getOutputStream());
        this.ois = new ObjectInputStream(s.getInputStream());
        this.control = new KeyControl();

        this.sessionID = this.server.getModel().getGame().getEntities().size();
        this.server.getModel().getGame().addEntity(new SmallPlayer(this.sessionID, 5, this.server.getModel().getGame()));
        this.server.notifyAllSessions();
    }

    public KeyControl getControl(){
        return this.control;
    }

    public int getSessionID(){
        return this.sessionID;
    }

    public void updateClient() throws IOException{
        this.oos.reset();
        this.oos.writeObject(this.getPlayerGame());
    }

    private Game getPlayerGame(){
        ArrayList<Entity> list = new ArrayList<>(this.server.getModel().getGame().getEntities());
        Entity player = list.get(this.sessionID);
        list.set(this.sessionID, list.get(0));
        list.set(0, player);
        return new Game(this.server.getModel().getGame().getMap(), list);
    }

    public void run(){
        try{
            while(true){
                Object o = this.ois.readObject();
                if(o!=null){
                    this.control = (KeyControl) o;

                    Player p = (Player)this.server.getModel().getGame().getEntities().get(this.sessionID);
                    if (this.control.goLeft() && this.control.goRight()) {
                        p.stop();
                    } else if (this.control.goLeft()) {
                        p.walkLeft();
                    } else if (this.control.goRight()) {
                        p.walkRight();
                    }else{
                        p.stop();
                    }
                    if(this.control.jump()){
                        p.jump();
                    }
                    this.server.notifyAllSessions();
                } else {
                    this.oos.reset();
                    this.oos.writeObject(this.getPlayerGame());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
