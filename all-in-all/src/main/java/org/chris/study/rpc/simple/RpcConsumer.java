package org.chris.study.rpc.simple;

/**
 * An RPC consumer
 */
public class RpcConsumer {

    public static void main(String[] args) throws Exception {
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);

        for (int i = 0; i < 5; i ++) {
            String hello = service.hello("World" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
