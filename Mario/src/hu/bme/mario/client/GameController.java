package hu.bme.mario.client;

import hu.bme.mario.network.FakeNetworkInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {

    FakeNetworkInterface networkInterface;

    public GameController(FakeNetworkInterface ni){
        this.networkInterface = ni;
    }


    public void keyPressed (KeyEvent e) {
        this.networkInterface.sendKeyEvent(e);
    }
    public void keyReleased (KeyEvent e) {
        this.networkInterface.sendKeyEvent(e);
    }
    public void keyTyped (KeyEvent e) {
    }
}
