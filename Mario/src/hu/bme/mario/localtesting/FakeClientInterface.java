package hu.bme.mario.localtesting;

import hu.bme.mario.client.GameDisplay;
import hu.bme.mario.model.Game;
import hu.bme.mario.network.KeyControl;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FakeClientInterface extends Thread{
    private GameDisplay display;
    private KeyControl control;
    private FakeSession fsession;

    public FakeClientInterface(GameDisplay display){
        this.display = display;
        this.control = new KeyControl();
        FakeServer fs = new FakeServer();
        fs.start();
        this.fsession = fs.createSession();
    }

    public void run(){
        double fps = 100;
        long tps = (long)(1000/fps);
        Game g;
        while(true){
            try{
                long l = System.currentTimeMillis()+tps;
                g = this.fsession.getGame();
                this.display.displayGame(g);
                Thread.sleep(Math.max(0,l-System.currentTimeMillis()));
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
                this.fsession.updateControl(this.control);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}