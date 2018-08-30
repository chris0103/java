package brokenpipe;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * This client will cause the broken pipe error.
 * 
 * @author czhu30
 */
public class Client1 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 3113));

            /*
             * when closing the socket:
             * with this setting, send RST to server and close immediately regardless of unsent data;
             * without this setting, wait for data to finish being sent, and send FIN to the server
             */
            socket.setSoLinger(true, 0);

            OutputStream os = socket.getOutputStream();
            os.write("hello".getBytes());
            socket.close();
            System.in.read(); // prevent main thread from exiting directly
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
