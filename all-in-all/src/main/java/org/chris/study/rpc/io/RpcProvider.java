package org.chris.study.rpc.io;

public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcExport.export(service);
    }
}
