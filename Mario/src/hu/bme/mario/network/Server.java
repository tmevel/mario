package hu.bme.mario.network;

import hu.bme.mario.client.GameDisplay;
import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.Block;
import hu.bme.mario.model.Game;
import hu.bme.mario.model.SmallPlayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class Server  extends Thread{
    private GameDisplay display;

    private DatagramSocket socket;
    private byte[] buf = new byte[256];
    private byte[] packetdata;

    public Server(GameDisplay display){
        this.display = display;
    }
    public Server() throws SocketException {
        socket = new DatagramSocket(1234);
    }

    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    public void run(){
        Block[][] map = new Block[40][10];
        map[5][7] = new BaseBlock();

        map[12][5] = new BaseBlock();
        map[13][5] = new BaseBlock();
        map[14][5] = new BaseBlock();

        for(int x=0;x<map.length;x++){
            map[x][0] = new BaseBlock();
        }

        SmallPlayer sp = new SmallPlayer(0,1);

        Game g = new Game(map);
        g.addEntity(sp);

        double cx = 0;

        while(true){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client sent " + data(buf));
            if (data(buf).toString().equals("end"))
            {
                System.out.println("END OF TRANSMISSION");
                break;
            }


            buf = new byte[256];//Créer un packet pour l'envoyer au client (On obtient l'adresse et le port grace au premier packet envoyé
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packetdata = "CURRENTMAP".getBytes(); //A CHANGER
            packet = new DatagramPacket(packetdata, packetdata.length, address, port);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
