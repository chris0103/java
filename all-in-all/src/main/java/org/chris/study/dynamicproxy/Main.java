package org.chris.study.dynamicproxy;

import java.lang.reflect.Proxy;

public class Main {
    
    public static void debugProxyExample() throws BazException {
        Foo foo = (Foo) DebugProxy.newProxyInstance(new FooImpl());
        foo.bar(null);
    }
    
    public static void delegatorExample() throws BazException {
        Class<?>[] proxyInterfaces = new Class[] {Foo.class};
        Object[] delegates = new Object[] {new FooImpl()};
        Foo foo = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(), proxyInterfaces, new Delegator(proxyInterfaces, delegates));
        foo.hashCode();
        foo.equals(foo);
        foo.toString();
        foo.bar(null);
    }

    public static void main(String[] args) throws Exception {
        delegatorExample();
    }
}
