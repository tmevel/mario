package hu.bme.mario.network;

import hu.bme.mario.client.GameFrame;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

public class ClientInterface{
    private DatagramSocket socket;
    private static InetAddress address;
    private byte[] input;
    private static byte[] received;

    static private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException{
        Socket clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    //Used by the game controller to send the inputs to the server
    public void sendKeyEvent(KeyEvent e){
        out.println(e);
    }

    public void close() throws IOException{
        in.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        //Each client will have a Frame?
        new GameFrame();

        //Initializing a client
        System.out.println("Client configuration");
        ClientInterface client = new ClientInterface();
        client.startConnection("127.0.0.1", 1234);
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getLocalHost();
        String str = "begin";
        DatagramPacket initpacket = new DatagramPacket(str.getBytes(), str.length(), address, 1234);
        socket.send(initpacket);
        System.out.println("Client ready");

        //Receiving the game file
        while(true){
            received = new byte[256];
            DatagramPacket packet = new DatagramPacket(received, received.length);
            socket.receive(packet);
            String answer = new String(packet.getData(), 0, packet.getLength());
            System.out.println(answer);
            if(answer=="end"){
                break;
            }
        }
        client.close();
    }
}