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

    public Session(Socket s, Server serv) throws IOException, ClassNotFoundException {
        this.server = serv;
        this.oos = new ObjectOutputStream(s.getOutputStream());
        this.ois = new ObjectInputStream(s.getInputStream());
        this.control = new KeyControl();

        Object o = this.ois.readObject();
        if(o==null){
            this.sessionID = this.server.getModel().getGame().getPlayers().size();
            this.server.getModel().getGame().addPlayer(new SmallPlayer(this.sessionID+1, 5, this.server.getModel().getGame()));
        }else{
            this.sessionID = ((Integer)o).intValue(); //reconnection
        }
        this.oos.writeObject(new Integer(this.sessionID));
        this.server.notifyAllSessions();
    }



    public KeyControl getControl(){
        return this.control;
    }

    public int getSessionID(){
        return this.sessionID;
    }

    public synchronized void updateClient() throws IOException{
        this.oos.reset();
        this.oos.writeObject(this.getPlayerGame());
    }

    private ClientGame getPlayerGame(){
        synchronized (this.server.getModel().getGame()){
            return new ClientGame(this.server.getModel().getGame(), this.sessionID);
        }
    }

    public void run() {
        try {
            while (true) {
                Object o = this.ois.readObject();
                if (o != null) {
                    this.control = (KeyControl) o;

                    Player p = this.getPlayerGame().getPlayer();
                    if (this.control.goLeft() && this.control.goRight()) {
                        p.stop();
                    } else if (this.control.goLeft()) {
                        p.walkLeft();
                    } else if (this.control.goRight()) {
                        p.walkRight();
                    } else {
                        p.stop();
                    }
                    if (this.control.jump()) {
                        p.jump();
                    }
                    if (this.control.special()) {
                        p.special();
                    }
                    this.server.notifyAllSessions();
                } else {
                    this.updateClient();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try{
                this.oos.close();
                this.ois.close();
                System.out.println("connection error, closed");
            }catch(Exception e2){
                e2.printStackTrace();
            }
            this.server.removeSession(this);
        }
    }
}
