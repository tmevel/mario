package hu.bme.mario.network;

import hu.bme.mario.client.GameFrame;
import hu.bme.mario.model.*;

import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class Server extends Thread{
    private Game game;
    private ServerSocket ss;
    private ArrayList<Session> sessions;

    public Server(int port) throws IOException{
        //Game Parameters
        Block[][] map = new Block[40][10];
        map[5][7] = new BaseBlock();
        map[12][5] = new BaseBlock();
        map[13][5] = new QuestionBlock();
        map[14][5] = new BaseBlock();
        for(int x=0;x<map.length;x++){
            map[x][0] = new BaseBlock();
        }
        game = new Game(map);
        SmallPlayer sp = new SmallPlayer(15,1, game);
        game.addEntity(sp);
        ss = new ServerSocket(port);
        this.sessions = new ArrayList<Session>();
    }



    public void run() {
        while (true) {
            try {
                System.out.println("waiting for connection");
                Session s = new Session(this.game, ss.accept());
                System.out.println("connection...");
                sessions.add(s);
                s.start();
                System.out.println("connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void runModel(){


        while(true) {

            if(this.sessions.size()==0) {
            }else if (this.sessions.get(0).getControl().goLeft() && this.sessions.get(0).getControl().goRight()) {
            } else if (this.sessions.get(0).getControl().goLeft()) {
                //this.game.getEntities().get(0).decX();
            } else if (this.sessions.get(0).getControl().goRight()) {
                //this.game.getEntities().get(0).incX();
            }

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void close() throws IOException {
        //TODO close all sessions
    }

    //I used two main functions for the server and the client
    public static void main(String[] args) throws Exception {
        System.out.println("Server");
        Server server = new Server(12345);
        server.start();
        server.runModel();
        Thread.sleep(600000);
        server.close();
    }
}
