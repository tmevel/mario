package hu.bme.mario.network;

import hu.bme.mario.model.Game;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;

public class Session extends Thread{
    private Game g;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private KeyControl control;

    public Session(Game g, Socket s) throws IOException {
        this.g = g;
        this.oos = new ObjectOutputStream(s.getOutputStream());
        this.ois = new ObjectInputStream(s.getInputStream());
        this.control = new KeyControl();
    }

    public KeyControl getControl(){
        return this.control;
    }

    public void run(){
        try{
            while(true){
                Object o = this.ois.readObject();
                if(o!=null){
                    System.out.println("rcved");
                    this.control = (KeyControl) o;
                }else {
                    this.oos.reset();
                    this.oos.writeObject(this.g);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
