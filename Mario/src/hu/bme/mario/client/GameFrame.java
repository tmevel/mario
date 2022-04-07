package hu.bme.mario.client;

import hu.bme.mario.network.FakeNetworkInterface;

import javax.swing.*;

public class GameFrame extends JFrame {
    private GameDisplay display;

    public GameFrame(){
        this.setSize(1600,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.display = new GameDisplay();
        this.add(this.display);
        this.setVisible(true);
        FakeNetworkInterface fni = new FakeNetworkInterface(this.display);
        fni.start();
        this.addKeyListener(new GameController(fni));
    }
    public static void main(String[] args){
        new GameFrame();
    }
}
