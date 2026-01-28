package org.chris.study.rpc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class RpcNioMultServer {

    private Selector selector;

    public static void start() throws IOException {
        RpcNioMultServer server = new RpcNioMultServer();
        server.initServer(8080);
        server.listen();
    }

    /**
     * Initialize the server.
     *
     * @param port
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        // non-blocking mode
        serverChannel.configureBlocking(false);

        // bind the ServerChannel socket to the port
        serverChannel.socket().bind(new InetSocketAddress(port));

        // get the selector and bind it to the server channel on the OP_ACCEPT event
        this.selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() {
        System.out.println("The server is listening on port ");

        while (true) {
            try {
                // blocked until the registered event occurs
                selector.select();

                Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
                while (ite.hasNext()) {
                    SelectionKey key = ite.next();
                    ite.remove();   // remove the key to avoid duplicate processing

                    // event processing
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        byte[] bytes = readMsgFromClient(channel);
                        if (bytes != null && bytes.length > 0) {
                            RpcNioMultServerTask task = new RpcNioMultServerTask(bytes, channel);
                            ThreadPoolUtil.addTask(task);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] readMsgFromClient(SocketChannel channel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        try {
            int headCount = channel.read(byteBuffer);
            if (headCount <= 0) {
                return null;
            }
            byteBuffer.flip();
            int length = byteBuffer.getInt();
            byteBuffer = ByteBuffer.allocate(length);
            int bodyCount = channel.read(byteBuffer);
            if (bodyCount <= 0) {
                return null;
            }
            return byteBuffer.array();
        } catch (IOException e) {
            System.out.println("Read message from client error!");
            e.printStackTrace();
            return null;
        }
    }
}

