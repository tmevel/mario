package hu.bme.mario.localtesting;

import hu.bme.mario.model.*;
import hu.bme.mario.network.Session;


import java.util.ArrayList;

public class FakeServer extends Thread{
    private Game game;
    private ArrayList<FakeSession> sessions;

    public FakeServer(){
        //Game Parameters
        Block[][] map = new Block[40][10];
        map[5][7] = new BaseBlock();
        map[12][5] = new BaseBlock();
        map[13][5] = new QuestionBlock();
        map[14][5] = new BaseBlock();
        map[13][1] = new BaseBlock();
        map[18][1] = new BaseBlock();
        for(int x=0;x<map.length;x++){
            map[x][0] = new BaseBlock();
        }
        game = new Game(map);
        SmallPlayer sp = new SmallPlayer(15,4, game);

        game.addEntity(sp);
        Goomba g = new Goomba(15,4, Direction.RIGHT, game);
        game.addEntity(g);
        this.sessions = new ArrayList<FakeSession>();
    }


    public FakeSession createSession(){
        FakeSession fs = new FakeSession(this.game);
        this.sessions.add(fs);
        return fs;
    }

    public void run() {
        this.runModel();
    }


    public void runModel(){
        double fps = 100;
        long tps = (long)(1000/fps);

        while(true) {
            long l = System.currentTimeMillis()+tps;
            Player p = (Player)this.game.getEntities().get(0);
            if(this.sessions.size()>0) {
                if (this.sessions.get(0).getControl().goLeft() && this.sessions.get(0).getControl().goRight()) {
                    p.stop();
                } else if (this.sessions.get(0).getControl().goLeft()) {
                    p.walkLeft();
                } else if (this.sessions.get(0).getControl().goRight()) {
                    p.walkRight();
                }else{
                    p.stop();
                }
                if(this.sessions.get(0).getControl().jump()){
                    ((Player)this.game.getEntities().get(0)).jump();
                }
            }

            this.game.update((double)tps/1000);

            try {
                Thread.sleep(Math.max(0,l-System.currentTimeMillis()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
