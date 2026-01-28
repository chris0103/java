package org.chris.study.rpc.io;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcHandler implements InvocationHandler {

    private String host = "127.0.0.1";

    private int port = 1234;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //与服务提供方建立连接
        Socket socket = new Socket(host, port);
        try {
            //获取输出流，并写出方法名、参数名、参数值
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            try {
                output.writeUTF(method.getName());
                output.writeObject(method.getParameterTypes());
                output.writeObject(args);
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                try {
                    //获得返回结果
                    Object result = input.readObject();
                    if (result instanceof Throwable) {
                        throw (Throwable) result;
                    }
                    return result;
                } finally {
                    input.close();
                }
            } finally {
                output.close();
            }
        } finally {
            socket.close();
        }
    }
}

