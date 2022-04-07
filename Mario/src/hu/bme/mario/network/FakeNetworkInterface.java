package hu.bme.mario.network;

import hu.bme.mario.client.GameDisplay;
import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.Block;
import hu.bme.mario.model.Game;
import hu.bme.mario.model.SmallPlayer;

import java.awt.event.KeyEvent;

public class FakeNetworkInterface extends Thread{

    private GameDisplay display;
    private boolean goLeft = false;
    private boolean goRight = false;

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

        SmallPlayer sp = new SmallPlayer(15,1);

        Game g = new Game(map);
        g.addEntity(sp);

        double cx = 0;

        while(true){
            cx+=0.05;
            this.display.displayGame(g);
            //this.display.forceCameraX(cx);
            if(this.goLeft && this.goRight) {

            }else if(this.goLeft){
                g.getEntities().get(0).decX();
            }else if(this.goRight) {
                g.getEntities().get(0).incX();
            }
            //g.getEntities().get(0).decX();

            try{
                Thread.sleep(20);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void sendKeyEvent(KeyEvent e){
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

    }
}
