package hu.bme.mario.client;

import hu.bme.mario.network.ClientInterface;
import hu.bme.mario.network.FakeNetworkInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {

    FakeNetworkInterface networkInterface;
    ClientInterface clientInterface;

    public GameController(ClientInterface ci){
        this.clientInterface = ci;
    }


    public void keyPressed (KeyEvent e) {
        this.clientInterface.sendKeyEvent(e);
    }
    public void keyReleased (KeyEvent e) {
        this.clientInterface.sendKeyEvent(e);
    }
    public void keyTyped (KeyEvent e) {
    }
}
