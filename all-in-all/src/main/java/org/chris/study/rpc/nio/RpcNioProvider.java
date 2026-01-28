package org.chris.study.rpc.nio;

import java.io.IOException;

public class RpcNioProvider {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        BeanContainer.addBean(HelloService.class, helloService);
        startMultRpcNioServer();
    }

    public static void startMultRpcNioServer() {
        Runnable r = () -> {
          try {
              RpcNioMultServer.start();
          } catch (IOException e) {
              e.printStackTrace();
          }
        };
        Thread t = new Thread(r);
        t.start();
    }
}
