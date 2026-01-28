package org.chris.study.rpc.nio;

public class RpcNioConsumer {

    public static void main(String[] args) {
        multipartRpcNio();
    }

    public static void multipartRpcNio() {
        HelloService helloService = RpcProxyFactory.getMultService(HelloService.class);
        for (int i = 0; i < 5; i++) {
            final int j = i;
            Runnable r = () -> {
                String result = helloService.sayHello("world!");
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
