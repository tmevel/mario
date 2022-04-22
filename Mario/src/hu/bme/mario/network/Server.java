package hu.bme.mario.network;

import hu.bme.mario.client.GameFrame;
import hu.bme.mario.model.BaseBlock;
import hu.bme.mario.model.Block;
import hu.bme.mario.model.Game;
import hu.bme.mario.model.SmallPlayer;

import java.net.*;
import java.io.*;


public class Server {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private byte[] buf = new byte[256];
    private boolean goLeft = false;
    private boolean goRight = false;


    public void start(int port) throws IOException {
        //Starting the server and connecting to a client
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listening on port " + port);
        clientSocket = serverSocket.accept();
        System.out.println("Connected to " + clientSocket.getRemoteSocketAddress());
        System.out.println("Server ready");

        //Open a stream of communication between the two
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        DatagramSocket socket = new DatagramSocket(1234);
        String inputLine;

        //The first packet is used to get an UDP connection
        DatagramPacket initpacket = new DatagramPacket(buf, buf.length);
        socket.receive(initpacket);

        //Game Parameters
        Block[][] map = new Block[40][10];
        map[5][7] = new BaseBlock();
        map[12][5] = new BaseBlock();
        map[13][5] = new BaseBlock();
        map[14][5] = new BaseBlock();
        for(int x=0;x<map.length;x++){
            map[x][0] = new BaseBlock();
        }
        SmallPlayer sp = new SmallPlayer(15,1);
        Game g = new Game(map);
        g.addEntity(sp);
        double cx = 0;

        //Main loop checking for inputs on the stream
        while ((inputLine = in.readLine()) != null) {
            if ("end".equals(inputLine)) {
                out.println("End");
                break;
            }

            //Change the game file based on the inputs
            cx += 0.05;
            if (this.goLeft && this.goRight) {
            } else if (this.goLeft) {
                g.getEntities().get(0).decX();
            } else if (this.goRight) {
                g.getEntities().get(0).incX();
            }

            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Sends the game file after a key has been pressed
            InetAddress address = initpacket.getAddress();
            port = initpacket.getPort();
            byte[] packetdata = "Serialized_Game".getBytes();
            DatagramPacket packet = new DatagramPacket(packetdata, packetdata.length, address, port);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    //I used two main functions for the server and the client
    public static void main(String[] args) throws IOException {
        System.out.println("Server configuration");
        Server server=new Server();
        server.start(1234);
        server.stop();
    }
}
