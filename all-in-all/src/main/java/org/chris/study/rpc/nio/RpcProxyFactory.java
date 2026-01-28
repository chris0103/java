package org.chris.study.rpc.nio;

import java.lang.reflect.Proxy;

public class RpcProxyFactory {

    public static <T> T getMultService(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] {interfaceClass}, new RpcNIoMultHandler());
    }
}
