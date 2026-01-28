package org.chris.study.rpc.io;

/**
 * Hello service implementation.
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
