package hu.bme.mario.client;

import hu.bme.mario.model.Game;
import hu.bme.mario.network.ClientInterface;

public class DisplayUpdateThread extends Thread{
    private GameDisplay display;
    private ClientInterface clientInt;

    public DisplayUpdateThread(GameDisplay d, ClientInterface ci){
        this.display = d;
        this.clientInt = ci;
    }

    public void run(){
        double fps = 60;
        long tps = (long)(1000/fps);
        Game g;
        while(true){
            try{
                long l = System.currentTimeMillis()+tps;
                g = this.clientInt.getLocalGame();
                this.display.displayGame(g);
                Thread.sleep(Math.max(0,l-System.currentTimeMillis()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
