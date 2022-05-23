package hu.bme.mario.network;

import hu.bme.mario.client.GameDisplay;
import hu.bme.mario.model.ClientGame;
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
    private final int sessionID;
    private final String ip;

    public ClientInterface(String ip) throws IOException, ClassNotFoundException{
        this.ip = ip;
        Socket s = new Socket(ip, PORT);
        this.oos = new ObjectOutputStream(s.getOutputStream());
        this.oos.writeObject(null);
        this.ois = new ObjectInputStream(s.getInputStream());
        this.control = new KeyControl();
        this.sessionID = ((Integer)this.ois.readObject()).intValue();
        this.forceRetrieveGame();
        this.model.start();
    }

    private void reconnect() throws IOException, ClassNotFoundException{
        Socket s = new Socket(ip, PORT);
        this.oos.close();
        this.ois.close();
        this.oos = new ObjectOutputStream(s.getOutputStream());
        this.ois = new ObjectInputStream(s.getInputStream());
        this.oos.writeObject(new Integer(this.sessionID));
        System.out.println("reconnected at session: "+((Integer)this.ois.readObject()).intValue());
    }

    public ClientGame getLocalGame(){
        return (ClientGame) this.model.getGame();
    }

    private void forceRetrieveGame() throws IOException{
        this.oos.writeObject(null);
        try {
            ClientGame g = (ClientGame) this.ois.readObject();
            this.model = new ModelThread(g);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void run(){
        ClientGame g;
        while(true){
            try{
                g = (ClientGame) this.ois.readObject();
                synchronized (this.model.getGame()){
                    this.model.setGame(g);
                }
            }catch(Exception e){
                e.printStackTrace();
                try {
                    this.reconnect();
                }catch (Exception e2){}
            }
        }
    }

    public synchronized void sendKeyEvent(KeyEvent ke){
        try{
            if(this.control.updateAndCheckChanged(ke)) {
                this.oos.reset();
                this.oos.writeObject(this.control);
            }
            Thread.sleep(10);
        }catch(Exception e){
            e.printStackTrace();
            try {
                this.reconnect();
            }catch (Exception e2){
            }
        }
    }
}