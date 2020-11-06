package networking;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {
        // 127.0.0.1 is the address of the server (this is the localhost address; i.e.
        // the address of our own machine)
        // 1234 is the port that the server will be listening on
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 1234);
            // Write a string into the socket, and flush the buffer
            OutputStream outStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(outStream, StandardCharsets.UTF_8));
            writer.println("Hello world!");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
