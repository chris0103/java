package org.chris.study.rpc.simple;

/**
 * Hello service implementation.
 */
public class HelloServiceImpl implements HelloService {

    public String hello(String name) {
        return "Hello, " + name;
    }
}
