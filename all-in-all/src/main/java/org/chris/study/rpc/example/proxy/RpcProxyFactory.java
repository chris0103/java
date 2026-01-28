package org.chris.study.rpc.example.proxy;

import org.chris.study.rpc.example.niomultipart.RpcNIoMultHandler;

import java.lang.reflect.Proxy;

/**
 * @author: lifs
 * @create: 2018-06-28 23:35
 **/
public class RpcProxyFactory {

    /**
     * 多线程环境代理对象
     * 
     * @param interfaceClass
     * @return T
     * @createTime：2018/7/1
     * @author: shakeli
     */
    public static <T> T getMultService(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
                new RpcNIoMultHandler());
    }
}
