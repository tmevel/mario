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
    private KeyControl control;

    public ClientInterface(String ip, int port, GameDisplay display) throws IOException{
        Socket s = new Socket(ip, port);
        this.oos = new ObjectOutputStream(s.getOutputStream());
        this.ois = new ObjectInputStream(s.getInputStream());
        this.display = display;
        this.control = new KeyControl();
    }

    public void run(){
        Game g;
        while(true){
            try{
                long l = System.currentTimeMillis();
                Thread.sleep(20);
                this.oos.writeObject(null);
                g = (Game) this.ois.readObject();
                this.display.displayGame(g);
                System.out.println(System.currentTimeMillis()-l);
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
            if(this.control.updateAndCheckChanged(ke)) {
                this.oos.reset();
                this.oos.writeObject(this.control);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}