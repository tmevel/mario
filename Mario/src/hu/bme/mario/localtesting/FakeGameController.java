package hu.bme.mario.localtesting;

import hu.bme.mario.network.ClientInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FakeGameController implements KeyListener {

    FakeClientInterface networkInterface;

    public FakeGameController(FakeClientInterface ci){
        this.networkInterface = ci;
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
