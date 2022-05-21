package hu.bme.mario.network;

import hu.bme.mario.client.GameDisplay;
import hu.bme.mario.model.Game;
import hu.bme.mario.model.ModelThread;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

public class ClientInterface extends Thread{
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private KeyControl control;
    private ModelThread model;

    private final int PORT = 3000;

    public ClientInterface(String ip) throws IOException{
        Socket s = new Socket(ip, PORT);
        this.oos = new ObjectOutputStream(s.getOutputStream());
        this.ois = new ObjectInputStream(s.getInputStream());
        this.control = new KeyControl();
        this.forceRetrieveGame();
        this.model.start();
    }

    public Game getLocalGame(){
        return this.model.getGame();
    }

    private void forceRetrieveGame() throws IOException{
        this.oos.writeObject(null);
        try {
            Game g = (Game) this.ois.readObject();
            this.model = new ModelThread(g);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void run(){
        Game g;
        while(true){
            try{
                g = (Game) this.ois.readObject();
                this.model.setGame(g);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void sendKeyEvent(KeyEvent ke){
        try{
            if(this.control.updateAndCheckChanged(ke)) {
                this.oos.reset();
                this.oos.writeObject(this.control);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}