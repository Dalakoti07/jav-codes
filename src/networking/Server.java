package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) {
        //Open a listening "ServerSocket" on port 1234.
        ServerSocket serverSocket;
        {
            try {
                serverSocket = new ServerSocket(1234);
                while (true) {
                    // Wait for a client connection.
                    // Once a client connected, we get a "Socket" object
                    // that can be used to send and receive messages to/from the newly
                    // connected client
                    Socket clientSocket = serverSocket.accept();
                    // Here we'll add the code to handle one specific client.
                    // we creating a separate thread to handle each client, which would work as long as people<<1000
                    new Thread(() -> {
                        // Get the socket's InputStream, to read bytes from the socket
                        InputStream in = null;
                        try {
                            in = clientSocket.getInputStream();
                            // wrap the InputStream in a reader so you can read a String instead of bytes
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                            // Read text from the socket and print line by line
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
