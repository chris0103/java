package org.chris.study.rpc.nio;

import com.typesafe.config.ConfigException;
import scala.concurrent.java8.FuturesConvertersImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class RpcNioMultClient {

    private static RpcNioMultClient rpcNioClient;

    private Selector selector;
    private SocketChannel channel;

    private String serverIp = "localhost";
    private int port = 8080;

    private RpcNioMultClient() {
        initClient();
    }

    public static RpcNioMultClient getInstance() {
        if (rpcNioClient == null) {
            synchronized (RpcNioMultClient.class) {
                if (rpcNioClient == null) {
                    rpcNioClient = new RpcNioMultClient();
                }
            }
        }
        return rpcNioClient;
    }

    public void initClient() {
        try {
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            selector = Selector.open();
            channel.connect(new InetSocketAddress(serverIp, port));

            if (channel.isConnectionPending()) {
                channel.finishConnect();
            }
            System.out.println("Client connected to server at " + serverIp + ":" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                // bind the channel to the selector for read events
                // only read events are expected from the server
                channel.register(selector, SelectionKey.OP_READ);

                // listen for read events
                selector.select();

                Iterator ite = selector.selectedKeys().iterator();
                while (ite.hasNext()) {
                    SelectionKey key = (SelectionKey) ite.next();
                    ite.remove();   // remove the key to avoid duplicate processing
                    if (key.isReadable()) {
                        readMsgFromServer();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMsg2Server(byte[] bytes) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length + 4);

            // write into the length and data
            buffer.putInt(bytes.length);
            buffer.put(bytes);

            buffer.flip();
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void readMsgFromServer() {
        ByteBuffer byteBuffer;
        try {
            // read the request id
            byteBuffer = ByteBuffer.allocate(8);
            int readIdCount = channel.read(byteBuffer);
            if (readIdCount < 0) {
                return;
            }
            byteBuffer.flip();
            Long requestId = byteBuffer.getLong();

            // read the data length
            byteBuffer = ByteBuffer.allocate(4);
            int readHeadCount = channel.read(byteBuffer);
            if (readHeadCount < 0) {
                return;
            }
            byteBuffer.flip();
            int length = byteBuffer.getInt();

            // read the data
            byteBuffer = ByteBuffer.allocate(length);
            int readBodyCount = channel.read(byteBuffer);
            if (readBodyCount < 0) {
                return;
            }
            byte[] dataBytes = byteBuffer.array();
            RpcContainer.addResponse(requestId, dataBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
