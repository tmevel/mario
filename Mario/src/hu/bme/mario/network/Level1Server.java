package hu.bme.mario.network;

import hu.bme.mario.model.*;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Level1Server extends Server{
    public Level1Server() throws IOException {
        super();
    }

    protected void initLevel(){
        Block[][] map = new Block[60][15];
        map[5][7] = new BaseBlock();
        map[12][5] = new BreakableBlock();
        map[13][5] = new QuestionBlock();
        map[14][5] = new BreakableBlock();
        map[13][1] = new BaseBlock();
        map[17][1] = new BaseBlock();
        for(int x=0;x<map.length;x++){
            map[x][0] = new BaseBlock();
        }
        map[29][0] = null;
        map[30][0] = null;
        map[28][1] = new BaseBlock();
        map[28][2] = new BaseBlock();
        map[28][3] = new BaseBlock();
        map[28][4] = new BaseBlock();
        map[31][1] = new BaseBlock();
        map[31][2] = new BaseBlock();
        map[31][3] = new BaseBlock();
        map[31][4] = new BaseBlock();
        map[27][1] = new BaseBlock();
        map[27][2] = new BaseBlock();
        map[27][3] = new BaseBlock();
        map[32][1] = new BaseBlock();
        map[32][2] = new BaseBlock();
        map[32][3] = new BaseBlock();
        map[26][1] = new BaseBlock();
        map[26][2] = new BaseBlock();
        map[33][1] = new BaseBlock();
        map[33][2] = new BaseBlock();
        map[25][1] = new BaseBlock();
        map[34][1] = new BaseBlock();

        map[40][4] = new BaseBlock();
        map[41][4] = new BaseBlock();
        map[42][4] = new BaseBlock();

        map[45][5] = new BreakableBlock();
        map[46][5] = new QuestionBlock();
        map[47][5] = new BreakableBlock();
        map[48][5] = new QuestionBlock();
        map[49][5] = new BreakableBlock();
        map[55][7] = new BreakableBlock();
        map[56][7] = new BreakableBlock();
        map[57][7] = new BreakableBlock();

        for(int y=0;y<map[0].length;y++){
            map[0][y] = new BaseBlock();
            map[map.length-1][y] = new BaseBlock();
        }
        Game game = new Game(map);
        Goomba g = new Goomba(15,4, Direction.RIGHT, game);
        game.addEntity(g);
        Star s = new Star(15,4, Direction.RIGHT, game);
        game.addEntity(s);

        super.model = new Level1ModelThread(game, this);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server Level 1");

        Server server = new Level1Server();
        server.start();
        JOptionPane.showMessageDialog(null,"Wait for all the others...");
        server.startModel();
    }
}
