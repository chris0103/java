package org.chris.study.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DebugProxy implements InvocationHandler {
    
    private Object obj;

    private DebugProxy(Object obj) {
        this.obj = obj;
    }
    
    public static Object newProxyInstance(Object proxiedObj) {
        return Proxy.newProxyInstance(
                proxiedObj.getClass().getClassLoader(), 
                proxiedObj.getClass().getInterfaces(), 
                new DebugProxy(proxiedObj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try {
            System.out.println("before method " + method.getName());
            result = method.invoke(obj, args);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        } finally {
            System.out.println("after method " + method.getName());
        }
        return result;
    }
    
    

}
