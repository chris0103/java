package org.chris.study.rpc.nio;

/**
 * Hello service implementation.
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
