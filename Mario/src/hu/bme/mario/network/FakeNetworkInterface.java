package hu.bme.mario.network;

import hu.bme.mario.client.GameDisplay;
import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.Block;
import hu.bme.mario.model.Game;
import hu.bme.mario.model.SmallPlayer;

public class FakeNetworkInterface extends Thread{

    private GameDisplay display;

    public FakeNetworkInterface(GameDisplay display){
        this.display = display;
    }



    public void run(){
        Block[][] map = new Block[40][10];
        map[5][7] = new BaseBlock();

        map[12][5] = new BaseBlock();
        map[13][5] = new BaseBlock();
        map[14][5] = new BaseBlock();

        for(int x=0;x<map.length;x++){
            map[x][0] = new BaseBlock();
        }

        SmallPlayer sp = new SmallPlayer(0,1);

        Game g = new Game(map);
        g.addEntity(sp);

        double cx = 0;

        while(true){
            cx+=0.05;
            this.display.displayGame(g);
            this.display.forceCameraX(cx);

            try{
                Thread.sleep(20);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
