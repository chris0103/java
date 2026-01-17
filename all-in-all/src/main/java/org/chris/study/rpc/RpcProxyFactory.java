package org.chris.study.rpc;

import java.lang.reflect.Proxy;

public class RpcProxyFactory {

    public static <T> T getService(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new RpcHandler());
    }
}

