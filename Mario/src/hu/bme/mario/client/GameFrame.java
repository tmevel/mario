package hu.bme.mario.client;

import hu.bme.mario.network.ClientInterface;
import hu.bme.mario.network.FakeNetworkInterface;

import javax.swing.*;
import java.io.*;

public class GameFrame extends JFrame {
    private GameDisplay display;

    public GameFrame(){
        this.setSize(1600,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.display = new GameDisplay();
        this.add(this.display);
        this.setVisible(true);
        ClientInterface ci = new ClientInterface(this.display);
        this.addKeyListener(new GameController(ci));
    }
    public static void main(String[] args){
        new GameFrame();
    }
}
