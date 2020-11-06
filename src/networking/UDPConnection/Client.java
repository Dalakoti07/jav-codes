package networking.UDPConnection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) {
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            String ex = "Hello, World its working!";
            byte[] buf = ex.getBytes();
            DatagramPacket packet = new DatagramPacket(buf,buf.length, address, 4160);
            clientSocket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
