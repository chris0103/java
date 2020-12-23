package org.chris.study.dynamicproxy;

public class FooImpl implements Foo {

    @Override
    public Object bar(Object foo) throws BazException {
        String str = "bar";
        System.out.println(str);
        return str;
    }
}
