package hu.bme.mario.network;

import java.io.IOException;
import java.net.*;

public class ClientInterface{
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] input;
    private byte[] received;

    public ClientInterface() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public void sendAndReceiveMessage(String str) throws IOException {
        input = str.getBytes();
        DatagramPacket packet = new DatagramPacket(input, input.length, address, 1234);
        InetAddress ip = InetAddress.getLocalHost();
        socket.send(packet);
        input = new byte[256];

        received = new byte[256];
        packet = new DatagramPacket(received, received.length);
        socket.receive(packet);
        String answer = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Server sent " + answer);
        received = new byte[256];
    }

    public void close(){
        socket.close();
    }


}
