package hu.bme.mario.network;

import hu.bme.mario.client.GameFrame;
import hu.bme.mario.model.*;

import java.net.*;
import java.io.*;
import java.util.ArrayList;


public abstract class Server extends Thread{
    protected ModelThread model;
    private ServerSocket ss;
    private ArrayList<Session> sessions;
    private final int PORT = 3000;

    public Server() throws IOException{
        this.initLevel();
        ss = new ServerSocket(PORT);
        this.sessions = new ArrayList<Session>();
    }

    protected abstract void initLevel();

    public ModelThread getModel(){
        return this.model;
    }

    public void notifyAllSessions(){
        for(Session s:this.sessions){
            try {
                s.updateClient();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void run() {
        this.model.start();
        while (true) {
            try {
                System.out.println("waiting for connection");
                Session s = new Session(ss.accept(), this);
                System.out.println("connection...");
                sessions.add(s);
                s.start();
                System.out.println("connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
