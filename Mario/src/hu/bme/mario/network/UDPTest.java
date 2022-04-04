package hu.bme.mario.network;

import hu.bme.mario.network.ClientInterface;
import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.Block;
import hu.bme.mario.model.Game;
import hu.bme.mario.model.SmallPlayer;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPTest {
    ClientInterface client;

    public void setup() throws SocketException, UnknownHostException {
        new Server().start();
        client = new ClientInterface();
    }

    public void test() throws IOException {
        client.sendAndReceiveMessage("Action1");
        client.sendAndReceiveMessage("Action2");
        client.sendAndReceiveMessage("Action3");
    }

    public void tearDown() throws IOException {
        client.sendAndReceiveMessage("end");
        client.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Test des transmissions UDP");

        Block[][] map = new Block[20][10];
        map[5][7] = new BaseBlock();

        SmallPlayer sp = new SmallPlayer(0,1);

        Game g = new Game(map);
        g.addEntity(sp);


        UDPTest test = new UDPTest();

        test.setup();
        test.test();
        test.tearDown();


    }
}