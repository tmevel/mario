package hu.bme.mario.network;

import hu.bme.mario.model.Game;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;

public class Session extends Thread{
    private Game g;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private boolean goLeft = false;
    private boolean goRight = false;

    public Session(Game g, Socket s) throws IOException {
        this.g = g;
        this.oos = new ObjectOutputStream(s.getOutputStream());
        this.ois = new ObjectInputStream(s.getInputStream());
    }

    public boolean getGoLeft(){
        return goLeft;
    }

    public  boolean getGoRight(){
        return goRight;
    }

    public void run(){
        try{
            while(true){
                Object o = this.ois.readObject();
                if(o!=null){
                    KeyControl e = (KeyControl) o;
                    if(e.getID() == KeyEvent.KEY_PRESSED){
                        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                            this.goRight = true;
                        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
                            this.goLeft = true;
                        }
                    }else if(e.getID() == KeyEvent.KEY_RELEASED){
                        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                            this.goRight = false;
                        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
                            this.goLeft = false;
                        }
                    }
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
