package hu.bme.mario.localtesting;

import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.Block;
import hu.bme.mario.model.Game;
import hu.bme.mario.model.SmallPlayer;
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
        map[13][5] = new BaseBlock();
        map[14][5] = new BaseBlock();
        for(int x=0;x<map.length;x++){
            map[x][0] = new BaseBlock();
        }
        SmallPlayer sp = new SmallPlayer(15,1);
        game = new Game(map);
        game.addEntity(sp);
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
        while(true) {

            if(this.sessions.size()==0) {
            }else if (this.sessions.get(0).getControl().goLeft() && this.sessions.get(0).getControl().goRight()) {
            } else if (this.sessions.get(0).getControl().goLeft()) {
                this.game.getEntities().get(0).decX();
            } else if (this.sessions.get(0).getControl().goRight()) {
                this.game.getEntities().get(0).incX();
            }

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
