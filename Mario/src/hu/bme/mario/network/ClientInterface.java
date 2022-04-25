package hu.bme.mario.network;

import hu.bme.mario.client.GameDisplay;
import hu.bme.mario.client.GameFrame;
import hu.bme.mario.model.Game;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

public class ClientInterface extends Thread{
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private GameDisplay display;

    public ClientInterface(String ip, int port, GameDisplay display) throws IOException{
        Socket s = new Socket(ip, port);
        this.oos = new ObjectOutputStream(s.getOutputStream());
        this.ois = new ObjectInputStream(s.getInputStream());
        this.display = display;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(50);
                this.oos.writeObject(null);
                Game g = (Game) this.ois.readObject();
                this.display.displayGame(g);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    public void close() throws IOException{
        //TODO
    }

    public void sendKeyEvent(KeyEvent ke){
        try{
            this.oos.writeObject(new KeyControl(ke.getID(), ke.getKeyCode()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}