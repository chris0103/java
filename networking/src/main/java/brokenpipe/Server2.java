package brokenpipe;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Receiving message from the client and then send back messages after 10 seconds, during which the client shall close
 * using FIN. This server will terminate normally.
 * 
 * @author czhu30
 */
public class Server2 {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3113);
            Socket socket = server.accept();
            InputStream is = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len = is.read(buf);
            System.out.println("Server received: " + new String(buf, 0, len));

            // give time for the client to terminate (by entering anything in the console)
            Thread.sleep(10000);

            socket.getOutputStream().write("hello".getBytes());
            socket.getOutputStream().write("hello2".getBytes());
            System.out.println("Server done sending.");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
