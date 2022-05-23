package hu.bme.mario.client;

import hu.bme.mario.network.ClientInterface;

import javax.swing.*;
import java.io.*;

public class GameFrame extends JFrame {
    private GameDisplay display;
    private DisplayUpdateThread displayUpdateThread;

    public GameFrame() throws IOException, ClassNotFoundException{
        this.setSize(1600,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.display = new GameDisplay();
        this.add(this.display);
        this.setVisible(true);
        ClientInterface ci = new ClientInterface("127.0.0.1");
        ci.start();
        this.addKeyListener(new GameController(ci));
        this.displayUpdateThread = new DisplayUpdateThread(display, ci);
        this.displayUpdateThread.start();

    }
    public static void main(String[] args){
        try {
            new GameFrame();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
