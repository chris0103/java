package org.chris.study.rpc.simple;

/**
 * An RPC provider to export the hello service.
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
