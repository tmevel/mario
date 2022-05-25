package hu.bme.mario.network;

import hu.bme.mario.model.*;

public class Level1ModelThread extends ModelThread {
    private Server server;
    private static final double newStarDelay = 40;
    private double delay = 40;

    public Level1ModelThread(Game g, Server server){
        super(g);
        this.server = server;
    }

    private void newStar(){
        this.delay = newStarDelay;
        synchronized (game){
            Star s = new Star(Math.random()>0.5?15:50,4, Direction.RIGHT, game);
            game.addEntity(s);

            for(int i=1;i<60;i+=8){
                Goomba g = new Goomba(i,14, Direction.RIGHT, game);
                game.addEntity(g);
            }
        }

        this.server.notifyAllSessions();
    }

    public void run(){
        double fps = 100;
        long tps = (long)(1000/fps);

        while(true) {
            try{
                long l = System.currentTimeMillis()+tps;
                double dt = (double)tps/1000;
                synchronized (this.game){
                    this.game.update(dt);
                }
                this.delay -= dt;
                if(this.delay<=0){
                    this.newStar();
                    this.server.notifyAllSessions();
                }
                try {
                    Thread.sleep(Math.max(0,l-System.currentTimeMillis()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}
