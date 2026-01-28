package org.chris.study.rpc.example.rpc;

import org.chris.study.rpc.example.proxy.RpcProxyFactory;
import org.chris.study.rpc.example.service.HelloService;

/**
 * @author: lifs
 * @create: 2018-06-28 23:34
 **/
public class RpcNioConsumer {
    public static void main(String[] args) {
        // singleRpcNio();
        multipartRpcNio();
    }

    /**
     * 多线程IO调用示例
     * 
     * @param
     * @return void
     * @createTime：2018/7/1
     * @author: shakeli
     */
    public static void multipartRpcNio() {
        HelloService proxy = RpcProxyFactory.getMultService(HelloService.class);
        for (int i = 0; i < 100; i++) {
            final int j = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    String result = proxy.sayHello("world!");
                }
            };
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}
