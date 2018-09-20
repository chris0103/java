package brokenpipe;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * This client will NOT cause the broken pipe error.
 * 
 * @author czhu30
 */
public class Client2 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 3113));
            OutputStream os = socket.getOutputStream();
            os.write("hello".getBytes());
            socket.close();
            System.in.read(); // prevent main thread from exiting directly
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
