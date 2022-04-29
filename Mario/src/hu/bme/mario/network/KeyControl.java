package hu.bme.mario.network;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class KeyControl implements Serializable {
    private boolean right = false;
    private boolean left = false;

    public boolean updateAndCheckChanged(KeyEvent ke){
        boolean changed = false;
        if(ke.getID() == KeyEvent.KEY_PRESSED){
            if(ke.getKeyCode()==KeyEvent.VK_RIGHT){
                if(!right){
                    changed = true;
                }
                right = true;
            }else if(ke.getKeyCode()==KeyEvent.VK_LEFT){
                if(!left){
                    changed = true;
                }
                left = true;
            }
        }else if(ke.getID() == KeyEvent.KEY_RELEASED){
            if(ke.getKeyCode()==KeyEvent.VK_RIGHT){
                if(right){
                    changed = true;
                }
                right = false;
            }else if(ke.getKeyCode()==KeyEvent.VK_LEFT){
                if(left){
                    changed = true;
                }
                left = false;
            }
        }

        return changed;
    }

    public boolean goRight(){
        return this.right;
    }
    public boolean goLeft(){
        return this.left;
    }
}
