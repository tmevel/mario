import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.Block;
import hu.bme.mario.model.Game;
import hu.bme.mario.model.SmallPlayer;

public class Main {
    public static void main(String[] args){
        System.out.println("a");

        Block[][] map = new Block[20][10];
        map[5][7] = new BaseBlock();

        SmallPlayer sp = new SmallPlayer(0,1);

        Game g = new Game(map);
        g.addEntity(sp);

    }
}
