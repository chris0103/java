package org.chris.study.rpc;

public class RpcConsumer {

    public static void main(String[] args) throws Exception {
        HelloService proxy = RpcProxyFactory.getService(HelloService.class);
        String result = proxy.sayHello("world");
        System.out.println(result);
    }
}

