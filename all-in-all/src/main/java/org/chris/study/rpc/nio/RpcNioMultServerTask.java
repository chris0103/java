package org.chris.study.rpc.nio;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RpcNioMultServerTask implements Runnable {

    private byte[] bytes;

    private SocketChannel channel;

    public RpcNioMultServerTask(byte[] bytes, SocketChannel channel) {
        this.bytes = bytes;
        this.channel = channel;
    }

    @Override
    public void run() {
        if (bytes != null && bytes.length > 0 && channel != null) {
            RequestMultObject requestMultiObject =
                    (RequestMultObject) SerializeUtil.unSerialize(bytes);
            requestHandle(requestMultiObject, channel);
        }
    }

    public void requestHandle(RequestMultObject requestObject, SocketChannel channel) {
        Long requestId = requestObject.getRequestId();
        Object obj = BeanContainer.getBean(requestObject.getClazz());
        String methodName = requestObject.getMethodName();
        Class<?>[] paramTypes = requestObject.getParamTypes();
        Object[] args = requestObject.getArgs();

        try {
            Method method = obj.getClass().getMethod(methodName, paramTypes);
            String result = (String) method.invoke(obj, args);
            byte[] bytes = SerializeUtil.serialize(result);
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length + 12);

            buffer.putLong(requestId);
            buffer.putInt(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
    }


    public SocketChannel getChannel() {
        return channel;
    }

    public void setChannel(SocketChannel channel) {
        this.channel = channel;
    }

}
