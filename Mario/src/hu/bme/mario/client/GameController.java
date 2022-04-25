package hu.bme.mario.client;
import hu.bme.mario.network.ClientInterface;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {

    ClientInterface networkInterface;

    public GameController(ClientInterface ci){
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
