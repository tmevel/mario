package hu.bme.mario.network;

import hu.bme.mario.model.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Level1Server extends Server{
    public Level1Server() throws IOException {
        super();
    }

    protected void initLevel(){
        Block[][] map = new Block[40][10];
        map[5][7] = new BaseBlock();
        map[12][5] = new BaseBlock();
        map[13][5] = new QuestionBlock();
        map[14][5] = new BaseBlock();
        for(int x=0;x<map.length;x++){
            map[x][0] = new BaseBlock();
        }
        Game game = new Game(map);
        Goomba g = new Goomba(15,4, Direction.RIGHT, game);
        game.addEntity(g);

        super.model = new ModelThread(game);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server Level 1");

        Server server = new Level1Server();
        server.start();
    }
}
